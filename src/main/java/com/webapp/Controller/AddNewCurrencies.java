package com.webapp.Controller;

import com.webapp.Model.Currencies;
import com.webapp.Service.CurrenciesDAO;
import com.webapp.Service.RecordCurrenciesDAO;
import com.webapp.Util.Common;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(urlPatterns = {"/add"})
public class AddNewCurrencies extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(Common.JSP_ADD_NEW_CATEGORY);
        requestDispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String category = request.getParameter("category");
        String national = request.getParameter("national");
        String strValue = request.getParameter("value");
        String strAmount = request.getParameter("amount");
        if(name.isEmpty() || category.isEmpty() || national.isEmpty()|| strValue.isEmpty() || strAmount.isEmpty()){
            redirectForward(request, response, Common.NOTIFY_ADD_NEW_CURRENCIES_CANNOT_EMPTY_VARIABLE);
            return;
        }

        int value = Integer.parseInt(strValue);
        int amount = Integer.parseInt(strAmount);
        if(amount < 0 || value < 0){
            redirectForward(request, response, Common.NOTIFY_ADD_NEW_CURRENCIES_VALUE_GREATER_ZERO_VARIABLE);
            return;
        }

        Currencies currencies = new Currencies(name, category, national, value, amount);
        CurrenciesDAO currenciesDAO = new CurrenciesDAO();
        System.out.println("ID: " + currencies.getId());
        boolean isInsertSuccess = currenciesDAO.insertCurrencies(currencies);
        if(isInsertSuccess){
            RecordCurrenciesDAO recordCurrenciesDAO = new RecordCurrenciesDAO();
            recordCurrenciesDAO.insertNewRecordHistory(currencies);
            redirectForward(request, response, Common.NOTIFY_ADD_NEW_CURRENCIES_SUCCESS);
        } else{
            redirectForward(request, response, Common.NOTIFY_ADD_NEW_CURRENCIES_FAIL);
        }

    }

    private void redirectForward(HttpServletRequest request, HttpServletResponse response, String status) throws ServletException, IOException{
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(Common.JSP_ADD_NEW_CATEGORY);
        request.setAttribute(Common.STATUS_ADD_NEW_CURRENCIES_VARIABLE, status);
        requestDispatcher.include(request, response);
        request.setAttribute(Common.STATUS_ADD_NEW_CURRENCIES_VARIABLE, null);
    }
}

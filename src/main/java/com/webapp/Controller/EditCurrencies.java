package com.webapp.Controller;

import com.webapp.Model.Currencies;
import com.webapp.Model.RecordCurrencies;
import com.webapp.Service.CurrenciesDAO;
import com.webapp.Service.RecordCurrenciesDAO;
import com.webapp.Util.Common;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.*;
import java.util.Date;
import java.util.List;

@WebServlet(urlPatterns = {"/edit"})
public class EditCurrencies extends HttpServlet {

    private boolean isForwardNewServlet = false;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        proccessRequest(request, response);
        if(isForwardNewServlet){
            return;
        }
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(Common.JSP_EDIT_CURRENCIES_INFO);
        requestDispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        proccessRequest(request, response);
        if(isForwardNewServlet){
            return;
        }
        String strID = request.getParameter("id");
        String name = request.getParameter("pName");
        String category = request.getParameter("pCategory");
        String national = request.getParameter("pNational");
        String strValue = request.getParameter("pValue");
        String strAmount = request.getParameter("pAmount");
        if(name.isEmpty() || category.isEmpty() || national.isEmpty()|| strValue.isEmpty() || strAmount.isEmpty()){
            redirectForward(request, response, Common.NOTIFY_EDIT_CURRENCIES_CANNOT_EMPTY_VARIABLE);
            return;
        }
        int id = Integer.parseInt(strID);
        int value = Integer.parseInt(strValue);
        int amount = Integer.parseInt(strAmount);
        if(amount < 0 || value < 0){
            redirectForward(request, response, Common.NOTIFY_EDIT_CURRENCIES_VALUE_GREATER_ZERO_VARIABLE);
            return;
        }

        Currencies currencies = new Currencies(id, name, category, national, value, amount);
        CurrenciesDAO currenciesDAO = new CurrenciesDAO();
        boolean isEditSuccess = currenciesDAO.updateCurrencies(currencies);
        if(isEditSuccess){
            RecordCurrenciesDAO recordCurrenciesDAO = new RecordCurrenciesDAO();
            recordCurrenciesDAO.insertNewRecordHistory(currencies);
            redirectForward(request, response, Common.NOTIFY_EDIT_CURRENCIES_SUCCESS);
        } else{
            redirectForward(request, response, Common.NOTIFY_EDIT_CURRENCIES_FAIL);
        }
    }

    private void redirectForward(HttpServletRequest request, HttpServletResponse response, String status) throws ServletException, IOException{
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(Common.JSP_EDIT_CURRENCIES_INFO);
        request.setAttribute(Common.STATUS_EDIT_CURRENCIES_VARIABLE, status);
        requestDispatcher.include(request, response);
        request.setAttribute(Common.STATUS_EDIT_CURRENCIES_VARIABLE, null);
    }

    private void proccessRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        isForwardNewServlet = false;
        String strID = request.getParameter("id");
        String contextPath = request.getContextPath();
        String regex = "^[\\d]+$";
        if(!CommonService.isMatchesWithRegex(strID, regex)){
            isForwardNewServlet = true;
            response.sendRedirect(contextPath + Common.URL_HOME_SERVLET);
            return;
        }
        int currenciesID = Integer.parseInt(strID);
        CurrenciesDAO currenciesDAO = new CurrenciesDAO();
        Currencies currencies = currenciesDAO.getCurrenciesWithID(currenciesID);
        if(currencies == null){
            isForwardNewServlet = true;
            response.sendRedirect(contextPath + Common.URL_HOME_SERVLET);
            return;
        }
        request.setAttribute(Common.ATTRIBUTE_CURRENCIES_OBJECT_NAME, currencies);
    }
}

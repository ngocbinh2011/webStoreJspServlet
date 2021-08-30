package com.webapp.Controller;

import com.webapp.Model.Currencies;
import com.webapp.Model.RecordCurrencies;
import com.webapp.Service.CurrenciesDAO;
import com.webapp.Service.RecordCurrenciesDAO;
import com.webapp.Service.UserDAO;
import com.webapp.Util.Common;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.Date;

@WebServlet(urlPatterns = {"/delete"})
public class DeleteCurrencies extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String contextPath = request.getContextPath();
        String strCurrencisID = request.getParameter("id");
        String regex = "^[\\d]+$";
        if(!CommonService.isMatchesWithRegex(strCurrencisID, regex)){
            response.sendRedirect(contextPath + Common.URL_HOME_SERVLET);
            return;
        }

        int currenciesID = Integer.parseInt(strCurrencisID);
        CurrenciesDAO currenciesDAO = new CurrenciesDAO();
        Currencies currencies = currenciesDAO.getCurrenciesWithID(currenciesID);

        if(currencies != null){
            boolean isSuccess = currenciesDAO.deleteCurrencies(currencies);
            if(isSuccess){
                RecordCurrenciesDAO recordCurrenciesDAO = new RecordCurrenciesDAO();
                if(recordCurrenciesDAO.isExitsID(currenciesID)){
                    recordCurrenciesDAO.deleteAllRecordByID(currenciesID);
                }
            }
        }
        response.sendRedirect(contextPath + Common.URL_HOME_SERVLET);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}

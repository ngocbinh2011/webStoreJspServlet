package com.webapp.Controller;

import com.webapp.Model.Account;
import com.webapp.Model.Currencies;
import com.webapp.Service.CurrenciesDAO;
import com.webapp.Service.SessionDAO;
import com.webapp.Util.Common;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = {"/home"})
public class HomePage extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CurrenciesDAO currenciesDAO = new CurrenciesDAO();
        HttpSession session = request.getSession();
        String sessionID = session.getId();
        SessionDAO sessionDAO = new SessionDAO();
        Account account = sessionDAO.getAccountWithSessionID(sessionID);

        List<Currencies> listCurrencies = currenciesDAO.getAllCurrencies();
        request.setAttribute(Common.ATTRIBUTE_LIST_CURRENCIES_NAME, listCurrencies);
        request.setAttribute(Common.ATTRIBUTE_USERNAME_NAME, account.getUsername());
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(Common.JSP_HOME);
        requestDispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}

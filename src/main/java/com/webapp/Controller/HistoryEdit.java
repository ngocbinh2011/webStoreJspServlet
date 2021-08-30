package com.webapp.Controller;

import com.webapp.Model.RecordCurrencies;
import com.webapp.Service.RecordCurrenciesDAO;
import com.webapp.Util.Common;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = {"/history-edit"})
public class HistoryEdit extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String currenciesID = request.getParameter("id");
        String regex = "^[\\d]+$";
        if(!CommonService.isMatchesWithRegex(currenciesID, regex)){
            response.sendRedirect(request.getContextPath() + Common.URL_HOME_SERVLET);
            return;
        }
        int historyID = Integer.parseInt(currenciesID);

        RecordCurrenciesDAO recordCurrenciesDAO = new RecordCurrenciesDAO();
        List<RecordCurrencies> listRecord = recordCurrenciesDAO.getListRecordWithID(historyID);

        request.setAttribute("historyId", currenciesID);
        request.setAttribute("listRecord", listRecord);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(Common.JSP_HISTORY_UPDATE_INFO);
        requestDispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}

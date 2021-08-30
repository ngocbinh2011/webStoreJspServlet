package com.webapp.Controller;


import com.webapp.Model.Account;
import com.webapp.Model.SessionRecord;
import com.webapp.Service.LoginDAO;
import com.webapp.Service.SessionDAO;
import com.webapp.Service.UserDAO;
import com.webapp.Util.Common;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

@WebServlet(urlPatterns = "/login")

public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String contextPath = request.getContextPath();

        // Cookies
        String jSessionID = CommonService.getJSessionID(request);
        if(jSessionID == null || !jSessionID.equals(session.getId())){
            RequestDispatcher requestDispatcherLogin = request.getRequestDispatcher(Common.JSP_LOGIN);
            requestDispatcherLogin.forward(request, response);
            return;
        }

        SessionDAO sessionDAO = new SessionDAO();
        if(sessionDAO.isExistsSessionID(jSessionID)){
            Account account = sessionDAO.getAccountWithSessionID(jSessionID);
            if(account.getUsername() != null){
                CommonService.resetTimeOutSession(response, jSessionID);
                sessionDAO.updateSessionNearLeastDay(jSessionID);
                response.sendRedirect(contextPath + Common.URL_HOME_SERVLET);
            }
            return;
        }
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(Common.JSP_LOGIN);
        requestDispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter printWriter = response.getWriter();
        String contextPath = request.getContextPath();
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(Common.JSP_LOGIN);

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if(username.isEmpty() || password.isEmpty()){
            request.setAttribute(Common.STATUS_LOGIN_VARIABLE, Common.NOTIFY_WRONG_ACCOUNT_LOGIN);
            requestDispatcher.include(request, response);
            request.setAttribute(Common.STATUS_LOGIN_VARIABLE, null);
        } else{
            Account account = new Account(username, password);
            LoginDAO loginDAO = new LoginDAO();
            if(loginDAO.isLoginSuccess(account)){
                HttpSession session = request.getSession();
                String sessionID = session.getId();
                SessionDAO sessionDAO = new SessionDAO();

                if(sessionDAO.isExistsSessionID(sessionID)){
                    sessionID = request.changeSessionId();
                }
                Cookie loginCookie = new Cookie(Common.COOKIE_LOGIN_NAME, sessionID);
                loginCookie.setMaxAge(Common.TIME_OUT_SESSION_AND_COOKIE);
                response.addCookie(loginCookie);

                sessionDAO.insertSessionRecord(new SessionRecord(sessionID, account, new Date()));
                response.sendRedirect(contextPath + Common.URL_HOME_SERVLET);
            } else{
                request.setAttribute(Common.STATUS_LOGIN_VARIABLE, Common.NOTIFY_WRONG_ACCOUNT_LOGIN);
                requestDispatcher.include(request, response);
                request.setAttribute(Common.STATUS_LOGIN_VARIABLE, null);
            }
        }
    }


}

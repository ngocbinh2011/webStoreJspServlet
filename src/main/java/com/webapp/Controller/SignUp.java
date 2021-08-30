package com.webapp.Controller;

import com.webapp.Model.Account;
import com.webapp.Service.UserDAO;
import com.webapp.Util.Common;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = {"/sign-up"})
public class SignUp extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(Common.JSP_SIGN_UP);
        requestDispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String usernameSignUp = request.getParameter("usernameSignUp");
        String passwordSignUp = request.getParameter("passwordSignUp");
        String passwordConfirm = request.getParameter("passwordConfirm");
        if(passwordSignUp == null || !passwordSignUp.equals(passwordConfirm)){
            redirectForward(request, response, Common.NOTIFY_NOT_DUPLICATE_PASSWORD_SIGN_UP);
            return;
        }

        UserDAO userDAO = new UserDAO();
        if(userDAO.isExistsUsername(usernameSignUp)){
            redirectForward(request, response, Common.NOTIFY_USERNAME_IS_DUPLICATE_SIGN_UP);
        } else{
            Account account = new Account(usernameSignUp, passwordSignUp);
            boolean isInsertSucess = userDAO.insertUser(account);
            if(isInsertSucess){
                RequestDispatcher requestDispatcher = request.getRequestDispatcher(Common.URL_LOGIN_SERVLET
                        + "?username=" + usernameSignUp + "&password=" + passwordSignUp);
                requestDispatcher.forward(request, response);
            } else{
                redirectForward(request, response, Common.NOTIFY_UNSUCESS_SIGN_UP);
            }
        }
    }

    private void redirectForward(HttpServletRequest request, HttpServletResponse response, String status) throws ServletException, IOException{
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(Common.JSP_SIGN_UP);
        request.setAttribute(Common.STATUS_SIGN_UP_VARIABLE, status);
        requestDispatcher.include(request, response);
        request.setAttribute(Common.STATUS_SIGN_UP_VARIABLE, null);
    }

}

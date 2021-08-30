package com.webapp.Controller;

import com.webapp.Model.SessionRecord;
import com.webapp.Service.SessionDAO;
import com.webapp.Util.Common;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(urlPatterns = {"/logout"})
public class Logout extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String sessionID = session.getId();

        SessionDAO sessionDAO = new SessionDAO();
        SessionRecord sessionRecord = sessionDAO.getSessionRecord(sessionID);

        // erase SessionRecord in database and cookies
        sessionDAO.deleteSessionRecord(sessionRecord);
        Cookie cookie = new Cookie(Common.COOKIE_LOGIN_NAME, sessionID);
        cookie.setMaxAge(0);
        response.addCookie(cookie);
        response.sendRedirect(request.getContextPath() + Common.URL_LOGIN_SERVLET);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}

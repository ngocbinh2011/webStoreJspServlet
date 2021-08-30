package com.webapp.Controller;

import com.webapp.Model.Account;
import com.webapp.Service.SessionDAO;
import com.webapp.Util.Common;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebFilter(urlPatterns = {"/*"})
public class LoginFilter implements Filter {
    private static Map<String, Boolean> urlExcept = new HashMap<>();
    static {
        urlExcept.put(Common.URL_LOGIN_SERVLET, true);
        urlExcept.put(Common.URL_SIGN_UP_SERVLET, true);
    }
    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest requestLog = (HttpServletRequest) request;
        String contextPath = requestLog.getContextPath();
        HttpServletResponse responseLog = (HttpServletResponse) response;

        String servletPath = requestLog.getServletPath();
        if(servletPath != null && servletPath.equals(Common.URL_LOGIN_SERVLET)){
            chain.doFilter(request, response);
            return;
        }
        if(urlExcept.containsKey(servletPath)){
            chain.doFilter(request, response);
            return;
        }
        HttpSession session = requestLog.getSession();
        String jSessionID = CommonService.getJSessionID(requestLog);
        if(jSessionID == null || !session.getId().equals(jSessionID)){
            responseLog.sendRedirect(contextPath + Common.URL_LOGIN_SERVLET);
            return;
        }

        SessionDAO sessionDAO = new SessionDAO();
        if(sessionDAO.isExistsSessionID(jSessionID)){
            Account account = sessionDAO.getAccountWithSessionID(jSessionID);
            if(account.getUsername() != null){
                CommonService.resetTimeOutSession(responseLog, jSessionID);
                sessionDAO.updateSessionNearLeastDay(jSessionID);
                chain.doFilter(request, response);
            } else{
                responseLog.sendRedirect(contextPath + Common.URL_LOGIN_SERVLET);
            }
        } else {
            responseLog.sendRedirect(contextPath + Common.URL_LOGIN_SERVLET);
        }
    }

}

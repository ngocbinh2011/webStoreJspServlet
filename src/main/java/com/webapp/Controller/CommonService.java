package com.webapp.Controller;

import com.webapp.Util.Common;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommonService {
    protected static String getJSessionID(HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        if(cookies == null){
            return null;
        }
        String jSessionID = null;
        for(Cookie cookie: cookies) {
            if (cookie.getName().equals(Common.COOKIE_LOGIN_NAME)) {
                jSessionID = cookie.getValue();
                break;
            }
        }
        return jSessionID;
    }

    protected static void resetTimeOutSession(HttpServletResponse response, String jSessionID){
        Cookie cookie = new Cookie(Common.COOKIE_LOGIN_NAME, jSessionID);
        cookie.setMaxAge(1 * Common.HOURS);
        response.addCookie(cookie);
    }

    protected static boolean isMatchesWithRegex(String input, String regex){
        if(input == null || regex == null){
            return false;
        }
        Matcher matcher = Pattern.compile(regex).matcher(input);
        return matcher.matches();
    }


}

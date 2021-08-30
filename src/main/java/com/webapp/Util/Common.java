package com.webapp.Util;

public class Common {
    public static final String JSP_HOME = "Home.jsp";
    public static final String JSP_ADD_NEW_CATEGORY = "AddNewCategory.jsp";
    public static final String JSP_HISTORY_UPDATE_INFO = "HistoryUpdateInfo.jsp";
    public static final String JSP_LOGIN = "Login.jsp";
    public static final String JSP_SIGN_UP = "SignUp.jsp";
    public static final String JSP_EDIT_CURRENCIES_INFO = "EditCurrencies.jsp";

    public static final String URL_LOGIN_SERVLET = "/login";
    public static final String URL_HOME_SERVLET = "/home";
    public static final String URL_ADD_NEW_CURRENCIES_SERVLET = "/add";
    public static final String URL_EDIT_CURRENCIES_SERVLET = "/edit";
    public static final String URL_HISTORY_EDIT_INFO_SERVLET = "/history-edit";
    public static final String URL_SIGN_UP_SERVLET = "/sign-up";

    public static final String COOKIE_LOGIN_NAME = "JSESSIONID";

    public static final int DAYS = 24 * 60 * 60; //seconds
    public static final int HOURS = 60 * 60; // seconds
    public static final int MINUTES = 60; // seconds

    public static final String NOTIFY_WRONG_ACCOUNT_LOGIN = "Username or Password is wrong!";
    public static final String NOTIFY_UNSUCESS_SIGN_UP = "Sign up is risk. Please try again!";
    public static final String NOTIFY_USERNAME_IS_DUPLICATE_SIGN_UP = "The username is duplicate!";
    public static final String NOTIFY_NOT_DUPLICATE_PASSWORD_SIGN_UP = "Password must same!";
    public static final String NOTIFY_ADD_NEW_CURRENCIES_SUCCESS = "add sucess!";
    public static final String NOTIFY_ADD_NEW_CURRENCIES_FAIL = "add fail! try again";
    public static final String NOTIFY_ADD_NEW_CURRENCIES_VALUE_GREATER_ZERO_VARIABLE = "value must greater than 0";
    public static final String NOTIFY_ADD_NEW_CURRENCIES_CANNOT_EMPTY_VARIABLE = "can't empty";
    public static final String NOTIFY_EDIT_CURRENCIES_SUCCESS = "edit sucess!";
    public static final String NOTIFY_EDIT_CURRENCIES_FAIL = "edit fail! try again";
    public static final String NOTIFY_EDIT_CURRENCIES_VALUE_GREATER_ZERO_VARIABLE = "value must greater than 0";
    public static final String NOTIFY_EDIT_CURRENCIES_CANNOT_EMPTY_VARIABLE = "can't empty";

    public static final String STATUS_LOGIN_VARIABLE = "statusLogin";
    public static final String STATUS_SIGN_UP_VARIABLE = "statusSignUp";
    public static final String STATUS_ADD_NEW_CURRENCIES_VARIABLE = "statusAdd";
    public static final String STATUS_EDIT_CURRENCIES_VARIABLE = "statusEdit";

    public static final String ATTRIBUTE_LIST_CURRENCIES_NAME = "listCurrencies";
    public static final String ATTRIBUTE_USERNAME_NAME = "username";
    public static final String ATTRIBUTE_CURRENCIES_OBJECT_NAME = "currencies";

    public static int TIME_OUT_SESSION_AND_COOKIE_DAY = 1; // days;
    public static int TIME_OUT_SESSION_AND_COOKIE = 1 * DAYS; // seconds

    public static String TIME_ZONE = "UTC";
}

package com.webapp.Service;

import com.webapp.Model.Account;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDAO {
    public boolean isLoginSuccess(Account account) {
        return (new UserDAO()).checkLogin(account);
    }

}

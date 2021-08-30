package com.webapp.Service;

import com.webapp.Model.Account;
import com.webapp.Model.Currencies;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class UserDAO {
    private static Map<String, Account> listUser = new HashMap<>();

    private static final String USERNAME_COLUMN_TABLE_ACCOUNT = "usernameLogin";
    private static final String PASSWORD_COLUMN_TABLE_ACCOUNT = "passwordLogin";

    private static final int INDEX_USERNAME_COLUMN_TABLE_ACCOUNT = 1;
    private static final int INDEX_PASSWORD_COLUMN_TABLE_ACCOUNT = 2;

    private static String SQL_SELECT_ALL_ACOUNT = "SELECT * FROM accountLogin";
    private static final String SQL_INSERT_NEW_ACCOUNT = "INSERT INTO accountLogin VALUES(?,?)";


    static {
        initGetAllUserAccount();
    }

    public static void initGetAllUserAccount() {
        Connection connection = ConnectionDB.getConnection();
        if (connection == null) {
            return;
        }
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL_SELECT_ALL_ACOUNT);
            while (resultSet.next()) {
                String username = resultSet.getString(USERNAME_COLUMN_TABLE_ACCOUNT);
                String password = resultSet.getString(PASSWORD_COLUMN_TABLE_ACCOUNT);
                Account account = new Account(username, password);
                listUser.put(username, account);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean insertUser(Account account) {
        Connection connection = ConnectionDB.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_INSERT_NEW_ACCOUNT);
            preparedStatement.setString(INDEX_USERNAME_COLUMN_TABLE_ACCOUNT, account.getUsername());
            preparedStatement.setString(INDEX_PASSWORD_COLUMN_TABLE_ACCOUNT, account.getPassword());
            preparedStatement.execute();
            listUser.put(account.getUsername(), account);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean checkLogin(Account account) {
        if(!isExistsUsername(account.getUsername())){
            return false;
        }
        Account user = listUser.get(account.getUsername());
        boolean isSamePassword = user.getPassword().equals(account.getPassword());
        return isSamePassword;
    }

    public boolean isExistsUsername(String username){
        if(username == null){
            return false;
        }
        return listUser.containsKey(username);
    }

}

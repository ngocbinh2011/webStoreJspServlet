package com.webapp.Service;

import com.webapp.Model.Account;
import com.webapp.Model.SessionRecord;
import com.webapp.Util.Common;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;

public class SessionDAO {
    private static Map<String, SessionRecord> listSessionRecord = new HashMap<>();

    private static final String SQL_SELECT_ALL_SESSION_RECORD = "SELECT * FROM sessionInfo";
    private static final String SQL_DELETE_FROM_SESSION_RECORD = "DELETE FROM sessionInfo WHERE sessionID = ?";
    private static final String SQL_INSERT_NEW_SESSION_RECORD = "INSERT INTO sessionInfo VALUES(?,?,?,?)";
    private static final String SQL_DELETE_TIME_OUT_SESSION_RECORD = "DELETE FROM sessionInfo WHERE NEARLEST < ?";
    private static final String SQL_UPDATE_SET_SESSION_NEARLEAST_DATE = "UPDATE sessionInfo SET nearlest = ? WHERE sessionid = ?";

    private static final String SESSIONID_COLUMN_TABLE_SESSION_RECORD = "sessionid";
    private static final String USERNAME_LOGIN_COLUMN_TABLE_SESSION_RECORD = "usernameLogin";
    private static final String PASSWORD_LOGIN_COLUMN_TABLE_SESSION_RECORD = "passwordLogin";
    private static final String NEARLEAST_COLUMN_TABLE_SESSION_RECORD = "nearLest";

    private static final int INDEX_SESSIONID_COLUMN_TABLE_SESSION_RECORD = 1;
    private static final int INDEX_USERNAME_LOGIN_COLUMN_TABLE_SESSION_RECORD = 2;
    private static final int INDEX_PASSWORD_LOGIN_COLUMN_TABLE_SESSION_RECORD = 3;
    private static final int INDEX_NEARLEAST_COLUMN_TABLE_SESSION_RECORD = 4;

    static {
        deleteSessionAfterTimeOut();
        initSessionRecord();
    }

    private static void deleteSessionAfterTimeOut(){
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone(Common.TIME_ZONE));
        calendar.add(Calendar.DAY_OF_MONTH, -(Common.TIME_OUT_SESSION_AND_COOKIE_DAY));
        Date date = calendar.getTime();
        System.out.println(date);
        String strDate = new SimpleDateFormat("yyyy/MM/dd").format(date);

        Connection connection = ConnectionDB.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE_TIME_OUT_SESSION_RECORD);
            preparedStatement.setString(1, strDate);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    private static void initSessionRecord() {
        Connection connection = ConnectionDB.getConnection();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL_SELECT_ALL_SESSION_RECORD);
            while (resultSet.next()) {
                String sessionId = resultSet.getString(SESSIONID_COLUMN_TABLE_SESSION_RECORD);
                String username = resultSet.getString(USERNAME_LOGIN_COLUMN_TABLE_SESSION_RECORD);
                String password = resultSet.getString(PASSWORD_LOGIN_COLUMN_TABLE_SESSION_RECORD);
                Date nearLeast = resultSet.getDate(NEARLEAST_COLUMN_TABLE_SESSION_RECORD);
                SessionRecord sessionRecord = new SessionRecord(sessionId, new Account(username, password), nearLeast);
                listSessionRecord.put(sessionRecord.getSessionID(), sessionRecord);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean deleteSessionRecord(SessionRecord sessionRecord) {
        Connection connection = ConnectionDB.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement(SQL_DELETE_FROM_SESSION_RECORD);
            statement.setString(1, sessionRecord.getSessionID());
            statement.execute();
            listSessionRecord.remove(sessionRecord.getSessionID());
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean insertSessionRecord(SessionRecord sessionRecord) {
        Connection connection = ConnectionDB.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_INSERT_NEW_SESSION_RECORD);
            preparedStatement.setString(INDEX_SESSIONID_COLUMN_TABLE_SESSION_RECORD, sessionRecord.getSessionID());
            preparedStatement.setString(INDEX_USERNAME_LOGIN_COLUMN_TABLE_SESSION_RECORD, sessionRecord.getAccount().getUsername());
            preparedStatement.setString(INDEX_PASSWORD_LOGIN_COLUMN_TABLE_SESSION_RECORD, sessionRecord.getAccount().getPassword());
            Date date = sessionRecord.getNearLest();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
            String strDate = simpleDateFormat.format(date);
            preparedStatement.setString(INDEX_NEARLEAST_COLUMN_TABLE_SESSION_RECORD, strDate);
            preparedStatement.execute();
            listSessionRecord.put(sessionRecord.getSessionID(), sessionRecord);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean updateSessionNearLeastDay(String sessionID){
        if(!isExistsSessionID(sessionID)){
            return false;
        }
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
        String strDate = format.format(date);

        Connection connection = ConnectionDB.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE_SET_SESSION_NEARLEAST_DATE);
            preparedStatement.setString(1, strDate);
            preparedStatement.setString(2, sessionID);
            preparedStatement.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;

    }

    public boolean isExistsSessionID(String sessionID) {
        if(sessionID == null){
            return false;
        }
        return listSessionRecord.containsKey(sessionID);
    }

    public SessionRecord getSessionRecord(String sessionID) {
        if (isExistsSessionID(sessionID)) {
            return listSessionRecord.get(sessionID);
        }
        return null;
    }

    public Account getAccountWithSessionID(String sessionID) {
        if (isExistsSessionID(sessionID)) {
            return listSessionRecord.get(sessionID).getAccount();
        }
        return null;
    }


}

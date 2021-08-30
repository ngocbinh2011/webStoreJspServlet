package com.webapp.Service;

import com.webapp.Model.Currencies;

import javax.swing.plaf.nimbus.State;
import java.sql.*;
import java.util.*;

public class CurrenciesDAO {
    private static Map<Integer, Currencies> listCurrencies = new LinkedHashMap<>();

    private static final String ID_COLUMN_TABLE_CURRENCIES = "id";
    private static final String NAME_COLUMN_TABLE_CURRENCIES = "name";
    private static final String CATEGORY_COLUMN_TABLE_CURRENCIES = "category";
    private static final String NATIONAL_COLUMN_TABLE_CURRENCIES = "national";
    private static final String IMAGE_COLUMN_TABLE_CURRENCIES = "image";
    private static final String VALUE_COLUMN_TABLE_CURRENCIES = "value";
    private static final String AMOUNT_COLUMN_TABLE_CURRENCIES = "amount";

    private static final int INDEX_ID_COLUMN_TABLE_CURRENCIES = 1;
    private static final int INDEX_NAME_COLUMN_TABLE_CURRENCIES = 2;
    private static final int INDEX_CATEGORY_COLUMN_TABLE_CURRENCIES = 3;
    private static final int INDEX_NATIONAL_COLUMN_TABLE_CURRENCIES = 4;
    private static final int INDEX_IMAGE_COLUMN_TABLE_CURRENCIES = 5;
    private static final int INDEX_VALUE_COLUMN_TABLE_CURRENCIES = 6;
    private static final int INDEX_AMOUNT_COLUMN_TABLE_CURRENCIES = 7;

    private static final String SQL_SELECT_ALL_CURRENCIES = "SELECT * FROM currenciesdata";
    private static final String SQL_DELETE_FROM_CURRENCIES = "DELETE FROM currenciesdata WHERE ID = ?";
    private static final String SQL_UPDATE_CURRENCIES =
            "UPDATE currenciesdata SET name = ?, category = ?, national = ?, image = ?, value = ?, amount = ? WHERE ID = ?";
    private static final String SQL_INSERT_NEW_CURRENCIES =
            "INSERT INTO currenciesdata(name,category,national,image,value,amount) VALUES(?,?,?,?,?,?)";

    private static int indexAutoIncrementInDatabase = 0;

    static {
        initGetAllCurrencies();
        Currencies.setIndexAutoIncrement(indexAutoIncrementInDatabase);
    }

    private static void initGetAllCurrencies() {
        Connection connection = ConnectionDB.getConnection();
        if (connection == null) {
            return;
        }
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL_SELECT_ALL_CURRENCIES);
            while (resultSet.next()) {
                int id = resultSet.getInt(ID_COLUMN_TABLE_CURRENCIES);
                indexAutoIncrementInDatabase = Math.max(indexAutoIncrementInDatabase, id);
                String name = resultSet.getString(NAME_COLUMN_TABLE_CURRENCIES);
                String category = resultSet.getString(CATEGORY_COLUMN_TABLE_CURRENCIES);
                String national = resultSet.getString(NATIONAL_COLUMN_TABLE_CURRENCIES);
                String image = resultSet.getString(IMAGE_COLUMN_TABLE_CURRENCIES);
                int value = resultSet.getInt(VALUE_COLUMN_TABLE_CURRENCIES);
                int amount = resultSet.getInt(AMOUNT_COLUMN_TABLE_CURRENCIES);
                Currencies currencies = new Currencies(id, name, category, national, image, value, amount);
                listCurrencies.put(currencies.getId(), currencies);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Currencies> getAllCurrencies() {
        Collection<Currencies> collection = listCurrencies.values();
        return new ArrayList<Currencies>(collection);
    }

    public boolean deleteCurrencies(Currencies currencies) {
        Connection connection = ConnectionDB.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement(SQL_DELETE_FROM_CURRENCIES);
            statement.setInt(1, currencies.getId());
            statement.execute();
            listCurrencies.remove(currencies.getId());
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean updateCurrencies(Currencies currencies) {
        Connection connection = ConnectionDB.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE_CURRENCIES);
            preparedStatement.setString(INDEX_NAME_COLUMN_TABLE_CURRENCIES - 1, currencies.getName());
            preparedStatement.setString(INDEX_CATEGORY_COLUMN_TABLE_CURRENCIES - 1, currencies.getCategoryName());
            preparedStatement.setString(INDEX_NATIONAL_COLUMN_TABLE_CURRENCIES - 1, currencies.getNational());
            preparedStatement.setString(INDEX_IMAGE_COLUMN_TABLE_CURRENCIES - 1, currencies.getImage());
            preparedStatement.setInt(INDEX_VALUE_COLUMN_TABLE_CURRENCIES - 1, currencies.getValueCurrencies());
            preparedStatement.setInt(INDEX_AMOUNT_COLUMN_TABLE_CURRENCIES - 1, currencies.getAmount());
            preparedStatement.setInt(7, currencies.getId());
            preparedStatement.executeUpdate();
            listCurrencies.put(currencies.getId(), currencies);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean insertCurrencies(Currencies currencies) {
        Connection connection = ConnectionDB.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_INSERT_NEW_CURRENCIES);
            preparedStatement.setString(INDEX_NAME_COLUMN_TABLE_CURRENCIES - 1, currencies.getName());
            preparedStatement.setString(INDEX_CATEGORY_COLUMN_TABLE_CURRENCIES - 1, currencies.getCategoryName());
            preparedStatement.setString(INDEX_NATIONAL_COLUMN_TABLE_CURRENCIES - 1, currencies.getNational());
            preparedStatement.setString(INDEX_IMAGE_COLUMN_TABLE_CURRENCIES - 1, currencies.getImage());
            preparedStatement.setInt(INDEX_VALUE_COLUMN_TABLE_CURRENCIES - 1, currencies.getValueCurrencies());
            preparedStatement.setInt(INDEX_AMOUNT_COLUMN_TABLE_CURRENCIES - 1, currencies.getAmount());
            preparedStatement.execute();
            listCurrencies.put(currencies.getId(), currencies);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean isExitsCurrenciesID(int id){
        return listCurrencies.containsKey(id);
    }

    public Currencies getCurrenciesWithID(int id){
        if(!isExitsCurrenciesID(id)){
            return null;
        }
        return listCurrencies.get(id);
    }

}


package com.webapp.Service;

import com.webapp.Model.Currencies;
import com.webapp.Model.RecordCurrencies;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;

public class RecordCurrenciesDAO {
    private static Map<Integer, List<RecordCurrencies>> listRecord = new LinkedHashMap<>();

    private static final String ID_COLUMN_TABLE_RECORD = "id";
    private static final String NAME_COLUMN_TABLE_RECORD = "name";
    private static final String CATEGORY_COLUMN_TABLE_RECORD = "category";
    private static final String NATIONAL_COLUMN_TABLE_RECORD = "national";
    private static final String IMAGE_COLUMN_TABLE_RECORD = "image";
    private static final String VALUE_COLUMN_TABLE_RECORD = "value";
    private static final String AMOUNT_COLUMN_TABLE_RECORD = "amount";
    private static final String DATE_EDIT_COLUMN_TABLE_RECORD = "dateEdit";

    private static final int INDEX_ID_COLUMN_TABLE_RECORD = 1;
    private static final int INDEX_NAME_COLUMN_TABLE_RECORD = 2;
    private static final int INDEX_CATEGORY_COLUMN_TABLE_RECORD = 3;
    private static final int INDEX_NATIONAL_COLUMN_TABLE_RECORD = 4;
    private static final int INDEX_IMAGE_COLUMN_TABLE_RECORD = 5;
    private static final int INDEX_VALUE_COLUMN_TABLE_RECORD = 6;
    private static final int INDEX_AMOUNT_COLUMN_TABLE_RECORD = 7;
    private static final int INDEX_DATE_EDIT_COLUMN_TABLE_RECORD = 8;

    private static final String SQL_SELECT_ALL_RECORD = "SELECT * FROM record";
    private static final String SQL_DELETE_FROM_RECORD = "DELETE FROM record WHERE ID = ?";
    private static final String SQL_INSERT_NEW_RECORD =
            "INSERT INTO record(id,name,category,national,image,value,amount, dateEdit) VALUES(?,?,?,?,?,?,?,?)";

    static {
        initGetAllRecord();
    }

    private static void initGetAllRecord() {
        Connection connection = ConnectionDB.getConnection();
        if (connection == null) {
            return;
        }
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL_SELECT_ALL_RECORD);
            while (resultSet.next()) {
                int id = resultSet.getInt(ID_COLUMN_TABLE_RECORD);
                String name = resultSet.getString(NAME_COLUMN_TABLE_RECORD);
                String category = resultSet.getString(CATEGORY_COLUMN_TABLE_RECORD);
                String national = resultSet.getString(NATIONAL_COLUMN_TABLE_RECORD);
                String image = resultSet.getString(IMAGE_COLUMN_TABLE_RECORD);
                int value = resultSet.getInt(VALUE_COLUMN_TABLE_RECORD);
                int amount = resultSet.getInt(AMOUNT_COLUMN_TABLE_RECORD);
                Date date = resultSet.getDate(DATE_EDIT_COLUMN_TABLE_RECORD);
                RecordCurrencies recordCurrencies =
                        new RecordCurrencies(id, name, category, national, image, value,amount, date);
                List<RecordCurrencies> list = null;
                if(listRecord.containsKey(id)){
                    list = listRecord.get(id);
                } else{
                    list = new ArrayList<>();
                }
                list.add(recordCurrencies);
                listRecord.put(id, list);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

//    public List<Currencies> getAllCurrencies() {
//        Collection<Currencies> collection = listCurrencies.values();
//        return new ArrayList<Currencies>(collection);
//    }
//
    public boolean deleteAllRecordByID(int id) {
        Connection connection = ConnectionDB.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement(SQL_DELETE_FROM_RECORD);
            statement.setInt(1, id);
            statement.execute();
            listRecord.remove(id);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;

    }

    public boolean insertCurrencies(RecordCurrencies currencies) {
        Connection connection = ConnectionDB.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_INSERT_NEW_RECORD);
            preparedStatement.setInt(INDEX_ID_COLUMN_TABLE_RECORD, currencies.getId());
            preparedStatement.setString(INDEX_NAME_COLUMN_TABLE_RECORD, currencies.getName());
            preparedStatement.setString(INDEX_CATEGORY_COLUMN_TABLE_RECORD, currencies.getCategoryName());
            preparedStatement.setString(INDEX_NATIONAL_COLUMN_TABLE_RECORD, currencies.getNational());
            preparedStatement.setString(INDEX_IMAGE_COLUMN_TABLE_RECORD, currencies.getImage());
            preparedStatement.setInt(INDEX_VALUE_COLUMN_TABLE_RECORD, currencies.getValueCurrencies());
            preparedStatement.setInt(INDEX_AMOUNT_COLUMN_TABLE_RECORD, currencies.getAmount());
            preparedStatement.setString(INDEX_DATE_EDIT_COLUMN_TABLE_RECORD,
                    new SimpleDateFormat("yyyy/MM/dd").format(currencies.getDateEdit()));
            preparedStatement.execute();
            List<RecordCurrencies> list = null;
            if(listRecord.containsKey(currencies.getId())){
                list = listRecord.get(currencies.getId());
            } else{
                list = new ArrayList<>();
            }
            list.add(currencies);
            listRecord.put(currencies.getId(), list);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean isExitsID(int id){
        return listRecord.containsKey(id);
    }

    public List<RecordCurrencies> getListRecordWithID(int id){
        if(!isExitsID(id)){
            return new ArrayList<>();
        } else{
            return listRecord.get(id);
        }
    }

    public void insertNewRecordHistory(Currencies currencies){
        RecordCurrencies recordCurrencies = new RecordCurrencies(currencies, new Date());
        insertCurrencies(recordCurrencies);
    }

}


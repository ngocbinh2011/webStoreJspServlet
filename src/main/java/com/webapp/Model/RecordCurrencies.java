package com.webapp.Model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class RecordCurrencies extends Currencies {
    private Date dateEdit;

    public RecordCurrencies() {

    }

    public RecordCurrencies(Currencies currencies, Date date){
        this(currencies.getId(), currencies.getName(), currencies.getCategoryName(), currencies.getNational(),
                currencies.getValueCurrencies(), currencies.getAmount(), date);
    }

    public RecordCurrencies(int id, String name, String categoryName,
                            String national, int valueCurrencies, int amount, Date dateEdit) {
        super(id, name, categoryName, national, valueCurrencies, amount);
        this.dateEdit = processDate(dateEdit);
    }

    public RecordCurrencies(int id, String name, String categoryName, String national, String image, int valueCurrencies, int amount, Date dateEdit) {
        super(id, name, categoryName, national, image, valueCurrencies, amount);
        this.dateEdit = processDate(dateEdit);
    }

    private Date processDate(Date date){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
        String strDate = simpleDateFormat.format(date);
        try {
            Date newDate = simpleDateFormat.parse(strDate);
            return newDate;
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return null;
    }

    public String getDateFormat(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String dateFormat = simpleDateFormat.format(this.dateEdit);
        return dateFormat;
    }

    public Date getDateEdit() {
        return dateEdit;
    }

    public void setDateEdit(Date dateEdit) {
        this.dateEdit = dateEdit;
    }
}

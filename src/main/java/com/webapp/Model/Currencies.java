package com.webapp.Model;

import com.webapp.Service.CurrenciesDAO;

public class Currencies {
    private int id;
    private String name;
    private String categoryName;
    private String national;
    private String image;
    private int valueCurrencies;
    private int amount;

    private static int indexAutoIncrement = 0;

    public Currencies() {

    }

    public Currencies(String name, String categoryName, String national, String image, int valueCurrencies, int amount) {
        this(name, categoryName, national, valueCurrencies, amount);
        this.image = image;
    }

    public Currencies(String name, String categoryName, String national, int valueCurrencies, int amount) {
        this.id = ++indexAutoIncrement;
        this.name = name;
        this.categoryName = categoryName;
        this.national = national;
        this.valueCurrencies = valueCurrencies;
        this.amount = amount;
    }

    public Currencies(int id, String name, String categoryName, String national, int valueCurrencies, int amount) {
        this.id = id;
        this.name = name;
        this.categoryName = categoryName;
        this.national = national;
        this.valueCurrencies = valueCurrencies;
        this.amount = amount;
    }

    public Currencies(int id, String name, String categoryName, String national, String image, int valueCurrencies, int amount) {
        this(id, name, categoryName, national, valueCurrencies, amount);
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getNational() {
        return national;
    }

    public void setNational(String national) {
        this.national = national;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getValueCurrencies() {
        return valueCurrencies;
    }

    public void setValueCurrencies(int valueCurrencies) {
        this.valueCurrencies = valueCurrencies;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public static int getIndexAutoIncrement() {
        return indexAutoIncrement;
    }

    public static void setIndexAutoIncrement(int indexAutoIncrement) {
        Currencies.indexAutoIncrement = indexAutoIncrement;
    }
}

package com.webapp.Service;

import com.webapp.Model.Currencies;

public interface IUserService {
    void addNewCurrencies(Currencies currencies);

    void UpdateCurrencies(Currencies currencies);

    void deleteCurrencies(Currencies currencies);

}

package com.cengizhanozeyranoglu.services;

import com.cengizhanozeyranoglu.dto.CurrencyRatesResponse;

public interface ICurrencyRatesService {

    public CurrencyRatesResponse getCurrencyRates(String startDate , String endDate);
}

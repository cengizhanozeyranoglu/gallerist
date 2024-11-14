package com.cengizhanozeyranoglu.controller;

import com.cengizhanozeyranoglu.dto.CurrencyRatesResponse;

public interface IRestCurrencyRateController {

    public RootEntity<CurrencyRatesResponse> getCurrencyRates(String startDate, String endDate);
}

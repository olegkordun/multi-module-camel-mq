package me.kordun.enums;

import org.joda.money.CurrencyUnit;

public enum Currency {
    USD(CurrencyUnit.USD),EUR(CurrencyUnit.EUR);

    private CurrencyUnit currencyUnit;
    Currency(CurrencyUnit currency){
        currencyUnit = currency;
    }

    public CurrencyUnit getCurrencyUnit(){
        return currencyUnit;
    }
}

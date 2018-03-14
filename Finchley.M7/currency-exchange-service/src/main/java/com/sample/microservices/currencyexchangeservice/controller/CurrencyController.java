package com.sample.microservices.currencyexchangeservice.controller;

import com.sample.microservices.currencyexchangeservice.Model.CurrencyConversionBean;
import com.sample.microservices.currencyexchangeservice.Model.ExchangeValue;
import com.sample.microservices.currencyexchangeservice.service.DBServiceProxy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
public class CurrencyController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private Environment environment;

    @Autowired
    DBServiceProxy proxy;

    @GetMapping("currency-exchange/from/{from}/to/{to}/quantity/{quantity}")
    public CurrencyConversionBean retreiveExchangeValue(@PathVariable String from,
                                                       @PathVariable String to,
                                                       @PathVariable BigDecimal quantity){

        logger.info("calling currency conversion proxy");
        ExchangeValue exchangeValue=  proxy.retreiveExchangeValue(from, to);
        return new CurrencyConversionBean(exchangeValue.getId(),from,to,exchangeValue.getConversionMultiple(),
                quantity,exchangeValue.getConversionMultiple(),
                Integer.parseInt(environment.getProperty("local.server.port")));
    }
}

package com.sample.microservices.dbservice.controller;

import com.sample.microservices.dbservice.Model.ExchangeValue;
import com.sample.microservices.dbservice.dao.DBServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DBServiceController {
    @Autowired
    private Environment environment;

    @Autowired
    private DBServiceRepository repository;

    @GetMapping("currency-exchange-data/from/{from}/to/{to}")
    public ExchangeValue retreiveExchangeValue(@PathVariable String from, @PathVariable String to){
        ExchangeValue exchangeValue =  repository.findByFromAndTo(from,to);//new ExchangeValue(100L,from,to, BigDecimal.valueOf(65));
        exchangeValue.setPort(Integer.parseInt(environment.getProperty("local.server.port")));
        return  exchangeValue;
    }
}

package com.sample.microservices.currencyexchangeservice.service;

import com.sample.microservices.currencyexchangeservice.Model.ExchangeValue;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("db-service")
public interface DBServiceProxy {
    @GetMapping("currency-exchange-data/from/{from}/to/{to}")
    public ExchangeValue retreiveExchangeValue(@PathVariable("from") String from, @PathVariable("to") String to);
}

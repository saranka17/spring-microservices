package com.sample.microservices.currencyconversionservice.service;

import com.sample.microservices.currencyconversionservice.model.CurrencyConversionBean;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.math.BigDecimal;

//@FeignClient(name = "currency-exchange-service",url = "localhost:8100")
//@FeignClient(name = "currency-exchange-service")
@FeignClient(name = "api-gateway-server")
@RibbonClient(name = "currency-exchange-service")
public interface CurrencyExchangeServiceProxy {

    @GetMapping("currency-exchange-service/currency-exchange/from/{from}/to/{to}/quantity/{quantity}")
    public CurrencyConversionBean retreiveExchangeValue(@PathVariable("from") String from, @PathVariable("to") String to, @PathVariable("quantity") BigDecimal quantity);
}

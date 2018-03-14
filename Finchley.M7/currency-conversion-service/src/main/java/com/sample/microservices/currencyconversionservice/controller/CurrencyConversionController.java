package com.sample.microservices.currencyconversionservice.controller;

import com.sample.microservices.currencyconversionservice.model.CurrencyConversionBean;
import com.sample.microservices.currencyconversionservice.service.CurrencyExchangeServiceProxy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.HashMap;

@RestController
public class CurrencyConversionController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private CurrencyExchangeServiceProxy proxy;


    @GetMapping("currency-exchange/from/{from}/to/{to}/quantity/{quantity}")
    public CurrencyConversionBean convertCurrency(@PathVariable String from,
                                                  @PathVariable String to,
                                                  @PathVariable BigDecimal quantity){

        HashMap<String,String> uriVariables=new HashMap<String,String>();
        uriVariables.put("from",from);
        uriVariables.put("to",to);
        ResponseEntity<CurrencyConversionBean> responseEntity = new RestTemplate().getForEntity("http://localhost:8100/currency-exchange/from/{from}/to/{to}",
                    CurrencyConversionBean.class,uriVariables);
        CurrencyConversionBean currencyConversionBean=  responseEntity.getBody();
        return new CurrencyConversionBean(currencyConversionBean.getId(),from,to,
                currencyConversionBean.getConversionMultiple(),
                quantity,quantity.multiply(currencyConversionBean.getConversionMultiple()),
                currencyConversionBean.getPort());
    }

    @GetMapping("currency-exchange-feign/from/{from}/to/{to}/quantity/{quantity}")
    public CurrencyConversionBean convertCurrencyFeign(@PathVariable String from,
                                                  @PathVariable String to,
                                                  @PathVariable BigDecimal quantity){

        System.out.println("calling currency conversion proxy");
        CurrencyConversionBean currencyConversionBean=  proxy.retreiveExchangeValue(from, to,quantity);
        logger.info("{}",currencyConversionBean);
        return new CurrencyConversionBean(currencyConversionBean.getId(),from,to,
                currencyConversionBean.getConversionMultiple(),
                quantity,quantity.multiply(currencyConversionBean.getConversionMultiple()),
                currencyConversionBean.getPort());
    }
}

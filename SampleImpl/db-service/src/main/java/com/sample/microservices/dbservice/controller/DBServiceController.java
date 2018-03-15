package com.sample.microservices.dbservice.controller;

import com.sample.microservices.dbservice.Model.RiskView;
import com.sample.microservices.dbservice.dao.DBServiceRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DBServiceController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private Environment environment;

    @Autowired
    private DBServiceRepository repository;

    @GetMapping("clientsummary/from/{id}")
    public RiskView retreiveExchangeValue(@PathVariable String id){
        logger.info("fetching data");
        RiskView riskView =  repository.findById(id);
        riskView.setPort(environment.getProperty("local.server.port"));
        logger.info("got the result");
        return riskView;
    }
}

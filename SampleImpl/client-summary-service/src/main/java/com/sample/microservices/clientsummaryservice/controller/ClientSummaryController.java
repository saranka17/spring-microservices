package com.sample.microservices.clientsummaryservice.controller;

import com.sample.microservices.clientsummaryservice.model.RiskViewModel;
import com.sample.microservices.clientsummaryservice.service.DBServiceProxy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ClientSummaryController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private DBServiceProxy dbServiceProxy;

    @Autowired
    private Environment environment;

    @GetMapping("/clientsummary/id/{id}")
    public RiskViewModel getClientSummary(@PathVariable String id){
        logger.info("calling getLimitsFromConfig");
        RiskViewModel result= dbServiceProxy.getClientSummary(id);

        result.setPort(environment.getProperty("local.server.port"));
        return result;
    }

}

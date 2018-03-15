package com.sample.microservices.riskviewservice.controller;

import com.sample.microservices.riskviewservice.model.RiskView;
import com.sample.microservices.riskviewservice.service.ClientServiceProxy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RiskViewController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private RiskView riskView;

    @Autowired
    private ClientServiceProxy clientServiceProxy;

    @GetMapping("/riskview/id/{id}")
    public RiskView getLimitsFromConfig(@PathVariable String id){
        logger.info("calling getLimitsFromConfig");
        return clientServiceProxy.getClientSummary(id);

    }
}

package com.sample.microservices.limitsservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LimitsConfigController {

    @Autowired
    private Configuration configuration;

    @GetMapping("/limits")
    public LimitConfig getLimitsFromConfig(){

        return new LimitConfig(configuration.getMinimum(),configuration.getMaximum());

    }
}

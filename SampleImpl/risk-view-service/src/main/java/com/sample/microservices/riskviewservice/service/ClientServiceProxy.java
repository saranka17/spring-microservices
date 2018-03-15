package com.sample.microservices.riskviewservice.service;

import com.sample.microservices.riskviewservice.model.RiskView;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;



@FeignClient(name = "client-summary-service")
@RibbonClient(name = "client-summary-service")
public interface ClientServiceProxy {

    @GetMapping("clientsummary/id/{id}/")
    public RiskView getClientSummary(@PathVariable("id") String id);
}

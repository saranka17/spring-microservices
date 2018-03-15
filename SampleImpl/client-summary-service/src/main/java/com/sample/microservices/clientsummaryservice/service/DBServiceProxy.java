package com.sample.microservices.clientsummaryservice.service;

import com.sample.microservices.clientsummaryservice.model.RiskViewModel;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "db-service")

public interface DBServiceProxy {

    @GetMapping("clientsummary/from/{id}/")
    public RiskViewModel getClientSummary(@PathVariable("id") String id);
}

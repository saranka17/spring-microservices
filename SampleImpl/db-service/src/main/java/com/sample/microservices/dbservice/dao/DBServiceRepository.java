package com.sample.microservices.dbservice.dao;

import com.sample.microservices.dbservice.Model.RiskView;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DBServiceRepository extends JpaRepository<RiskView,Long> {
    RiskView findById(String id);
}

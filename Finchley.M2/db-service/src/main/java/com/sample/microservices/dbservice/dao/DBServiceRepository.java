package com.sample.microservices.dbservice.dao;

import com.sample.microservices.dbservice.Model.ExchangeValue;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DBServiceRepository extends JpaRepository<ExchangeValue,Long> {
    ExchangeValue findByFromAndTo(String from, String to);
}

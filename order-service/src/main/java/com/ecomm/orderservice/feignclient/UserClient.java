package com.ecomm.orderservice.feignclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.ecomm.orderservice.domain.User;

@FeignClient(name = "user-service", url = "http://localhost:8811/")
public interface UserClient {

    @GetMapping(value = "/user-service/users/{id}")
    public User getUserById(@PathVariable("id") Long id);
}

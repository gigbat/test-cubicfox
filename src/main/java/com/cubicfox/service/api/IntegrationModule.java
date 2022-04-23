package com.cubicfox.service.api;

import org.springframework.http.ResponseEntity;

public interface IntegrationModule {
    ResponseEntity<?> saveIntegrationUsers();
}

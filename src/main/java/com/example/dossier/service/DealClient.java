package com.example.dossier.service;

import com.example.dossier.dto.ApplicationDTO;
import com.example.dossier.dto.SesCodeDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

@FeignClient(name = "deal-client", url = "${feign-client.deal-client.base-url}")
public interface DealClient {
    @GetMapping(
            value = "${feign-client.deal-client.get-application-path}",
            consumes = "application/json"
    )
    ApplicationDTO getApplication(@PathVariable(value = "applicationId") Long applicationId);

    @PutMapping(
            value = "${feign-client.deal-client.update-application-status-path}",
            consumes = "application/json"
    )
    Void updateApplicationStatus(@PathVariable(value = "applicationId") Long applicationId);

    @GetMapping(
            value = "${feign-client.deal-client.get-ses-code-path}",
            consumes = "application/json"
    )
    SesCodeDTO getSesCode(@PathVariable(value = "applicationId") Long applicationId);
}

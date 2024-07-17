package com.example.dossier.services.client;

import com.example.dossier.dtos.ApplicationDTO;
import com.example.dossier.dtos.SesCodeDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "deal-client", url = "${feign-client.deal-client.base-url}")
public interface DealClient {
    @RequestMapping(method = RequestMethod.GET,
            value = "${feign-client.deal-client.get-application-path}",
            consumes = "application/json"
    )
    ApplicationDTO getApplication(@PathVariable(value = "applicationId") Long applicationId);

    @RequestMapping(method = RequestMethod.PUT,
            value = "${feign-client.deal-client.update-application-status-path}",
            consumes = "application/json"
    )
    Void updateApplicationStatus(@PathVariable(value = "applicationId") Long applicationId);

    @RequestMapping(method = RequestMethod.GET,
            value = "${feign-client.deal-client.get-ses-code-path}",
            consumes = "application/json"
    )
    SesCodeDTO getSesCode(@PathVariable(value = "applicationId") Long applicationId);
}

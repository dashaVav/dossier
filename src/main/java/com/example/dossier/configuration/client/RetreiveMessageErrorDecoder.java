package com.example.dossier.configuration.client;

import com.example.dossier.exception.DealException;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Response;
import feign.codec.ErrorDecoder;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;

@Component
public class RetreiveMessageErrorDecoder implements ErrorDecoder {
    private final ErrorDecoder errorDecoder = new Default();

    @Override
    public Exception decode(String methodKey, Response response) {
        try (InputStream bodyIs = response.body().asInputStream()) {
            ObjectMapper mapper = new ObjectMapper();
            DealExceptionDTO e = mapper.readValue(bodyIs, DealExceptionDTO.class);
            throw new DealException(e.getError(), e.getStatus());
        } catch (IOException e) {
            return errorDecoder.decode(methodKey, response);
        }
    }
}

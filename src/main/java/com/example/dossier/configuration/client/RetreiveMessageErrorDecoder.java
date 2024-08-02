package com.example.dossier.configuration.client;

import com.example.dossier.exception.DealException;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Response;
import feign.codec.ErrorDecoder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;

@Slf4j
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
            Exception exception = errorDecoder.decode(methodKey, response);
            log.error("Deal service return exception: {}", exception.getMessage());
            return exception;
        }
    }
}

package com.example.dossier.service;

import com.example.dossier.dto.EmailMessage;

public interface DlqProducer {
    void send(EmailMessage errorMessage);
}

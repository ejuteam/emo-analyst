package com.emoOpner.service;

import com.emoOpner.response.AResponse;
import org.springframework.stereotype.Service;

@Service
public interface WordAnalystService {
    public AResponse doTokenization();

}

package com.example.piidetector.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.piidetector.vo.AnalyzeRequest;
import com.example.piidetector.vo.AnalyzeResult;

@Service
public class NlpClient {

    private final RestTemplate restTemplate;

    public NlpClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public AnalyzeResult analyzeText(String model, String text) {
        return restTemplate.postForObject(
            "http://localhost:8000/analyze",
            new AnalyzeRequest(model, text),
            AnalyzeResult.class // 修正: AnalyzeResultに変更
        );
    }
}

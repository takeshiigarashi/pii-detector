package com.example.piidetector.service;

import org.springframework.stereotype.Service;

import com.example.piidetector.vo.AnalyzeResult;

@Service
public class NlpClientService {
  private final NlpClient nlpClient;

  public NlpClientService(NlpClient nlpClient) {
    this.nlpClient = nlpClient;
  }

  public AnalyzeResult analyzeText(String model, String text) {
    return nlpClient.analyzeText(model, text);
  }

}

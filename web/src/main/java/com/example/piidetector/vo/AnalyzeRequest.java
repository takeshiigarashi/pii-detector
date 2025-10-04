package com.example.piidetector.vo;

public class AnalyzeRequest {
  private final String _model;
  private final String _text;

  public AnalyzeRequest(String model, String text) {
    _model = model;
    _text = text;
  }

  public String getModel() {
    return _model;
  }

  public String getText() {
    return _text;
  }
}

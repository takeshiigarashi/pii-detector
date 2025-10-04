package com.example.piidetector.vo;

import java.util.List;

public class AnalyzeResult {
  private List<AnalyzeResultToken> tokens;

  public List<AnalyzeResultToken> getTokens() {
    return tokens;
  }

  public void setTokens(List<AnalyzeResultToken> tokens) {
    this.tokens = tokens;
  }
}

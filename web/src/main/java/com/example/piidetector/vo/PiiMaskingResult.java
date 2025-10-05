package com.example.piidetector.vo;

import java.util.List;

public class PiiMaskingResult {
  private String _text;
  private String _maskedText;
  private List<AnalyzeResultToken> _tokens;
  private List<PiiEntity> _entities;

  public String getText() {
    return _text;
  }
  public void setText(String text) {
    this._text = text;
  }
  public String getMaskedText() {
    return _maskedText;
  }
  public void setMaskedText(String maskedText) {
    this._maskedText = maskedText;
  }
  public List<AnalyzeResultToken> getTokens() {
    return _tokens;
  }
  public void setTokens(List<AnalyzeResultToken> tokens) {
    this._tokens = tokens;
  }
  public List<PiiEntity> getEntities() {
    return _entities;
  }
  public void setEntities(List<PiiEntity> entities) {
    this._entities = entities;
  }
}

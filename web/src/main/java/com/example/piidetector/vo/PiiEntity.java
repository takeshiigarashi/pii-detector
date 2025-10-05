package com.example.piidetector.vo;

public class PiiEntity {
  private String _type;
  private Integer _idx;
  private String _text;
  private String _maskedText;

  public String getType() {
    return _type;
  }
  public void setType(String type) {
    this._type = type;
  }
  public Integer getIdx() {
    return _idx;
  }
  public void setIdx(Integer idx) {
    this._idx = idx;
  }
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
}

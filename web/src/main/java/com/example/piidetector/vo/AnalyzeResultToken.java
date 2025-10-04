package com.example.piidetector.vo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AnalyzeResultToken {

  private String surface;
  private String lemma;
  private Integer idx;
  private String pos;
  private String tag;
  @JsonProperty("ent_type") 
  private String entType;
  @JsonProperty("is_alpha")
  private Boolean alpha;
  @JsonProperty("is_digit")
  private Boolean digit;
  @JsonProperty("is_punct")
  private Boolean punct;
  @JsonProperty("is_stop") 
  private Boolean stop;
  @JsonProperty("is_credit_card")
  private Boolean creditCard;
  @JsonProperty("is_email")
  private Boolean email;
  @JsonProperty("is_phone_number")
  private Boolean phoneNumber;
  private String dpendency;
  private String head;

  public String getSurface() {
    return surface;
  }
  public void setSurface(String surface) {
    this.surface = surface;
  }

  public String getLemma() {
    return lemma;
  }
  public void setLemma(String lemma) {
    this.lemma = lemma;
  }
  public Integer getIdx() {
    return idx;
  }
  public void setIdx(Integer idx) {
    this.idx = idx;
  }
  public String getPos() {
    return pos;
  }
  public void setPos(String pos) {
    this.pos = pos;
  }
  public String getTag() {
    return tag;
  }
  public void setTag(String tag) {
    this.tag = tag;
  }
  public String getEntType() {
    return entType;
  }
  
  public void setEntType(String entType) {
    this.entType = entType;
  }
  public Boolean getAlpha() {
    return alpha;
  }
  public void setAlpha(Boolean alpha) {
    this.alpha = alpha;
  }
  public Boolean getDigit() {
    return digit;
  }
  public void setDigit(Boolean digit) {
    this.digit = digit;
  }
  public Boolean getPunct() {
    return punct;
  }
  public void setPunct(Boolean punct) {
    this.punct = punct;
  }
  public Boolean getStop() {
    return stop;
  }
  public void setStop(Boolean stop) {
    this.stop = stop;
  }

  public Boolean getCreditCard() {
    return creditCard;
  }

  public void setCreditCard(Boolean creditCard) {
    this.creditCard = creditCard;
  }
  public Boolean getEmail() {
    return email;
  }
  public void setEmail(Boolean email) {
    this.email = email;
  }
  public Boolean getPhoneNumber() {
    return phoneNumber;
  }
  public void setPhoneNumber(Boolean phoneNumber) {
    this.phoneNumber = phoneNumber;
  }
  
  public String getDpendency() {
    return dpendency;
  } 
  public void setDpendency(String dpendency) {
    this.dpendency = dpendency;
  }
  public String getHead() {
    return head;
  }
  public void setHead(String head) {
    this.head = head;
  }
}



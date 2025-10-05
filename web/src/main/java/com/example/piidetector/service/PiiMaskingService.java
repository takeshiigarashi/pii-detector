package com.example.piidetector.service;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.piidetector.vo.AnalyzeResultToken;
import com.example.piidetector.vo.PiiEntity;
import com.example.piidetector.vo.PiiMaskingResult;

@Service
public class PiiMaskingService {
  private final NlpClientService nlpClientService;

  public PiiMaskingService(NlpClientService nlpClientService) {
    this.nlpClientService = nlpClientService;
  }

  public PiiMaskingResult maskPii(String model, String text) {
    var analyzeResult = nlpClientService.analyzeText(model, text);
    var tokens = analyzeResult.getTokens();

    StringBuilder maskedText = new StringBuilder();
    var piiEntities = new ArrayList<PiiEntity>();
    String lastPiiType = null;
    for (var token : tokens) {
      Optional<String> piiType = detectPii(token);
      if (piiType.isEmpty()) {
        lastPiiType = null;
        maskedText.append(token.getSurface());
      } else {
        var maskedSurface = "*".repeat(token.getSurface().length());
        maskedText.append(maskedSurface);

        // piiTypeが変わった場合、新規のPiiEntityを追加する
        if (lastPiiType == null || !lastPiiType.equals(piiType.get())) {
          lastPiiType = piiType.get();
          var entity = new PiiEntity();
          entity.setType(piiType.get());
          entity.setIdx(token.getIdx());
          entity.setText(token.getSurface());
          entity.setMaskedText(maskedSurface);
          piiEntities.add(entity);
        } else {
          // 最後のPiiEntityにテキストを追加する
          var entity =  piiEntities.get(piiEntities.size() - 1);
          entity.setText(entity.getText() + token.getSurface());
          entity.setMaskedText(entity.getMaskedText() + maskedSurface);
        }
      }
    }

    var result = new PiiMaskingResult();
    result.setText(text);
    result.setMaskedText(maskedText.toString());
    result.setTokens(tokens);
    result.setEntities(piiEntities);
    return result;
  }

  protected Optional<String> detectPii(AnalyzeResultToken token) {
    if (Boolean.TRUE.equals(token.getCreditCard())) {
      return Optional.of("CREDIT_CARD");
    }
    if (Boolean.TRUE.equals(token.getEmail())) {
      return Optional.of("EMAIL");
    }
    if (Boolean.TRUE.equals(token.getPhoneNumber())) {
      return Optional.of("PHONE_NUMBER");
    }
    if ("Postal_Address".equals(token.getEntType())) {
      return Optional.of("POSTAL_ADDRESS");
    }
    if ("Person".equals(token.getEntType())) {
      return Optional.of("PERSON");
    }

    return Optional.empty();
  }
}

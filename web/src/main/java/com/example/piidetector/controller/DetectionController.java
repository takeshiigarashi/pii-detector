package com.example.piidetector.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.piidetector.service.NlpClientConst;
import com.example.piidetector.service.NlpClientService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class DetectionController {
    private final static Logger logger = LoggerFactory.getLogger(DetectionController.class);
    private final NlpClientService nlpClientService;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public DetectionController(NlpClientService nlpClientService) {
        this.nlpClientService = nlpClientService;
    }
    
    @GetMapping("/detect")
    public String index(Model model) {
        model.addAttribute("modelName", NlpClientConst.MODEL_JA_GINZA_ELECTRA);
        return "detect";
    }

    @PostMapping("/detect")
    public String detect(@RequestParam("modelName") String modelName, @RequestParam("inputText") String inputText, Model model) {

        // NLPサービスを呼び出す
        var result = nlpClientService.analyzeText(modelName, inputText);

        try {
            logger.debug("NLP Result: {}", objectMapper.writeValueAsString(result));
        } catch (Exception e) {
            logger.warn("Error serializing NLP result", e);
        }

        model.addAttribute("modelName", modelName);
        model.addAttribute("inputText", inputText);
        model.addAttribute("result", result);
        return "detect";
    }
}

package com.example.piidetector.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class DetectionController {

    @PostMapping("/detect")
    public String detect(@RequestBody String input, Model model) {
        // PII検出ロジックをここに実装
        model.addAttribute("result", "result");
        return "index";
    }
}

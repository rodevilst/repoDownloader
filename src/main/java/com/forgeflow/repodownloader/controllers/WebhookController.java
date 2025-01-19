package com.forgeflow.repodownloader.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/v1/hook")
public class WebhookController {
    private static final Logger logger = LoggerFactory.getLogger(WebhookController.class);
    @PostMapping
    public ResponseEntity<String> handleWebhook(@RequestBody Map<String, Object> payload,
                                                @RequestHeader("X-GitHub-Event") String eventType) {
        logger.info("📩 Получен вебхук от GitHub!");
        logger.info("🔹 Тип события: {}", eventType);
        logger.info("📜 Данные: {}", payload);

        return ResponseEntity.status(HttpStatus.OK).body("Webhook received");
    }
}

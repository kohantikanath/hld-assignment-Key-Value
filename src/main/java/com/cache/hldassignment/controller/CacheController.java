package com.cache.hldassignment.controller;

import com.cache.hldassignment.service.CacheService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/")
public class CacheController {

    private final CacheService cacheService;

    public CacheController(CacheService cacheService) {
        this.cacheService = cacheService;
    }

    @PostMapping("/put")
    public ResponseEntity<Map<String, String>> put(@RequestBody Map<String, String> request) {
        String key = request.get("key");
        String value = request.get("value");

        if (key == null || value == null || key.length() > 256 || value.length() > 256) {
            return ResponseEntity.badRequest().body(Map.of("status", "ERROR", "message", "Invalid key or value"));
        }

        cacheService.put(key, value);
        return ResponseEntity.ok(Map.of("status", "OK", "message", "Key inserted/updated successfully."));
    }

    @GetMapping("/get")
    public ResponseEntity<Map<String, String>> get(@RequestParam String key) {
        String value = cacheService.get(key);

        if (value == null) {
            return ResponseEntity.ok(Map.of("status", "ERROR", "message", "Key not found."));
        }

        Map<String, String> response = new HashMap<>();
        response.put("status", "OK");
        response.put("key", key);
        response.put("value", value);
        return ResponseEntity.ok(response);
    }
}

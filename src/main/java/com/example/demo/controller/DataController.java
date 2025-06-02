package com.example.demo.controller;

import com.example.demo.model.ApiResponse;
import com.example.demo.service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class DataController {

    @Autowired
    private DataService dataService;

    @GetMapping("/group/{groupNumber}")
    public ResponseEntity<ApiResponse> getGroupData(@PathVariable int groupNumber) {
        ApiResponse response = dataService.getInitialDataForGroup(groupNumber);
        if (response == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(response);
    }

    public static class TriggerActionRequest {
        public String mainObjectId;
        public String actionType;
    }

    @PostMapping("/trigger-action")
    public ResponseEntity<ApiResponse> triggerAction(@RequestBody TriggerActionRequest request) {
        ApiResponse response = dataService.triggerActionAndUpdate(request.mainObjectId, request.actionType);
        if (response == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(response);
    }
}
package com.liferay.CommunityApp.controllers;

import com.liferay.CommunityApp.models.ReportModel;
import com.liferay.CommunityApp.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/reports")
public class ReportController {

    @Autowired
    private ReportService reportService;

    @GetMapping("/community")
    public ResponseEntity<List<ReportModel>> getCommunityReport() {
        List<ReportModel> communityReport = reportService.getCommunityReport();

        return ResponseEntity.ok(communityReport);
    }
}


package com.liferay.CommunityApp.controllers;

import com.liferay.CommunityApp.models.ReportModel;
import com.liferay.CommunityApp.service.ReportService;
import com.liferay.CommunityApp.service.utils.ExportExcelReportUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/reports")
@Tag(name = "Report")
public class ReportController {

    @Autowired
    private ReportService reportService;

    @Operation(summary = "Obter relatório da comunidade", description = "Endpoint para obter o relatório da comunidade.")
    @GetMapping("/community")
    public ResponseEntity<List<ReportModel>> getCommunityReport() {
        List<ReportModel> communityReport = reportService.CommunityReport();
        return ResponseEntity.ok(communityReport);
    }

    @Operation(summary = "Exportar relatório para Excel", description = "Endpoint para exportar o relatório da comunidade para um arquivo Excel.")
    @GetMapping("/export/excel")
    public ResponseEntity<Object> exportExcelFile(HttpServletResponse response) {
        try {
            response.setContentType("application/octet-stream");
            DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
            String currentDateTime = dateFormatter.format(new Date());

            String headerKey = "Content-Disposition";
            String headerValue = "attachment; filename=report" + currentDateTime + ".xlsx";
            response.setHeader(headerKey, headerValue);

            List<ReportModel> reportModelList = reportService.CommunityReport();

            ExportExcelReportUtil excelExporter = new ExportExcelReportUtil(reportModelList);

            excelExporter.export(response);

            return ResponseEntity.status(HttpStatus.OK).body("SUCCESS: Report exported successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("CONFLICT: Error while trying to export report. Contact Support ASAP");
        }
    }

}

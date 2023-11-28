package com.liferay.CommunityApp.service.utils;

import com.liferay.CommunityApp.models.ReportModel;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;
import java.util.List;

public class ExportExcelReportUtil {
    private final XSSFWorkbook workbook;
    private XSSFSheet sheet;
    private final List<ReportModel> reportModelList;

    public ExportExcelReportUtil(List<ReportModel> reportModelList) {
        this.reportModelList = reportModelList;
        workbook = new XSSFWorkbook();
    }

    private void writeHeaderLine() {
        sheet = workbook.createSheet("Reports");

        Row row = sheet.createRow(0);

        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setFontHeight(16);
        style.setFont(font);

        createCell(row, 0, "Community Name", style);
        createCell(row, 1, "Private Community", style);
        createCell(row, 2, "Number of Members", style);
        createCell(row, 3, "Number of Posts", style);
        createCell(row, 4, "Number of Comments", style);
    }

    private void createCell(Row row, int columnCount, Object value, CellStyle style) {
        sheet.autoSizeColumn(columnCount);
        Cell cell = row.createCell(columnCount);
        if (value instanceof Integer) {
            cell.setCellValue((Integer) value);
        } else if (value instanceof Boolean) {
            cell.setCellValue((Boolean) value);
        } else if (value instanceof Double) {
            cell.setCellValue((Double) value);
        } else {
            cell.setCellValue((String) value);
        }
        cell.setCellStyle(style);
    }

    private void writeDataLines() {
        int rowCount = 1;

        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setFontHeight(14);
        style.setFont(font);

        for (ReportModel report : reportModelList) {
            Row row = sheet.createRow(rowCount++);
            int columnCount = 0;

            createCell(row, columnCount++, report.getCommunityName(), style);
            createCell(row, columnCount++, report.getPrivateCommunity().toString(), style);
            createCell(row, columnCount++, report.getNumberOfMembers(), style);
            // Adicione os outros campos conforme necessário
            // createCell(row, columnCount++, report.getNumberOfPosts(), style);
            // createCell(row, columnCount++, report.getNumberOfComments(), style);
        }
    }

    public void export(HttpServletResponse response) throws IOException {
        writeHeaderLine();
        writeDataLines();

        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();

        outputStream.close();
    }
}

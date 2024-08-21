
package com.clinivapps.util;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import java.util.Map;
import org.springframework.web.servlet.view.document.AbstractExcelView;

import com.clinivapps.model.LogReportModel;


public class LogReportsView extends AbstractExcelView
{
    SimpleDateFormat dateTimeFormat  = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());

    protected void buildExcelDocument(final Map<String, Object> model, final HSSFWorkbook workbook, final HttpServletRequest request, final HttpServletResponse response) throws Exception {
        @SuppressWarnings("unchecked")
		final Map<String, List<LogReportModel>> reportMap = (Map<String, List<LogReportModel>>)  model.get("LogReports");
        final Map.Entry<String, List<LogReportModel>> entry = reportMap.entrySet().iterator().next();
        final HSSFSheet excelSheet = workbook.createSheet("v");
        this.setExcelHeader(excelSheet);
        workbook.setSheetName(0, "LogReports");
        this.setExcelRows(excelSheet, entry.getValue());
    }
    
    public void setExcelHeader(final HSSFSheet excelSheet) {
        final HSSFRow excelHeader = excelSheet.createRow(0);
        excelHeader.createCell(0).setCellValue("Role");
        excelHeader.createCell(1).setCellValue("Activity Name");
        excelHeader.createCell(2).setCellValue("Description");
        excelHeader.createCell(3).setCellValue("Site Number");
        excelHeader.createCell(4).setCellValue("Participant Id");
        excelHeader.createCell(5).setCellValue("Field Name");
        excelHeader.createCell(6).setCellValue("Original Value");
        excelHeader.createCell(7).setCellValue("Modified Value");
        excelHeader.createCell(8).setCellValue("Created Date Time");
       
    }
    
    public void setExcelRows(final HSSFSheet excelSheet, final List<LogReportModel> reportMap) {
        int rowNum = 1;
        for (final LogReportModel model : reportMap) {
            final HSSFRow row = excelSheet.createRow(rowNum++);
            row.createCell(0).setCellValue(model.getRoleName());
            row.createCell(1).setCellValue(model.getActivity());
            row.createCell(2).setCellValue(model.getDescription());
            row.createCell(3).setCellValue(model.getSiteId());
            row.createCell(4).setCellValue(model.getTrailParticipant());
            row.createCell(5).setCellValue(model.getFieldName());
            row.createCell(6).setCellValue(model.getOriginalAnswer());
            row.createCell(7).setCellValue(model.getModifiedAnswer());
            row.createCell(8).setCellValue(dateTimeFormat.format(model.getCreatedDate()));
        }
    }
}

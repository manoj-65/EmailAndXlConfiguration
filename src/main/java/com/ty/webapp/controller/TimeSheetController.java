package com.ty.webapp.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ty.webapp.entity.Info;

@RestController
public class TimeSheetController {
	@GetMapping("/getTimeSheet")
	public void getXlSheet(HttpServletResponse response) throws IOException {
		List<Info> infoList = getInfoList();
		try (HSSFWorkbook workbook = new HSSFWorkbook()) {
			HSSFSheet sheet = workbook.createSheet("Info Data");
			HSSFRow headerRow = sheet.createRow(0);
			headerRow.createCell(0).setCellValue("Name");
			headerRow.createCell(1).setCellValue("Age");
			int rowNum = 1;
			for (Info info : infoList) {
				HSSFRow dataRow = sheet.createRow(rowNum++);
				dataRow.createCell(0).setCellValue(info.getName());
				dataRow.createCell(1).setCellValue(info.getAge());
			}
			response.setContentType("application/vnd.ms-excel");
			response.setHeader("Content-Disposition", "attachment; filename=info.xls");
			try (OutputStream outputStream = response.getOutputStream()) {
				workbook.write(outputStream);
			}
		}

	}

	private List<Info> getInfoList() {
		List<Info> infoList = new ArrayList<>();
		infoList.add(new Info("Manoj", 30));
		infoList.add(new Info("Ram", 25));
		infoList.add(new Info("Ravi", 35));
		return infoList;
	}

	public static String convert(LocalDateTime of) {
		// To Get the Date in a format of dd-month-yyyy
		String time = "" + of.getDayOfMonth() + " - " + of.getMonth() + " - " + of.getYear() + " ";
		int hour = of.getHour();
		// Logic to convert time in 12 hour format
		if (hour > 12) {
			time += (hour - 12) + ":" + of.getMinute() + " PM.";
		} else {
			time += hour + ":" + of.getMinute() + " AM.";
		}
		return time;
	}
}

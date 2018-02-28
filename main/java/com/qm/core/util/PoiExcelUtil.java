package com.qm.core.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.*;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PoiExcelUtil {
	private static final Logger LOGGER = LoggerFactory.getLogger(PoiExcelUtil.class);

	public static List<Map<String, Object>> readExcelToListMap(String filePath) {
		List<Map<String, Object>> list = new ArrayList();
		boolean isXlsx = false;
		if (filePath.endsWith("xlsx")) {
			isXlsx = true;
		}
		try {
			InputStream input = new FileInputStream(filePath);
			Workbook workbook = null;
			if (isXlsx) {
				workbook = new XSSFWorkbook(input);
			} else {
				workbook = new HSSFWorkbook(input);
			}
			Sheet sheet = workbook.getSheetAt(0);
			int totalRow = sheet.getLastRowNum();
			for (int rowIndex = 0; rowIndex <= totalRow; rowIndex++) {
				Map<String, Object> rowMap = new HashMap();
				Row row = sheet.getRow(rowIndex);
				int totalCell = row.getLastCellNum();
				for (int cellIndex = 0; cellIndex < totalCell; cellIndex++) {
					Cell cell = row.getCell(cellIndex);
					rowMap.put("value" + (cellIndex + 1), getStringCellValue(cell));
				}
				list.add(rowMap);
			}
		} catch (IOException ex) {
			LOGGER.error("����������������������������--" + filePath);
			return null;
		}
		return list;
	}

	private static String getStringCellValue(Cell cell) {
		if (cell == null) {
			return "";
		}
		String strCell = "";
		switch (cell.getCellType()) {
		case 1:
			strCell = cell.getStringCellValue();
			break;
		case 0:
			strCell = String.valueOf(cell.getNumericCellValue());
			if (strCell.contains(".0")) {
				strCell = strCell.replaceAll("\\.0", "");
			}
			break;
		case 4:
			strCell = String.valueOf(cell.getBooleanCellValue());
			break;
		case 3:
			strCell = "";
			break;
		case 2:
			strCell = String.valueOf(cell.getCellFormula());
			break;
		default:
			strCell = "";
		}
		if ((strCell.equals("")) || (strCell == null)) {
			return "";
		}
		return strCell;
	}

	public static void ListMapExpotToExcel(String sheetName, Timestamp start, Timestamp end,
			List<Map<String, Object>> headList, List<Map<String, Object>> dataList, HttpServletResponse response) {
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet(sheetName);

		HSSFCellStyle titleStyle = workbook.createCellStyle();
		titleStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
		titleStyle.setFillPattern((short) 1);
		titleStyle.setAlignment((short) 2);
		titleStyle.setBorderBottom((short) 1);
		titleStyle.setBorderTop((short) 1);
		titleStyle.setBorderLeft((short) 1);
		titleStyle.setBorderRight((short) 1);
		HSSFRow titleRow = sheet.createRow(0);
		HSSFCell cellTitle = titleRow.createCell(0);
		cellTitle.setCellStyle(titleStyle);
		cellTitle.setCellType(1);
		HSSFRichTextString textTitle = new HSSFRichTextString(sheetName);
		cellTitle.setCellValue(textTitle);
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, headList.size() - 1));
		for (int i = 1; i < headList.size(); i++) {
			cellTitle = titleRow.createCell(i);
			cellTitle.setCellStyle(titleStyle);
		}
		HSSFCellStyle cellStyle = workbook.createCellStyle();
		cellStyle.setBorderBottom((short) 1);
		cellStyle.setBorderTop((short) 1);
		cellStyle.setBorderLeft((short) 1);
		cellStyle.setBorderRight((short) 1);

		Integer[] rowsWidth = new Integer[headList.size()];

		HSSFRow headRow = sheet.createRow(2);
		for (int headIndex = 0; headIndex < headList.size(); headIndex++) {
			Map<String, Object> headName = (Map) headList.get(headIndex);
			HSSFCell cellHead = headRow.createCell(headIndex);
			HSSFCellStyle headStyle = workbook.createCellStyle();
			headStyle.setBorderBottom((short) 1);
			headStyle.setBorderTop((short) 1);
			headStyle.setBorderLeft((short) 1);
			headStyle.setBorderRight((short) 1);
			cellHead.setCellStyle(headStyle);
			cellHead.setCellType(1);
			HSSFRichTextString text = new HSSFRichTextString(headName.get("name").toString());
			cellHead.setCellValue(text);
			rowsWidth[headIndex] = Integer.valueOf(text.getString().getBytes().length);
		}
		Integer cellSize = Integer.valueOf(headList.size());
		for (int rowIndex = 0; rowIndex < dataList.size(); rowIndex++) {
			HSSFRow row = sheet.createRow(rowIndex + 3);
			Map<String, Object> data = (Map) dataList.get(rowIndex);
			for (int cellIndex = 0; cellIndex < cellSize.intValue(); cellIndex++) {
				HSSFCell cell = row.createCell(cellIndex);
				cell.setCellStyle(cellStyle);
				cell.setCellType(1);
				HSSFRichTextString text = new HSSFRichTextString(
						data.get(((Map) headList.get(cellIndex)).get("key").toString()).toString());
				cell.setCellValue(text);
				if (text.getString().getBytes().length > rowsWidth[cellIndex].intValue()) {
					rowsWidth[cellIndex] = Integer.valueOf(text.getString().getBytes().length);
				}
			}
		}
		Integer totalWidth = Integer.valueOf(0);
		for (int i = 0; i < headList.size(); i++) {
			sheet.setColumnWidth(i, rowsWidth[i].intValue() * 256 + 1024);
			totalWidth = Integer.valueOf(totalWidth.intValue() + rowsWidth[i].intValue() + 4);
		}
		HSSFRow secRow = sheet.createRow(1);
		HSSFCell secCell = secRow.createCell(0);
		secCell.setCellType(1);
		String leftString = "����������";
		String rightString = "������������:��" + CommonUtils.formatTimeStamp("yyyy-MM-dd", start) + " - "
				+ CommonUtils.formatTimeStamp("yyyy-MM-dd", end);
		totalWidth = Integer.valueOf(totalWidth.intValue() - leftString.getBytes().length
				- rightString.getBytes().length + headList.size() / 2 + headList.size() + 1);
		byte[] bytes = new byte[totalWidth.intValue()];
		String nullString = new String(bytes);
		HSSFRichTextString secText = new HSSFRichTextString(leftString + nullString + rightString);
		secCell.setCellValue(secText);
		sheet.addMergedRegion(new CellRangeAddress(1, 1, 0, headList.size() - 1));
		secCell.setCellStyle(cellStyle);
		for (int i = 1; i < headList.size(); i++) {
			secCell = secRow.createCell(i);
			secCell.setCellStyle(cellStyle);
		}
		if (workbook != null) {
			try {
				String fileName = "Excel-"
						+ CommonUtils.formatTimeStamp("yyyy-MM-dd HH:mm:ss", CommonUtils.getTimestamp()) + ".xls";
				String headStr = "attachment; filename=\"" + fileName + "\"";
				response.setContentType("APPLICATION/OCTET-STREAM");
				response.setHeader("Content-Disposition", headStr);
				OutputStream out = response.getOutputStream();
				workbook.write(out);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static void ListMapOutPut(String sheetName, List<Map<String, Object>> headList,
			List<Map<String, Object>> dataList, HttpServletResponse response) {
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet(sheetName);

		HSSFCellStyle cellStyle = workbook.createCellStyle();
		cellStyle.setBorderBottom((short) 1);
		cellStyle.setBorderTop((short) 1);
		cellStyle.setBorderLeft((short) 1);
		cellStyle.setBorderRight((short) 1);

		Integer[] rowsWidth = new Integer[headList.size()];

		HSSFRow headRow = sheet.createRow(0);
		for (int headIndex = 0; headIndex < headList.size(); headIndex++) {
			Map<String, Object> headName = (Map) headList.get(headIndex);
			HSSFCell cellHead = headRow.createCell(headIndex);
			HSSFCellStyle headStyle = workbook.createCellStyle();
			headStyle.setBorderBottom((short) 1);
			headStyle.setBorderTop((short) 1);
			headStyle.setBorderLeft((short) 1);
			headStyle.setBorderRight((short) 1);
			cellHead.setCellStyle(headStyle);
			cellHead.setCellType(1);
			HSSFRichTextString text = new HSSFRichTextString(headName.get("name").toString());
			cellHead.setCellValue(text);
			rowsWidth[headIndex] = Integer.valueOf(text.getString().getBytes().length);
		}
		Integer cellSize = Integer.valueOf(headList.size());
		for (int rowIndex = 0; rowIndex < dataList.size(); rowIndex++) {
			HSSFRow row = sheet.createRow(rowIndex + 1);
			Map<String, Object> data = (Map) dataList.get(rowIndex);
			for (int cellIndex = 0; cellIndex < cellSize.intValue(); cellIndex++) {
				HSSFCell cell = row.createCell(cellIndex);
				cell.setCellStyle(cellStyle);
				cell.setCellType(1);
				HSSFRichTextString text;
				if (CommonUtils.isEmpty(data.get(((Map) headList.get(cellIndex)).get("key").toString()))) {
					text = new HSSFRichTextString("");
				} else {
					text = new HSSFRichTextString(
							data.get(((Map) headList.get(cellIndex)).get("key").toString()).toString());
				}
				cell.setCellValue(text);
				if (text.getString().getBytes().length > rowsWidth[cellIndex].intValue()) {
					rowsWidth[cellIndex] = Integer.valueOf(text.getString().getBytes().length);
				}
			}
		}
		for (int i = 0; i < headList.size(); i++) {
			sheet.setColumnWidth(i, rowsWidth[i].intValue() * 256 + 1024);
		}
		if (workbook != null) {
			try {
				String fileName = "Excel-"
						+ CommonUtils.formatTimeStamp("yyyy-MM-dd HH:mm:ss", CommonUtils.getTimestamp()) + ".xls";
				String headStr = "attachment; filename=\"" + fileName + "\"";
				response.setContentType("APPLICATION/OCTET-STREAM");
				response.setHeader("Content-Disposition", headStr);
				OutputStream out = response.getOutputStream();
				workbook.write(out);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}

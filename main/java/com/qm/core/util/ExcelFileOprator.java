package com.qm.core.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;


public class ExcelFileOprator {
	
	private Workbook excelWorkBook ;
	
	public ExcelFileOprator() throws Exception{
		excelWorkBook = new SXSSFWorkbook(100);
	}
	
	public ExcelFileOprator(String excelFileUrl) throws Exception{
		File excelFile=null;
		if(excelFileUrl==null || "".equals(excelFileUrl.trim())){
			excelFile=new File(ExcelFileOprator.class.getResource("").getPath(),"temp.xlsx");
		}else{
			excelFile=new File(excelFileUrl);
		}
		FileInputStream fin=new FileInputStream(excelFile);
		excelWorkBook = WorkbookFactory.create(fin);
		fin.close();
	}
	
	public ExcelFileOprator(File excelFile) throws Exception{
		FileInputStream fin=new FileInputStream(excelFile);
		excelWorkBook = WorkbookFactory.create(fin);
		fin.close();
	}
	
	public ExcelFileOprator(InputStream fin) throws Exception{
		excelWorkBook = WorkbookFactory.create(fin);
		fin.close();
	}
	
	public List<String[]> readData(String sheetName,int startIndex,int[] columns) throws Exception{
		Sheet dataSheet = excelWorkBook.getSheet(sheetName);
		if(dataSheet==null){
			System.out.println(sheetName +"sheetName 不存在");
		}
		return readData(dataSheet, startIndex, columns);
	}
	
	public List<String[]> readData(int sheetIndex,int startIndex,int[] columns) throws Exception{
		Sheet dataSheet = excelWorkBook.getSheetAt(sheetIndex);
		if(dataSheet==null){
			System.out.println("sheetIndex "+sheetIndex +" 不存在");
		}
		return readData(dataSheet, startIndex, columns);
	}
	
	public List<String[]> readData(Sheet dataSheet,int startIndex,int[] columns) throws Exception{
		List<String[]> retList=new ArrayList<String[]>(1024);
		int maxColumn=getMax(columns);
		for (int rowIndex =startIndex; rowIndex<= dataSheet.getLastRowNum(); rowIndex++) {
			Row row = dataSheet.getRow(rowIndex);
			if (null != row && row.getLastCellNum()>=maxColumn) {
				String[] dataArr=new String[columns.length];
				for(int i=0;i<columns.length;i++){
					dataArr[i]=getCellValue(row.getCell(columns[i]));
				}
				for(int i=0;i<columns.length;i++){
					if(!StringUtil.isBlank(dataArr[i])){
						retList.add(dataArr);
						break;
					}
				}
			}
		}
		return retList;
	}
	
	public List<String> readData(String sheetName,int startIndex,int column) throws Exception{
		Sheet dataSheet = excelWorkBook.getSheet(sheetName);
		if(dataSheet==null){
			System.out.println(sheetName +"sheetName 不存在");
		}
		return readData(dataSheet, startIndex, column);
	}
	
	public List<String> readData(int sheetIndex,int startIndex,int column) throws Exception{
		Sheet dataSheet = excelWorkBook.getSheetAt(sheetIndex);
		if(dataSheet==null){
			System.out.println("sheetIndex "+sheetIndex +" 不存在");
		}
		return readData(dataSheet, startIndex, column);
	}
	
	public List<String> readData(Sheet dataSheet,int startIndex,int column) throws Exception{
		List<String> retList=new ArrayList<String>(1024);
		for (int rowIndex =startIndex; rowIndex<= dataSheet.getLastRowNum(); rowIndex++) {
			Row row = dataSheet.getRow(rowIndex);
			if (null != row && row.getLastCellNum()>=column) {
				String value=getCellValue(row.getCell(column));
				if(!StringUtil.isBlank(value)){
					retList.add(value);
				}
			}
		}
		return retList;
	}
	
	private void writeData(String[] titleArr,List<String[]> dataList,Sheet dataSheet){
		//追加内容
		int start=dataSheet.getLastRowNum();
		if(titleArr!=null){
			Row titleRow=dataSheet.createRow(start);
			for(int i=0;i<titleArr.length;i++){
				Cell cell=titleRow.createCell(i);
				cell.setCellValue(titleArr[i]);
			}
			start++;
		}
		for(int i=0;i<dataList.size();i++){
			Row dataRow=dataSheet.createRow(i+start);
			for(int j=0;j<dataList.get(i).length;j++){
				Cell cell=dataRow.createCell(j);
				cell.setCellValue(dataList.get(i)[j]);
			}
		}
	}
	//新增写超链接方法1
	private void writeData(String[] titleArr,List<String[]> dataList,Sheet dataSheet,int linkRowIndex,String linkContent,String linkVisName){
		//追加内容
		int start=dataSheet.getLastRowNum();
		if(titleArr!=null){
			Row titleRow=dataSheet.createRow(start);
			for(int i=0;i<titleArr.length;i++){
				Cell cell=titleRow.createCell(i);
				cell.setCellValue(titleArr[i]);
			}
			start++;
		}
		for(int i=0;i<dataList.size();i++){
			Row dataRow=dataSheet.createRow(i+start);
			for(int j=0;j<dataList.get(i).length;j++){
				Cell cell=dataRow.createCell(j);
				cell.setCellValue(dataList.get(i)[j]);
			}
			Cell cell=dataRow.createCell(linkRowIndex);
			//设置超链接 单元格颜色
			XSSFCellStyle linkStyle =  (XSSFCellStyle) excelWorkBook.createCellStyle();
			XSSFFont cellFont= (XSSFFont) excelWorkBook.createFont();
			cellFont.setUnderline((byte) 1);
			cellFont.setColor(HSSFColor.BLUE.index);
			linkStyle.setFont(cellFont);
			cell.setCellStyle(linkStyle);
			//不同sheet之间的超链接
			/*Hyperlink hyperlink = new HSSFHyperlink(Hyperlink.LINK_DOCUMENT);
			hyperlink.setAddress("#sheet5!A1");*/
			cell.setCellFormula("HYPERLINK(\""+linkContent+i+".xlsx\",\""+linkVisName+"\")");
			
		}
		
	}
	//新增写超链接方法2
		private void writeData(String[] titleArr,List<String[]> dataList,Sheet dataSheet,int[] linkRowIndex,String linkPath){
			//追加内容
			int start=dataSheet.getLastRowNum();
			if(titleArr!=null){
				Row titleRow=dataSheet.createRow(start);
				for(int i=0;i<titleArr.length;i++){
					Cell cell=titleRow.createCell(i);
					cell.setCellValue(titleArr[i]);
				}
				start++;
			}
			//设置超链接 单元格颜色
			XSSFCellStyle linkStyle =  (XSSFCellStyle) excelWorkBook.createCellStyle();
			XSSFFont cellFont= (XSSFFont) excelWorkBook.createFont();
			cellFont.setUnderline((byte) 1);
			cellFont.setColor(HSSFColor.BLUE.index);
			linkStyle.setFont(cellFont);
			
			for(int i=0;i<dataList.size();i++){
				Row dataRow=dataSheet.createRow(i+start);
				for(int j=0;j<dataList.get(i).length;j++){
					Cell cell=dataRow.createCell(j);
					if(isExitRow(linkRowIndex,j) && !"0".equals(dataList.get(i)[j].toString())){
						cell.setCellStyle(linkStyle);
						cell.setCellFormula("HYPERLINK(\""+linkPath+"/"+i+j+".xlsx\",\""+dataList.get(i)[j]+"\")");
					}else{
						cell.setCellValue(dataList.get(i)[j]);
					}
					
				}
			}
		}
	public void writeData(String[] titleArr,List<String[]> dataList,String sheetName){
		Sheet dataSheet = excelWorkBook.getSheet(sheetName);
		if(dataSheet==null){
			dataSheet=excelWorkBook.createSheet(sheetName);
		}
		writeData(titleArr, dataList, dataSheet);
	}
	
	public void writeData(String[] titleArr,List<String[]> dataList,int sheetIndex){
		Sheet dataSheet =null;
		if(sheetIndex>=excelWorkBook.getNumberOfSheets() || excelWorkBook.getSheetAt(sheetIndex)==null){
			dataSheet=excelWorkBook.createSheet("sheet"+(sheetIndex+1));
		}else{
			dataSheet=excelWorkBook.getSheetAt(sheetIndex);
		}
		writeData(titleArr, dataList, dataSheet);
	}
	public void writeData(String[] titleArr,List<String[]> dataList,int sheetIndex,int linkRowIndex,String linkContent,String linkVisName){
		Sheet dataSheet =null;
		if(sheetIndex>=excelWorkBook.getNumberOfSheets() || excelWorkBook.getSheetAt(sheetIndex)==null){
			dataSheet=excelWorkBook.createSheet("sheet"+(sheetIndex+1));
		}else{
			dataSheet=excelWorkBook.getSheetAt(sheetIndex);
		}
		writeData(titleArr, dataList, dataSheet,linkRowIndex, linkContent, linkVisName);
	}
	//生成超链接 linkRowIndex 超链接 列集合  
	public void writeData(String[] titleArr,List<String[]> dataList,int sheetIndex,int[] linkRowIndex,String linkPath){
		Sheet dataSheet =null;
		if(sheetIndex>=excelWorkBook.getNumberOfSheets() || excelWorkBook.getSheetAt(sheetIndex)==null){
			dataSheet=excelWorkBook.createSheet("sheet"+(sheetIndex+1));
		}else{
			dataSheet=excelWorkBook.getSheetAt(sheetIndex);
		}
		writeData(titleArr, dataList, dataSheet,linkRowIndex, linkPath);
	}
	
	public void writeData(String title,List<String> dataList,String sheetName){
		List<String[]> list=new ArrayList<String[]>(1024);
		for(String data:dataList){
			list.add(new String[]{data});
		}
		writeData(new String[]{title}, list, sheetName);
	}
	
	public void writeData(String title,List<String> dataList,int sheetIndex){
		List<String[]> list=new ArrayList<String[]>(1024);
		for(String data:dataList){
			list.add(new String[]{data});
		}
		writeData(new String[]{title}, list, sheetIndex);
	}
	
	public void saveExcelFile(String saveFileUrl) throws Exception{
		FileOutputStream fout=new FileOutputStream(new File(saveFileUrl));
		excelWorkBook.write(fout);
		fout.close();
	}
	
	public void saveExcelFile(String savePath,String fileName) throws Exception{
		File filePath = new File(savePath);
		if(!filePath.exists()){
			filePath.mkdirs();
		}
		FileOutputStream fout=new FileOutputStream(new File(savePath+"/"+fileName));
		excelWorkBook.write(fout);
		fout.close();
	}
	
	public int getSheetLength(){
		return excelWorkBook.getNumberOfSheets();
	}
	
	public String getSheetName(int sheetIdx){
		return excelWorkBook.getSheetName(sheetIdx);
	}
	
	public void transmit(OutputStream outputStream) throws Exception{
		excelWorkBook.write(outputStream);
	}
	
	private int getMax(int[] arr){
		int max=0;
		for(int i=0;i<arr.length;i++){
			max=Math.max(max, arr[i]);
		}
		return max;
	}
	
	//获取excel单元格内容
	@SuppressWarnings("deprecation")
	private String getCellValue(Cell cell){
		String value=null;
		if (null != cell) {
			switch (cell.getCellType()) {
			case Cell.CELL_TYPE_NUMERIC: // 数字
//						value=cell.getNumericCellValue()+"";
				if(org.apache.poi.ss.usermodel.DateUtil.isCellDateFormatted(cell)){  
					 value = String.valueOf(cell.getDateCellValue()); 
                }else{   
                  cell.setCellType(Cell.CELL_TYPE_STRING);  
                  String temp = cell.getStringCellValue();  
                  //判断是否包含小数点，如果不含小数点，则以字符串读取，如果含小数点，则转换为Double类型的字符串  
                  if(temp.indexOf(".")>-1){  
               	   	value = String.valueOf(new Double(temp)).trim();  
                  }else{  
               	   	value = temp.trim();  
                  }  
                }
				break;
			case Cell.CELL_TYPE_STRING: // 字符串
				value=cell.getStringCellValue()+"";
				break;
			case Cell.CELL_TYPE_BOOLEAN: // Boolean
				value=cell.getBooleanCellValue()+"";
				break;
			case Cell.CELL_TYPE_FORMULA: // 公式
//						value=cell.getCellFormula()+"";
				cell.setCellType(Cell.CELL_TYPE_STRING);  
				value = cell.getStringCellValue();  
                if(value!=null){  
               	 	value = value.replaceAll("#N/A","").trim();  
                }
				break;
			case Cell.CELL_TYPE_BLANK: // 空值
				value=" ";
				break;
			case Cell.CELL_TYPE_ERROR: // 故障
				value=" ";
				break;
			default:
				value=null;
				break;
			}
		}
		if(value!=null){
			value=value.trim().replaceAll("^'","");
		}else{
			return "";
		}
		return value;
	}
	
	public Workbook getExcelWorkBook() {
		return excelWorkBook;
	}
	
	private boolean isExitRow(int[] rows,int row){
		for(int tmp:rows){
			if(row == tmp){
				return true;
			}
		}
		return false;
	}
}

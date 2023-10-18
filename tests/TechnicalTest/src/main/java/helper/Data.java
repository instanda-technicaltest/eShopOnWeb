package helper;
import java.io.File;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

public class Data {
	public static HashMap<String, String> map = new HashMap<String,String>(); 


	public String getData(String header) {

		return map.get(header);
	}

	public void setData(String sheetName, String scenarioName) {
		String filePath = System.getProperty("user.dir")+"\\src\\main\\resources\\data";
		Data data = new Data();
		try {
			data.setData(filePath,"TestData.xls",sheetName,scenarioName);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void setData(String scenarioName) {
		String filePath = System.getProperty("user.dir")+"\\src\\main\\resources\\data";
		Data data = new Data();
		try {
			data.setData(filePath,"TestData.xls","Data",scenarioName);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void setData(String filePath, String fileName, String sheetName, String testCaseName) throws IOException {
		File file = new File(filePath+"\\"+fileName);
		FileInputStream input = new FileInputStream(file);
		Workbook book = null;
		book = new HSSFWorkbook(input);
		Sheet sheet = book.getSheet(sheetName);
		int rowCount = sheet.getLastRowNum()-sheet.getFirstRowNum();
		for (int i = 0; i < rowCount+1; i++) {
			Row row = sheet.getRow(i);
			System.out.println(row.getCell(0).getStringCellValue());
			if(row.getCell(0).getStringCellValue().equals(testCaseName)) {
				Row headRow = sheet.getRow(0);
				for (int j = 0; j <= row.getLastCellNum()+1; j++) {
					try {
						map.put(headRow.getCell(j).getStringCellValue(),row.getCell(j).getStringCellValue());
					}catch(Exception e) {
					}
				}
			}
		} 
		book.close();
	}

	public static  void ClearData() {
		map.clear();
	}

	public void setValue(String key, String value) {
		map.put(key, value);
	}

}

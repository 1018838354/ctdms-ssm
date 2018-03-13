package cn.jxufe.ctdms.util.excel;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelParse {

	//测试
	public static void main(String[] args) {
		parse(
						"E:\\QQ\\QQmessage\\1059654342\\FileRecv\\软通学院本科162学期课表_撤班后.xls",
						MyExcelCourse.class);
	}

	public static <T extends ExcelOperation> List<T> parse(String ExcelPath,
														   Class<T> cls) {
		InputStream stream;
		try {
			stream = new FileInputStream(ExcelPath);
		} catch (FileNotFoundException e) {
			return Collections.emptyList();
		}
		return (List<T>) parseByPath(stream, ExcelPath, cls);
	}


	public static <T extends ExcelOperation> List<T> parse(byte[] bytes,
														   String name, Class<T> cls) {
		InputStream stream = new ByteArrayInputStream(bytes);
		return (List<T>) parseByPath(stream, name, cls);
	}

	private static <T extends ExcelOperation> List<T> parseByPath(InputStream stream,
																  String ExcelPath, Class<T> cls) {
		List<T> beans = null; // 作为course 表的信息插入
		Workbook workbook = null;
		try {
			if (ExcelPath.endsWith("xls")) {
				workbook = new HSSFWorkbook(stream);
			} else if (ExcelPath.endsWith("xlsx")) {
				workbook = new XSSFWorkbook(stream);
			} else {
				stream.close();
				return Collections.emptyList();
			}
			beans = new ArrayList<>();
			Sheet sheet = (Sheet) workbook.getSheetAt(
					cls.getDeclaredField("SheetAt").getInt(null));  
			/**
			 * 检查文档 是否 符合规范
			 */
			T excel = cls.getDeclaredConstructor().newInstance();
			if(!excel.check(sheet.getRow(0))){
			//Method method = cls.getMethod("check",Row.class);
			//if (!(boolean)method.invoke(null,sheet.getRow(0))) {
				System.err.println("不规范的教学计划");
				return Collections.emptyList();
			} 
			eachRow(cls, beans, sheet);
		} catch (Exception e) {
			e.printStackTrace();
			return Collections.emptyList();
		}
		return beans;
	}

	private static <T extends ExcelOperation> void eachRow(Class<T> cls,
														   List<T> beans, Sheet sheet) throws InstantiationException,
			IllegalAccessException {
		int rows = sheet.getPhysicalNumberOfRows();
		for (int r = 1; r < rows; r++) {
			Row row = sheet.getRow(r);
			if (row != null) {
				int cells = row.getPhysicalNumberOfCells();
				ExcelOperation eb = (ExcelOperation) cls.newInstance();
				for (short i = 0; i < cells; i++) {
					Cell cell = row.getCell((short) i);
					if (cell != null) {
						eb.fillByCell(cell);
					}
				}
				beans.add((T) eb);
			}
		}
	}


}

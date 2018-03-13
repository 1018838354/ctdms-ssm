package cn.jxufe.ctdms.util.excel;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

public interface ExcelOperation {


	/**
	 * 对每个cell 进行操作
	 * @param cell
	 * @return
	 */
	void fillByCell(Cell cell);

	/**
	 * 检查是否为标准的excel
	 *
	 * @param sheet
	 * @return
	 */
	boolean check(Row row);

}

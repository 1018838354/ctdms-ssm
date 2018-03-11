package cn.jxufe.ctdms.util.excel;

import cn.jxufe.ctdms.bean.Course;
import cn.jxufe.ctdms.bean.CourseTime;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import java.util.ArrayList;
import java.util.List;

public class MyExcelCourse implements ExcelOperation {
 
	// Course  位于 第二张表
	public final static int SheetAt = 1;

	private Course course = new Course();
	private List<CourseTime> courseTimes = new ArrayList<>();

	public void fillByCell(Cell cell) {
		String strCell = cellValueToString(cell);
		switch (cell.getColumnIndex()) {
		case 0:
			course.setcCode(strCell);
			break;
		case 1:
			course.setClassCode(strCell);
			break;
		case 3:
			course.setcName(strCell);
			break;
		case 4:
			course.setTeacherName(strCell);
			break;
		case 5: 
		case 6: 
		case 7:
			setCourseTimes(strCell);
			break;
		case 8: 
		case 9: 
		case 10:
			setClassRooms(strCell, cell.getColumnIndex() - 8);
			break;
		case 11:
			course.setWeekly(strCell);
			break;
		case 14:
			course.setDistrict(strCell);
			break;
		default:
			break;
		}
	}
	private void setCourseTimes(String str){
		if("".equals(str))
			return;
		CourseTime ct = new CourseTime(str);
		courseTimes.add(ct);
	}
	private void setClassRooms(String str,int index){
		if("".equals(str))
			return;
		if(courseTimes.size() -1 < index )
			return;
		courseTimes.get(index).setClassRoom(str);
	}

	public String cellValueToString(Cell cell) {
		if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
			return String.valueOf(cell.getNumericCellValue());
		} else if (cell.getCellType() == Cell.CELL_TYPE_BLANK) {
			return "";
		}
		String value = cell.getStringCellValue();
		return "0000".equals(value)?"":value;
	}
	public boolean check(Row row) {
		for (int i = 0; i < 15; i++)
			switch (i) {
			case 0:
				if (!"课程号".equals(row.getCell(i).getStringCellValue()))
					return false;
				break;
			case 1:
				if (!"班次".equals(row.getCell(i).getStringCellValue()))
					return false;
				break;
			case 2:
				if (!"学分".equals(row.getCell(i).getStringCellValue()))
					return false;
				break;
			case 3:
				if (!"课程名称".equals(row.getCell(i).getStringCellValue()))
					return false;
				break;
			default:
				break;
			}
		return true;
	}


	@Override
	public String toString() {
		//return courseCode+"-["+shift+"]:"+courseName+":"+teacherName;
		return course.toString();
	}

	public List<CourseTime> getCourseTimes() {
		return courseTimes;
	}

	public Course getCourse() {
		return course;
	}
}

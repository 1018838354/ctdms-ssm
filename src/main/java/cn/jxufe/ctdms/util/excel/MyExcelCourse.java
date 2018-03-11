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
			setCourseCode(strCell);
			break;
		case 1:
			setShift(strCell);
			break;
		case 3:
			setCourseName(strCell);
			break;
		case 4:
			setTeacherName(strCell);
			break;
		case 5: 
		case 6: 
		case 7:
			setCourseTime(getCourseTimes(),strCell);
			break;
		case 8: 
		case 9: 
		case 10:
			setClassRoom(getCourseTimes(),strCell, cell.getColumnIndex() - 8);
			break;
		case 11:
			setWeekly(strCell);
			break;
		case 14:
			setDistrict(strCell);
			break;
		default:
			break;
		}
	}
	private void setCourseTime(List<CourseTime> cts ,String str){
		if("".equals(str))
			return;
		CourseTime ct = new CourseTime(str);
		cts.add(ct);
	}
	private void setClassRoom(List<CourseTime> cts ,String str,int index){
		if("".equals(str))
			return;
		if(cts.size() -1 < index )
			return;
		cts.get(index).setClassRoom(str);
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

	public Course toCourse(){
		course.setcName(courseName);
		course.setcCode(courseCode);
		course.setDistrict(district); 
		course.setWeekly(weekly);
		return course;
	}

	private String courseName="";
	private String teacherName="";
	private String courseCode=""; 
	private String shift="";
	private String district="";		//校区
	private String weekly="";
	@Override
	public String toString() { 
		return courseCode+"-["+shift+"]:"+courseName+":"+teacherName;
	}
	public String getShift() {
		return shift;
	}
	public void setShift(String shift) {
		this.shift = shift;
	}   
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String name) {
		this.courseName = name;
	}
	public String getCourseCode() {
		return courseCode;
	}
	public void setCourseCode(String courseCode) {
		this.courseCode = courseCode;
	}
	public String getTeacherName() {
		return teacherName;
	}
	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	} 
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public String getWeekly() {
		return weekly;
	}
	public void setWeekly(String weekly) {
		this.weekly = weekly;
	}
	public List<CourseTime> getCourseTimes() {
		return courseTimes;
	}
	public void setCourseTimes(List<CourseTime> courseTimes) {
		this.courseTimes = courseTimes;
	}

	
}

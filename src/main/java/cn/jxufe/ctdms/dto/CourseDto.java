package cn.jxufe.ctdms.dto;

public class CourseDto {
    private String classCode;
    private String cName;
    private String cCode;
    private String teacherName;
    private String district;
    private String weekly;
    private int day;
    private int classIndex;
    private int lastTime;
    private String classRoom;

    public CourseDto() {
    }

    public CourseDto(String classCode, String cName, String cCode, String teacherName, String district, String weekly, int day, int classIndex, int lastTime, String classRoom) {
        this.classCode = classCode;
        this.cName = cName;
        this.cCode = cCode;
        this.teacherName = teacherName;
        this.district = district;
        this.weekly = weekly;
        this.day = day;
        this.classIndex = classIndex;
        this.lastTime = lastTime;
        this.classRoom = classRoom;
    }

    @Override
    public String toString() {
        return "CourseDto{" +
                "classCode='" + classCode + '\'' +
                ", cName='" + cName + '\'' +
                ", cCode='" + cCode + '\'' +
                ", teacherName='" + teacherName + '\'' +
                ", district='" + district + '\'' +
                ", weekly='" + weekly + '\'' +
                ", day=" + day +
                ", classIndex=" + classIndex +
                ", lastTime=" + lastTime +
                ", classRoom='" + classRoom + '\'' +
                '}';
    }

    public String getClassCode() {
        return classCode;
    }

    public void setClassCode(String classCode) {
        this.classCode = classCode;
    }

    public String getcName() {
        return cName;
    }

    public void setcName(String cName) {
        this.cName = cName;
    }

    public String getcCode() {
        return cCode;
    }

    public void setcCode(String cCode) {
        this.cCode = cCode;
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

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getClassIndex() {
        return classIndex;
    }

    public void setClassIndex(int classIndex) {
        this.classIndex = classIndex;
    }

    public int getLastTime() {
        return lastTime;
    }

    public void setLastTime(int lastTime) {
        this.lastTime = lastTime;
    }

    public String getClassRoom() {
        return classRoom;
    }

    public void setClassRoom(String classRoom) {
        this.classRoom = classRoom;
    }
}

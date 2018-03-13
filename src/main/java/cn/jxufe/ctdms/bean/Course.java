package cn.jxufe.ctdms.bean;

// table course
//课程信息
public class Course {
    //课程id
    long cId;
    //课程信息id
    long ciId;
    //课程名
    String cName;
    //课程代码
    String cCode;
    // 进度表状态
    int state;
    // 文件record id
    long recordId;
    //学期
    String term;
    //教师名
    String teacherName;
    //uId
    long uId;
    //班级代码
    String classCode;
    //校区
    String district;
    //周次
    String weekly;

    @Override
    public String toString() {
        return "Course{" +
                "cId=" + cId +
                ", cName='" + cName + '\'' +
                ", cCode='" + cCode + '\'' +
                ", term='" + term + '\'' +
                ", teacherName='" + teacherName + '\'' +
                ", district='" + district + '\'' +
                ", weekly='" + weekly + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if(o instanceof CourseInfo){
            if(((CourseInfo)o).getcCode().equals(cCode))return true;
        }
        if (o == null || getClass() != o.getClass()) return false;

        Course course = (Course) o;

        return cCode.equals(course.cCode);
    }

    @Override
    public int hashCode() {
        return cCode.hashCode();
    }

    public long getCiId() {
        return ciId;
    }

    public void setCiId(long ciId) {
        this.ciId = ciId;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public long getRecordId() {
        return recordId;
    }

    public void setRecordId(long recordId) {
        this.recordId = recordId;
    }

    public String getcName() {
        return cName;
    }

    public void setcName(String cName) {
        this.cName = cName;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public String getcCode() {
        return cCode;
    }

    public void setcCode(String cCode) {
        this.cCode = cCode;
    }

    public long getcId() {

        return cId;
    }

    public long getuId() {
        return uId;
    }

    public void setuId(long uId) {
        this.uId = uId;
    }

    public void setcId(long cId) {
        this.cId = cId;
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

    public String getClassCode() {
        return classCode;
    }

    public void setClassCode(String classCode) {
        this.classCode = classCode;
    }
}

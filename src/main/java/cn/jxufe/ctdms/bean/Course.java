package cn.jxufe.ctdms.bean;

//课程信息
public class Course {
    //课程id
    long cId;
    //课程名
    String cName;
    //课程代码
    String cCode;
    //学期
    String term;
    //教师名
    String teacherName;

    String classCode;

    String district;	//校区

    String weekly;		//周次

    @Override
    public String toString() {
        return "Course{" +
                "cId=" + cId +
                ", cName='" + cName + '\'' +
                ", cCode='" + cCode + '\'' +
                ", term='" + term + '\'' +
                ", teacherName='" + teacherName + '\'' +
                ", classCode='" + classCode + '\'' +
                ", district='" + district + '\'' +
                ", weekly='" + weekly + '\'' +
                '}';
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

    public void setcId(long cId) {
        this.cId = cId;
    }

    public String getClassCode() {
        return classCode;
    }

    public void setClassCode(String classCode) {
        this.classCode = classCode;
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
}
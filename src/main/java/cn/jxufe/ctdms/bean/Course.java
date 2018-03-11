package cn.jxufe.ctdms.bean;

//课程信息
public class Course {
    //课程id
    long cId;
    //课程名
    String cName;
    //学期
    String term;
    //课程代码
    String cCode;
    //校区
    String district;
    //周次
    String weekly;

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

    public long getcId() {

        return cId;
    }

    public void setcId(long cId) {
        this.cId = cId;
    }
}

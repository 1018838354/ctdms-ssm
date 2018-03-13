package cn.jxufe.ctdms.bean;

import cn.jxufe.ctdms.enums.DocStateEnum;

/**
 * 课程信息
 */
public class CourseInfo {
    long cId;
    String cName;
    String cCode;
    //大纲状态
    int state;
    // 文件record id
    long recordId;

    public CourseInfo() {
    }

    public CourseInfo(long cId, String cName, String cCode, int state, long recordId) {
        this.cId = cId;
        this.cName = cName;
        this.cCode = cCode;
        this.state = state;
        this.recordId = recordId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if(o instanceof  String){
            if(cCode.equals((String)o))return true;
        }
        if (o == null || getClass() != o.getClass()) return false;

        CourseInfo that = (CourseInfo) o;

        if (!cName.equals(that.cName)) return false;
        return cCode.equals(that.cCode);
    }

    @Override
    public int hashCode() {
        int result = cName.hashCode();
        result = 31 * result + cCode.hashCode();
        return result;
    }

    public CourseInfo(Course c) {
        this.cId = c.getcId();
        this.cName = c.getcName();
        this.cCode = c.getcCode();
        this.state = DocStateEnum.WAIT.getState();
        this.recordId = -1;
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

    public long getcId() {
        return cId;
    }

    public void setcId(long cId) {
        this.cId = cId;
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
}

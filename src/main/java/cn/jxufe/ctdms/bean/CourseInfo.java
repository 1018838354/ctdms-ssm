package cn.jxufe.ctdms.bean;

import cn.jxufe.ctdms.enums.DocStateEnum;

/**
 * 课程信息
 */
public class CourseInfo {
    long ciId;
    String cName;
    String cCode;
    //大纲状态
    int state;
    // 文件record id
    long recordId;

    public CourseInfo() {
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if(o instanceof Course){
            if(cCode.equals(((Course)o).getcCode())) return true;
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
        this.cName = c.getcName();
        this.cCode = c.getcCode();
        this.state = DocStateEnum.WAIT.getState();
        this.recordId = 0;
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

    public String getcCode() {
        return cCode;
    }

    public void setcCode(String cCode) {
        this.cCode = cCode;
    }
}

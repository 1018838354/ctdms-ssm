package cn.jxufe.ctdms.bean;

//教师上传文档 任务
public class UploadTask {
    //任务 id
    long taskId;
    //课程id
    long cId;
    //任务 负责人
    long uId;
    //任务类型，待上传文档类型
    int type;
    //班级代码 : B04
    String classCode;
    //上传记录 id
    long recordId;
    //上传次数
    int times;
    //学期
    String term;
    //文档状态
    int state;

    public UploadTask() {
    }

    public UploadTask(long uId,long cId) {
        this.cId = cId;
        this.uId = uId;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public long getTaskId() {
        return taskId;
    }

    public void setTaskId(long taskId) {
        this.taskId = taskId;
    }

    public long getcId() {
        return cId;
    }

    public void setcId(long cId) {
        this.cId = cId;
    }

    public long getuId() {
        return uId;
    }

    public void setuId(long uId) {
        this.uId = uId;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getClassCode() {
        return classCode;
    }

    public void setClassCode(String classCode) {
        this.classCode = classCode;
    }

    public long getRecordId() {
        return recordId;
    }

    public void setRecordId(long recordId) {
        this.recordId = recordId;
    }

    public int getTimes() {
        return times;
    }

    public void setTimes(int times) {
        this.times = times;
    }
}

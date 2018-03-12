package cn.jxufe.ctdms.dto;

public class UploadTaskDto {
    long taskId ; //id
    int type; //上传文件类型
    int state; //文档状态
    String classCode; //班级代码
    String cName; //课程名

    public UploadTaskDto() {
    }


    public UploadTaskDto(long taskId, int type, int state, String classCode, String cName) {
        this.taskId = taskId;
        this.type = type;
        this.state = state;
        this.classCode = classCode;
        this.cName = cName;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public long getTaskId() {
        return taskId;
    }

    public void setTaskId(long taskId) {
        this.taskId = taskId;
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

    public String getcName() {
        return cName;
    }

    public void setcName(String cName) {
        this.cName = cName;
    }
}

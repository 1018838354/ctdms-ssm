package cn.jxufe.ctdms.bean;

import java.util.Date;

/**
  *  table upload_record
  *  上传文件记录
  */
public class UploadRecord {
    //记录id
    long recordId;
    //用户id
    long uId;
    //任务Id
    long taskId;
    //上传文件类型
    int type;
    //文件url
    String url;
    //文件名
    String docName;
    //上传时间
    Date date;


    public long getRecordId() {
        return recordId;
    }

    public void setRecordId(long recordId) {
        this.recordId = recordId;
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDocName() {
        return docName;
    }

    public void setDocName(String docName) {
        this.docName = docName;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public long getTaskId() {
        return taskId;
    }

    public void setTaskId(long taskId) {
        this.taskId = taskId;
    }
}

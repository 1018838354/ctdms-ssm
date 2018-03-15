package cn.jxufe.ctdms.dto;

import java.util.List;

/**
 * 统计信息返回 DTO
 */
public class StatisticsDto {
    private int fileNums ;                  //文件数

    private int unDoneNums;                 //未完成人数

    private int uploadRate;                 //提交率

    private int doneRate;                   //完成率

    private int reviewPassRate;             //通过率

    //private List<Integer> loginFrequency;   //登录频率
    private int[]loginFrequency;

    public int getFileNums() {
        return fileNums;
    }

    public void setFileNums(int fileNums) {
        this.fileNums = fileNums;
    }

    public int getUnDoneNums() {
        return unDoneNums;
    }

    public void setUnDoneNums(int unDoneNums) {
        this.unDoneNums = unDoneNums;
    }

    public int getUploadRate() {
        return uploadRate;
    }

    public void setUploadRate(int uploadRate) {
        this.uploadRate = uploadRate;
    }

    public int getDoneRate() {
        return doneRate;
    }

    public void setDoneRate(int doneRate) {
        this.doneRate = doneRate;
    }

    public int getReviewPassRate() {
        return reviewPassRate;
    }

    public void setReviewPassRate(int reviewPassRate) {
        this.reviewPassRate = reviewPassRate;
    }

    public int[] getLoginFrequency() {
        return loginFrequency;
    }

    public void setLoginFrequency(int[] loginFrequency) {
        this.loginFrequency = loginFrequency;
    }
}

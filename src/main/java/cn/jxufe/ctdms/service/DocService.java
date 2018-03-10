package cn.jxufe.ctdms.service;

public interface DocService {

    //审核
    public void review(long uId , long cId , String docType ,boolean isPass);
}

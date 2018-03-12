package cn.jxufe.ctdms.bean;

public class Test {
    private long cId;

    private String username;

    public Test(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public long getcId() {

        return cId;
    }

    public void setcId(long cId) {
        this.cId = cId;
    }
}

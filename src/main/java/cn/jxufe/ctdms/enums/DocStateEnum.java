package cn.jxufe.ctdms.enums;

public enum DocStateEnum {
     WAIT(0,"待上传")
    , UPLOAD(2,"已上传")   //待审核
    , FAIL(4,"审核不通过")//需要重新上传
    , PASS_1(8,"通过第一次审核")
    , PASS_2(16,"通过第二次审核")
    , SUCCESS(1024,"完成");

    private int state;

    private String stateInfo;

    private DocStateEnum(int state, String stateInfo) {
        this.state = state;
        this.stateInfo = stateInfo;
    }

    public int getState() {
        return state;
    }

    public String getStateInfo() {
        return stateInfo;
    }

    public static DocStateEnum stateOf(int index) {
        for (DocStateEnum state : values()) {
            if (state.getState() == index) {
                return state;
            }
        }
        return null;
    }
}

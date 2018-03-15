package cn.jxufe.ctdms.exception;

public class PermissionDeniedException extends Exception{
    public static final String DEFAULT_MSG = "用户权限不足";
    public PermissionDeniedException(String message) {
        super(message);
    }
    public PermissionDeniedException() {
        super(DEFAULT_MSG);
    }
}

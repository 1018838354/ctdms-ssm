package cn.jxufe.ctdms.enums;

public enum TableEnum {

    COURSE(202,"course"),
    COURSE_TIME(203,"course_time"),
    UPLOAD_TASK(563,"upload_task"),
    UPLOAD_RECORD(564,"upload_record"),
    USER(100,"user");

    private int tableId;

    private String tableName;

    private TableEnum(int tableId, String tableName) {
        this.tableId = tableId;
        this.tableName = tableName;
    }

    public int getTableId() {
        return tableId;
    }
}

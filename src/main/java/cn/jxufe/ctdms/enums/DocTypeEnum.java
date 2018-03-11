package cn.jxufe.ctdms.enums;

public enum DocTypeEnum {
    TEACH(1,"doc","教学进度表"),
    SYLLABUS(11,"doc","教学大纲");

    private int typeId;
    private String suffix;
    private String typeInfo;


    private DocTypeEnum(int typeId,String suffix, String typeInfo) {
        this.typeId = typeId;
        this.suffix = suffix;
        this.typeInfo = typeInfo;
    }

    public int getTypeId() {
        return typeId;
    }

    public String getSuffix() {
        return suffix;
    }

    public String getTypeInfo() {
        return typeInfo;
    }
}

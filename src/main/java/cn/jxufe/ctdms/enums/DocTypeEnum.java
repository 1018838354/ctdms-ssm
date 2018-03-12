package cn.jxufe.ctdms.enums;

public enum DocTypeEnum {
    TEACH(0,"doc","教学进度表"),
    SYLLABUS(11,"doc","教学大纲"),
    CP(100,"xls","排课计划");
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

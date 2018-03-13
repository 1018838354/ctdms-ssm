package cn.jxufe.ctdms.util;

/**
 * id生成器
 */
public class IDGenerator {
    private static SnowflakeIdWorker ourInstance;

    public static long nextId() {
        if(ourInstance == null){
            ourInstance = new SnowflakeIdWorker(1,1);
        }
        return ourInstance.nextId();
    }

    private IDGenerator() {
    }
}

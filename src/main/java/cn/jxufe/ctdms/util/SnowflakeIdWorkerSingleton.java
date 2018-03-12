package cn.jxufe.ctdms.util;

public class SnowflakeIdWorkerSingleton {
    private static SnowflakeIdWorker ourInstance;

    public static SnowflakeIdWorker getInstance() {
        if(ourInstance == null){
            ourInstance = new SnowflakeIdWorker(1,1);
        }
        return ourInstance;
    }

    private SnowflakeIdWorkerSingleton() {
    }
}

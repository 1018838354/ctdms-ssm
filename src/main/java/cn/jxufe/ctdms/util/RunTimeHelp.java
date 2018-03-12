package cn.jxufe.ctdms.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RunTimeHelp {
    private long start;
    private static final Logger log = LoggerFactory.getLogger(RunTimeHelp.class);

    public RunTimeHelp() {
        this.start = 0;
    }

    public void start(){
        start = System.currentTimeMillis();
    }

    public long end(String msg){
        long time = System.currentTimeMillis() - start;
        log.info(msg +": "+time/1000 +"s");
        return time;
    }
}

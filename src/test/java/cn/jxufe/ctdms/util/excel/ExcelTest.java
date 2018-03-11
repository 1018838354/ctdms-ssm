package cn.jxufe.ctdms.util.excel;

import org.junit.Before;
import org.junit.Test;

public class ExcelTest {

    @Before
    public void init() {
    }
    @Test
    public void parse(){
        System.out.println(ExcelParse
                .parse
                        ("/Users/ye2moe/Downloads/软通学院本科162学期课表_撤班后.xls",MyExcelCourse.class)
 );
    }
}

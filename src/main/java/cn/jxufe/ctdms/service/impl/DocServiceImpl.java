package cn.jxufe.ctdms.service.impl;

import cn.jxufe.ctdms.service.DocService;
import cn.jxufe.ctdms.util.excel.ExcelOperation;
import cn.jxufe.ctdms.util.excel.ExcelParse;
import cn.jxufe.ctdms.util.excel.MyExcelCourse;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class DocServiceImpl implements DocService{






    @Override
    public void cp(MultipartFile file) throws IOException {
        //解析excels
        List<MyExcelCourse> excels = ExcelParse.parse(file.getBytes(),file.getOriginalFilename(), MyExcelCourse.class);
        //检查当前学期是否已上传，已上传则清空之前课程并覆盖更新。
        if(checkTermHasCp()){
            deleteNowCp();
        }
        //导入课程，新建老师.
        importCourse(excels);
        createTeacher(excels);
        //引用置空 ，让虚拟机gc回收。
        excels = null;
    }

    private void importCourse(List<MyExcelCourse> excels) {

    }

    private void createTeacher(List<MyExcelCourse> excels) {

    }

    private boolean checkTermHasCp() {
        return true;
    }

    private void deleteNowCp() {
    }




}

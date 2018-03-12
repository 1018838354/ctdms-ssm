package cn.jxufe.ctdms.controller;

import cn.jxufe.ctdms.dto.CourseDto;
import cn.jxufe.ctdms.dto.Result;
import cn.jxufe.ctdms.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author pxf
 * 返回课程表信息
 */

@Controller
public class CourseController {

    @Autowired
    private CourseService courseService;

    @GetMapping("/{uId}/courses")
    @ResponseBody
    public Result<List<CourseDto>> findByUId(@PathVariable("uId") long uId){
        Result<List<CourseDto>> result = new  Result<List<CourseDto>>();
        List<CourseDto> cts = courseService.getCourseDto(uId);
        result.setData(cts);
        result.setSuccess(true);
        return  result;
    }
}

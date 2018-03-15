package cn.jxufe.ctdms.controller;

import cn.jxufe.ctdms.dto.CourseDto;
import cn.jxufe.ctdms.dto.Result;
import cn.jxufe.ctdms.dto.UploadTaskDto;
import cn.jxufe.ctdms.exception.PermissionDeniedException;
import cn.jxufe.ctdms.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
public class CourseController {

    @Autowired
    private CourseService courseService;
    /**
     * @author pxf
     * 返回课程表信息
     */
    @GetMapping("/{uId}/courses")
    @ResponseBody
    public Result<List<CourseDto>> findByUId(@PathVariable("uId") long uId){
        Result<List<CourseDto>> result = new  Result<List<CourseDto>>();
        List<CourseDto> cts = courseService.getCourseDto(uId);
        result.setData(cts);
        result.setSuccess(true);
        return  result;
    }


    /**
     * 教师上传任务 url
     * ye2moe
     * @param uId
     * @return
     */
    @GetMapping("/{uId}/tasks")
    @ResponseBody
    public Result<List<UploadTaskDto>> tasks(@PathVariable("uId") long uId){
        List<UploadTaskDto> utd = courseService.getTasks(uId);

        return new Result<>(true,utd);
    }





    @PostMapping("/{uId}/review/{id}/{type}/{isPass}")
    @ResponseBody
    public Result<String> review(@PathVariable("uId") long uId, @PathVariable("id") long id
            ,@PathVariable("type") int type,@PathVariable("isPass")int isPass){
        try {
            courseService.review(uId,id,type,isPass==1);
        } catch (PermissionDeniedException e) {
            return new Result<>(false,e.getMessage());
        }
        return new Result<>(true,"审核成功");
    }

}

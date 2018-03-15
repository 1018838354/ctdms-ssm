package cn.jxufe.ctdms.controller;

import cn.jxufe.ctdms.bean.User;
import cn.jxufe.ctdms.dto.Result;
import cn.jxufe.ctdms.dto.StatisticsDto;
import cn.jxufe.ctdms.service.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.websocket.server.PathParam;
import java.util.List;

/**
 *  统计信息  controller
 *
 */
@Controller
public class StatisticsController {

    @Autowired
    StatisticsService statisticsService;

    @GetMapping("/{uId}/statistics")
    @ResponseBody
    public Result statistics(@PathParam("uId")Integer uId) {

        return new Result(true,statisticsService.statistics());
    }
}

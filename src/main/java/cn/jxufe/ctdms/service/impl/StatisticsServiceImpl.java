package cn.jxufe.ctdms.service.impl;

import cn.jxufe.ctdms.dao.CourseDao;
import cn.jxufe.ctdms.dto.StatisticsDto;
import cn.jxufe.ctdms.enums.DocStateEnum;
import cn.jxufe.ctdms.service.CourseService;
import cn.jxufe.ctdms.service.DocService;
import cn.jxufe.ctdms.service.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class StatisticsServiceImpl implements StatisticsService {
    @Autowired
    DocService docService;
    @Autowired
    CourseDao courseDao;
    @Autowired
    CourseService courseService;
    @Override
    public StatisticsDto statistics() {
        StatisticsDto statisticsDto = new StatisticsDto();
        statisticsDto.setFileNums(docService.getFileCount());

        statisticsDto.setUnDoneNums(courseService.getUnDoneNumsByState(DocStateEnum.SUCCESS.getState()));
        int uploadNums  = courseDao.getCourseNumsGEThenState(DocStateEnum.UPLOAD.getState());
        int totalNums = courseDao.getCourseNums();
        statisticsDto.setUploadRate( uploadNums * 100 /totalNums );

        int doneNums = courseDao.getCourseNumsGEThenState(DocStateEnum.SUCCESS.getState());
        statisticsDto.setDoneRate(doneNums*100 / totalNums);

        int reviewPassNums =  courseDao.getCourseNumsGEThenState(DocStateEnum.PASS_1.getState());
        statisticsDto.setReviewPassRate(reviewPassNums*100/totalNums);

        int []loginFrequency = new int[7];
        Random random = new Random(System.currentTimeMillis());
        for(int i=0;i< loginFrequency.length;i++){
            loginFrequency[i]=random.nextInt(50)+15;
        }
        statisticsDto.setLoginFrequency(loginFrequency);


        return statisticsDto;
    }
}

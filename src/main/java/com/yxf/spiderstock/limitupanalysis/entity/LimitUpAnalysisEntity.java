package com.yxf.spiderstock.limitupanalysis.entity;

import lombok.Data;
import lombok.ToString;

import java.util.Date;

/**
 * 涨停复盘 实体类
 * @author yxf
 */
@Data
public class LimitUpAnalysisEntity {

    private String date;

    private String title;

    private String content;

    private String url;

    private String picPath;

    private Date spiderDate;

}

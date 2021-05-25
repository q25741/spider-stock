package com.yxf.spiderstock.dailylimit.entity;

import java.util.Date;

/**
 *
 * 涨停复盘实体类
 * @author yangxiangfeng
 * @date 2021/5/25 5:24 下午
 */
public class DailyLimitEntity {

    /** 股票代码 */
    private String code;

    /** 股票名称 */
    private String name;

    /** 连板高度 */
    private Integer height;

    /** 年内换手率 */
    private Double turnoverRate;

    /** 年内换手率时间 */
    private Date turnoverRateDate;

    /** 60% */
    private Double sixtyPercent;

    /** 70% */
    private Double seventyPercent;

    /** 80% */
    private Double eightyPercent;

    /** 龙虎榜 */
    private String longhubang;

    /** 观察 */
    private String observed;
}

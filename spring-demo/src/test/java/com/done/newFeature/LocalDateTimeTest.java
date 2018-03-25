package com.done.newFeature;

import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;


/**
 * Created by Done Lin on 2017/12/17.
 */
//@Log4j
@Slf4j
public class LocalDateTimeTest {

    @Test
    public void testLocalDate() {
        LocalDate today = LocalDate.now();
        LocalDate yestoday = LocalDate.of(2017, 12, 16);
        LocalDate tomorry = LocalDate.parse("2017-12-18");
        LocalDate twoDaysAge = LocalDate.parse("2017/12/15", DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        log.info("todaty=" + today + " , yestoday=" + yestoday + " ,tomorry=" + tomorry + " ,twoDaysAge=" + twoDaysAge);


        // 取本月第1天：
        LocalDate firstDayOfThisMonth = today.with(TemporalAdjusters.firstDayOfMonth());
        // 取本月第2天：
        LocalDate secondDayOfThisMonth = today.withDayOfMonth(2);
        // 取本月最后一天，再也不用计算是28，29，30还是31：
        LocalDate lastDayOfThisMonth = today.with(TemporalAdjusters.lastDayOfMonth());
        // 取下一天：
        LocalDate nextDay = today.plusDays(1);
        LocalDate lastfiveDay =  today.plusDays(-5);
        // 取2015年1月第一个周一，这个计算用Calendar要死掉很多脑细胞：
        LocalDate firstMondayOf2015 = LocalDate.parse("2015-01-01").with(TemporalAdjusters.firstInMonth(DayOfWeek.MONDAY));
        log.info("firstDayOfThisMonth=" + firstDayOfThisMonth +
                " , secondDayOfThisMonth=" + secondDayOfThisMonth +
                " ,lastDayOfThisMonth=" + lastDayOfThisMonth +
                " ,nextDay=" + nextDay +
                " ,lastfiveDay=" + lastfiveDay +
                " ,firstMondayOf2015=" + firstMondayOf2015);
    }



    @Test
    public void testLocalTime(){
        LocalTime now = LocalTime.now();
        LocalTime mid = LocalTime.parse("12:00:00.123");
        log.info("now="+now+" , mid="+mid);
    }



    @Test
    public void testLocalDateTime(){
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime mid = LocalDateTime.parse("2019-02-12T02:09:00.123");
        log.info("now="+now+" , mid="+mid);
    }
}

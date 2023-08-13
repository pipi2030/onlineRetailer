package com.neutech.Utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class StringUtils {
    public static String convertFormat(Date date){
        Date now = new Date();
        //该时间距离现在有多少秒
        Long seconds = (now.getTime() - date.getTime()) / 1000;
        if(seconds < 60){
            return "刚刚";
        }else if(seconds < 60 * 60){
            return seconds / 60 + "分钟前发布";
        }else if(seconds < 60 * 60 * 3){
            return seconds / (60 * 60) + "小时前发布";
        }
        Calendar today = Calendar.getInstance();
        //获取今天凌晨的时间
        today.set(Calendar.YEAR,Calendar.MONTH,Calendar.DAY_OF_MONTH,0,0,0);
        Calendar dateCal = Calendar.getInstance();
        dateCal.setTime(date);
        if(today.before(date)){
            return "今日" + dateCal.HOUR +  "时" + dateCal.MINUTE +"分" + dateCal.SECOND +  "秒发布";
        }else {
            DateFormat df = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分");
            return df.format(date);
        }
    }
}

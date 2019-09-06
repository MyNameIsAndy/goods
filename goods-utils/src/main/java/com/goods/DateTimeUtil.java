package com.goods;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class DateTimeUtil {
    public static int DATE_FORMAT = 2;
    public static Locale DATE_LOCALE;

    public DateTimeUtil() {
    }
    /**
     * 字符串转换成日期
     * @param str
     * @return date  create by lyy 2017-10-09
     */
    public static Date strToDateMessage(String str) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String strDate=  sdf.format(new Date(Long.valueOf(str).longValue()));
        Date date = null;
        try {
            date = sdf.parse(strDate);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return date;
    }


    public static String dateToMillis(Date date) {
        return StringUtil.zeroPadString(Long.toString(date.getTime()), 15);
    }

    public static String getCurrentTimeStamp() {
        DateFormat df = DateFormat.getDateTimeInstance(0, 1, new Locale("zh", "CN"));
        return df.format(new Date(System.currentTimeMillis()));
    }

    public static int compareTo(Timestamp st1, Timestamp st2) {
        long thisTime = st1 != null?st1.getTime():0L;
        long anotherTime = st2 != null?st2.getTime():0L;
        return thisTime < anotherTime?-1:(thisTime == anotherTime?0:1);
    }

    public static String toDateTimeString(Timestamp ts) {
        return ts == null?"":DateFormat.getDateTimeInstance(1, 2, Locale.CHINA).format(ts);
    }

    public static String toDateString(Timestamp ts) {
        return ts == null?"":DateFormat.getDateInstance(1, Locale.CHINA).format(ts);
    }
    public static Date strToDateLong(String strDate) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        ParsePosition pos = new ParsePosition(0);
        Date strtodate = formatter.parse(strDate, pos);
        return strtodate;
    }
    public static String toDateString(Timestamp ts, String format) {
        if(ts == null) {
            return "";
        } else {
            DateFormat df = new SimpleDateFormat(format);
            return df.format(ts);
        }
    }

    public static String toTimeString(Timestamp ts) {
        return ts == null?"":DateFormat.getTimeInstance(1, Locale.CHINA).format(ts);
    }

    public static long nowMillis() {
        return System.currentTimeMillis();
    }

    public static Timestamp nowTimestamp() {
        return new Timestamp(System.currentTimeMillis());
    }

    public static Date nowDate() {
        return new Date();
    }

    public static Timestamp getDayStart(Timestamp stamp) {
        return getDayStart(stamp, 0);
    }

    public static Timestamp getDayStart(Timestamp stamp, int daysLater) {
        Calendar tempCal = Calendar.getInstance();
        tempCal.setTime(new Date(stamp.getTime()));
        tempCal.set(tempCal.get(1), tempCal.get(2), tempCal.get(5), 0, 0, 0);
        tempCal.add(5, daysLater);
        return new Timestamp(tempCal.getTime().getTime());
    }

    public static Timestamp getNextDayStart(Timestamp stamp) {
        return getDayStart(stamp, 1);
    }

    public static Timestamp getDayEnd(Timestamp stamp) {
        return getDayEnd(stamp, 0);
    }

    public static Timestamp getDayEnd(Timestamp stamp, int daysLater) {
        Calendar tempCal = Calendar.getInstance();
        tempCal.setTime(new Date(stamp.getTime()));
        tempCal.set(tempCal.get(1), tempCal.get(2), tempCal.get(5), 23, 59, 59);
        tempCal.add(5, daysLater);
        return new Timestamp(tempCal.getTime().getTime());
    }

    public static Timestamp toTimestamp(String dateString) {
        if(dateString != null && dateString.trim().length() != 0) {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

            try {
                Date date = format.parse(dateString.trim());
                return new Timestamp(date.getTime());
            } catch (ParseException var3) {
                return null;
            }
        } else {
            return null;
        }
    }

    public static Timestamp parseTime(String dateString, String timeFormat) {
        if(dateString != null && dateString.trim().length() != 0) {
            SimpleDateFormat format = new SimpleDateFormat(timeFormat);

            try {
                Date date = format.parse(dateString.trim());
                return new Timestamp(date.getTime());
            } catch (ParseException var4) {
                return null;
            }
        } else {
            return null;
        }
    }

    public static Date parseDate(String dateString, String timeFormat) {
        if(dateString != null && dateString.trim().length() != 0) {
            SimpleDateFormat format = new SimpleDateFormat(timeFormat);

            try {
                Date date = format.parse(dateString.trim());
                return date;
            } catch (ParseException var4) {
                return null;
            }
        } else {
            return null;
        }
    }

    public static String timestampDiff(Timestamp st1, Timestamp st2) {
        int count = timestampDiff(5, st1, st2);
        return count < 0?null:count + " 天";
    }

    public static int timestampDiff(int unit, Timestamp st1, Timestamp st2) {
        if(st1 != null && st2 != null) {
            Calendar g1 = Calendar.getInstance();
            Calendar g2 = Calendar.getInstance();
            g1.setTime(st1);
            g2.setTime(st2);
            Calendar gc1;
            Calendar gc2;
            if(g2.after(g1)) {
                gc2 = (Calendar)g2.clone();
                gc1 = (Calendar)g1.clone();
            } else {
                gc2 = (Calendar)g1.clone();
                gc1 = (Calendar)g2.clone();
            }

            if(5 == unit) {
                gc1.clear(14);
                gc1.clear(13);
                gc1.clear(12);
                gc1.clear(11);
                gc2.clear(14);
                gc2.clear(13);
                gc2.clear(12);
                gc2.clear(11);
            } else if(10 == unit) {
                gc1.clear(14);
                gc1.clear(13);
                gc1.clear(12);
                gc2.clear(14);
                gc2.clear(13);
                gc2.clear(12);
            } else if(12 == unit) {
                gc1.clear(14);
                gc1.clear(13);
                gc2.clear(14);
                gc2.clear(13);
            } else if(2 == unit) {
                gc1.clear(14);
                gc1.clear(13);
                gc1.clear(12);
                gc1.clear(11);
                gc1.clear(5);
                gc2.clear(14);
                gc2.clear(13);
                gc2.clear(12);
                gc2.clear(11);
                gc2.clear(5);
            } else if(1 == unit) {
                gc1.clear(14);
                gc1.clear(13);
                gc1.clear(12);
                gc1.clear(11);
                gc1.clear(5);
                gc1.clear(2);
                gc2.clear(14);
                gc2.clear(13);
                gc2.clear(12);
                gc2.clear(11);
                gc2.clear(5);
                gc2.clear(2);
            } else {
                if(13 != unit) {
                    return 0;
                }

                gc1.clear(14);
                gc2.clear(14);
            }

            int elapsed;
            for(elapsed = 0; gc1.before(gc2); ++elapsed) {
                gc1.add(unit, 1);
            }

            return elapsed;
        } else {
            return -1;
        }
    }

    public static String formatDatetime(Date aDate) {
        if(aDate == null) {
            return "";
        } else {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(aDate);
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String s = df.format(calendar.getTime());
            return s;
        }
    }

    public static Date stringToDate(String date) {
        Date ret = null;
        SimpleDateFormat df = null;
        if(date.length() <= 10) {
            df = new SimpleDateFormat("yyyy-MM-dd", DATE_LOCALE);
        } else {
            df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", DATE_LOCALE);
        }

        ret = df.parse(date, new ParsePosition(0));
        return ret;
    }

    public static String getDatesInterval(Date startDate, Date endDate) throws Exception {
        String start = formatDatetime(startDate);
        String end = formatDatetime(endDate);
        startDate = stringToDate(start);
        endDate = stringToDate(end);
        String interval = "";
        long miSecondInterval = endDate.getTime() - startDate.getTime();
        if(miSecondInterval < 0L) {
            throw new Exception("开始时间不能在结束时间之后! endDate.getTime() = " + endDate.getTime() + "; startDate.getTime() = " + startDate.getTime() + "; miSecondInterval = " + miSecondInterval);
        } else {
            long ddInterval = miSecondInterval / 86400000L;
            long left = miSecondInterval % 86400000L;
            long hhInterval = left / 3600000L;
            left %= 3600000L;
            long mmInterval = left / 60000L;
            left %= 60000L;
            long ssInterval = left / 1000L;
            if(ddInterval != 0L) {
                interval = interval + ddInterval + "天";
            }

            if(hhInterval != 0L || interval.length() != 0) {
                interval = interval + hhInterval + "小时";
            }

            if(mmInterval != 0L || interval.length() != 0) {
                interval = interval + mmInterval + "分";
            }

            interval = interval + ssInterval + "秒";
            return interval;
        }
    }

    public static String formatDate(String s, int iType, int iLanguage) {
        String _tempStr = null;
        s = s.trim();
        if(s.equals("")) {
            return "";
        } else {
            String sYear = s.substring(0, 4);
            String sMonth = s.substring(4, 6);
            String sDay = s.substring(6, 8);
            String sHours = s.substring(8, 10);
            String sMinutes = s.substring(10, 12);
            String sSeconds = s.substring(12, 14);
            switch(iType) {
                case 0:
                    if(iLanguage == 0) {
                        _tempStr = sYear + "-" + sMonth + "-" + sDay;
                    } else {
                        _tempStr = sYear + "年";
                        if(!sMonth.equals("00")) {
                            _tempStr = _tempStr + sMonth + "月";
                        }

                        if(!sDay.equals("00")) {
                            _tempStr = _tempStr + sDay + "日";
                        }
                    }
                    break;
                case 1:
                    if(iLanguage == 0) {
                        _tempStr = sYear + "-" + sMonth + "-" + sDay + " " + sHours;
                    } else {
                        _tempStr = sYear + "年";
                        if(!sMonth.equals("00")) {
                            _tempStr = _tempStr + sMonth + "月";
                        }

                        if(!sDay.equals("00")) {
                            _tempStr = _tempStr + sDay + "日";
                        }

                        if(!sHours.equals("00")) {
                            _tempStr = _tempStr + sHours + "时";
                        }
                    }
                    break;
                case 2:
                    if(iLanguage == 0) {
                        _tempStr = sYear + "-" + sMonth + "-" + sDay + " " + sHours + ":" + sMinutes;
                    } else {
                        _tempStr = sYear + "年";
                        if(!sMonth.equals("00")) {
                            _tempStr = _tempStr + sMonth + "月";
                        }

                        if(!sDay.equals("00")) {
                            _tempStr = _tempStr + sDay + "日";
                        }

                        if(!sHours.equals("00")) {
                            _tempStr = _tempStr + sHours + "时";
                        }

                        if(!sMinutes.equals("00")) {
                            _tempStr = _tempStr + sMinutes + "分";
                        }
                    }
                    break;
                case 3:
                    if(iLanguage == 0) {
                        _tempStr = sYear + "-" + sMonth + "-" + sDay + " " + sHours + ":" + sMinutes + ":" + sSeconds;
                    } else {
                        _tempStr = sYear + "年";
                        if(!sMonth.equals("00")) {
                            _tempStr = _tempStr + sMonth + "月";
                        }

                        if(!sDay.equals("00")) {
                            _tempStr = _tempStr + sDay + "日";
                        }

                        if(!sHours.equals("00")) {
                            _tempStr = _tempStr + sHours + "时";
                        }

                        if(!sMinutes.equals("00")) {
                            _tempStr = _tempStr + sMinutes + "分";
                        }

                        if(!sSeconds.equals("00")) {
                            _tempStr = _tempStr + sSeconds + "秒";
                        }
                    }
            }

            return _tempStr;
        }
    }

    public static int getLastDate(String selectDate) {
        int dates = 0;
        Calendar calendar = Calendar.getInstance();
        DateFormat dateFormat = DateFormat.getDateInstance(DATE_FORMAT, DATE_LOCALE);

        try {
            calendar.setTime(dateFormat.parse(selectDate));
        } catch (ParseException var6) {
            System.err.println("catch exception in getLastDate(selectDate). select date:" + selectDate);
        }

        int year = calendar.get(1);
        switch(calendar.get(2) + 1) {
            case 1:
                dates = 31;
                break;
            case 2:
                if(year % 400 != 0 && (year % 4 != 0 || year % 100 == 0)) {
                    dates = 28;
                } else {
                    dates = 29;
                }
                break;
            case 3:
                dates = 31;
                break;
            case 4:
                dates = 30;
                break;
            case 5:
                dates = 31;
                break;
            case 6:
                dates = 30;
                break;
            case 7:
                dates = 31;
                break;
            case 8:
                dates = 31;
                break;
            case 9:
                dates = 30;
                break;
            case 10:
                dates = 31;
                break;
            case 11:
                dates = 30;
                break;
            case 12:
                dates = 31;
        }

        return dates;
    }

    public static String getLastDateOfMonth() {
        return getCurrentYear() + "-" + getCurrentMonth() + "-" + getLastDate(getCurrentDate());
    }

    public static String getFirstDateOfMonth() {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-");
        String s = df.format(new Date());
        s = s + "01";
        return s;
    }

    public static String getLastDateOfLastMonth() {
        DateFormat dateFormat = DateFormat.getDateInstance(DATE_FORMAT, DATE_LOCALE);
        Date uDate = null;

        try {
            uDate = dateFormat.parse(getFirstDateOfMonth());
        } catch (ParseException var5) {
            System.err.println("Catch Exception in getLastDateOfLastMonth of DateUtil.");
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(uDate);
        calendar.add(5, -1);
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String s = df.format(calendar.getTime());
        return s;
    }

    public static String getNextDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(5, 1);
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String s = df.format(calendar.getTime());
        return s;
    }

    public static String getPreviousDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(5, -1);
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String s = df.format(calendar.getTime());
        return s;
    }

    public static String getCurrentMonth() {
        SimpleDateFormat df = new SimpleDateFormat("MM");
        String month = df.format(new Date());
        return month;
    }

    public static String getCurrentYear() {
        SimpleDateFormat df = new SimpleDateFormat("yyyy");
        return df.format(new Date());
    }

    public static String getCurrentHour() {
        SimpleDateFormat df = new SimpleDateFormat("HH");
        String hour = df.format(new Date());
        return hour;
    }

    public static java.sql.Date getCurrentSqlDate(int iType) {
        String format = null;
        switch(iType) {
            case 0:
                format = "yyyy-MM-dd";
                break;
            case 1:
                format = "yyyy-MM-dd HH";
                break;
            case 2:
                format = "yyyy-MM-dd HH:mm";
                break;
            case 3:
            case 4:
            case 5:
                format = "yyyy-MM-dd HH:mm:ss";
        }

        return getSqlDate(getCurrentDate(iType), format);
    }

    public static java.sql.Date getSqlDate(String dateStr, String format) {
        DateFormat df = new SimpleDateFormat(format);
        java.sql.Date date = null;

        try {
            if(dateStr != null && dateStr.trim().length() > 0) {
                date = new java.sql.Date(df.parse(dateStr).getTime());
            }
        } catch (ParseException var5) {
            System.err.println("the given string cannot be parsed as a date : " + dateStr + "|" + format);
        }

        return date;
    }

    public static String getCurrentDate() {
        return getCurrentDate(0);
    }

    public static String getCurrentDate(int iType) {
        SimpleDateFormat df = null;
        switch(iType) {
            case 0:
                df = new SimpleDateFormat("yyyy-MM-dd");
                break;
            case 1:
                df = new SimpleDateFormat("yyyy-MM-dd HH");
                break;
            case 2:
                df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                break;
            case 3:
                df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                break;
            case 4:
                long currentTimeMillis = System.currentTimeMillis();
                Timestamp ts = new Timestamp(currentTimeMillis);
                return ts.toString();
        }

        return df.format(new Date());
    }

    public static String getDay(Timestamp timestamp) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(timestamp);
        return String.valueOf(calendar.get(5));
    }

    public static String getPeriod(Timestamp timestamp) {
        int day = Integer.parseInt(getDay(timestamp));
        return day >= 1 && day <= 10?"1":(day >= 11 && day <= 20?"2":"3");
    }

    public static String getQuarterByDay(String sDate1) {
        String quarter = "";
        DateFormat dateFormat = DateFormat.getDateInstance(DATE_FORMAT, DATE_LOCALE);
        Date date1 = null;

        try {
            date1 = dateFormat.parse(sDate1);
        } catch (ParseException var6) {
            System.err.println("Catch Exception in getQuarterByDay(sDate1) sDate1:" + sDate1);
            return "";
        }

        Calendar mydate = Calendar.getInstance();
        mydate.setTime(date1);
        Double dd = new Double(Math.floor((double)(mydate.get(2) / 3)));
        switch(dd.intValue()) {
            case 0:
                quarter = "1";
                break;
            case 1:
                quarter = "2";
                break;
            case 2:
                quarter = "3";
                break;
            case 3:
                quarter = "4";
        }

        return quarter;
    }

    public static String getQuarter(Timestamp timestamp) {
        String quarter = null;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(timestamp);
        int month = calendar.get(2) + 1;
        if(month != 1 && month != 2 && month != 3) {
            if(month != 4 && month != 5 && month != 6) {
                if(month != 7 && month != 8 && month != 9) {
                    if(month == 10 || month == 11 || month == 12) {
                        quarter = "4";
                    }
                } else {
                    quarter = "3";
                }
            } else {
                quarter = "2";
            }
        } else {
            quarter = "1";
        }

        return quarter;
    }

    public static String[] getStartEndDates(String year, String quarter, String month) {
        String[] ret = new String[]{year, year};
        if(quarter.equals("")) {
            ret[0] = ret[0] + "-01-01";
            ret[1] = ret[1] + "-12-31";
        } else if(month.equals("")) {
            switch(Integer.parseInt(quarter)) {
                case 1:
                    ret[0] = ret[0] + "-01-" + getStartEndDates(year, "1")[0];
                    ret[1] = ret[1] + "-03-" + getStartEndDates(year, "3")[1];
                    break;
                case 2:
                    ret[0] = ret[0] + "-04-" + getStartEndDates(year, "4")[0];
                    ret[1] = ret[1] + "-06-" + getStartEndDates(year, "6")[1];
                    break;
                case 3:
                    ret[0] = ret[0] + "-07-" + getStartEndDates(year, "7")[0];
                    ret[1] = ret[1] + "-09-" + getStartEndDates(year, "9")[1];
                    break;
                case 4:
                    ret[0] = ret[0] + "-10-" + getStartEndDates(year, "10")[0];
                    ret[1] = ret[1] + "-12-" + getStartEndDates(year, "12")[1];
            }
        } else {
            ret[0] = year + "-" + month + "-" + getStartEndDates(year, month)[0];
            ret[1] = year + "-" + month + "-" + getStartEndDates(year, month)[1];
        }

        return ret;
    }

    public static String[] getStartEndDates(String year, String month) {
        String[] ret = new String[2];
        int iYear = Integer.parseInt(year);
        int iMonth = Integer.parseInt(month);
        switch(iMonth) {
            case 1:
                ret[0] = "1";
                ret[1] = "31";
                break;
            case 2:
                if((iYear % 400 == 0 || iYear % 4 == 0) && iYear % 100 != 0) {
                    ret[0] = "1";
                    ret[1] = "29";
                } else {
                    ret[0] = "1";
                    ret[1] = "28";
                }
                break;
            case 3:
                ret[0] = "1";
                ret[1] = "31";
                break;
            case 4:
                ret[0] = "1";
                ret[1] = "30";
                break;
            case 5:
                ret[0] = "1";
                ret[1] = "31";
                break;
            case 6:
                ret[0] = "1";
                ret[1] = "30";
                break;
            case 7:
                ret[0] = "1";
                ret[1] = "31";
                break;
            case 8:
                ret[0] = "1";
                ret[1] = "31";
                break;
            case 9:
                ret[0] = "1";
                ret[1] = "30";
                break;
            case 10:
                ret[0] = "1";
                ret[1] = "31";
                break;
            case 11:
                ret[0] = "1";
                ret[1] = "30";
                break;
            case 12:
                ret[0] = "1";
                ret[1] = "31";
        }

        return ret;
    }

    public static int getCurrentWeekDay() {
        Calendar c = Calendar.getInstance();
        int num = c.get(7);
        int weekDayNum;
        if(num == 1) {
            weekDayNum = 7;
        } else {
            weekDayNum = num - 1;
        }

        return weekDayNum;
    }

    /**
     * 计算开日期与结束日期的年差
     * @param startDateStr
     * @param endDateStr
     * @return
     */
    public static int getYearsInterval(String startDateStr,String endDateStr){
        Date startDate = stringToDate(startDateStr);
        Date endDate = stringToDate(endDateStr);
        if (startDate.after(endDate)) {
            Date t = startDate;
            startDate = endDate;
            endDate = t;
        }
        Calendar startCalendar = Calendar.getInstance();
        startCalendar.setTime(startDate);
        Calendar endCalendar = Calendar.getInstance();
        endCalendar.setTime(endDate);
        Calendar temp = Calendar.getInstance();
        temp.setTime(endDate);
        temp.add(Calendar.DATE, 1);

        int year = endCalendar.get(Calendar.YEAR)
                - startCalendar.get(Calendar.YEAR);
        int month = endCalendar.get(Calendar.MONTH)
                - startCalendar.get(Calendar.MONTH);
        if(month > 0){
            year = year + 1;
        }
        return year;
    }

    /**
     * 计算开日期与结束日期的年差
     * @param startDateStr
     * @return
     */
    public static int getCurrentYearsInterval(String startDateStr){
        String currentDateStr = getCurrentDate();
        return getYearsInterval(startDateStr,currentDateStr);
    }


    /**
     * 获得两个日期之前相差的天数
     * @param startStr
     * @param endStr
     * @return
     */
    public static int getDaysInterval(String startStr, String endStr) {
        Date startDate = stringToDate(startStr);
        Date endDate = stringToDate(endStr);
        if (startDate.after(endDate)) {
            Date t = startDate;
            startDate = endDate;
            endDate = t;
        }
        Calendar startCalendar = Calendar.getInstance();
        startCalendar.setTime(startDate);
        Calendar endCalendar = Calendar.getInstance();
        endCalendar.setTime(endDate);
        int days = (int)((endCalendar.getTimeInMillis()-startCalendar.getTimeInMillis())/(3600L*1000*24));
        return days;
    }
    /**
     * 获得两个日期之间相差的 type 1:天、2:时、3:分、4秒
     * @param startStr
     * @param endStr
     * @return
     */
    public static int getTimesInterval(String startStr, String endStr,int type) {
        Date startDate = stringToDate(startStr);
        Date endDate = stringToDate(endStr);
        if (startDate.after(endDate)) {
            Date t = startDate;
            startDate = endDate;
            endDate = t;
        }
        Calendar startCalendar = Calendar.getInstance();
        startCalendar.setTime(startDate);
        Calendar endCalendar = Calendar.getInstance();
        endCalendar.setTime(endDate);
        Long intervals=endCalendar.getTimeInMillis()-startCalendar.getTimeInMillis();
        int times=0;
        if(type==1){
            times=(int)((intervals)/(3600L*1000*24));
        }else if(type==2){
            times=(int)((intervals)/(3600L*1000));
        }else if(type==3){
            times=(int)((intervals)/(60L*1000));
        }else if(type==4){
            times=(int)((intervals)/(1L*1000));
        }
        return times;
    }
    /**
     * 获得两个日期之前相差的月份
     * @param startStr
     * @param endStr
     * @return
     */
    public static int getMonthsInterval(String startStr, String endStr) {
        Date startDate = stringToDate(startStr);
        Date endDate = stringToDate(endStr);
        if (startDate.after(endDate)) {
            Date t = startDate;
            startDate = endDate;
            endDate = t;
        }
        Calendar startCalendar = Calendar.getInstance();
        startCalendar.setTime(startDate);
        Calendar endCalendar = Calendar.getInstance();
        endCalendar.setTime(endDate);
        Calendar temp = Calendar.getInstance();
        temp.setTime(endDate);
        temp.add(Calendar.DATE, 1);

        int year = endCalendar.get(Calendar.YEAR)
                - startCalendar.get(Calendar.YEAR);
        int month = endCalendar.get(Calendar.MONTH)
                - startCalendar.get(Calendar.MONTH);

        if ((startCalendar.get(Calendar.DATE) == 1)
                && (temp.get(Calendar.DATE) == 1)) {
            return year * 12 + month + 1;
        } else if ((startCalendar.get(Calendar.DATE) != 1)
                && (temp.get(Calendar.DATE) == 1)) {
            return year * 12 + month;
        } else if ((startCalendar.get(Calendar.DATE) == 1)
                && (temp.get(Calendar.DATE) != 1)) {
            return year * 12 + month;
        } else {
            return (year * 12 + month - 1) < 0 ? 0 : (year * 12 + month);
        }
    }

    /**
     * 计算开始日期days天之后的日期
     * @param startTime 开始日期
     * @param days 天数
     * @param format 格式
     * @author apple
     * @date 2012-04-24
     * @return
     */
    public static String getAddDates(Timestamp startTime,int days,String format){
        if(startTime != null){
            Timestamp endTime = getDayStart(startTime,days-1);
            return toDateString(endTime,format);
        }else{
            return "";
        }
    }

    /**
     * 计算开始日期days天之后的日期
     *
     * @param startDate
     *            开始日期
     * @param days
     *            天数
     * @param format
     *            格式
     * @author apple
     * @date 2012-04-24
     * @return
     */
    public static String getAddDates(String startDate, int days, String format) {
        if (startDate != null) {
            Timestamp startTime = toTimestamp(startDate);
            Timestamp endTime = getDayStart(startTime, days-1);
            return toDateString(endTime, format);
        } else {
            return "";
        }
    }
    /**
     * 获取指定N天前/后的时间
     * @param startDate
     * @param day
     * @param format
     * @return
     */
    public static String getAddDay(String startDate, int day, String format) {
        SimpleDateFormat df = new SimpleDateFormat(format);
        if (startDate != null) {
            Calendar c = Calendar.getInstance();
            c.setTime(parseDate(startDate, format));
            c.add(Calendar.DAY_OF_MONTH,day);
            Date endDate = c.getTime();
            return df.format(endDate);
        } else {
            return "";
        }
    }
    /**
     * 获取指定N分钟前/后的时间
     * @param startDate 开始日期
     * @param minute  分钟数
     * @param format 格式
     * @return
     */
    public static String getAddMinute(String startDate, int minute, String format) {
        SimpleDateFormat df = new SimpleDateFormat(format);
        if (startDate != null) {
            Calendar c = Calendar.getInstance();
            c.setTime(parseDate(startDate, "yyyy-MM-dd HH:mm:ss"));
            c.add(Calendar.MINUTE, minute);
            Date endDate = c.getTime();
            return df.format(endDate);
        } else {
            return "";
        }
    }

    /**
     * 计算开始日期days天之后的日期
     * @param startTime 开始日期
     * @param seconds 秒数
     * @author apple
     * @date 2018-01-19
     * @return
     */
    public static String addSecondsTime(String startTime, int seconds) {
        return addSecondsTime(startTime,seconds,"yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 计算开始日期days天之后的日期
     * @param startTime 开始日期
     * @param seconds 秒数
     * @param format 格式
     * @author apple
     * @date 2018-01-19
     * @return
     */
    public static String addSecondsTime(String startTime, int seconds, String format) {
        SimpleDateFormat df = new SimpleDateFormat(format);
        if (startTime != null) {
            Calendar c = Calendar.getInstance();
            c.setTime(parseDate(startTime, "yyyy-MM-dd HH:mm:ss"));
            c.add(Calendar.SECOND, seconds);
            Date endDate = c.getTime();
            return df.format(endDate);
        } else {
            return "";
        }
    }

    public static String getBeforeCurrent(int days, String format) {
        SimpleDateFormat df = new SimpleDateFormat(format);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.DATE, -days);
        Date time = calendar.getTime();
        return df.format(time);
    }


    /**
     * 计算开始日期增加月数后的日期
     *
     * @param startDate
     *            开始日期
     * @param month
     *            月数
     * @param format
     *            返回格式
     * @author apple
     * @date 2012-04-24
     * @return
     */
    public static String getAddMonth(String startDate, int month, String format) {
        SimpleDateFormat df = new SimpleDateFormat(format);
        if (startDate != null) {
            Calendar c = Calendar.getInstance();
            c.setTime(parseDate(startDate, "yyyy-MM-dd"));
            c.add(Calendar.MONTH, month);
            c.add(Calendar.DAY_OF_YEAR, -1);
            Date endDate = c.getTime();
            return df.format(endDate);
        } else {
            return "";
        }
    }

    public static String getYetDay() {
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd"); //设置时间格式
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, -10);
        date = calendar.getTime();
        return sdf.format(date);

    }

    /**
     *
     * @param type 1:2010-09-02 22; 2:2010-09-22 22:23; 3:2010-09-22 22:23:59
     * @return
     */
    public static String getCurrentTime(int type){
        String format = "yyyy-MM-dd";
        if(type == 1){
            format = "yyyy-MM-dd HH";
        }else if(type == 2){
            format = "yyyy-MM-dd HH:mm";
        }else if(type == 3){
            format = "yyyy-MM-dd HH:mm:ss";
        }else if(type == 4){
            format = "yyyyMMddHHmmss";
        }else{
            format = "yyyyMMddHHmmsssss";
        }
        return toDateString(nowTimestamp(),format);
    }

    public static String formate(Date date,int type){
        String format = "yyyy-MM-dd";
        if(type == 1){
            format = "yyyy-MM-dd HH";
        }else if(type == 2){
            format = "yyyy-MM-dd HH:mm";
        }else if(type == 3){
            format = "yyyy-MM-dd HH:mm:ss";
        }else if(type == 4){
            format = "yyyyMMdd HH:mm:ss";
        }
        return new SimpleDateFormat(format).format(date);
    }

    /**
     * 比较两个日期的大小，如果date1>=date2，则返回true，反之返回false
     * @param date1	第一个日期
     * @param date2	第二个日期
     * @param type	日期格式
     * @return
     */
    public static boolean compareDate(String date1,String date2,int type){
        boolean result = true;
        String format ="";
        switch(type){
            case 1:
                format = "yyyy-MM-dd hh";
                break;
            case 2:
                format = "yyyy-MM-dd HH:mm";
                break;
            case 3:
                format = "yyyy-MM-dd HH:mm:ss";
                break;
            case 4:
                format = "yyyy-MM-dd";
                break;
            case 5:
                format = "yyyyMMdd";
                break;
        }
        DateFormat df = new SimpleDateFormat(format);
        try {
            Date dt1 = df.parse(date1);
            Date dt2 = df.parse(date2);
            if(dt1.getTime()<dt2.getTime()){
                result = false;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 比较日期与当前日期的大小
     * @param date
     * @param type  日期格式
     * @return      -1-今天以前  0 - 今天  1 - 今天之后  -2-异常
     */
    public static int compareToday(String date,int type){
        int result = -2;
        String format ="";
        switch(type){
            case 1:
                format = "yyyy-MM-dd hh";
                break;
            case 2:
                format = "yyyy-MM-dd HH:mm";
                break;
            case 3:
                format = "yyyy-MM-dd HH:mm:ss";
                break;
            case 4:
                format = "yyyy-MM-dd";
                break;
            case 5:
                format = "yyyyMMdd";
        }
        DateFormat df = new SimpleDateFormat(format);
        try {
            Date dt1 = df.parse(date);
            Date dt2 = df.parse(df.format(new Date()));
            result = Long.compare(dt1.getTime(),dt2.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return result;
    }


    public static String getSystemDate(String format){
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        return dateFormat.format(new Date());
    }
    public static String getSystemDate(int format){
        if(format == 11){
            return getSystemDate("yyyyMMdd");
        } else if(format == 21){
            return getSystemDate("HH:mm:ss");
        } else if(format == 31){
            return getSystemDate("yyyy-MM-dd HH:mm:ss");
        } else if (format == 32){
            return getSystemDate("yyyyMMdd HH:mm:ss");
        } else if(format == 33){
            return getSystemDate("yyyyMMddHHmmss");
        }
        return getSystemDate("yyyyMMdd");
    }

    static {
        DATE_LOCALE = Locale.SIMPLIFIED_CHINESE;
    }

    public static String formatDateToString(Date tm, String date_format) {
        if (tm == null) {
            return "";
        }
        return formatDate(tm, date_format);
    }

    public static String formatDate(Date date, String pattern) {
        if (date == null) {
            return null;
        }
        return (new SimpleDateFormat(pattern)).format(date);
    }

    /**
     * 判断当前时间是否在[startTime, endTime]区间,单位：天
     *
     * @param nowDayStr 当前时间
     * @param startDayStr 开始时间
     * @param endDayStr 结束时间
     * @return
     */
    public static boolean isEffectiveDate(String nowDayStr, String startDayStr, String endDayStr) {
        Date nowDay = stringToDate(nowDayStr);
        Date startDay = stringToDate(startDayStr);
        Date endDay = stringToDate(endDayStr);
        if (nowDay.getTime() == startDay.getTime()
                || nowDay.getTime() == endDay.getTime()) {
            return true;
        }
        Calendar date = Calendar.getInstance();
        date.setTime(nowDay);
        Calendar begin = Calendar.getInstance();
        begin.setTime(startDay);
        Calendar end = Calendar.getInstance();
        end.setTime(endDay);
        if (date.after(begin) && date.before(end)) {
            return true;
        } else {
            return false;
        }
    }

    public static String getFormatStr(String dateString,String sourceFormat,String targetFormat){
        SimpleDateFormat sdf = new SimpleDateFormat(sourceFormat);
        try {
            if(StringUtil.isNotEmpty(dateString)){
                Date date = sdf.parse(dateString);
                return getFormatStr(date,targetFormat);
            }else{
                return null;
            }
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
    }


    public static String getFormatStr(Date date,String formatStr) {
        String rtnDateStr = "";
        TimeZone tz = TimeZone.getTimeZone("Asia/Shanghai");
        SimpleDateFormat myfmt = new SimpleDateFormat(formatStr);
        myfmt.setTimeZone(tz);
        rtnDateStr = myfmt.format(date).toString();
        return rtnDateStr;
    }

    /**
     *  对传入的Date类型代表的日期赋予temp的增量,得到结果
     * @param date
     * @param temp
     * @return String
     */
    public static String getTranDay(Date date,int temp){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, temp);
        int inyear=calendar.get(Calendar.YEAR);
        int inMonth=calendar.get(Calendar.MONTH)+1;
        int inday=calendar.get(Calendar.DAY_OF_MONTH);
        String strMonth=null;
        String strDay=null;
        if(inMonth<10){
            strMonth="0"+inMonth;
        }else{
            strMonth=String.valueOf(inMonth);
        }
        if(inday<10){
            strDay="0"+inday;
        }else{
            strDay=String.valueOf(inday);
        }
        String quryTime=inyear+strMonth+strDay;
        return quryTime;
    }

    /**
     * 获取指定距离天数
     * @param distanceDay exp:前一天-1  后天+1
     * @return StringDate
     */
    public static String getAppointTime(Integer distanceDay)throws IllegalArgumentException{
        SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd");
        Date date=new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, distanceDay);
        date = calendar.getTime();
        return sdf.format(date);
    }

    public static String longToDate(long longDate){
        Date date = new Date(longDate);
        SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sd.format(date);
    }

    /**
     * 测试方法<br>
     *
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(getSystemDate("yyyyMMdd HH:mm:ss"));
    }
}

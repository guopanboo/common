package club.janna.common;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期处理类
 * @author guopanbo
 *
 */
public class DateUtils {

	/**
	 * 得到当前的时间
	 * @return
	 */
	public static Date getTime() {
		return new Date();
	}
	
	/**
	 * 得到当前的毫秒数
	 * @return
	 */
	public static long getCurrentTimeMillis() {
		return System.currentTimeMillis();
	}
	
	/**
	 * 得到一天的开始时间
	 * 
	 * @return
	 */
	public static Date getDayBegin() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.MILLISECOND, 001);
		return cal.getTime();
	}

	/**
	 * 得到几天之前的开始时间
	 * 
	 * @return
	 */
	public static Date getDaysAgoBegin(int n) {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.MILLISECOND, 001);
		cal.set(Calendar.DATE, cal.get(Calendar.DATE) - n);
		return cal.getTime();
	}

	/**
	 * 自定义格式日期格式化
	 * 
	 * @param d
	 * @param format
	 * @return
	 */
	public static String getStrByDate(Date d, String format) {
		if (d == null || isBlank(format)) {
			return "";
		}
		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat(format);
			return dateFormat.format(d);
		} catch (Exception e) {
			// TODO: handle exception
			return "";
		}
	}

	/**
	 * 返回日期对应的星期几
	 * @param d
	 * @return
	 */
	public static String getWeekByDate(Date d) {
		if (d == null)
			return "";
		Calendar cal = Calendar.getInstance();
		cal.setTime(d);
		switch(cal.get(Calendar.DAY_OF_WEEK)) {
		case 1 : return "星期日";
		case 2 : return "星期一";
		case 3 : return "星期二";
		case 4 : return "星期三";
		case 5 : return "星期四";
		case 6 : return "星期五";
		case 7 : return "星期六";
		default : return "";
		}
	}

	/**
	 * 通过时间得到字符串
	 * 
	 * @param d
	 * @param isLongTime
	 *            长格式/短格式
	 * @return
	 */
	public static String getStrByDate(Date d, Boolean isLongTime) {
		if (d == null)
			return "";
		SimpleDateFormat dateFormat = null;
		String format = "yyyy-MM-dd";
		if (isLongTime) {
			dateFormat = new SimpleDateFormat(format + " HH:mm:ss");
		} else {
			dateFormat = new SimpleDateFormat(format);
		}
		return dateFormat.format(d);
	}

	/**
	 * 通过字符串获取时间
	 * 
	 * @param date
	 * @return
	 */
	public static Date getTimeByString(String date) {
		Date d = FormatStringToDate("yyyy-MM-dd HH:mm:ss", date);
		if (d != null) {
			return d;
		}
		d = FormatStringToDate("yyyy-MM-dd HH:mm", date);
		if (d != null) {
			return d;
		}
		d = FormatStringToDate("yyyy-MM-dd HH", date);
		if (d != null) {
			return d;
		}
		d = FormatStringToDate("yyyy-MM-dd", date);
		if (d != null) {
			return d;
		}
		d = FormatStringToDate("yyyy-MM", date);
		if (d != null) {
			return d;
		}
		d = FormatStringToDate("yyyy", date);
		if (d != null) {
			return d;
		}
		return null;
	}

	private static Date FormatStringToDate(String format, String date) {
		if (date == null) {
			return null;
		}
		try {
			return InitSimpleDateFormat(format).parse(date.trim().replaceAll("/", "-"));
		} catch (ParseException e) {
			return null;
		}
	}

	private static DateFormat InitSimpleDateFormat(String format) {
		return new SimpleDateFormat(format);
	}

	private static boolean isBlank(String str) {
		return str == null ? true : "".equals(str);
	}
}

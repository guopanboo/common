import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {
	
	
	/**
	 * �õ�һ��Ŀ�ʼʱ��
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
	 * ͨ��ʱ��õ��ַ���
	 * @param d
	 * @param isLongTime ����ʽ/�̸�ʽ
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
	 * ͨ���ַ�����ȡʱ��
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
}

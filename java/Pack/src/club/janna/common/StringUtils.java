package club.janna.common;

import java.math.BigDecimal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 字符串工具
 * 处理，判断
 * @author guopanbo
 *
 */
public class StringUtils {
	
	/**
	 * 判断字符串是否为空
	 * 包含为null或者为空字符串
	 * @param str
	 * @return
	 */
	public static boolean isBlank(String str) {
		return str == null ? true : "".equals(str.trim());
	}
	
	/**
	 * 判断字符串是否不为空
	 * @param str
	 * @return
	 */
	public static boolean isNotBlank(String str) {
		return !isBlank(str);
	}
	
	/**
	 * 判断字符串是否为false
	 * @param str
	 * @return
	 */
	public static boolean isFalse(String str) {
		return isBlank(str) || "N".equals(str) || "0".equals(str);
	}
	
	/**
	 * 判断字符是否为true
	 * @param str
	 * @return
	 */
	public static boolean isTrue(String str) {
		return !isFalse(str);
	}
	
	/**
	 * 通过字符串获取Decimal
	 * @param str
	 * @return
	 */
	public static BigDecimal getDecimal(String str) {
		if(isBlank(str))
			return new BigDecimal(0);
		try {
			return new BigDecimal(str);
		} catch(Exception e) {
			return new BigDecimal(0);
		}
	}
	
	/**
	 * 是否是数字
	 * @param str
	 * @return
	 */
	public static boolean isNumber(String str) {
		if (isBlank(str)) 
			return false;
		return regular("^[0-9]*$", str);
	}
	
	/**
	 * 是否是手机号
	 * @param phone
	 * @return
	 */
	public final static boolean isPhoneNo(String phone) {
		if (isBlank(phone)) {
			return false;
		}
		return regular("^1\\d{10}$", phone);
	}
	
	/**
	 * 不是手机号
	 * @param phone
	 * @return
	 */
	public final static boolean isNotPhoneNo(String phone) {
		return !isPhoneNo(phone);
	}
	
	/**
	 * 判断是否是邮箱
	 * @param email
	 * @return
	 */
	public final static boolean isEmail(String email) {
		if (isBlank(email)) {
			return false;
		}
		return regular("^\\s*\\w+(?:\\.{0,1}[\\w-]+)*@[a-zA-Z0-9]+(?:[-.][a-zA-Z0-9]+)*\\.[a-zA-Z]+\\s*$", email);
	}
	
	/**
	 * 正则表达式匹配字符串
	 * @param format
	 * @param str
	 * @return
	 */
	public final static boolean regular(String format, String str) {
		Pattern pattern = Pattern.compile(format);
		Matcher matcher = pattern.matcher(str);
		return matcher.matches();
	}
}

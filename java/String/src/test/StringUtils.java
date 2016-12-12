package test;

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
}

package club.janna.common;

public class PasswordUtils {
	
	private static PasswordUtils pu = null;
	
	private PasswordUtils() {}
	
	public static PasswordUtils getInstance() {
		if(pu == null)
			pu = new PasswordUtils();
		return pu;
	}
	
	/**
	 * 生成加密密码
	 * @param passwd
	 * @return
	 */
	public String generatorPassword(String passwd) {
		if(StringUtils.isBlank(passwd))
			return null;
		return encrypt(passwd);
	}
	
	/**
	 * 验证密码是否和加密后的密码一致
	 * @param passwd
	 * @param enpasswd
	 * @return
	 */
	public boolean verification(String passwd, String enpasswd) {
		if(StringUtils.isBlank(passwd) || StringUtils.isBlank(enpasswd))
			return false;
		return enpasswd.equals(encrypt(passwd));
	}
	
	/**
	 * 加密
	 * @param passwd
	 * @return
	 */
	private String encrypt(String passwd) {
		return Encrypt.SHA256(passwd);
	}
}

package club.janna.common;

import java.util.UUID;

/**
 * Id生成器
 * @author guopanbo
 *
 */
public class IDGenerator {
	
	/**
	 * 生成id
	 * @return
	 */
	public static String getId() {
		return UUID.randomUUID().toString();
	}
}

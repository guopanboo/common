package club.janna.test.sha1;

import org.junit.Assert;
import org.junit.Test;

public class TestEncript {
	
	@Test
	public void test() {
		String encript = Sha.sha1("guopanbo");
		Assert.assertEquals("d86d5b1a46106c59fd99dd08d03ab60c5fd10dd5", encript);
	}
}

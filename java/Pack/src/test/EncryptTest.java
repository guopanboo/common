package test;

import org.junit.Test;

import club.janna.common.Encrypt;

public class EncryptTest {
	
	@Test
	public void testSHA256() {
		System.out.println(Encrypt.SHA256("guopanbo"));
	}
	
	@Test
	public void testSHA512() {
		System.out.println(Encrypt.SHA512("guopanbo"));
	}
}

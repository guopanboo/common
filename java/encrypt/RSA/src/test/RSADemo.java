package test;

import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.math.BigInteger;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

public class RSADemo {
	public static boolean generateKey(String publickey, String privatekey) {
		if(StringUtils.isBlank(publickey) || StringUtils.isBlank(privatekey))
			return false;
		ObjectOutputStream b1 = null;
		ObjectOutputStream b2 = null;
		try {
			KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
			kpg.initialize(1024);
			KeyPair kp = kpg.genKeyPair();
			PublicKey pbkey = kp.getPublic();
			PrivateKey prkey = kp.getPrivate();
			// 保存公钥
			FileOutputStream f1 = new FileOutputStream(publickey);
			b1 = new ObjectOutputStream(f1);
			b1.writeObject(pbkey);
			// 保存私钥
			FileOutputStream f2 = new FileOutputStream(privatekey);
			b2 = new ObjectOutputStream(f2);
			b2.writeObject(prkey);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			if(b1 != null)
				try {
					b1.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			if(b2 != null)
				try {
					b2.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		return true;
	}

	/**
	 * 加密
	 * @param content 内容
	 * @param key 公钥
	 * @return
	 */
	public static String encrypt(String content, String key) {
		if(StringUtils.isBlank(content) || StringUtils.isBlank(key))
			return null;
		FileInputStream f = null;
		ObjectInputStream b = null;
		BufferedWriter out = null;
		String r = null;
		try {
			f = new FileInputStream(key);
			b = new ObjectInputStream(f);
			RSAPublicKey pbk = (RSAPublicKey) b.readObject();
			// 获取公钥及参数e,n
			BigInteger e = pbk.getPublicExponent();
			BigInteger n = pbk.getModulus();
			// 获取明文m
			byte ptext[] = content.getBytes("UTF-8");
			BigInteger m = new BigInteger(ptext);
			// 计算密文c
			BigInteger c = m.modPow(e, n);
			// 密文
			r = c.toString();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			if(out != null)
				try {
					out.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		return r;
	}

	/**
	 * 解密
	 * @param encrypt 密文
	 * @param key 私钥
	 * @return
	 */
	public static String decrypt(String encrypt, String key) {
		if(StringUtils.isBlank(encrypt) || StringUtils.isBlank(key))
			return null;
		FileInputStream f = null;
		ObjectInputStream b = null;
		String content = null;
		try {
			// 读取密文
			BigInteger c = new BigInteger(encrypt);
			// 读取私钥
			f = new FileInputStream(key);
			b = new ObjectInputStream(f);
			RSAPrivateKey prk = (RSAPrivateKey) b.readObject();
			BigInteger d = prk.getPrivateExponent();
			// 获取私钥参数及解密
			BigInteger n = prk.getModulus();
			BigInteger m = c.modPow(d, n);
			// 解密结果
			if(m == null)
				return null;
			byte[] mt = m.toByteArray();  
			if(mt == null)
				return null;
			StringBuffer sb = new StringBuffer();
			for(byte bt : mt)
				sb.append((char)bt);
			content = sb.toString();
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			if(f != null)
				try {
					f.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			if(b != null)
				try {
					b.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		return content;
	}

	public static void main(String args[]) {
		try {
			//生成公钥私钥
//			boolean b = generateKey("/Users/guopanbo/workspacej/testdata/publickey.dat", "/Users/guopanbo/workspacej/testdata/privatekey.dat");
//			System.out.println(b ? "success" : "defeat");
//			String encrypt = encrypt("2016-12-13", "/Users/guopanbo/workspacej/testdata/publickey.dat");
//			System.out.println(encrypt);
			String content = decrypt("13213443316002686880847620011953640339735985590376495750418184049998125158343573367239759380015031289766178782138463356650728807853731339436468385460784651746478029758788360156349113169117499378329760169590903622776664109135049285681641009347054313191008605619638838679262277856075807244329447374121338646363", "/Users/guopanbo/workspacej/testdata/privatekey.dat");
			System.out.println(content);
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}
}

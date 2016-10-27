package test.watermark;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageOutputStream;

public class Watermark {
	public static void main(String[] args) {

		String[][] textCover = new String[1][]; // 文字水印
		// for(int i = 0; i < tcount; i++) {
		String[] text = new String[5];
		text[0] = "测试测试测试测试测试测试测试测试测试";
		text[1] = "446";
		text[2] = "45";
		text[3] = "1";
		text[4] = "black";
		textCover[0] = text;
		// }

		String targetPic = "C:/Users/郭攀博/Pictures/53a4e6497a18c.jpg"; // 目标图片绝对路径
		String[][] imageCover = new String[][] { { targetPic, "0", "0" } };
		// 字体库文件路径
		// String fontBasePath =
		// "/export/home/ilearn/app/product/as904/jdk/jre/lib/fonts/simsun.ttc";
		// String fontName = "宋体";
		float alpha = 0.3f;
		// boolean quality = true;

		// 字体颜色
		Map<String, Color> colorMap = new HashMap<>();
		colorMap.put("black", Color.black);
		colorMap.put("red", Color.red);
		colorMap.put("blue", Color.blue);
		colorMap.put("yellow", Color.yellow);

		// OutputStream responseOS = null;
		FileImageOutputStream fos = null;
		try {
			// out.clear();
			//
			// response.reset();
			// response.setContentType("application/octet-stream;
			// charset=UTF-8"); //设置成application/x-msdownload也可以
			//
			// response.setHeader("Content-Disposition",
			// "attachment;filename=license.jpg");
			// //如果设置成inline;filename=xxx.jpg，就直接在页面中打开

			fos = new FileImageOutputStream(new File("C:/Users/郭攀博/Desktop/out.jpg"));

			/*
			 * String filename =
			 * "/app_shared/mainpage/exam/exam_new/certImg/1111.jpg";//
			 * 保存文件路径和文件名 OutputStream bos = new FileOutputStream(filename);//
			 * 建立一个上传文件的路径
			 */
			File img = new File(targetPic);
			Image src = ImageIO.read(img);
			int width = src.getWidth(null); // 图片宽
			int height = src.getHeight(null); // 图片高
			BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
			Graphics2D g = image.createGraphics();
			g.drawImage(src, 0, 0, width, height, null);
			if (textCover != null) {
				for (int i = 0; i < textCover.length; i++) {
					if (isEmptyStr(textCover[i][1]) || isEmptyStr(textCover[i][2]))
						continue;
					// 从指定的字库创建字体类
					// Font font = Font.createFont(Font.TRUETYPE_FONT, new
					// FileInputStream(new File(fontBasePath)));
					// //设置字形、样式和大小
					// font = Font.decode(fontName).deriveFont(Font.BOLD,
					// Float.parseFloat(textCover[i][3]));
					// g.setFont(font);
					g.setColor(colorMap.get(textCover[i][4]));
					g.drawString(textCover[i][0], Integer.parseInt(textCover[i][1]),
							Integer.parseInt(textCover[i][2]) + 20);
				}
			}
			if (imageCover != null) {
				g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, alpha));
				for (int i = 0; i < imageCover.length; i++) {
					Image src_biao = ImageIO.read(new File(imageCover[i][0]));
					int width_biao = src_biao.getWidth(null);
					int height_biao = src_biao.getHeight(null);
					g.drawImage(src_biao, ((width - width_biao) / 2) + Integer.parseInt(imageCover[i][1]),
							((height - height_biao) / 2) + Integer.parseInt(imageCover[i][2]), width_biao, height_biao,
							null);
				}
			}
			g.dispose();

			// JPEGImageEncoder encoder =
			// JPEGCodec.createJPEGEncoder(responseOS);
			// JPEGEncodeParam param = encoder.getDefaultJPEGEncodeParam(image);
			// param.setQuality(quality?10.0f:1.0f, true);
			// encoder.encode(image, param);

			/*
			 * bos.flush(); bos.close();
			 */

			// response.flushBuffer();
			// out.clear();
			// out = pageContext.pushBody();

			ImageIO.write(image, "jpg", fos);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (fos != null) {
				try {
					fos.flush();
					fos.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static boolean isEmptyStr(String str) {
		return str == null || "".equals(str);
	}
}
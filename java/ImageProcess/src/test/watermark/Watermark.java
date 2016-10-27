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

		String[][] textCover = new String[1][]; // ����ˮӡ
		// for(int i = 0; i < tcount; i++) {
		String[] text = new String[5];
		text[0] = "���Բ��Բ��Բ��Բ��Բ��Բ��Բ��Բ���";
		text[1] = "446";
		text[2] = "45";
		text[3] = "1";
		text[4] = "black";
		textCover[0] = text;
		// }

		String targetPic = "C:/Users/���ʲ�/Pictures/53a4e6497a18c.jpg"; // Ŀ��ͼƬ����·��
		String[][] imageCover = new String[][] { { targetPic, "0", "0" } };
		// ������ļ�·��
		// String fontBasePath =
		// "/export/home/ilearn/app/product/as904/jdk/jre/lib/fonts/simsun.ttc";
		// String fontName = "����";
		float alpha = 0.3f;
		// boolean quality = true;

		// ������ɫ
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
			// charset=UTF-8"); //���ó�application/x-msdownloadҲ����
			//
			// response.setHeader("Content-Disposition",
			// "attachment;filename=license.jpg");
			// //������ó�inline;filename=xxx.jpg����ֱ����ҳ���д�

			fos = new FileImageOutputStream(new File("C:/Users/���ʲ�/Desktop/out.jpg"));

			/*
			 * String filename =
			 * "/app_shared/mainpage/exam/exam_new/certImg/1111.jpg";//
			 * �����ļ�·�����ļ��� OutputStream bos = new FileOutputStream(filename);//
			 * ����һ���ϴ��ļ���·��
			 */
			File img = new File(targetPic);
			Image src = ImageIO.read(img);
			int width = src.getWidth(null); // ͼƬ��
			int height = src.getHeight(null); // ͼƬ��
			BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
			Graphics2D g = image.createGraphics();
			g.drawImage(src, 0, 0, width, height, null);
			if (textCover != null) {
				for (int i = 0; i < textCover.length; i++) {
					if (isEmptyStr(textCover[i][1]) || isEmptyStr(textCover[i][2]))
						continue;
					// ��ָ�����ֿⴴ��������
					// Font font = Font.createFont(Font.TRUETYPE_FONT, new
					// FileInputStream(new File(fontBasePath)));
					// //�������Ρ���ʽ�ʹ�С
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

package test.qrcode;

import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Shape;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;


import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;

public class QRCodeUtils {
	private static final int BLACK = 0xFF000000;
	private static final int WHITE = 0xFFFFFFFF;
	
	private static final int QR_SIZE = 400;
	private static final int LOGO_WIDTH = 36;
	private static final int LOGO_HEIGHT = 36;

	private QRCodeUtils() {}
	
	/**
	 * 生成二维码
	 * @param content 二维码包含的内容
	 * @param path 生成的二维码的路径
	 * @param logo 二维码中间的logo，可以传空或空字符串则不加logo
	 * @return
	 */
	public static boolean generate(String content, String path, String logo) {
		if(isBlank(content) || isBlank(path))
			return false;
		MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
	    Map<EncodeHintType, String> hints = new HashMap<EncodeHintType, String>();
	    hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
		try {
		    BitMatrix bitMatrix = multiFormatWriter.encode(content, BarcodeFormat.QR_CODE, QR_SIZE, QR_SIZE, hints);
		    File file1 = new File(path,"test.jpg");
		    writeToFile(bitMatrix, "jpg", file1, logo);
		     
		} catch (Exception e) {
		    e.printStackTrace();
		    return false;
		}
		return true;
	}

	public static BufferedImage toBufferedImage(BitMatrix matrix, String logo) {
		int width = matrix.getWidth();
		int height = matrix.getHeight();
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				image.setRGB(x, y, matrix.get(x, y) ? BLACK : WHITE);
			}
		}
		insertImage(image, logo, true);
		return image;
	}
	
	/** 
     * 插入LOGO 
     *  
     * @param source 
     *            二维码图片 
     * @param imgPath 
     *            LOGO图片地址 
     * @param needCompress 
     *            是否压缩 
     * @throws Exception 
     */  
    private static void insertImage(BufferedImage source, String imgPath,  
            boolean needCompress) {
    	if(isBlank(imgPath))
    		return;
        File file = new File(imgPath);  
        if (!file.exists()) {  
            System.err.println(""+imgPath+"   该文件不存在！");  
            return;  
        } 
        try {
	        Image src = ImageIO.read(new File(imgPath));
	        int width = src.getWidth(null);  
	        int height = src.getHeight(null);  
	        if (needCompress) { // 压缩LOGO  
	            if (width > LOGO_WIDTH) {  
	                width = LOGO_WIDTH;  
	            }  
	            if (height > LOGO_HEIGHT) {  
	                height = LOGO_HEIGHT;  
	            }  
	            Image image = src.getScaledInstance(width, height,  
	                    Image.SCALE_SMOOTH);  
	            BufferedImage tag = new BufferedImage(width, height,  
	                    BufferedImage.TYPE_INT_RGB);  
	            Graphics g = tag.getGraphics();  
	            g.drawImage(image, 0, 0, null); // 绘制缩小后的图  
	            g.dispose();  
	            src = image;  
	        }  
	        // 插入LOGO  
	        Graphics2D graph = source.createGraphics();  
	        int x = (QR_SIZE - width) / 2;  
	        int y = (QR_SIZE - height) / 2;  
	        graph.drawImage(src, x, y, width, height, null);  
	        Shape shape = new RoundRectangle2D.Float(x, y, width, width, 6, 6);  
	        graph.setStroke(new BasicStroke(3f));  
	        graph.draw(shape);  
	        graph.dispose(); 
        } catch(Exception e) {
        	return;
        }
    }

	public static void writeToFile(BitMatrix matrix, String format, File file, String logo) throws IOException {
		BufferedImage image = toBufferedImage(matrix, logo);
		if (!ImageIO.write(image, format, file)) {
			throw new IOException("Could not write an image of format " + format + " to " + file);
		}
	}
	
	public static boolean isBlank(String str) {
		return str == null ? true : "".equals(str);
	}
	
}

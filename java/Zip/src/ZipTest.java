import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.junit.Test;


public class ZipTest {
	
	@Test
	public void test() {
		ZipMultiFile("C:/Users/guopa/Desktop/test", "C:/Users/guopa/Desktop/test.zip");
	}
	
	public void ZipMultiFile(String filepath ,String zippath) {
		try {
	        File file = new File(filepath);// 要被压缩的文件夹
	        File zipFile = new File(zippath);
	        InputStream input = null;
	        ZipOutputStream zipOut = new ZipOutputStream(new FileOutputStream(zipFile));
	        if(file.isDirectory()){
	        	List<File> files = listFiles(file);
	        	for(File f : files) {
	        		input = new FileInputStream(f);
	        		zipOut.putNextEntry(new ZipEntry(f.getPath().substring(f.getPath().indexOf(file.getName()), f.getPath().length())));
	                int temp = 0;
	                while((temp = input.read()) != -1){
	                    zipOut.write(temp);
	                }
	                input.close();
	            }
	        	
	        	
	        }
	        zipOut.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public List<File> listFiles(File file) {
		List<File> result = new ArrayList<>();
		if(file == null)
			return null;
		File[] files = file.listFiles();
		for(File f : files) {
			if(f.isDirectory())
				result.addAll(listFiles(f));
			else
				result.add(f);
		}
		return result;
	}
}

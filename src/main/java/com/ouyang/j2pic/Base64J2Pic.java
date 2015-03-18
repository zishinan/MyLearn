package com.ouyang.j2pic;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.log4j.Logger;

import com.owtelse.codec.Base64;

public class Base64J2Pic {
	private static final Logger logger = Logger.getLogger(Base64J2Pic.class);
	public static void main(String[] args) {
		String path = "C:\\Users\\Administrator\\Desktop\\image\\111.jpg";
		String imgStr = getImgString(path);
		String filePath = "C:\\Users\\Administrator\\Desktop\\image\\222.jpg";
		setImgFromString(imgStr,filePath);
	}

	private static boolean setImgFromString(String imgStr, String filePath) {
		OutputStream os = null;
		try {
			byte[] imgByte = Base64.decode(imgStr);
			for (byte b : imgByte) {
				if(b < 0){
					b += 256;
				}
			}
			os = new FileOutputStream(filePath);
			os.write(imgByte);
			os.flush();
			return true;
		} catch (Exception e) {
			logger.error("Set file error");
		}finally{
			closeOutputStream(os);
		}
		return false;
	}

	private static void closeOutputStream(OutputStream os) {
		try {
			os.close();
		} catch (IOException e) {
			logger.error("Close outputStream error!");
		}
	}

	private static String getImgString(String path) {
		InputStream is = null;
		byte[] data = null;
		try {
			is = new FileInputStream(path);
			data = new byte[is.available()];
			is.read(data);
			return Base64.encode(data);
		} catch (Exception e) {
			logger.error("Get file error");
		}finally{
			closeInputStream(is);
		}
		return null;
	}

	
	private static void closeInputStream(InputStream is) {
		try {
			is.close();
		} catch (IOException e) {
			logger.error("Close inputstream error!");
		}
	}
}

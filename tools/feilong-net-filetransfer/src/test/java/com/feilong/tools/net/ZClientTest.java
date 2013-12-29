package com.feilong.tools.net;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;

import org.apache.commons.net.ftp.FTPClient;

public class ZClientTest{

	private static void getFiles(FTPClient ftp,File localDir) throws IOException{
		String[] names = ftp.listNames();
		for (String name : names){
			File file = new File(localDir.getPath() + File.separator + name);
			if (!file.exists()){
				file.createNewFile();
			}
			long pos = file.length();
			RandomAccessFile raf = new RandomAccessFile(file, "rw");
			raf.seek(pos);
			ftp.setRestartOffset(pos);
			InputStream is = ftp.retrieveFileStream(name);
			if (is == null){
				System.out.println("no such file:" + name);
			}else{
				System.out.println("start getting file:" + name);
				int b;
				while ((b = is.read()) != -1){
					raf.write(b);
				}
				is.close();
				if (ftp.completePendingCommand()){
					System.out.println("done!");
				}else{
					System.out.println("can't get file:" + name);
				}
			}
			raf.close();
		}
	}
}

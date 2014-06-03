package com.feilong.tools.net;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;

import org.apache.commons.net.ftp.FTPClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ZClientTest{

	private static final Logger	log	= LoggerFactory.getLogger(ZClientTest.class);

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
				log.info("no such file:" + name);
			}else{
				log.info("start getting file:" + name);
				int b;
				while ((b = is.read()) != -1){
					raf.write(b);
				}
				is.close();
				if (ftp.completePendingCommand()){
					log.info("done!");
				}else{
					log.info("can't get file:" + name);
				}
			}
			raf.close();
		}
	}
}

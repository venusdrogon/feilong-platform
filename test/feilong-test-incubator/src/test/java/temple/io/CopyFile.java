/*
 * Copyright (C) 2008 feilong (venusdrogon@163.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package temple.io;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The Class Util.
 * 
 * @author <a href="mailto:venusdrogon@163.com">feilong</a>
 * @version 1.0.7 2014-6-25 15:40:45
 */
public class CopyFile{

	/** The Constant log. */
	private static final Logger	log	= LoggerFactory.getLogger(CopyFile.class);

	/**
	 * Copy file.
	 * 
	 * @param fileName
	 *            the file name
	 * @param newFileName
	 *            the new file name
	 * @return true, if successful
	 */
	public static boolean copyFile(String fileName,String newFileName){
		BufferedOutputStream bos = null;
		FileInputStream in = null;
		File old = new File(fileName);
		if (!old.exists()){
			return false;
		}
		File newPath = new File(newFileName);
		if (!newPath.getParentFile().exists()){
			newPath.getParentFile().mkdirs();
		}
		try{
			in = new FileInputStream(fileName);
			bos = new BufferedOutputStream(new FileOutputStream(newFileName));
			byte[] buf = new byte[1024];
			int b = in.read(buf, 0, buf.length);
			while (b != -1){
				bos.write(buf, 0, b);
				b = in.read(buf);
			}
		}catch (FileNotFoundException e){
			log.error(e.getClass().getName(), e);
			return false;
		}catch (IOException e1){
			e1.printStackTrace();
			return false;
		}finally{
			try{
				in.close();
				bos.close();
			}catch (IOException e){
				log.error(e.getClass().getName(), e);
			}
		}
		return true;
	}

}

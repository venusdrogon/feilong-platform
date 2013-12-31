/**
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
/**
 * This product currently only contains code developed by authors
 * of specific components, as identified by the source code files.
 *
 * Since product implements StAX API, it has dependencies to StAX API
 * classes.
 *
 * For additional credits (generally to people who reported problems)
 * see CREDITS file.
 */
package elsepackage.temple.awt;

import java.awt.color.ColorSpace;
import java.awt.image.BufferedImage;
import java.awt.image.ColorConvertOp;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.feilong.commons.core.awt.ImageUtil;


/**
 * CMYK转RGB 图片格式转换
 * 
 * @author 徐新望 Jul 9, 2010
 */
public class CmykToRgb{
	private final static Logger	log					= LoggerFactory.getLogger(CmykToRgb.class);
	/**
	 * Creates new RGB images from all the CMYK images passed in on the command line. The new filename generated is, for example "GIF_original_filename.gif".
	 */
	public static void main(String[] args){
		String filename = "E:\\workspaces\\project\\cmyk.jpg";
		boolean isCmykType = ImageUtil.isCMYKType(filename);
		if (isCmykType){
			try{
				String rgbFile = CmykToRgb.cmyk2rgb(filename);
				log.error(ImageUtil.isCMYKType(rgbFile) + ": " + rgbFile);
			}catch (IOException e){
				e.printStackTrace();
			}
		}
	}

	/**
	 * CMYK转RGB If 'filename' is a CMYK file, then convert the image into RGB, store it into a JPEG file, and return the new filename.
	 * 
	 * @param filename
	 */
	private static String cmyk2rgb(String filename) throws IOException{
		// Change this format into any ImageIO supported format.
		String format = "gif";
		File imageFile = new File(filename);
		String rgbFilename = filename;
		BufferedImage image = ImageIO.read(imageFile);
		if (image != null){
			int colorSpaceType = image.getColorModel().getColorSpace().getType();
			if (colorSpaceType == ColorSpace.TYPE_CMYK){
				BufferedImage rgbImage = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_3BYTE_BGR);
				ColorConvertOp op = new ColorConvertOp(null);
				op.filter(image, rgbImage);
				rgbFilename = changeExtension(imageFile.getName(), format);
				rgbFilename = new File(imageFile.getParent(), format + "_" + rgbFilename).getPath();
				ImageIO.write(rgbImage, format, new File(rgbFilename));
			}
		}
		return rgbFilename;
	}

	/**
	 * Change the extension of 'filename' to 'newExtension'.
	 * 
	 * @param filename
	 * @param newExtension
	 * @return filename with new extension
	 */
	private static String changeExtension(String filename,String newExtension){
		String result = filename;
		if (filename != null && newExtension != null && newExtension.length() != 0){
			int dot = filename.lastIndexOf('.');
			if (dot != -1){
				result = filename.substring(0, dot) + '.' + newExtension;
			}
		}
		return result;
	}
}

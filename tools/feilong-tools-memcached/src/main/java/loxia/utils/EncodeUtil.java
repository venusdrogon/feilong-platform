package loxia.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.zip.Deflater;
import java.util.zip.DeflaterOutputStream;

public class EncodeUtil{

	private static char[]	encodes	= "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/".toCharArray();

	public static String intToBase62(int value){
		return encodeBase62(intToByteArray1(value)).toString();
	}

	public static byte[] intToByteArray1(int i){
		byte[] result = new byte[4];
		result[0] = (byte) ((i >> 24) & 0xFF);
		result[1] = (byte) ((i >> 16) & 0xFF);
		result[2] = (byte) ((i >> 8) & 0xFF);
		result[3] = (byte) (i & 0xFF);
		return result;
	}

	public static byte[] compressData(byte[] input) throws IOException{
		ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
		OutputStream outPutStream = getCompressedOutputStream(byteOut);
		outPutStream.write(input);
		outPutStream.flush();
		outPutStream.close();
		return byteOut.toByteArray();
	}

	public static OutputStream getCompressedOutputStream(OutputStream destStream){
		Deflater compresser = new Deflater(Deflater.BEST_COMPRESSION);
		return new DeflaterOutputStream(destStream, compresser);
	}

	public static StringBuffer encodeBase62(byte[] data){
		StringBuffer sb = new StringBuffer(data.length * 2);
		int pos = 0, val = 0;
		for (int i = 0; i < data.length; i++){
			val = (val << 8) | (data[i] & 0xFF);
			pos += 8;
			while (pos > 5){
				char c = encodes[val >> (pos -= 6)];
				sb.append(
				/**/c == 'i' ? "ia" :
				/**/c == '+' ? "ib" :
				/**/c == '/' ? "ic" : c);
				val &= ((1 << pos) - 1);
			}
		}
		if (pos > 0){
			char c = encodes[val << (6 - pos)];
			sb.append(
			/**/c == 'i' ? "ia" :
			/**/c == '+' ? "ib" :
			/**/c == '/' ? "ic" : c);
		}
		return sb;
	}
}

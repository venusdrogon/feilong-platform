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
package com.feilong.commons.core.io;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.feilong.commons.core.util.Validator;

/**
 * 提供写文件操作
 * 
 * <ul>
 * <li>{@link #write(InputStream, OutputStream)} 写资源,速度最快的方法,速度比较请看 电脑资料 {@code <<压缩解压性能探究>>}</li>
 * <li>{@link #write(String, String)} 将字符串写到文件中</li>
 * <li>{@link #write(InputStream, String, String)} 将inputStream 写到 某个文件夹,名字为fileName</li>
 * <li>{@link #write(String, String, String)} 将字符串/文字写到文件中</li>
 * <li>{@link #write(String, String, String, FileWriteMode)} 将字符串写到文件中</li>
 * </ul>
 * 
 * 如果需要覆盖写文件,可以调用 {@link #write(String, String, String, FileWriteMode)}.
 * 
 * @author <a href="mailto:venusdrogon@163.com">金鑫</a>
 * @version 1.0 Dec 23, 2013 10:23:23 PM
 * @since 1.0.0
 */
public final class IOWriteUtil{

    /** The Constant log. */
    private static final Logger log = LoggerFactory.getLogger(IOWriteUtil.class);

    /** Don't let anyone instantiate this class. */
    private IOWriteUtil(){
        //AssertionError不是必须的. 但它可以避免不小心在类的内部调用构造器. 保证该类在任何情况下都不会被实例化.
        //see 《Effective Java》 2nd
        throw new AssertionError("No " + getClass().getName() + " instances for you!");
    }

    /**
     * 将inputStream 写到 某个文件夹(文件夹路径 最后不带"/"),名字为fileName.
     * 
     * <h3>最终的fileAllName 格式是</h3>
     * 
     * <blockquote>
     * <p>
     * {@code directoryName + "/" + fileName}
     * </p>
     * </blockquote>
     * <p>
     * 拼接文件路径.<br>
     * 如果拼接完的文件路径 父路径不存在,则自动创建(支持级联创建 文件夹)
     * </p>
     *
     * @param inputStream
     *            上传得文件流
     * @param directoryName
     *            文件夹路径 最后不带"/"
     * @param fileName
     *            文件名称
     * @throws UncheckedIOException
     *             the unchecked io exception
     * @see com.feilong.commons.core.io.IOWriteUtil#write(InputStream, OutputStream)
     */
    public static void write(InputStream inputStream,String directoryName,String fileName) throws UncheckedIOException{
        String fileAllName = directoryName + "/" + fileName;
        // 拼接文件路径.如果拼接完的文件路径 父路径不存在,则自动创建
        File file = new File(fileAllName);
        File fileParent = file.getParentFile();
        if (!fileParent.exists()){
            fileParent.mkdirs();
        }
        OutputStream outputStream = FileUtil.getFileOutputStream(fileAllName);
        write(inputStream, outputStream);
    }

    /**
     * 写资源,速度最快的方法,速度比较请看 电脑资料 {@code  <<压缩解压性能探究>>} .<br>
     * <b>(注意，本方法最终会关闭 <code>inputStream</code>以及 <code>outputStream</code>).</b>
     *
     * @param inputStream
     *            inputStream
     * @param outputStream
     *            outputStream
     * @throws UncheckedIOException
     *             the unchecked io exception
     * @see java.io.OutputStream#write(byte[], int, int)
     * @see #write(int, InputStream, OutputStream)
     * @see org.apache.commons.io.IOUtils#copyLarge(InputStream, OutputStream)
     */
    public static void write(InputStream inputStream,OutputStream outputStream) throws UncheckedIOException{
        //Just write in blocks instead of copying it entirely into Java's memory first.
        //The below basic example writes it in blocks of 10KB.
        //This way you end up with a consistent memory usage of only 10KB instead of the complete content length. 
        //Also the enduser will start getting parts of the content much sooner.
        write(IOConstants.DEFAULT_BUFFER_LENGTH, inputStream, outputStream);
    }

    /**
     * 写资源,速度最快的方法,速度比较请看 电脑资料 {@code  <<压缩解压性能探究>>} .<br>
     * <b>(注意，本方法最终会关闭 <code>inputStream</code>以及 <code>outputStream</code>).</b>
     *
     * @param bufferLength
     *            每次循环buffer大小
     * @param inputStream
     *            inputStream
     * @param outputStream
     *            outputStream
     * @throws UncheckedIOException
     *             the unchecked io exception
     * @see java.io.OutputStream#write(byte[], int, int)
     * @see org.apache.commons.io.IOUtils#copyLarge(InputStream, OutputStream)
     * @see <a href="http://stackoverflow.com/questions/10142409/write-an-inputstream-to-an-httpservletresponse">As creme de la creme with
     *      regard to performance, you could use NIO Channels and ByteBuffer. Create the following utility/helper method in some custom
     *      utility class,</a>
     * @see #writeUseIO(int, InputStream, OutputStream)
     * @see #writeUseNIO(int, InputStream, OutputStream)
     */
    public static void write(int bufferLength,InputStream inputStream,OutputStream outputStream) throws UncheckedIOException{
        writeUseNIO(bufferLength, inputStream, outputStream);
        //writeUseIO(bufferLength, inputStream, outputStream);
    }

    /**
     * 使用NIO API 来写数据 (效率高).
     *
     * @param bufferLength
     *            the buffer length
     * @param inputStream
     *            the input stream
     * @param outputStream
     *            the output stream
     * @throws UncheckedIOException
     *             the unchecked io exception
     * @since 1.0.8
     */
    private static void writeUseNIO(int bufferLength,InputStream inputStream,OutputStream outputStream) throws UncheckedIOException{
        int i = 0;
        int sumSize = 0;
        int j = 0;

        ///方案2 
        //As creme de la creme with regard to performance, you could use NIO Channels and ByteBuffer. 

        ReadableByteChannel readableByteChannel = Channels.newChannel(inputStream);
        WritableByteChannel writableByteChannel = Channels.newChannel(outputStream);

        ByteBuffer byteBuffer = ByteBuffer.allocate(bufferLength);

        try{
            while (readableByteChannel.read(byteBuffer) != -1){
                byteBuffer.flip();
                j = writableByteChannel.write(byteBuffer);
                sumSize += j;
                byteBuffer.clear();
                i++;
            }

            if (log.isDebugEnabled()){
                log.debug("Write data over,sumSize:[{}],bufferLength:[{}],loopCount:[{}]", FileUtil.formatSize(sumSize), bufferLength, i);
            }
        }catch (IOException e){
            throw new UncheckedIOException(e);
        }finally{
            try{
                if (writableByteChannel != null){
                    outputStream.close();
                    writableByteChannel.close();
                }
                if (readableByteChannel != null){
                    inputStream.close();
                    readableByteChannel.close();
                }
            }catch (IOException e){
                throw new UncheckedIOException(e);
            }
        }
    }

    /**
     * 使用IO API来写数据.
     *
     * @param bufferLength
     *            the buffer length
     * @param inputStream
     *            the input stream
     * @param outputStream
     *            the output stream
     * @throws UncheckedIOException
     *             the unchecked io exception
     * @since 1.0.8
     * @deprecated use {@link #writeUseNIO(int, InputStream, OutputStream)}
     */
    @Deprecated
    private static void writeUseIO(int bufferLength,InputStream inputStream,OutputStream outputStream) throws UncheckedIOException{
        int i = 0;
        int sumSize = 0;
        int j = 0;

        byte[] bytes = new byte[bufferLength];
        try{
            //从输入流中读取一定数量的字节，并将其存储在缓冲区数组bytes 中。以整数形式返回实际读取的字节数。在输入数据可用、检测到文件末尾或者抛出异常前，此方法一直阻塞。 
            //如果 bytes 的长度为 0，则不读取任何字节并返回 0；否则，尝试读取至少一个字节。
            //如果因为流位于文件末尾而没有可用的字节，则返回值 -1；否则，至少读取一个字节并将其存储在 bytes 中。 
            //将读取的第一个字节存储在元素 bytes[0] 中，下一个存储在 b[1] 中，依次类推。
            //读取的字节数最多等于 b 的长度。设 k 为实际读取的字节数；这些字节将存储在 b[0] 到 b[k-1] 的元素中，不影响 b[k] 到 b[b.length-1] 的元素。 
            //类 InputStream 的 read(b) 方法的效果等同于：read(b, 0, b.length) 
            while ((j = inputStream.read(bytes)) != -1){

                //迅雷下载会报下面的异常，但是不影响下载效果
                //ClientAbortException:  java.net.SocketException: Software caused connection abort: socket write error
                outputStream.write(bytes, 0, j);
                i++;
                sumSize = sumSize + j;

                //if (log.isDebugEnabled()){
                //	log.debug(
                //					"write data,index:[{}],bufferLength:[{}],currentLoopLength:[{}],sumSize:[{}]",
                //					i,
                //					bufferLength,
                //					j,
                //					FileUtil.formatSize(sumSize));
                //}
            }
            if (log.isDebugEnabled()){
                log.debug("write data over,sumSize:[{}],bufferLength:[{}],loopCount:[{}]", FileUtil.formatSize(sumSize), bufferLength, i);
            }

            //刷新此输出流并强制写出所有缓冲的输出字节。flush 的常规协定是：如果此输出流的实现已经缓冲了以前写入的任何字节，则调用此方法指示应将这些字节立即写入它们预期的目标。 
            outputStream.flush();
        }catch (IOException e){
            throw new UncheckedIOException(e);
        }finally{
            try{
                // 用完关闭流 是个好习惯,^_^
                if (outputStream != null){
                    outputStream.close();
                }
                if (inputStream != null){
                    inputStream.close();
                }
            }catch (IOException e){
                throw new UncheckedIOException(e);
            }
        }
    }

    // *******************************************************************************************

    /**
     * 将字符串写到文件中
     * 
     * <ul>
     * <li>如果文件不存在,自动创建;包括其父文件夹(级联创建文件夹)</li>
     * <li>如果文件存在,则覆盖旧文件 ,默认 以覆盖的模式 {@link FileWriteMode#COVER}内容.</li>
     * <li>如果不设置encode,则默认使用 {@link CharsetType#GBK}编码</li>
     * </ul>
     *
     * @param filePath
     *            文件路径
     * @param content
     *            字符串内容
     * @throws UncheckedIOException
     *             the unchecked io exception
     * @throws IllegalArgumentException
     *             <ul>
     *             <li>如果filePath文件存在,且isDirectory</li>
     *             <li>如果filePath文件存在,且是!canWrite</li>
     *             </ul>
     * @see FileWriteMode
     * @see CharsetType
     */
    public static void write(String filePath,String content) throws UncheckedIOException,IllegalArgumentException{
        String encode = null;
        write(filePath, content, encode);
    }

    /**
     * 将字符串/文字写到文件中.
     * 
     * <ul>
     * <li>如果文件不存在,自动创建;包括其父文件夹(级联创建文件夹)</li>
     * <li>如果文件存在,则覆盖旧文件 ,默认 以覆盖的模式 {@link FileWriteMode#COVER}内容.</li>
     * </ul>
     *
     * @param filePath
     *            文件路径
     * @param content
     *            字符串内容
     * @param encode
     *            编码,如果isNullOrEmpty,则默认使用 {@link CharsetType#GBK}编码 {@link CharsetType}
     * @throws UncheckedIOException
     *             the unchecked io exception
     * @throws IllegalArgumentException
     *             <ul>
     *             <li>如果filePath文件存在,且isDirectory</li>
     *             <li>如果filePath文件存在,且是!canWrite</li>
     *             </ul>
     * @see FileWriteMode
     * @see CharsetType
     * @see #write(String, String, String, FileWriteMode)
     */
    public static void write(String filePath,String content,String encode) throws UncheckedIOException,IllegalArgumentException{
        write(filePath, content, encode, FileWriteMode.COVER);
    }

    /**
     * 将字符串写到文件中.
     * 
     * <ul>
     * <li>如果文件不存在,自动创建,包括其父文件夹 (支持级联创建 文件夹)</li>
     * <li>如果文件存在则覆盖旧文件,可以通过 指定 {@link FileWriteMode#APPEND}来表示追加内容而非覆盖</li>
     * </ul>
     *
     * @param filePath
     *            文件路径
     * @param content
     *            字符串内容
     * @param encode
     *            编码,如果isNullOrEmpty,则默认使用 {@link CharsetType#GBK}编码 {@link CharsetType}
     * @param fileWriteMode
     *            写模式
     * @throws UncheckedIOException
     *             the unchecked io exception
     * @throws IllegalArgumentException
     *             <ul>
     *             <li>如果filePath文件存在,且isDirectory</li>
     *             <li>如果filePath文件存在,且是!canWrite</li>
     *             </ul>
     * @see FileWriteMode
     * @see CharsetType
     * @see com.feilong.commons.core.io.FileUtil#cascadeMkdirs(String)
     * @see java.io.FileOutputStream#FileOutputStream(File, boolean)
     */
    public static void write(String filePath,String content,String encode,FileWriteMode fileWriteMode) throws UncheckedIOException,
                    IllegalArgumentException{

        if (Validator.isNullOrEmpty(encode)){
            encode = CharsetType.GBK;
        }

        // **************************************************************************8
        File file = FileUtil.cascadeMkdirs(filePath);

        boolean append = (fileWriteMode == FileWriteMode.APPEND);

        try{
            OutputStream outputStream = new FileOutputStream(file, append);
            Writer outputStreamWriter = new OutputStreamWriter(outputStream, encode);

            Writer writer = new PrintWriter(outputStreamWriter);
            writer.write(content);
            writer.close();

            if (log.isInfoEnabled()){
                log.info(
                                "fileWriteMode:[{}],contentLength:[{}],encode:[{}],fileSize:[{}],absolutePath:[{}]",
                                fileWriteMode,
                                content.length(),
                                encode,
                                FileUtil.getFileFormatSize(file),
                                file.getAbsolutePath());
            }
        }catch (IOException e){
            throw new UncheckedIOException(e);
        }
    }
}
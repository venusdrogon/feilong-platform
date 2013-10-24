package elsepackage.temple.io;

public class Temp{
	//
	// 10.读取文件属性
	// //import java.io.*;
	// // 文件属性的取得
	// File af = new File(%%1);
	// if (af.exists()) {
	// System.out.println(f.getName() + "的属性如下： 文件长度为：" + f.length());
	// System.out.println(f.isFile() ? "是文件" : "不是文件");
	// System.out.println(f.isDirectory() ? "是目录" : "不是目录");
	// System.out.println(f.canRead() ? "可读取" : "不");
	// System.out.println(f.canWrite() ? "是隐藏文件" : "");
	// System.out.println("文件夹的最后修改日期为：" + new Date(f.lastModified()));
	// } else {
	// System.out.println(f.getName() + "的属性如下：");
	// System.out.println(f.isFile() ? "是文件" : "不是文件");
	// System.out.println(f.isDirectory() ? "是目录" : "不是目录");
	// System.out.println(f.canRead() ? "可读取" : "不");
	// System.out.println(f.canWrite() ? "是隐藏文件" : "");
	// System.out.println("文件的最后修改日期为：" + new Date(f.lastModified()));
	// }
	// if(f.canRead()){
	// %%2
	// }
	// if(f.canWrite()){
	// %%3
	// }
	//
	// 11.写入属性
	// //import java.io.*;
	// File filereadonly=new File(%%1);
	// try {
	// boolean b=filereadonly.setReadOnly();
	// }
	// catch (Exception e) {
	// System.out.println("拒绝写访问："+e.printStackTrace());
	// }
	//
	//
	// 31.文件简单加密
	// //import java.io.*;
	// try {
	// File f=new File((new File(%%1)).getPath()+"\\enc_"+(new
	// File(%%1)).getName().split("//")[1]);
	// String strFileName = f.getName();
	// FileInputStream fileInputStream = new
	// FileInputStream(%%2+"\\"+strFilename);
	// byte[] buffer = new byte[fileInputStream.available()];
	// FileInputStream.read(buffer);
	// fileInputStream.close();
	// for(int i=0;i<buffer.length;i++)
	// {
	// int ibt=buffer[i];
	// ibt+=100;
	// ibt%=256;
	// buffer[i]=(byte)ibt;
	// }
	// FileOutputStream fileOutputStream = new FileOutputStream(f);
	// fileOutputStream.write(buffer,0,buffer.length);
	// fileOutputStream.close();
	// }
	// catch(ArrayIndexOutOfBoundException e){
	// e.printStackTrace();
	// }
	// catch(IOException e){
	// e.printStackTrace();
	// }
	//
	// 32.文件简单解密
	// //import java.io.*;
	// try {
	// File f=new File(%%1);
	// String strFileName = f.getName();
	// FileInputStream fileInputStream = new
	// FileInputStream(%%2+"\\enc_"+strFilename);
	// byte[] buffer = new byte[fileInputStream.available()];
	// FileInputStream.read(buffer);
	// fileInputStream.close();
	// for(int i=0;i<buffer.length;i++)
	// {
	// int ibt=buffer[i];
	// ibt-=100;
	// ibt+=256;
	// ibt%=256;
	// buffer[i]=(byte)ibt;
	// }
	// FileOutputStream fileOutputStream = new FileOutputStream(f);
	// fileOutputStream.write(buffer,0,buffer.length);
	// fileOutputStream.close();
	// }
	// catch(ArrayIndexOutOfBoundException e){
	// e.printStackTrace();
	// }
	// catch(IOException e){
	// e.printStackTrace();
	// }
	//
	//
	// 46.Grep
	// /*
	// import java.util.regex.*;
	// import java.io.*;
	// */
	// throws Exception
	// Pattern pattern = Pattern.compile(%%1); // 第一个参数为需要匹配的字符串
	// Matcher matcher = pattern.matcher("");
	// String file = %%2;
	// BufferedReader br = null;
	// String line;
	// try {
	// br = new BufferedReader (new FileReader (file)); // 打开文件
	// } catch (IOException e) {
	// // 没有打开文件，则产生异常
	// System.err.println ("Cannot read '" + file
	// + "': " + e.getMessage());
	// }
	// while ((line = br.readLine()) != null) { // 读入一行，直到文件结束
	// matcher.reset (line); // 匹配字符串
	// if (matcher.find()) { // 如果有匹配的字符串，则输出
	// System.out.println (file + ": " + line);
	// }
	// }
	// br.close(); // 关闭文件
	//
	// 48.批量重命名
	// //import java.io.*;
	// File target = new File("%%1");
	// String[] files = target.list();
	// File f = null;
	// String filename = null;
	// for (String file : files) {
	// f = new File(target, file);
	// filename = f.getName();
	// if (filename.substring(filename.lastIndexOf(".")).equalsIgnoreCase(
	// "%%2")) {
	// f.renameTo(new File(target.getAbsolutePath(), filename.replace(
	// "%%2", "%%3")));
	// // 这里可以反复使用replace替换,当然也可以使用正则表达式来替换了 ".txt" ".bat"
	// }
	// }
	//
	// 49.文本查找替换
	// //import java.nio.*;
	// String s1=%%1;
	// String s2=%%2;
	// String s3=%%3;
	// int pos=%%4;
	// /*变量i和j分别表示主串和模式串中当前字符串的位置，k表示匹配次数*/
	// int i,j,k=0;
	// i = pos;
	// j = 0;
	// //将s1转化成StringBuffer型进行操作
	// repStr = new StringBuffer(s1);
	// while(i<repStr.length()&&j<s2.length())
	// {
	// if(repStr.charAt(i) == s2.charAt(j))
	// {
	// ++i; ++j;
	// if(j==s2.length())
	// {
	// /*j=s2.length()表示字符串匹配成功，匹配次数加1，此外对主串进行字符串替换*/
	// k = k+1;
	// repStr.replace(i-j,i,s3);
	// //将j进行重新赋值开始新的比较
	// j = 0;
	// }
	// }
	// else {i = i-j+1; j = 0;}
	// }
	// return k;
	//
	//
	//
	// 52.设置JDK环境变量
	// @echo off
	// IF EXIST %1\bin\java.exe (
	// rem 如输入正确的 Java2SDK 安装目录，开始设置环境变量
	// @setx JAVA_HOME %1
	// @setx path %path%;%JAVA_HOME%\bin
	// @setx classpath %classpath%;.
	// @setx classpath %classpath%;%JAVA_HOME%\lib\tools.jar
	// @setx classpath %classpath%;%JAVA_HOME%\lib\dt.jar
	// @setx classpath %classpath%;%JAVA_HOME%\jre\lib\rt.jar
	// @echo on
	// @echo Java 2 SDK 环境参数设置完毕，正常退出。
	// ) ELSE (
	// IF "%1"=="" (
	// rem 如没有提供安装目录，提示之后退出
	// @echo on
	// @echo 没有提供 Java2SDK 的安装目录,不做任何设置，现在退出环境变量设置。
	// ) ELSE (
	// rem 如果提供非空的安装目录但没有bin\java.exe，则指定的目录为错误的目录
	// @echo on
	// @echo 非法的 Java2SDK 的安装目录,不做任何设置，现在退出环境变量设置。
	// )
	// )
	// //http://sourceforge.net/projects/jregistrykey/
	// //import ca.beq.util.win32.registry.*;
	// //import java.util.*;
	// 1.打开键
	// RegistryKey r = new RegistryKey(RootKey.HKEY_LOCAL_MACHINE,
	// "SOFTWARE\\Microsoft\\Windows\\CurrentVersion\\Explorer\\Shell Folders");
	// 2.添加键
	// RegistryKey r = new RegistryKey(RootKey.HKEY_CURRENT_USER,
	// "Software\\BEQ Technologies");
	// r.create();
	// 9.写入字符串值
	// RegistryKey r = new RegistryKey(RootKey.HKEY_CURRENT_USER,
	// "Software\\BEQ Technologies");
	// RegistryValue v = new RegistryValue("myVal", ValueType.REG_SZ, "data");
	// r.setValue(v);
	// 6.获取DWORD值
	// RegistryKey r = new RegistryKey(RootKey.HKEY_CURRENT_USER,
	// "Software\\BEQ Technologies");
	// if(r.hasValue("myValue")) {
	// RegistryValue v = r.getValue("myValue");
	// v.setType(ValueType.REG_DWORD);
	// } // if
	//
	//
	// 53.选择文件夹对话框
	// /*
	// import java.io.*;
	// import javax.swing.*;
	// */
	// JFileChooser chooser = new JFileChooser();
	// chooser.setCurrentDirectory(new File("."));
	// chooser.setFileFilter(new javax.swing.filechooser.FileFilter() {
	// public boolean accept(File f) {
	// return f.getName().toLowerCase().endsWith(".gif")
	// || f.isDirectory();
	// }
	// public String getDescription() {
	// return "GIF Images";
	// }
	// });
	// int r = chooser.showOpenDialog(null);
	// if (r == JFileChooser.APPROVE_OPTION) {
	// String name = chooser.getSelectedFile().getPath();
	// // label.setIcon(new ImageIcon(name));
	// }
	//
	// 54.删除空文件夹
	// //import java.io.*;
	// File f=new File(%%1);
	// if (isFolerNull(f)) {
	// for (File file :f.listFiles()) {
	// if (file.list().length == 0) {
	// System.out.println(file.getPath());
	// file.delete();
	// }
	// }
	// }
	//
	
	//
	// 58.创建快捷方式
	// //import java.io.*;
	// try {
	// PrintWriter pw=new PrintWriter(new FileOutputStream("C:/a.bat"));
	// pw.println(%%1);"C:/a.txt"
	// pw.close();
	// }
	// catch(IOException e){
	// e.printStackTrace();
	// }
	//
	// 59.弹出快捷菜单
	// //MouseEvent e
	// JPopupMenu jpm=new JPopupMenu();
	// show(jpm,x,y);
	//
	// 70.获取磁盘所有分区后再把光驱盘符去除(用"\0"代替)，把结果放在数组allfenqu[] 中，数组中每个元素代表一个分区盘符，不包括 :\\
	// 这样的路径，allfenqu[]数组开始时存放的是所有盘符。
	// 当我用这样的代码测试结果是正确的，光驱盘符会被去掉：
	// String root; //root代表盘符路径
	// for(i=0;i<20;i++) //0-20代表最大的盘符数
	// {
	// root.Format("%c:\\",allfenqu[i]);
	// if(GetDriveType(root)==5)
	// allfenqu[i]='\0';
	// }
	//
	// 但我用这样的代码时结果却无法去掉光驱盘符，allfenqu[]中还是会包含光驱盘符：
	// String root;
	// for(i=0;i<20;i++)
	// {
	// root=allfenqu[i]+":\\";
	// if(GetDriveType(root)==5)
	// allfenqu[i]='\0';
	// }
	//
	//
	// 74.写图像到剪切板 setClipboardImage
	// /*
	// import java.awt.*;
	// import java.awt.datatransfer.*;
	// import java.io.*;
	// private final Image image;
	// */
	// Transferable trans = new Transferable() {
	// public DataFlavor[] getTransferDataFlavors() {
	// return new DataFlavor[] { DataFlavor.imageFlavor };
	// }
	//
	// public boolean isDataFlavorSupported(DataFlavor flavor) {
	// return DataFlavor.imageFlavor.equals(flavor);
	// }
	//
	// public Object getTransferData(DataFlavor flavor)
	// throws UnsupportedFlavorException, IOException {
	// if (isDataFlavorSupported(flavor))
	// return image;
	// throw new UnsupportedFlavorException(flavor);
	// }
	// };
	// Toolkit.getDefaultToolkit().getSystemClipboard().setContents(trans,
	// null);
	//
	//
	//
	// 78.拷贝文件名复制文件
	// package p1;
	// import java.io.*;
	// import java.awt.*;
	// import java.awt.datatransfer.*;
	// public class VCFileCopy {
	// public static void main(String[] args) {
	// //
	// // 取得系统剪贴板里可传输的数据构造的Java对象
	// Transferable t = Toolkit.getDefaultToolkit().getSystemClipboard()
	// .getContents(null);
	// try {
	// if (t != null && t.isDataFlavorSupported(DataFlavor.stringFlavor)) {
	// // 因为原系的剪贴板里有多种信息, 如文字, 图片, 文件等
	// // 先判断开始取得的可传输的数据是不是文字, 如果是, 取得这些文字
	//
	// String s = (String) t.getTransferData(DataFlavor.stringFlavor);
	// // 同样, 因为Transferable中的DataFlavor是多种类型的,
	// // 所以传入DataFlavor这个参数, 指定要取得哪种类型的Data.
	// System.out.println(s);
	// int bytesum = 0;
	// int byteread = 0;
	// File oldfile = new File(s);
	// try {
	// if (oldfile.exists()) { // 文件存在时
	// FileInputStream inStream = new FileInputStream(oldfile); // 读入原文件
	// FileOutputStream fs = new FileOutputStream(new File(
	// "C:\\"
	// ,
	// oldfile.getName()));
	// byte[] buffer = new byte[5120];
	// while ((byteread = inStream.read(buffer)) != -1) {
	// bytesum += byteread; // 字节数 文件大小
	// fs.write(buffer, 0, byteread);
	// }
	// inStream.close();
	// }
	// } catch (Exception e) {
	// System.out.println("复制单个文件操作出错");
	// e.printStackTrace();
	// }
	// }
	// } catch (UnsupportedFlavorException ex) {
	// ex.printStackTrace();
	// } catch (IOException ex) {
	// ex.printStackTrace();
	// }
	// }
	// } 
	//
	// 80.提取包含头文件列表
	// import java.io.*;
	// import java.util.regex.*;
	// import java.util.*;
	// public class InlineExt {
	// private String CurDir = System.getProperty("user.dir");
	// public InlineExt() {
	// Pattern pattern = Pattern.compile("include.*?\".*?.hpp\""); //
	// 第一个参数为需要匹配的字符串
	// Matcher matcher = pattern.matcher("");
	// File delfile = new File(CurDir);
	// File[] files2 = delfile.listFiles();
	// for (int l = 0; l < files2.length; l++) {
	// if (files2[l].isDirectory()) {
	// Set<String> ts = new LinkedHashSet<String>();
	// File file = new File(files2[l].getPath(), "StdAfx.cpp");
	// BufferedReader br = null;
	// FileWriter fw = null;
	// String line;
	// try {
	// br = new BufferedReader(new FileReader(file)); // 打开文件
	// while ((line = br.readLine()) != null) {
	// matcher.reset(line); // 匹配字符串
	// if (matcher.find()) { // 如果有匹配的字符串，则输出
	// ts.add(line.substring(line.indexOf('\"') + 1, line
	// .lastIndexOf('\"')));
	// }
	// }
	// Iterator<String> it = ts.iterator();
	// File file2 = new File(files2[l], "ReadMe.txt");
	// if (file2.exists()) {
	// fw = new FileWriter(file2);
	// while (it.hasNext()) {
	// fw.write("#include \"" + it.next() + "\"\r\n");
	// }
	// }
	// } catch (IOException e) {
	// // 没有打开文件，则产生异常
	// System.err.println("Cannot read '" + file + "': "
	// + e.getMessage());
	// continue;
	// } finally {
	// try {
	// if (br != null)
	// br.close();
	// if (fw != null)
	// fw.close();
	// } catch (IOException e) {
	// e.printStackTrace();
	// }
	// }
	// }
	// }
	// }
	// public static void main(String[] args) {
	// new InlineExt();
	// }
	// }
	//

	//
	// 82.把JButton或JTree组件写到一个流中
	// /*
	// 由于JButton和JTree都已经实现了Serializable接口，因此你所说的问题是可以做到的。
	// 使用ObjectInputStream读取文件中的对象，使用ObjectOutputStream把对象写入文件。如：
	// */
	// /*
	// import java.io.*;
	// import javax.swing.*;
	// */
	// public class Save {
	// public static void main(String[] args) {
	//
	// // Write
	// JButton button = new JButton("TEST Button");
	// JTree tree = new JTree();
	// try {
	// ObjectOutputStream outForButton = new ObjectOutputStream(
	// new FileOutputStream("button"));
	// outForButton.writeObject(button);
	// outForButton.close();
	// ObjectOutputStream outForTree = new ObjectOutputStream(
	// new FileOutputStream("tree"));
	// outForTree.writeObject(tree);
	// outForTree.close();
	// } catch (FileNotFoundException e) {
	// e.printStackTrace();
	// } catch (IOException e) {
	// e.printStackTrace();
	// }
	// // Read
	//
	// try {
	// ObjectInputStream inForButton = new ObjectInputStream(
	// new FileInputStream("button"));
	// JButton buttonReaded = (JButton) inForButton.readObject();
	//
	// ObjectInputStream inForTree = new ObjectInputStream(
	// new FileInputStream("tree"));
	// JTree treeReaded = (JTree) inForTree.readObject();
	// } catch (FileNotFoundException e) {
	// e.printStackTrace();
	// } catch (IOException e) {
	// e.printStackTrace();
	// } catch (ClassNotFoundException e) {
	// e.printStackTrace();
	// }
	// }
	// }
	//
	//
	// 87.菜单勾选/取消开机自启动程序
	// //http://sourceforge.net/projects/jregistrykey/
	// //import ca.beq.util.win32.registry.*;
	// //import java.util.*;
	// RegistryKey r = new RegistryKey(RootKey.HKEY_CURRENT_USER,
	// "Software\\BEQ Technologies");
	// RegistryValue v = new RegistryValue("run", ValueType.REG_SZ, "data");
	// r.setValue(v);
	//
	// /////////////////////////////////////////////////////////////
	//
	// 拖一个CheckBox
	//
	// 1、软件启动时给CheckBox重置状态：
	// //http://sourceforge.net/projects/jregistrykey/
	// //import ca.beq.util.win32.registry.*;
	// //import java.util.*;
	// RegistryKey R_local = Registry.LocalMachine;
	// RegistryKey R_run =
	// R_local.CreateSubKey(@"SOFTWARE\Microsoft\Windows\CurrentVersion\Run");
	// if (R_run.GetValue("BirthdayTipF") == null)
	// {
	// checkBox1.Checked = false;
	// }
	// else
	// {
	// checkBox1.Checked = true;
	// }
	// R_run.Close();
	// R_local.Close();
	//
	// 2、CheckChanged事件：
	//
	// private void checkBox1_CheckedChanged(object sender, EventArgs e)
	// {
	// string R_startPath = Application.ExecutablePath;
	// if (checkBox1.Checked == true)
	// {
	// RegistryKey R_local = Registry.LocalMachine;
	// RegistryKey R_run =
	// R_local.CreateSubKey(@"SOFTWARE\Microsoft\Windows\CurrentVersion\Run");
	// R_run.SetValue("BirthdayTipF", R_startPath);
	// R_run.Close();
	// R_local.Close();
	// }
	// else
	// {
	// try
	// {
	// RegistryKey R_local = Registry.LocalMachine;
	// RegistryKey R_run =
	// R_local.CreateSubKey(@"SOFTWARE\Microsoft\Windows\CurrentVersion\Run");
	// R_run.Deletue("BirthdayTipF", false);
	// R_run.Close();
	// R_local.Close();
	// }
	// catch (Exception ex)
	// {
	// MessageBox.Show("您需要管理员权限修改", "提示", MessageBoxButtons.OK,
	// MessageBoxIcon.Error);
	// throw;
	// }
	//
	// }
	// }
	// 
	// 89.模拟键盘输入字符串
	// /*
	// import java.awt.*;
	// import java.awt.event.*;
	// throws Exception{
	// */
	// static Robot robot;
	// static{
	// try {
	// robot = new Robot();
	// } catch (AWTException e) {}
	// }
	//
	// static void sendKey(String ks){
	// KeyStore k = KeyStore.findKeyStore(ks);
	// if(k!=null){
	// if(k.upCase)
	// upCase(k.v);
	// else
	// sendKey(k.v);
	// }
	// else{
	// for(int i=0; i<ks.length(); i++){
	// char c = ks.charAt(i);
	// if(c>='0'&&c<='9'){
	// sendKey(c);
	// }
	// else if(c>='a'&&c<='z'){
	// sendKey(c-32);
	// }
	// else if(c>='A'&&c<='Z'){
	// upCase(c);
	// }
	// }
	// }
	// }
	// private static void upCase(int kc){
	// robot.keyPress(KeyEvent.VK_SHIFT);
	// robot.keyPress(kc);
	// robot.keyRelease(kc);
	// robot.keyRelease(KeyEvent.VK_SHIFT);
	// }
	// private static void sendKey(int kc){
	// robot.keyPress(kc);
	// robot.keyRelease(kc);
	// }
	// static class KeyStore{
	// //special keys
	// final static KeyStore[] sp = {
	// new KeyStore("{Tab}",KeyEvent.VK_TAB),//tab
	// new KeyStore("{Enter}",KeyEvent.VK_ENTER),//enter
	// new KeyStore("{PUp}",KeyEvent.VK_PAGE_UP),//page up
	// new KeyStore("{<}",KeyEvent.VK_LESS),//<
	// new KeyStore("{Up}",KeyEvent.VK_UP),//up key
	// new KeyStore("{At}",KeyEvent.VK_AT,true),//@
	// new KeyStore("{Dollar}",KeyEvent.VK_DOLLAR,true),//$
	// };
	//
	// String k;
	// int v;
	// boolean upCase;
	// KeyStore(String k,int v){
	// this(k,v,false);
	// }
	//
	// KeyStore(String s,int i,boolean up){
	// k=s;
	// v=i;
	// upCase=up;
	// }
	// static KeyStore findKeyStore(String k){
	// for(int i=0; i<sp.length; i++){
	// if(sp[i].k.equals(k))
	// return sp[i];
	// }
	// return null;
	// }
	// }
	// Thread.sleep(1000);
	// sendKey("{Tab}");//tab
	// sendKey("{<}");//<
	// sendKey("abcd123AHahahAA");//abcd123AHahahAA
	// sendKey("{At}");//@
	// sendKey("{Dollar}");//$
	// sendKey("{Up}");//up arrow
	//
	//
	// 91.操作内存映射文件
	// /*
	// import java.io.*;
	// import java.nio.*;
	// import java.nio.channels.*;
	// */
	// private static int length = 0x8FFFFFF; // 128 Mb
	// MappedByteBuffer out =
	// new RandomAccessFile("test.dat", "rw").getChannel()
	// .map(FileChannel.MapMode.READ_WRITE, 0, length);
	// for(int i = 0; i < length; i++)
	// out.put((byte)'x');
	// System.out.println("Finished writing");
	// for(int i = length/2; i < length/2 + 6; i++)
	// System.out.print((char)out.get(i));
	//
	//
	// 99.计算获取文件夹中文件的MD5值
	// /*
	// import java.io.*;
	// import java.math.*;
	// import java.security.*;
	// import java.util.*;
	// */
	// public static String getFileMD5(File file) {
	// if (!file.isFile()){
	// return null;
	// }
	// MessageDigest digest = null;
	// FileInputStream in=null;
	// byte buffer[] = new byte[1024];
	// int len;
	// try {
	// digest = MessageDigest.getInstance("MD5");
	// in = new FileInputStream(file);
	// while ((len = in.read(buffer, 0, 1024)) != -1) {
	// digest.update(buffer, 0, len);
	// }
	// in.close();
	// } catch (Exception e) {
	// e.printStackTrace();
	// return null;
	// }
	// BigInteger bigInt = new BigInteger(1, digest.digest());
	// return bigInt.toString(16);
	// }
	// /**
	// * 获取文件夹中文件的MD5值
	// * @param file
	// * @param listChild ;true递归子目录中的文件
	// * @return
	// */
	// public static Map<String, String> getDirMD5(File file,boolean listChild)
	// {
	// if(!file.isDirectory()){
	// return null;
	// }
	// //<filepath,md5>
	// Map<String, String> map=new HashMap<String, String>();
	// String md5;
	// File files[]=file.listFiles();
	// for(int i=0;i<files.length;i++){
	// File f=files[i];
	// if(f.isDirectory()&&listChild){
	// map.putAll(getDirMD5(f, listChild));
	// } else {
	// md5=getFileMD5(f);
	// if(md5!=null){
	// map.put(f.getPath(), md5);
	// }
	// }
	// }
	// return map;
	// }
	// getDirMD5(%%1,%%2);
}

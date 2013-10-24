package elsepackage;
import java.io.IOException;
import java.io.PrintStream;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.Proxy;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

public class ProxyTest{

	Proxy			proxy;

	URL				url;

	URLConnection	conn;

	//从网络通过代理读数据
	Scanner			scan;

	PrintStream		ps;

	//下面是代理服务器的地址和端口，
	//换成实际有效的代理服务器的地址和端口
	String			proxyAddress	= "202.128.23.32";

	int				proxyPort;

	//下面是你试图打开的网站地址
	String			urlStr			= "http://www.oneedu.cn";

	public void init(){
		try{
			url = new URL(urlStr);
			//创建一个代理服务器对象
			proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(proxyAddress, proxyPort));
			//使用指定的代理服务器打开连接
			conn = url.openConnection(proxy);
			//设置超时时长。
			conn.setConnectTimeout(5000);
			scan = new Scanner(conn.getInputStream());
			//初始化输出流
			ps = new PrintStream("Index.htm");
			while (scan.hasNextLine()){
				String line = scan.nextLine();
				//在控制台输出网页资源内容
				System.out.println(line);
				//将网页资源内容输出到指定输出流
				ps.println(line);
			}
		}catch (MalformedURLException ex){
			System.out.println(urlStr + "不是有效的网站地址！");
		}catch (IOException ex){
			ex.printStackTrace();
		}
		//关闭资源
		finally{
			if (ps != null){
				ps.close();
			}
		}
	}

	public static void main(String[] args){
		new ProxyTest().init();
	}
}

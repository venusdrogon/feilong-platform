package elsepackage.temple.io;


public class FeLongIoTemple{

	public static void main(String[] args){
		FeLongIoTemple feLongIoTemple = new FeLongIoTemple();
		feLongIoTemple.Print();
	}

	public void Print(){
		// /E:/Workspaces/eclipse3.5/feilong-platform/feilong-common/target/classes/
		String a = this.getClass().getClassLoader().getResource(".").getPath();
		// /E:/Workspaces/eclipse3.5/feilong-platform/feilong-common/target/classes/temple/io/
		String b = this.getClass().getResource("").getPath();
		// /E:/Workspaces/eclipse3.5/feilong-platform/feilong-common/target/classes/temple/io/%20
		String c = this.getClass().getResource(" ").getPath();
		// 获得编译类根目录
		// /E:/Workspaces/eclipse3.5/feilong-platform/feilong-common/target/classes/
		String d = this.getClass().getResource("/").getPath();
		// 获得应用程序完整路径
		// E:\Workspaces\eclipse3.5\feilong-platform\feilong-common
		String e = System.getProperty("user.dir");
		System.out.println(a);
		System.out.println(b);
		System.out.println(c);
		System.out.println(d);
		Thread thread = new Thread();
		thread.suspend();
		System.out.println(e);
	}
}

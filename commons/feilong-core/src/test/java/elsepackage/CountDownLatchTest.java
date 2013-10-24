package elsepackage;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class CountDownLatchTest{

	public static int		numberOfPeople	= 10;		//等车的学生数

	public static boolean	isGone			= false;	//车开的标志

	public static int		carWaitTime		= 3;		//车等的时间

	public static void main(String[] args) throws InterruptedException{
		CountDownLatch waitStudentsGetOn = new CountDownLatch(numberOfPeople);
		new Thread(new GetOn(waitStudentsGetOn)).start();
		waitStudentGetOn(waitStudentsGetOn);//等所有的学生上车
		driveHome();//开车走
	}

	private static void waitStudentGetOn(CountDownLatch waitStudentsGetOn) throws InterruptedException{
		System.out.println("赶紧的,抓紧时间上车..");
		waitStudentsGetOn.await(carWaitTime, TimeUnit.SECONDS);//等5秒，还没上车，就开走。。
	}

	private static void driveHome() throws InterruptedException{
		System.out.println("开车，鞋儿破 帽儿破 身上的袈裟破 你笑我 他笑我 一把扇儿破");
		isGone = true;
	}
}

class GetOn implements Runnable{

	private CountDownLatch	waitStudentsGetOn;

	GetOn(CountDownLatch waitStudentsGetOn){
		this.waitStudentsGetOn = waitStudentsGetOn;
	}

	public void run(){
		for (int i = 0; i < CountDownLatchTest.numberOfPeople; i++){
			try{
				if (CountDownLatchTest.isGone){
					System.out.println("妈的，还差：" + waitStudentsGetOn.getCount() + " 个没娃上车呢.怎么车走了");
					break;
				}
				boolean goonSuccess = new Student(i + 1).getOn();//顺序上车
				if (goonSuccess)
					waitStudentsGetOn.countDown();
			}catch (InterruptedException e){}
			if (waitStudentsGetOn.getCount() != 0l){
				System.out.println("还差：" + (waitStudentsGetOn.getCount()) + " 个没上车 ");
			}else{
				System.out.println("都上车了 ");
			}
		}
	}

	class Student{

		private int	myNum;	//学生编号

		public Student(int num){
			this.myNum = num;
		}

		//上车
		public boolean getOn() throws InterruptedException{
			Thread.currentThread().sleep(new Random().nextInt(2) * 1000);//上车使用的时间，随机
			if (CountDownLatchTest.isGone){
				return false;//不能上了，上车失败
			}
			System.out.print("编号为:" + myNum + "的同学上车了..");
			return true;
		}
	}
}

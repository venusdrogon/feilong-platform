package train.exception;

public class HelloGoodbye1{

	static{
		Runtime.getRuntime().addShutdownHook(new Thread(){

			public void run(){
				System.out.println("Goodbye world");
			}
		});
	}

	public static void main(String[] args){
		System.out.println("Hello world");

		System.exit(0);
	}
}

package elsepackage.temple.lang.annotation;

import java.lang.reflect.Method;

import org.apache.commons.lang3.ArrayUtils;


@MyAnnotation(name = "feilong",sex = 0,loveStrings = { "胡伟立", "三国" })
public class AnnotationTest{

	@MyAnnotation(name = "feilong",sex = 0,loveStrings = { "王菲" })
	public void limei(){}

	@MyAnnotation(name = "金鑫",sex = 1,loveStrings = { "胡伟立", "三国" })
	public void jinxin(){}

	public static void main(String[] args){
		System.out.println(AnnotationTest.class.isAnnotationPresent(MyAnnotation.class));
		MyAnnotation myAnnotation = AnnotationTest.class.getAnnotation(MyAnnotation.class);
		System.out.println(myAnnotation.name());
		// *************************************************************
		Method[] methods = AnnotationTest.class.getDeclaredMethods();
		for (Method method : methods){
			if (method.isAnnotationPresent(MyAnnotation.class)){
				System.out.println("[Test." + method.getName() + "].annotation:");
				MyAnnotation fieldAnnotation = method.getAnnotation(MyAnnotation.class);
				System.out.println(ArrayUtils.toString(fieldAnnotation.loveStrings()));
			}
		}
	}
}

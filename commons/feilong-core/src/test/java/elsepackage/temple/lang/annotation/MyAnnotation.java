package elsepackage.temple.lang.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
// 这个Annotation可以被写入javadoc
@Inherited
// 这个Annotation 可以被继承
@Target({ ElementType.TYPE, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
public @interface MyAnnotation{

	public String	name_english	= "venusdrogon";

	/**
	 * name属性
	 * 
	 * @return
	 */
	public String name() default "金鑫";

	/**
	 * 性别 1=男
	 * 
	 * @return
	 */
	public int sex() default 1;

	public String[] loveStrings();
}

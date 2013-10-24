package loxia.support.cache.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Cacheable{

	String value() default "";

	int expire() default 60 * 60 * 24 * 29;

	boolean cacheKey() default true;
}

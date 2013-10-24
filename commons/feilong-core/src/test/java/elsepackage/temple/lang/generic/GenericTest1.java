package elsepackage.temple.lang.generic;

import java.lang.reflect.ParameterizedType;
import java.util.LinkedList;
import java.util.List;

public class GenericTest1{

	public List<String>	list	= new LinkedList<String>();

	public static void main(String[] args) throws SecurityException,NoSuchFieldException{
		ParameterizedType pt = (ParameterizedType) GenericTest1.class.getField("list").getGenericType();
		System.out.println(pt.getActualTypeArguments().length);
		System.out.println(pt.getActualTypeArguments()[0]);
	}
}

package jdk.java.util;

import java.util.Locale;

public class LocaleTest{

	public static void main(String[] args){
		Locale myLocale = Locale.getDefault();
		System.out.println(myLocale.getCountry());
		System.out.println(myLocale.getLanguage());
		System.out.println(myLocale.getDisplayCountry());
		System.out.println(myLocale.getDisplayLanguage());
		System.out.println(2.00 - 1.10);
	}
}

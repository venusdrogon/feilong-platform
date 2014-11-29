/*
 * Copyright (C) 2008 feilong (venusdrogon@163.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package train.exception;

interface Type1{

	void f() throws CloneNotSupportedException;
}

interface Type2{

	void f() throws InterruptedException;
}

interface Type3 extends Type1,Type2{}

/**
 * The Class Arcane3.
 */
public class Arcane3 implements Type3{

	/* (non-Javadoc)
	 * @see train.exception.Type1#f()
	 */
	public void f(){
		System.out.println("Hello world");
	}

	/**
	 * The main method.
	 *
	 * @param args
	 *            the args
	 */
	public static void main(String[] args){
		Type3 t3 = new Arcane3();
		t3.f();
	}
}

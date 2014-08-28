package com.feilong.childandparent;

@SuppressWarnings("all")
public class Child extends Parent{

	private String	age;

	public Child(){}

	public Child(String name, String address){
		super(name, address);
		this.age = age;
	}

	public void func(Integer[] a){
		System.out.println("Child");
	};

	public void setAge(String age){
		this.age = age;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode(){
		final int prime = 31;
		int result = 1;
		result = prime * result + ((age == null) ? 0 : age.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Object obj){
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Child other = (Child) obj;
		if (age == null){
			if (other.age != null)
				return false;
		}else if (!age.equals(other.age))
			return false;
		return true;
	}

	public String getAge(){
		return age;
	}
}

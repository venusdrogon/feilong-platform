package loxia.dao;

import java.io.Serializable;

@SuppressWarnings("all")
public class Sort implements Serializable{

	private static final long	serialVersionUID	= -1392714341585793291L;

	public static final String	ASC					= "asc";

	public static final String	DESC				= "desc";

	public Sort(String sortStr){
		String[] strs = sortStr.trim().split(" ");
		this.field = strs[0];
		if (strs.length > 1){
			if (ASC.equalsIgnoreCase(strs[1])){
				this.type = ASC;
			}else if (DESC.equalsIgnoreCase(strs[1])){
				this.type = DESC;
			}else{
				throw new IllegalArgumentException("Wrong sort type definition, only asc or desc are supported.");
			}
		}
	}

	public Sort(String field, String type){
		this.field = field;
		this.type = type;
	}

	private String	field;

	private String	type	= ASC;

	public String getField(){
		return field;
	}

	public void setField(String field){
		this.field = field;
	}

	public String getType(){
		return type;
	}

	public void setType(String type){
		this.type = type;
	}

	@Override
	public int hashCode(){
		final int prime = 31;
		int result = 1;
		result = prime * result + ((field == null) ? 0 : field.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj){
		if (this == obj){
			return true;
		}
		if (obj == null){
			return false;
		}
		if (getClass() != obj.getClass()){
			return false;
		}
		Sort other = (Sort) obj;
		if (field == null){
			if (other.field != null){
				return false;
			}
		}else if (!field.equals(other.field)){
			return false;
		}
		if (type == null){
			if (other.type != null){
				return false;
			}
		}else if (!type.equals(other.type)){
			return false;
		}
		return true;
	}

	@Override
	public String toString(){
		return field + " " + type;
	}

}

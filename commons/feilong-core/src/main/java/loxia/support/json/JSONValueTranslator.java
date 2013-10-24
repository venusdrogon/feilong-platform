package loxia.support.json;

public interface JSONValueTranslator{

	String toJSONValueString(Object obj) throws JSONException;

	Object toConcreteObject(String str,Class<? extends Object> clazz) throws JSONException;
}

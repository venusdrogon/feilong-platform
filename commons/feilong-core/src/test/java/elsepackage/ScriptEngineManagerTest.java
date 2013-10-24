package elsepackage;
import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class ScriptEngineManagerTest{

	public static void main(String[] args){
		ScriptEngineManager manager = new ScriptEngineManager();
		ScriptEngine engine = manager.getEngineByExtension("js");
		try{
			engine.eval("function myFunction(name){var output = '';" + "  for (i = 0; i <= name.length; i++) {output = name.charAt(i)+'-'+ output"
					+ "  } return output;}");
			Invocable invokeEngine = (Invocable) engine;
			Object o = invokeEngine.invokeFunction("myFunction", "abcde");
			System.out.println(o);
		}catch (NoSuchMethodException e){
			System.err.println(e);
		}catch (ScriptException e){}
	}
}

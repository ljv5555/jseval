import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.util.Arrays;

/**
 * 
 */

/**
 * @author jverderber
 *
 */
public class jseval {

	/**
	 * @param args
	 */
	public static void main(String[] args) 
	{
		try{
			ProcessBuilder pb = (new java.lang.ProcessBuilder()).command( Arrays.asList(new String[]{"pwd"})).redirectErrorStream(true);
			Process p = pb.start();
			BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
			StringWriter sw = new StringWriter();
			p.waitFor();
			int i = br.read();
			while(i>-1)
			{
				sw.write(i);
				i=br.read();
			}
			try{sw.flush();br.close();}catch(Throwable t){}
			String allargs = " ";
			for(String arg : args){allargs=allargs+(" "+ arg);}
			allargs+=" ";
			System.out.print(String.valueOf((new javax.script.ScriptEngineManager()).getEngineByName("JavaScript").eval("(function(){ return ('' + ( "+allargs+" ) ); })()")));
		}catch(Exception ex){ex.printStackTrace();}
	}

}

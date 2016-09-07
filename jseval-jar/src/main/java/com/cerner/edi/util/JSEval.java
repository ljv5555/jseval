package com.cerner.edi.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Arrays;

import javax.script.ScriptEngineManager;

public class JSEval
{

    public static void main(String[] args){new JSEval(args);}
    public JSEval(String args[])
    {
        if (!(args.length == 1 && new File(args[0]).exists()))
        {
            System.out.println("Usage: ");
            System.out.println(" [java command]       ... " + getClass().getName() + " /tmp/inputFile.js");
            if (args.length == 1)
            {
                System.out.println("Error: file not found: " + new File(args[0]).getAbsolutePath());
            }
            System.exit(-1);
        }

        String fileContents = "error reading file: " + new File(args[0]).getAbsolutePath();
        Object rtn = "js error: \r\n--- --- ---\r\n" + fileContents+"\r\n--- --- --- \r\n";
        
        try
        {
            rtn = JSEval.JSEvalFile(new File(args[0]).getAbsolutePath());
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        System.out.println(""+rtn);
    }



    public static String readFile(InputStream in)
    {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        while (true)
        {
            String line = null;
            try
            {
                line = br.readLine();
            }
            catch (Exception e)
            {
            }
            if (line == null)
            {
                break;
            }
            pw.println(line);
        }
        try
        {
            br.close();
        }
        catch (Exception e)
        {
        }
        pw.close();
        return sw.toString();
    }


    public static Object JSEvalFile(String jsFile)
    {
        Object rtn = "JSEvalFile error: ---------" + jsFile + "---------";
        try
        {
            rtn = new ScriptEngineManager().getEngineByName("JavaScript").eval(readFile(new FileInputStream(new File(jsFile))));
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return rtn;
    }

        
 
}

package com.cerner.edi.util;

import static org.junit.Assert.assertTrue;

import java.io.FileWriter;

import org.junit.Test;

public class TestJSEval
{

    @Test
    public void testJsEval() throws Exception
    {
        FileWriter f = new FileWriter("tmp1.js",false);
        f.write("5+4");
        f.close();
        System.out.println(JSEval.JSEvalFile("tmp1.js"));
        assertTrue((""+JSEval.JSEvalFile("tmp1.js")).trim().indexOf("9")!=-1);
    }

}

package com.codenjoy.dojo.client;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class UtilsTest {

    @Test
    public void testPrettyPrintString() {
        assertEquals("{\n" +
                        "    'field1': 'string1',\n" +
                        "    'field2': [\n" +
                        "        'string2',\n" +
                        "        'string3'\n" +
                        "    ]\n" +
                        "}",
                Utils.prettyPrint("{'field1':'string1','field2':['string2','string3']}"));
    }

    @Test
    public void testPrettyPrintString_withSubObject(){
        assertEquals("{\n" +
                    "    'field1': 'string1',\n" +
                    "    'field2': {\n" +
                    "        'string1': true,\n" +
                    "        'string2': 2,\n" +
                    "        'string3': 'data'\n" +
                    "    }\n" +
                    "}",
                Utils.prettyPrint("{'field2':{'string2':2,'string1':true,'string3':'data'},'field1':'string1'}"));
    }

    @Test
    public void testPrettyPrintString_caseDoubleQuotes() {
        assertEquals("{\n" +
                    "    'current': 0,\n" +
                    "    'lastPassed': -1,\n" +
                    "    'multiple': false,\n" +
                    "    'scores': true,\n" +
                    "    'total': 1\n" +
                    "}",
                Utils.prettyPrint("{\"total\":1,\"scores\":true,\"current\":0,\"lastPassed\":-1,\"multiple\":false}"));
    }
}

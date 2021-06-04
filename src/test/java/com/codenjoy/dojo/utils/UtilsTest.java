package com.codenjoy.dojo.utils;

/*-
 * #%L
 * Codenjoy - it's a dojo-like platform from developers to developers.
 * %%
 * Copyright (C) 2018 Codenjoy
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/gpl-3.0.html>.
 * #L%
 */


import org.json.JSONArray;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class UtilsTest {

    @Test
    public void testGetStrings() {
        assertGetStrings("['string1', 'string2', 'string3']",
                "[string1, string2, string3]");

        assertGetStrings("['string']",
                "[string]");

        assertGetStrings("[]",
                "[]");
    }

    private void assertGetStrings(String source, String expected) {
        assertEquals(expected, Utils.getStrings(new JSONArray(source)).toString());
    }

    @Test
    public void testInject() {
        assertEquals("1234^*5678^*90AB^*CDEF^*HIJK^*LMNO^*PQRS^*TUVW^*XYZ",
                Utils.inject("1234567890ABCDEFHIJKLMNOPQRSTUVWXYZ", 4, "^*"));

        assertEquals("1234^*5678^*90AB^*CDEF^*HIJK^*LMNO^*PQRS^*TUVW^*",
                Utils.inject("1234567890ABCDEFHIJKLMNOPQRSTUVW", 4, "^*"));

        assertEquals("1234^*5678^*90AB^*CDEF^*HIJK^*LMNO^*PQRS^*TUV",
                Utils.inject("1234567890ABCDEFHIJKLMNOPQRSTUV", 4, "^*"));
    }

    @Test
    public void testInjectN() {
        assertEquals("12345\n" +
                    "67890\n" +
                    "ABCDE\n" +
                    "FHIJK\n" +
                    "LMNOP\n" +
                    "QRSTU\n" +
                    "VWXYZ\n",
                Utils.injectN("1234567890ABCDEFHIJKLMNOPQRSTUVWXYZ"));

        assertEquals("1234\n" +
                    "5678\n" +
                    "90AB\n" +
                    "CDEF\n" +
                    "HIJK\n" +
                    "LMNO\n",
                Utils.injectN("1234567890ABCDEFHIJKLMNO"));
    }
}

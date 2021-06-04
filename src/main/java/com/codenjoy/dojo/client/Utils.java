package com.codenjoy.dojo.client;

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


import org.apache.commons.text.StringEscapeUtils;
import org.json.JSONArray;
import org.json.SortedJSONObject;

import java.util.LinkedList;
import java.util.List;

public class Utils {

    public static String injectN(String expected) {
        int size = (int) Math.sqrt(expected.length());
        return inject(expected, size, "\n");
    }

    public static String inject(String string, int position, String substring) {
        StringBuilder result = new StringBuilder();
        for (int index = 1; index < string.length() / position + 1; index++) {
            result.append(string, (index - 1)*position, index*position).append(substring);
        }
        result.append(string.substring((string.length() / position) * position));
        return result.toString();
    }

    public static List<String> getStrings(JSONArray array) {
        List<String> result = new LinkedList<>();
        for (Object object : array.toList()) {
            result.add((String)object);
        }
        return result;
    }

    public static String clean(String json) {
        return json.replace('\"', '\'').replaceAll("\\r\\n", "\n");
    }

    // TODO почему-то этот малый слетает в MVN при билде из консоли для символов борды expansion
    public static String prettyPrint(String jsonString) {
        return clean(new SortedJSONObject(jsonString).toString(4));
    }

    public static String unescapeJava(String data) {
        return StringEscapeUtils.unescapeJava(data.replaceAll("\\\\u", "\\u"));
    }

}

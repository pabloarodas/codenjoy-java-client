package com.codenjoy.dojo.games.hex;

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


import com.codenjoy.dojo.services.printer.CharElements;

import java.util.*;

public enum Element implements CharElements {

    NONE(' '),
    WALL('☼'),
    MY_HERO('☺'),
    HERO1('☻'),
    HERO2('♥'),
    HERO3('♦'),
    HERO4('♣'),
    HERO5('♠'),
    HERO6('•'),
    HERO7('◘'),
    HERO8('○'),
    HERO9('◙'),
    HERO10('♂'),
    HERO11('♀');

    final char ch;

    Element(char ch) {
        this.ch = ch;
    }

    @Override
    public char ch() {
        return ch;
    }

    @Override
    public String toString() {
        return String.valueOf(ch);
    }

    public static List<Element> heroesElements() {
        return new LinkedList<>(Arrays.asList(Element.values())) {{
            remove(Element.NONE);
            remove(Element.WALL);
            remove(Element.MY_HERO);
        }};
    }

    private static Map<Character, Element> elements = new HashMap<>(){{
        Arrays.stream(Element.values())
                .forEach(el -> put(el.ch, el));
    }};

    // optimized for performance
    public static Element valueOf(char ch) {
        Element elements = Element.elements.get(ch);
        if (elements == null) {
            throw new IllegalArgumentException("No such element for " + ch);
        }
        return elements;
    }

}

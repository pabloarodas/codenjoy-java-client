package com.codenjoy.dojo.games.a2048;

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


import com.codenjoy.dojo.services.printer.CharElement;

import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;

public enum Element implements CharElement {

    _x('x'),
    _2('2'),
    _4('4'),
    _8('8'),
    _16('A'),
    _32('B'),
    _64('C'),
    _128('D'),
    _256('E'),
    _512('F'),
    _1024('G'),
    _2048('H'),
    _4096('I'),
    _8192('J'),
    _16384('K'),
    _32768('L'),
    _65536('M'),
    _131072('N'),
    _262144('O'),
    _524288('P'),
    _1048576('Q'),
    _2097152('R'),
    _4194304('S'),
    NONE(' ');

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

    public static Element valueOf(int number) {
        return Element.valueOf("_" + String.valueOf(number));
    }

    public static Element[] valuesExcept(Element... excluded) {
        List<Element> list = Arrays.asList(excluded);
        return Arrays.stream(values())
                .filter(el -> !list.contains(el))
                .collect(toList())
                .toArray(new Element[0]);
    }

    public static Element valueOf(char ch) {
        for (Element el : Element.values()) {
            if (el.ch == ch) {
                return el;
            }
        }
        throw new IllegalArgumentException("No such element for " + ch);
    }

    public int number() {
        if (this == NONE) {
            return 0;
        }
        if (this == _x) {
            return -1;
        }
        return Integer.parseInt(super.toString().substring(1));
    }
}

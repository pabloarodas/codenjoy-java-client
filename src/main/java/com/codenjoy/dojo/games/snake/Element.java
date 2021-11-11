package com.codenjoy.dojo.games.snake;

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

public enum Element implements CharElement {

/// Board stuff

    BAD_APPLE('☻',       "Stone. "),
    GOOD_APPLE('☺',      "Having eaten it, you shorten it in length. " +
                         "If it is not long enough, you die."),

    BREAK('☼',           "An obstacle that cannot be passed. " +
                         "It is also the border of the field."),

    NONE(' ',            "An empty place in the field where the snake can go."),

/// Snake head

    HEAD_DOWN('▼',       "Snake head is pointing down."),

    HEAD_LEFT('◄',       "Snake head is pointing left."),

    HEAD_RIGHT('►',      "Snake head is pointing right."),

    HEAD_UP('▲',         "Snake head is pointing up."),

/// Snake tail middle

    TAIL_HORIZONTAL('═', "Horizontal part of the body."),

    TAIL_VERTICAL('║',   "Vertical part of the body."),

    TAIL_LEFT_DOWN('╗',  "Turning the snake body from left to down."),

    TAIL_LEFT_UP('╝',    "Turning the snake body from left to up."),

    TAIL_RIGHT_DOWN('╔', "Turning the snake body from right to down."),

    TAIL_RIGHT_UP('╚',   "Turning the snake body from right to up."),

/// Snake tail end

    TAIL_END_DOWN('╙',   "Down tail."),

    TAIL_END_LEFT('╘',   "Left tail."),

    TAIL_END_UP('╓',     "Up tail."),

    TAIL_END_RIGHT('╕',  "Right tail.");

    private final char ch;
    private final String info;

    Element(char ch, String info) {
        this.ch = ch;
        this.info = info;
    }

    @Override
    public char ch() {
        return ch;
    }

    @Override
    public String info() {
        return info;
    }

    @Override
    public String toString() {
        return String.valueOf(ch);
    }

    public static Element valueOf(char ch) {
        for (Element el : Element.values()) {
            if (el.ch == ch) {
                return el;
            }
        }
        throw new IllegalArgumentException("No such element for " + ch);
    }
}

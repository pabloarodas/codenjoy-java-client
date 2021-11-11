package com.codenjoy.dojo.games.snakebattle;

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

    NONE(' ',             "Empty space - space where the snake can move."),

    WALL('☼',             "Impenetrable wall."),

    START_FLOOR('#',      "Respawn point from which the snake starts its movement."),

    OTHER('?',            "."),

    APPLE('○',            "Apple."),

    STONE('●',            "Stone."),

    FLYING_PILL('©',      "Flying pill/Angel's wings."),

    FURY_PILL('®',        "Fury pill/Devil's mask."),

    GOLD('$',             "Gold."),

/// голова твоей змеи в разных состояниях и напрвлениях

    HEAD_DOWN('▼',        "Your snake head pointing down."),

    HEAD_LEFT('◄',        "Your snake head pointing left."),

    HEAD_RIGHT('►',       "Your snake head pointing right."),

    HEAD_UP('▲',          "Your snake head pointing up."),

    HEAD_DEAD('☻',        "Your snake is dead."),

    HEAD_EVIL('♥',        "Your snake head under influence Fury pill/Devils mask."),

    HEAD_FLY('♠',         "Your snake head under influence Flying pill/Angels wings."),

    HEAD_SLEEP('&',       "Your snake head when snake is inactive."),

/// туловище твоей змеи

    BODY_HORIZONTAL('═',  "Body of your snake is directed horizontally."),

    BODY_VERTICAL('║',    "Body of your snake is directed vertically."),

    BODY_LEFT_DOWN('╗',   "Turning your snake body from left to down."),

    BODY_LEFT_UP('╝',     "Turning your snake body from left to up."),

    BODY_RIGHT_DOWN('╔',  "Turning your snake body from right to down."),

    BODY_RIGHT_UP('╚',    "Turning your snake body from left to up."),

/// хвост твоей змеи

    TAIL_END_DOWN('╙',    "Your snake tail (end) pointing down."),

    TAIL_END_LEFT('╘',    "Your snake tail (end) pointing left."),

    TAIL_END_UP('╓',      "Your snake tail (end) pointing up."),

    TAIL_END_RIGHT('╕',   "Your snake tail (end) pointing right."),

    TAIL_INACTIVE('~',    "Your snake tail (end) when snake is inactive."),

/// голова змейки противника

    ENEMY_HEAD_DOWN('˅',  "Enemy snake head pointing down."),

    ENEMY_HEAD_LEFT('<',  "Enemy snake head pointing left."),

    ENEMY_HEAD_RIGHT('>', "Enemy snake head pointing right."),

    ENEMY_HEAD_UP('˄',    "Enemy snake head pointing up."),

    ENEMY_HEAD_DEAD('☺',  "Enemy snake is dead."),

    ENEMY_HEAD_EVIL('♣',  "Enemy snake head under influence Fury pill/Devils mask."),

    ENEMY_HEAD_FLY('♦',   "Enemy snake head under influence Flying pill/Angels wings."),

    ENEMY_HEAD_SLEEP('ø', "Enemy snake head when snake is inactive."),

/// туловище змейки противника

    ENEMY_BODY_HORIZONTAL('─', "Body of enemy snake is directed horizontally."),

    ENEMY_BODY_VERTICAL('│',   "Body of enemy snake is directed vertically."),

    ENEMY_BODY_LEFT_DOWN('┐',  "Turning enemy snake body from left to down."),

    ENEMY_BODY_LEFT_UP('┘',    "Turning enemy snake body from left to up."),

    ENEMY_BODY_RIGHT_DOWN('┌', "Turning enemy snake body from right to down."),

    ENEMY_BODY_RIGHT_UP('└',   "Turning enemy snake body from left to up."),

/// хвост змейки противника

    ENEMY_TAIL_END_DOWN('¤',   "Enemy snake tail (end) pointing down."),

    ENEMY_TAIL_END_LEFT('×',   "Enemy snake tail (end) pointing left."),

    ENEMY_TAIL_END_UP('æ',     "Enemy snake tail (end) pointing up."),

    ENEMY_TAIL_END_RIGHT('ö',  "Enemy snake tail (end) pointing right."),

    ENEMY_TAIL_INACTIVE('*',   "Enemy snake tail (end) when snake is inactive.");

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

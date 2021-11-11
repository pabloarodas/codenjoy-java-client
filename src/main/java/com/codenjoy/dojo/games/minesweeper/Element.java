package com.codenjoy.dojo.games.minesweeper;

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

/// Разное на поле

    BANG('Ѡ', -7,           "Сапер взорвался на бомбе."),

    HERE_IS_BOMB('☻', -6,   "На секунду после смерти героя поле открывается " +
                            "и можно увидеть где были бомбы."),

    DESTROYED_BOMB('x', -1, "...а так же какие бомбы удалось нейтрализовать."),

    DETECTOR('☺', -5,       "Сапер."),

    FLAG('‼', -4,           "Флажок сапера, указывающий что тут вероятно бомба. " +
                            "Если он был выставлен на мину - она тут нейтрализуется " +
                            "и цифры вокруг обновятся. Если нет - штраф."),

    HIDDEN('*', -3,         "Туман - место где еще не бывал сапер. Там может быть бомба."),

    BORDER('☼', -2,         "Граница поля или препятствие для перемещения."),

/// Маркеры бомб вокруг

    NONE(' ', 0,            "Вокруг этой клеточки нет бомб."),

    ONE_MINE('1', 1,        "Вокруг этой клеточки одна бомба."),

    TWO_MINES('2', 2,       "Вокруг этой клеточки две бомбы."),

    THREE_MINES('3', 3,     "Вокруг этой клеточки три бомбы."),

    FOUR_MINES('4', 4,      "Вокруг этой клеточки четыре бомбы."),

    FIVE_MINES('5', 5,      "Вокруг этой клеточки пять бомб."),

    SIX_MINES('6', 6,       "Вокруг этой клеточки шесть бомб."),

    SEVEN_MINES('7', 7,     "Вокруг этой клеточки семь бомб."),

    EIGHT_MINES('8', 8,     "Вокруг этой клеточки восемь бомб.");

    private final char ch;
    private final int value;
    private final String info;

    Element(char ch, int value, String info) {
        this.ch = ch;
        this.value = value;
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

    public int value() {
        return value;
    }

    @Override
    public String toString() {
        return String.valueOf(ch);
    }

    public static Element get(int value) {
        for (Element element : values()) {
            if (element.value == value) {
                return element;
            }
        }
        throw new IllegalArgumentException("Value not found for: " + value);
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

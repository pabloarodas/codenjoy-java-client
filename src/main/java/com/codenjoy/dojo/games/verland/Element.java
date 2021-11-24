package com.codenjoy.dojo.games.verland;

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

    HERO_DEAD('X', -7, "Герой заразился инфекцией."),

    HERO('♥', -5, "Герой."),

    HERO_CURE('!', -4, "Попытка героем зачистить инфекцию. " +
            "Если инфекция была устранена ситуация вокруг обновится. " +
            "Если герой ошибся и зона была не инфицирована - штраф."),

    HERO_HEALING('x', -1, "На секунду после окончания игры поле открывается " +
            "и можно увидеть какую инфекцию удалось обеззаразить герою."),

    OTHER_HERO_DEAD('Y', -7, "Герой из моей команды заразился инфекцией."),

    OTHER_HERO('♠', -5, "Герой из моей команды в работе."),

    OTHER_HERO_CURE('+', -4, "Попытка героем из моей команды зачистить инфекцию. " +
            "Если инфекция была устранена ситуация вокруг обновится. " +
            "Если герой ошибся и зона была не инфицирована - штраф."),

    OTHER_HERO_HEALING('y', -1, "На секунду после окончания игры поле открывается " +
            "и можно увидеть какую инфекцию удалось обеззаразить " +
            "герою из моей команды."),

    ENEMY_HERO_DEAD('Z', -7, "Вражеский герой заразился инфекцией."),

    ENEMY_HERO('♣', -5, "Вражеский герой в работе."),

    ENEMY_HERO_HEALING('z', -1, "На секунду после окончания игры поле открывается " +
            "и можно увидеть какую инфекцию удалось обеззаразить " +
            "вражескому герою."),

    INFECTION('o', -6, "На секунду после смерти героя поле открывается " +
            "и можно увидеть где была инфекция."),
    HIDDEN('*', -3, "Туман - место где еще не бывал герой. Возможно эта зона инфицирована."),

    PATHLESS('#', -2, "Непроходимые территории - обычно граница поля, но может быть и " +
            "простое на пути героя."),

    CLEAR(' ', 0, "Вокруг этой зоны нет заражений."),

    ONE_CONTAGION('1', 1, "Вокруг этой зоны было зафиксировано одно заражение."),

    TWO_CONTAGIONS('2', 2, "Вокруг этой зоны было зафиксировано два заражения."),

    THREE_CONTAGIONS('3', 3, "Вокруг этой зоны было зафиксировано три заражения."),

    FOUR_CONTAGIONS('4', 4, "Вокруг этой зоны было зафиксировано четыре заражения."),

    FIVE_CONTAGIONS('5', 5, "Вокруг этой зоны было зафиксировано пять заражений."),

    SIX_CONTAGIONS('6', 6, "Вокруг этой зоны было зафиксировано шесть заражений."),

    SEVEN_CONTAGIONS('7', 7, "Вокруг этой зоны было зафиксировано семь заражений."),

    EIGHT_CONTAGIONS('8', 8, "Вокруг этой зоны было зафиксировано восемь заражений.");

    private final char ch;
    private final int value;
    private final String info;

    Element(char ch, int value, String info) {
        this.ch = ch;
        this.value = value;
        this.info = info;
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
}

package com.codenjoy.dojo.games.battlecity;

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
import java.util.Collection;
import java.util.List;

import static java.util.stream.Collectors.toList;

public enum Element implements CharElement {

    NONE(' '),
    BATTLE_WALL('☼'),
    BANG('Ѡ'),

	ICE('#'),
	TREE('%'),
	RIVER('~'),

	WALL('╬', 3),

    WALL_DESTROYED_DOWN('╩', 2),
    WALL_DESTROYED_UP('╦', 2),
    WALL_DESTROYED_LEFT('╠', 2),
    WALL_DESTROYED_RIGHT('╣', 2),

    WALL_DESTROYED_DOWN_TWICE('╨', 1),
    WALL_DESTROYED_UP_TWICE('╥', 1),
    WALL_DESTROYED_LEFT_TWICE('╞', 1),
    WALL_DESTROYED_RIGHT_TWICE('╡', 1),

    WALL_DESTROYED_LEFT_RIGHT('│', 1),
    WALL_DESTROYED_UP_DOWN('─', 1),

    WALL_DESTROYED_UP_LEFT('┌', 1),
    WALL_DESTROYED_RIGHT_UP('┐', 1),
    WALL_DESTROYED_DOWN_LEFT('└', 1),
    WALL_DESTROYED_DOWN_RIGHT('┘', 1),

    WALL_DESTROYED(' ', 0),

    BULLET('•'),

    TANK_UP('▲'),
    TANK_RIGHT('►'),
    TANK_DOWN('▼'),
    TANK_LEFT('◄'),

    OTHER_TANK_UP('˄'),
    OTHER_TANK_RIGHT('˃'),
    OTHER_TANK_DOWN('˅'),
    OTHER_TANK_LEFT('˂'),

    AI_TANK_UP('?'),
    AI_TANK_RIGHT('»'),
    AI_TANK_DOWN('¿'),
    AI_TANK_LEFT('«'),

    AI_TANK_PRIZE('◘'),

    PRIZE('!'),
    PRIZE_IMMORTALITY('1'),
    PRIZE_BREAKING_WALLS('2'),
    PRIZE_WALKING_ON_WATER('3'),
    PRIZE_VISIBILITY('4'),
    PRIZE_NO_SLIDING('5');

    public final char ch;
    int power;

    private static List<Element> result = null;
    public static Collection<Element> getWalls() {
        if (result == null) {
            result = Arrays.stream(values())
                    .filter(e -> e.name().startsWith(WALL.name()))
                    .collect(toList());
        }
        return result;
    }

    @Override
    public char ch() {
        return ch;
    }

    public int power() {
        return power;
    }

    Element(char ch) {
        this.ch = ch;
        this.power = -1;
    }

    Element(char ch, int power) {
        this.ch = ch;
        this.power = power;
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

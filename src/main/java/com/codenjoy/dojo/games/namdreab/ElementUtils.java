package com.codenjoy.dojo.games.namdreab;

/*-
 * #%L
 * Codenjoy - it's a dojo-like platform from developers to developers.
 * %%
 * Copyright (C) 2012 - 2022 Codenjoy
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

import com.codenjoy.dojo.services.Direction;
import com.google.common.collect.LinkedHashMultimap;
import com.google.common.collect.Multimap;

import java.util.Arrays;

import static com.codenjoy.dojo.games.namdreab.Element.*;
import static com.codenjoy.dojo.services.Direction.*;

public class ElementUtils {

    public static Multimap<Direction, Element> parts = LinkedHashMultimap.create();

    private static void part(Element element, Direction... directions) {
        Arrays.stream(directions)
                .forEach(direction -> {
                    parts.put(direction, element);
                    parts.put(direction, ElementUtils.enemyHero(element));
                });
    }

    static {
        part(HEAD_DOWN,       DOWN);
        part(HEAD_LEFT,       LEFT);
        part(HEAD_RIGHT,      RIGHT);
        part(HEAD_UP,         UP);
        part(HEAD_DEAD,       UP, DOWN, LEFT, RIGHT);
        part(HEAD_EVIL,       UP, DOWN, LEFT, RIGHT);
        part(HEAD_FLY,        UP, DOWN, LEFT, RIGHT);
        part(HEAD_SLEEP,      UP, DOWN, LEFT, RIGHT);

        part(BODY_HORIZONTAL, LEFT, RIGHT);
        part(BODY_VERTICAL,   UP, DOWN);
        part(BODY_LEFT_DOWN,  RIGHT, UP);
        part(BODY_LEFT_UP,    RIGHT, DOWN);
        part(BODY_RIGHT_DOWN, LEFT, UP);
        part(BODY_RIGHT_UP,   LEFT, DOWN);

        part(TAIL_END_DOWN,   DOWN);
        part(TAIL_END_LEFT,   LEFT);
        part(TAIL_END_UP,     UP);
        part(TAIL_END_RIGHT,  RIGHT);
        part(TAIL_INACTIVE,   UP, DOWN, LEFT, RIGHT);
    }

    public static final Element[] heroHead = new Element[]{
            HEAD_DOWN,
            HEAD_LEFT,
            HEAD_RIGHT,
            HEAD_UP,
            HEAD_SLEEP,
            HEAD_EVIL,
            HEAD_FLY
    };

    public static boolean isFly(Element element) {
        return element == HEAD_FLY
                || element == ENEMY_HEAD_FLY;
    }

    public static boolean isEvil(Element element) {
        return element == HEAD_EVIL
                || element == ENEMY_HEAD_EVIL;
    }

    public static Element tail(TailDirection direction) {
        switch (direction) {
            case VERTICAL_DOWN:    return TAIL_END_DOWN;
            case VERTICAL_UP:      return TAIL_END_UP;
            case HORIZONTAL_LEFT:  return TAIL_END_LEFT;
            case HORIZONTAL_RIGHT: return TAIL_END_RIGHT;
        }
        throw new IllegalArgumentException("Bad direction: " + direction);
    }
    public static Element head(Direction direction) {
        switch (direction) {
            case DOWN:  return HEAD_DOWN;
            case UP:    return HEAD_UP;
            case LEFT:  return HEAD_LEFT;
            case RIGHT: return HEAD_RIGHT;
        }
        throw new IllegalArgumentException("Bad direction: " + direction);
    }

    public static Element body(BodyDirection direction) {
        switch (direction) {
            case HORIZONTAL:        return BODY_HORIZONTAL;
            case VERTICAL:          return BODY_VERTICAL;
            case TURNED_LEFT_DOWN:  return BODY_LEFT_DOWN;
            case TURNED_LEFT_UP:    return BODY_LEFT_UP;
            case TURNED_RIGHT_DOWN: return BODY_RIGHT_DOWN;
            case TURNED_RIGHT_UP:   return BODY_RIGHT_UP;
        }
        throw new IllegalArgumentException("Bad direction: " + direction);
    }

    public static Element enemyHero(Element element) {
        switch (element) {
            case HEAD_DOWN:       return ENEMY_HEAD_DOWN;
            case HEAD_LEFT:       return ENEMY_HEAD_LEFT;
            case HEAD_RIGHT:      return ENEMY_HEAD_RIGHT;
            case HEAD_UP:         return ENEMY_HEAD_UP;
            case HEAD_DEAD:       return ENEMY_HEAD_DEAD;
            case HEAD_EVIL:       return ENEMY_HEAD_EVIL;
            case HEAD_FLY:        return ENEMY_HEAD_FLY;
            case HEAD_SLEEP:      return ENEMY_HEAD_SLEEP;
            case BODY_HORIZONTAL: return ENEMY_BODY_HORIZONTAL;
            case BODY_VERTICAL:   return ENEMY_BODY_VERTICAL;
            case BODY_LEFT_DOWN:  return ENEMY_BODY_LEFT_DOWN;
            case BODY_LEFT_UP:    return ENEMY_BODY_LEFT_UP;
            case BODY_RIGHT_DOWN: return ENEMY_BODY_RIGHT_DOWN;
            case BODY_RIGHT_UP:   return ENEMY_BODY_RIGHT_UP;
            case TAIL_END_DOWN:   return ENEMY_TAIL_END_DOWN;
            case TAIL_END_LEFT:   return ENEMY_TAIL_END_LEFT;
            case TAIL_END_UP:     return ENEMY_TAIL_END_UP;
            case TAIL_END_RIGHT:  return ENEMY_TAIL_END_RIGHT;
            case TAIL_INACTIVE:   return ENEMY_TAIL_INACTIVE;
        }
        throw new IllegalArgumentException("Bad hero state: " + element);
    }
}
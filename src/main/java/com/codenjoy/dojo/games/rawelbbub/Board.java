package com.codenjoy.dojo.games.rawelbbub;

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


import com.codenjoy.dojo.client.AbstractBoard;
import com.codenjoy.dojo.services.Point;

import java.util.List;

import static com.codenjoy.dojo.games.rawelbbub.Element.*;
import static com.codenjoy.dojo.services.PointImpl.pt;

/**
 * The class is a wrapper over the board string
 * coming from the server. Contains a number of
 * inherited methods {@link AbstractBoard},
 * but you can add any methods based on them here.
 */
public class Board extends AbstractBoard<Element> {

    @Override
    public Element valueOf(char ch) {
        return Element.valueOf(ch);
    }

    @Override
    protected int inversionY(int y) { // TODO разобраться с этим чудом
        return size - 1 - y;
    }

    public boolean isBarrierAt(int x, int y) {
        if (isOutOfField(x, y)) {
            return true;
        }

        return getBarriers().contains(pt(x, y));
    }

    public List<Point> getBarriers() {
        return get(REEFS,
                WALL,
                WALL_DESTROYED_DOWN,
                WALL_DESTROYED_UP,
                WALL_DESTROYED_LEFT,
                WALL_DESTROYED_RIGHT,
                WALL_DESTROYED_DOWN_TWICE,
                WALL_DESTROYED_UP_TWICE,
                WALL_DESTROYED_LEFT_TWICE,
                WALL_DESTROYED_RIGHT_TWICE,
                WALL_DESTROYED_LEFT_RIGHT,
                WALL_DESTROYED_UP_DOWN,
                WALL_DESTROYED_UP_LEFT,
                WALL_DESTROYED_RIGHT_UP,
                WALL_DESTROYED_DOWN_LEFT,
                WALL_DESTROYED_DOWN_RIGHT);
    }

    public Point getMe() {
        List<Point> points = get(HERO_UP,
                HERO_DOWN,
                HERO_LEFT,
                HERO_RIGHT);
        if (points.isEmpty()) {
            return null;
        }
        return points.get(0);
    }

    public List<Point> getEnemies() {
        return get(AI_UP,
                AI_DOWN,
                AI_LEFT,
                AI_RIGHT,
                OTHER_HERO_UP,
                OTHER_HERO_DOWN,
                OTHER_HERO_LEFT,
                OTHER_HERO_RIGHT,
                AI_PRIZE);
    }

    public List<Point> getBullets() {
        return get(BULLET);
    }

    public List<Point> getRiver() {
        return get(RIVER);
    }

    public boolean isGameOver() {
        return get(HERO_UP,
                HERO_DOWN,
                HERO_LEFT,
                HERO_RIGHT).isEmpty();
    }

    public Element getAt(int x, int y) {
        if (isOutOfField(x, y)) {
            return REEFS;
        }
        return super.getAt(x, y);
    }

    public boolean isBulletAt(int x, int y) {
        return getAt(x, y).equals(BULLET);
    }

    @Override
    public String toString() {
        return String.format("%s\n" +
                        "My tank at: %s\n" +
                        "Enemies at: %s\n" +
                        "Bullets at: %s\n",
                boardAsString(),
                getMe(),
                getEnemies(),
                getBullets());
    }
}

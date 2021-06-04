package com.codenjoy.dojo.games.bomberman;

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

import java.util.Collection;
import java.util.LinkedList;

import static com.codenjoy.dojo.services.PointImpl.pt;

public class Board extends AbstractBoard<Element> {

    public static final char ANY_CHAR = '?';

    @Override
    public Element valueOf(char ch) {
        return Element.valueOf(ch);
    }

    @Override
    protected int inversionY(int y) {
        return size - 1 - y;
    }

    @Override
    protected boolean withoutCorners() {
        return true;
    }

    public Element getAt(int x, int y) {
        if (isOutOfField(x, y)) {
            return Element.WALL;
        }
        return super.getAt(x, y);
    }

    public Collection<Point> getBarriers() {
        Collection<Point> all = getMeatChoppers();
        all.addAll(getWalls());
        all.addAll(getBombs());
        all.addAll(getDestroyableWalls());
        all.addAll(getOtherBombermans());

        return removeDuplicates(all);
    }

    @Override
    public String toString() {
        return String.format("%s\n" +
            "Bomberman at: %s\n" +
            "Other bombermans at: %s\n" +
            "Meat choppers at: %s\n" +
            "Destroy walls at: %s\n" +
            "Bombs at: %s\n" +
            "Blasts: %s\n" +
            "Expected blasts at: %s",
                boardAsString(),
                getBomberman(),
                getOtherBombermans(),
                getMeatChoppers(),
                getDestroyableWalls(),
                getBombs(),
                getBlasts(),
                getFutureBlasts());
    }

    public Point getBomberman() {
        return get(Element.BOMBERMAN,
                Element.BOMB_BOMBERMAN,
                Element.DEAD_BOMBERMAN).get(0);
    }

    public Collection<Point> getOtherBombermans() {
        return get(Element.OTHER_BOMBERMAN,
                Element.OTHER_BOMB_BOMBERMAN,
                Element.OTHER_DEAD_BOMBERMAN);
    }

    public boolean isMyBombermanDead() {
        return !get(Element.DEAD_BOMBERMAN).isEmpty();
    }

    public Collection<Point> getMeatChoppers() {
        return get(Element.MEAT_CHOPPER);
    }

    public Collection<Point> getWalls() {
        return get(Element.WALL);
    }

    public Collection<Point> getDestroyableWalls() {
        return get(Element.DESTROYABLE_WALL);
    }

    public Collection<Point> getBombs() {
        return get(Element.BOMB_TIMER_1,
                Element.BOMB_TIMER_2,
                Element.BOMB_TIMER_3,
                Element.BOMB_TIMER_4,
                Element.BOMB_TIMER_5,
                Element.BOMB_BOMBERMAN,
                Element.OTHER_BOMB_BOMBERMAN);
    }

    public Collection<Point> getPerks() {
        return get(Element.BOMB_COUNT_INCREASE,
                Element.BOMB_REMOTE_CONTROL,
                Element.BOMB_IMMUNE,
                Element.BOMB_BLAST_RADIUS_INCREASE);
    }

    public Collection<Point> getBlasts() {
        return get(Element.BOOM);
    }

    public Collection<Point> getFutureBlasts() {        
        Collection<Point> bombs = getBombs();
        Collection<Point> result = new LinkedList<>();
        for (Point bomb : bombs) {
            result.add(bomb);
            // TODO remove duplicate (check same logic inside parent isNear for example)
            result.add(pt(bomb.getX() - 1, bomb.getY()));
            result.add(pt(bomb.getX() + 1, bomb.getY()));
            result.add(pt(bomb.getX(), bomb.getY() - 1));
            result.add(pt(bomb.getX(), bomb.getY() + 1));
        }
        Collection<Point> result2 = new LinkedList<>();
        for (Point blast : result) {
            if (blast.isOutOf(size) || getWalls().contains(blast)) {
                continue;
            }
            result2.add(blast);
        }
        return removeDuplicates(result2);
    }

    public boolean isBarrierAt(int x, int y) { // TODO remove this method
        return getBarriers().contains(pt(x, y));
    }

    public boolean isBarrierAt(Point point) {
        return isBarrierAt(point.getX(), point.getY());
    }
    
}

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


import com.codenjoy.dojo.client.AbstractBoard;
import com.codenjoy.dojo.services.Point;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static com.codenjoy.dojo.games.verland.Element.heroes;
import static com.codenjoy.dojo.games.verland.Element.*;

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
    protected int inversionY(int y) {
        return size - 1 - y;
    }

    public Point getHero() {
        List<Point> list = get(heroes());
        return (list.isEmpty()) ? null : list.get(0);
    }

    public Element getAt(int x, int y) {
        if (isOutOfField(x, y)) {
            return PATHLESS;
        }
        return super.getAt(x, y);
    }

    public boolean isGameOver() {
        return !get(HERO_DEAD).isEmpty();
    }

    public boolean isWin() {
        return !(isGameOver() || get(HERO_HEALING).isEmpty());
    }

    public Collection<Point> getOtherHeroes() {
        return get(otherHeroes());
    }

    public Collection<Point> getEnemyHeroes() {
        return get(enemyHeroes());
    }

    public Collection<Point> getOtherStuff() {
        return get(otherStuff());
    }

    public Collection<Point> getWalls() {
        return get(PATHLESS);
    }

    public boolean is(Point point, Element... elements) {
        return getAllAt(point).stream()
                .anyMatch(element -> Arrays.asList(elements).contains(element));
    }

    public boolean isHeroAt(Point point) {
        return is(point, heroes());
    }

    public boolean isOtherHeroAt(Point point) {
        return is(point, otherHeroes());
    }

    public boolean isEnemyHeroAt(Point point) {
        return is(point, enemyHeroes());
    }

    public int countContagions(Point point) {
        return is(point, infectionMarkers()) ? getAt(point).value() : 0;
    }

    @Override
    public String toString() {
        return String.format("%s\n" +
                        "Hero at: %s\n" +
                        "Other heroes at: %s\n" +
                        "Enemy heroes at: %s\n" +
                        "Other stuff at: %s\n",
                boardAsString(),
                getHero(),
                getOtherHeroes(),
                getEnemyHeroes(),
                getOtherStuff());
    }

}

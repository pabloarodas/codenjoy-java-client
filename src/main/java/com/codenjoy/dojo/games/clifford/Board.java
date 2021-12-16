package com.codenjoy.dojo.games.clifford;

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
import java.util.List;

import static com.codenjoy.dojo.games.clifford.Element.*;

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

    @Override
    public Element getAt(int x, int y) {
        if (isOutOfField(x, y)) {
            return STONE;
        }
        return super.getAt(x, y);
    }

    public boolean isGameOver() {
        return !get(heroDie()).isEmpty();
    }

    public Point getHero() {
        List<Point> list = get(heroes());
        return (list.isEmpty()) ? null : list.get(0);
    }

    public Collection<Point> getOtherHeroes() {
        return get(otherHeroes());
    }

    public Collection<Point> getEnemyHeroes() {
        return get(enemyHeroes());
    }

    public Collection<Point> getRobbers() {
        return get(robbers());
    }

    public Collection<Point> getClues() {
        return get(clues());
    }

    public Collection<Point> getBackWays() {
        return get(backWays());
    }

    public Collection<Point> getMaskPotions() {
        return get(maskPotions());
    }

    public Collection<Point> getWalls() {
        return get(walls());
    }

    public Collection<Point> getLadders() {
        return get(ladders());
    }

    public Collection<Point> getPipes() {
        return get(pipes());
    }

    public Collection<Point> getDoors() {
        return get(doors());
    }

    public Collection<Point> getKeys() {
        return get(keys());
    }

    public Collection<Point> getBarriers() {
        Collection<Point> all = getWalls();

        // add other barriers here

        return removeDuplicates(all);
    }

    public boolean isHeroAt(Point pt) {
        return isAt(pt, heroes());
    }

    public boolean isOtherHeroAt(Point pt) {
        return isAt(pt, otherHeroes());
    }

    public boolean isEnemyHeroAt(Point pt) {
        return isAt(pt, enemyHeroes());
    }

    public boolean isRobberAt(Point pt) {
        return isAt(pt, robbers());
    }

    public boolean isClue(Point pt) {
        return isAt(pt, clues());
    }

    public boolean isBackWay(Point pt) {
        return isAt(pt, backWays());
    }

    public boolean isPotion(Point pt) {
        return isAt(pt, maskPotions());
    }

    public boolean isWall(Point pt) {
        return isAt(pt, walls());
    }

    public boolean isLadder(Point pt) {
        return isAt(pt, ladders());
    }

    public boolean isPipe(Point pt) {
        return isAt(pt, pipes());
    }

    public boolean isDoor(Point pt) {
        return isAt(pt, doors());
    }

    public boolean isKey(Point pt) {
        return isAt(pt, keys());
    }

    public boolean isBarrierAt(Point pt) {
        return getBarriers().contains(pt);
    }

    @Override
    public String toString() {
        return String.format("%s\n" +
                        "Hero at: %s\n" +
                        "Other heroes at: %s\n" +
                        "Enemy heroes at: %s\n" +
                        "Robbers at: %s\n" +
                        "Mask potions at: %s\n" +
                        "Keys at: %s\n",
                boardAsString(),
                getHero(),
                getOtherHeroes(),
                getEnemyHeroes(),
                getRobbers(),
                getMaskPotions(),
                getKeys());
    }

}
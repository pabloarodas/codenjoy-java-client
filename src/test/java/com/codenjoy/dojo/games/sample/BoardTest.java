package com.codenjoy.dojo.games.sample;

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


import com.codenjoy.dojo.client.Utils;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BoardTest {

    private Board board;

    @Before
    public void setup() {
         board = (Board) new Board().forString(
                 "☼☼☼☼☼☼☼\n" +
                 "☼  x  ☼\n" +
                 "☼ $   ☼\n" +
                 "☼X   Y☼\n" +
                 "☼ ☺ $ ☼\n" +
                 "☼  ☻  ☼\n" +
                 "☼☼☼☼☼☼☼\n");
    }

    @Test
    public void shouldToString() {
        asrtBrd("☼☼☼☼☼☼☼\n" +
                "☼  x  ☼\n" +
                "☼ $   ☼\n" +
                "☼X   Y☼\n" +
                "☼ ☺ $ ☼\n" +
                "☼  ☻  ☼\n" +
                "☼☼☼☼☼☼☼\n" +
                "\n" +
                "Hero at: [1,3]\n" +
                "Other heroes at: [[3,1], [5,3]]\n" +
                "Bombs at: [[3,5]]\n" +
                "Gold at: [[2,4], [4,2]]\n");
    }

    private void asrtBrd(String expected) {
        assertEquals(expected, board.toString());
    }

    @Test
    public void shouldGetElement() {
        assertEquals("{ =[[1,1], [1,2], [1,4], [1,5], [2,1], [2,3], [2,5], [3,2], [3,3], [3,4], [4,1], [4,3], [4,4], [4,5], [5,1], [5,2], [5,4], [5,5]], \n" +
                    "☼=[[0,0], [0,1], [0,2], [0,3], [0,4], [0,5], [0,6], [1,0], [1,6], [2,0], [2,6], [3,0], [3,6], [4,0], [4,6], [5,0], [5,6], [6,0], [6,1], [6,2], [6,3], [6,4], [6,5], [6,6]], \n" +
                    "☺=[[2,2]], \n" +
                    "☻=[[3,1]], \n" +
                    "X=[[1,3]], \n" +
                    "Y=[[5,3]], \n" +
                    "$=[[2,4], [4,2]], \n" +
                    "x=[[3,5]]}",
                Utils.elements(board, Element.values()));
    }

    @Test
    public void shouldGetAt() {
        assertEquals("☼", board.getAt(0, 0).toString());
        assertEquals("☺", board.getAt(2, 2).toString());
        assertEquals("☻", board.getAt(3, 1).toString());
        assertEquals("X", board.getAt(1, 3).toString());
        assertEquals("Y", board.getAt(5, 3).toString());
        assertEquals("$", board.getAt(2, 4).toString());
        assertEquals("$", board.getAt(4, 2).toString());
        assertEquals("x", board.getAt(3, 5).toString());
        assertEquals(" ", board.getAt(1, 1).toString());
    }

    @Test
    public void shouldIsAt() {
        assertEquals(false, board.isAt(0, 1, Element.HERO));
        assertEquals(true,  board.isAt(0, 1, Element.WALL));
    }
}
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
                 "☼*****☼\n" +
                 "☼*****☼\n" +
                 "☼*12**☼\n" +
                 "☼ ☺1**☼\n" +
                 "☼  ***☼\n" +
                 "☼☼☼☼☼☼☼\n");
    }

    @Test
    public void shouldToString() {
        asrtBrd("Board:\n" +
                "☼☼☼☼☼☼☼\n" +
                "☼*****☼\n" +
                "☼*****☼\n" +
                "☼*12**☼\n" +
                "☼ ☺1**☼\n" +
                "☼  ***☼\n" +
                "☼☼☼☼☼☼☼\n");
    }

    private void asrtBrd(String expected) {
        assertEquals(expected, board.toString());
    }

    @Test
    public void shouldGetElement() {
        assertEquals("{Ѡ=[], \n" +
                    "☻=[], \n" +
                    "☺=[[2,2]], \n" +
                    "‼=[], \n" +
                    "*=[[1,3], [1,4], [1,5], [2,4], [2,5], [3,1], [3,4], [3,5], [4,1], [4,2], [4,3], [4,4], [4,5], [5,1], [5,2], [5,3], [5,4], [5,5]], \n" +
                    "☼=[[0,0], [0,1], [0,2], [0,3], [0,4], [0,5], [0,6], [1,0], [1,6], [2,0], [2,6], [3,0], [3,6], [4,0], [4,6], [5,0], [5,6], [6,0], [6,1], [6,2], [6,3], [6,4], [6,5], [6,6]], \n" +
                    "x=[], \n" +
                    " =[[1,1], [1,2], [2,1]], \n" +
                    "1=[[2,3], [3,2]], \n" +
                    "2=[[3,3]], \n" +
                    "3=[], \n" +
                    "4=[], \n" +
                    "5=[], \n" +
                    "6=[], \n" +
                    "7=[], \n" +
                    "8=[]}",
                Utils.elements(board, Element.values()));
    }

    @Test
    public void shouldGetAt() {
        assertEquals("☼", board.getAt(0, 1).toString());
        assertEquals("*", board.getAt(1, 5).toString());
        assertEquals("*", board.getAt(2, 4).toString());
        assertEquals("2", board.getAt(3, 3).toString());
        assertEquals("☺", board.getAt(2, 2).toString());
        assertEquals(" ", board.getAt(1, 1).toString());
        assertEquals("1", board.getAt(2, 3).toString());
    }

    @Test
    public void shouldIsAt() {
        assertEquals(false, board.isAt(2, 4, Element.BANG));
        assertEquals(true,  board.isAt(2, 4, Element.HIDDEN));
    }
}
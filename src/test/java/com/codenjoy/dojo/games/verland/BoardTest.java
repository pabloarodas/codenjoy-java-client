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


import com.codenjoy.dojo.games.verland.Board;
import com.codenjoy.dojo.games.verland.Element;
import org.junit.Before;
import org.junit.Test;

import static com.codenjoy.dojo.services.PointImpl.pt;
import static org.junit.Assert.assertEquals;

public class BoardTest {

    private Board board;

    public static Board board(String boardString) {
        return (Board)new Board().forString(boardString);
    }

    @Before
    public void before() {
        board = board(
                "☼☼☼☼☼☼☼☼☼" +
                "☼1 Y   y☼" +
                "☼*2  x  ☼" +
                "☼o 3 ♠ +☼" +
                "☼♥  4   ☼" +
                "☼   Z   ☼" +
                "☼       ☼" +
                "☼X !    ☼" +
                "☼☼☼☼☼☼☼☼☼");
    }

    @Test
    public void shouldWorkToString() {
        assertEquals(
           /*8*/"☼☼☼☼☼☼☼☼☼\n" +
           /*7*/"☼1 Y   y☼\n" +
           /*6*/"☼*2  x  ☼\n" +
           /*5*/"☼o 3 ♠ +☼\n" +
           /*4*/"☼♥  4   ☼\n" +
           /*3*/"☼   Z   ☼\n" +
           /*2*/"☼       ☼\n" +
           /*1*/"☼X !    ☼\n" +
           /*0*/"☼☼☼☼☼☼☼☼☼\n" +
               /*012345678*/
                "\n" +
                "Hero at: [1,4]\n" +
                "Other heroes at: [[3,7], [5,5], [7,5], [7,7]]\n" +
                "Enemy heroes at: [[4,3]]\n" +
                "Heroes at: [[1,1], [1,4], [3,1], [5,6]]\n" +
                "Other stuff at: [[0,0], [0,1], [0,2], [0,3], [0,4], [0,5], [0,6], [0,7], [0,8], [1,0], [1,5], [1,6], [1,8], [2,0], [2,8], [3,0], [3,8], [4,0], [4,8], [5,0], [5,8], [6,0], [6,8], [7,0], [7,8], [8,0], [8,1], [8,2], [8,3], [8,4], [8,5], [8,6], [8,7], [8,8]]", board.toString());
    }
}

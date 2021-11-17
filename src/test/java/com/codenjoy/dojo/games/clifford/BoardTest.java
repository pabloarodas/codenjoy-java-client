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
                "☼ $  H☼\n" +
                "☼ ###H☼\n" +
                "☼    H☼\n" +
                "☼►   H☼\n" +
                "☼#####☼\n" +
                "☼☼☼☼☼☼☼\n");
    }

    @Test
    public void shouldToString() {
        asrtBrd("☼☼☼☼☼☼☼\n" +
                "☼ $  H☼\n" +
                "☼ ###H☼\n" +
                "☼    H☼\n" +
                "☼►   H☼\n" +
                "☼#####☼\n" +
                "☼☼☼☼☼☼☼\n" +
                "\n" +
                "Hero at: [1,2]\n" +
                "Other heroes at: []\n" +
                "Enemy heroes at: []\n" +
                "Robbers at: []\n" +
                "Mask potions at: []\n" +
                "Keys at: []\n");
    }

    private void asrtBrd(String expected) {
        assertEquals(expected, board.toString());
    }

    @Test
    public void shouldGetElement() {
        assertEquals("{ =[[1,3], [1,4], [1,5], [2,2], [2,3], [3,2], [3,3], [3,5], [4,2], [4,3], [4,5]], \n" +
                        "#=[[1,1], [2,1], [2,4], [3,1], [3,4], [4,1], [4,4], [5,1]], \n" +
                        "1=[], \n" +
                        "2=[], \n" +
                        "3=[], \n" +
                        "4=[], \n" +
                        "☼=[[0,0], [0,1], [0,2], [0,3], [0,4], [0,5], [0,6], [1,0], [1,6], [2,0], [2,6], [3,0], [3,6], [4,0], [4,6], [5,0], [5,6], [6,0], [6,1], [6,2], [6,3], [6,4], [6,5], [6,6]], \n" +
                        "*=[], \n" +
                        "$=[[2,5]], \n" +
                        "&=[], \n" +
                        "@=[], \n" +
                        "O=[], \n" +
                        "A=[], \n" +
                        "◄=[], \n" +
                        "►=[[1,2]], \n" +
                        "U=[], \n" +
                        "I=[], \n" +
                        "E=[], \n" +
                        "o=[], \n" +
                        "a=[], \n" +
                        "h=[], \n" +
                        "w=[], \n" +
                        "u=[], \n" +
                        "i=[], \n" +
                        "e=[], \n" +
                        "C=[], \n" +
                        "D=[], \n" +
                        "«=[], \n" +
                        "»=[], \n" +
                        "F=[], \n" +
                        "J=[], \n" +
                        "K=[], \n" +
                        "c=[], \n" +
                        "d=[], \n" +
                        "Z=[], \n" +
                        "z=[], \n" +
                        "f=[], \n" +
                        "j=[], \n" +
                        "k=[], \n" +
                        "L=[], \n" +
                        "N=[], \n" +
                        "P=[], \n" +
                        "Q=[], \n" +
                        "R=[], \n" +
                        "T=[], \n" +
                        "V=[], \n" +
                        "l=[], \n" +
                        "n=[], \n" +
                        "p=[], \n" +
                        "q=[], \n" +
                        "r=[], \n" +
                        "t=[], \n" +
                        "v=[], \n" +
                        "X=[], \n" +
                        ")=[], \n" +
                        "(=[], \n" +
                        "x=[], \n" +
                        "Y=[], \n" +
                        "y=[], \n" +
                        "g=[], \n" +
                        "s=[], \n" +
                        "b=[], \n" +
                        "G=[], \n" +
                        "S=[], \n" +
                        "B=[], \n" +
                        "+=[], \n" +
                        "-=[], \n" +
                        "!=[], \n" +
                        "•=[], \n" +
                        "H=[[5,2], [5,3], [5,4], [5,5]], \n" +
                        "~=[], \n" +
                        "W=[], \n" +
                        "m=[]}",
                Utils.elements(board, Element.values()));
    }

    @Test
    public void shouldGetAt() {
        assertEquals("☼", board.getAt(0, 1).toString());
        assertEquals(" ", board.getAt(1, 5).toString());
        assertEquals("#", board.getAt(2, 4).toString());
        assertEquals("►", board.getAt(1, 2).toString());
        assertEquals("H", board.getAt(5, 3).toString());
        assertEquals(" ", board.getAt(3, 5).toString());
        assertEquals("#", board.getAt(3, 1).toString());
    }

    @Test
    public void shouldIsAt() {
        assertEquals(false, board.isAt(2, 4, Element.CRACK_PIT));
        assertEquals(true, board.isAt(2, 4, Element.BRICK));
    }
}
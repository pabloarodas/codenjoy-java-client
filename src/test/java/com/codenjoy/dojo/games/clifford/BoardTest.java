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

import static com.codenjoy.dojo.services.PointImpl.pt;
import static org.junit.Assert.assertEquals;

public class BoardTest {

    private Board board;

    public static Board board(String boardString) {
        return (Board)new Board().forString(boardString);
    }
    
    @Before
    public void setup() {
        board = board(
                /*8*/"☼☼☼☼☼☼☼☼☼\n" +
                /*7*/"☼ ►*## $☼\n" +
                /*6*/"☼ H pq -☼\n" +
                /*5*/"☼ H  1 G☼\n" +
                /*4*/"☼m   & W☼\n" +
                /*3*/"☼ + ~~~ ☼\n" +
                /*2*/"☼Z3 S   ☼\n" +
                /*1*/"☼ @@  X ☼\n" +
                /*0*/"☼☼☼☼☼☼☼☼☼\n");
                    /*012345678*/
    }

    @Test
    public void shouldToString() {
        assertBoard(
                "☼☼☼☼☼☼☼☼☼\n" +
                "☼ ►*## $☼\n" +
                "☼ H pq -☼\n" +
                "☼ H  1 G☼\n" +
                "☼m   & W☼\n" +
                "☼ + ~~~ ☼\n" +
                "☼Z3 S   ☼\n" +
                "☼ @@  X ☼\n" +
                "☼☼☼☼☼☼☼☼☼\n" +
                "\n" +
                "Hero at: [2,7]\n" +
                "Other heroes at: [[1,2]]\n" +
                "Enemy heroes at: [[4,6], [5,6]]\n" +
                "Robbers at: [[6,1]]\n" +
                "Mask potions at: [[1,4]]\n" +
                "Keys at: [[2,3], [7,6]]\n");
    }

    private void assertBoard(String expected) {
        assertEquals(expected, board.toString());
    }

    @Test
    public void shouldWork_getElement() {
        assertEquals("{ =[[1,1], [1,3], [1,5], [1,6], [1,7], [2,4], [3,2], [3,3], [3,4], [3,5], [3,6], [4,1], [4,4], [4,5], [5,1], [5,2], [6,2], [6,4], [6,5], [6,6], [6,7], [7,1], [7,2], [7,3]], \n" +
                        "#=[[4,7], [5,7]], \n" +
                        "1=[[5,5]], \n" +
                        "2=[], \n" +
                        "3=[[2,2]], \n" +
                        "4=[], \n" +
                        "☼=[[0,0], [0,1], [0,2], [0,3], [0,4], [0,5], [0,6], [0,7], [0,8], [1,0], [1,8], [2,0], [2,8], [3,0], [3,8], [4,0], [4,8], [5,0], [5,8], [6,0], [6,8], [7,0], [7,8], [8,0], [8,1], [8,2], [8,3], [8,4], [8,5], [8,6], [8,7], [8,8]], \n" +
                        "*=[[3,7]], \n" +
                        "$=[[7,7]], \n" +
                        "&=[[5,4]], \n" +
                        "@=[[2,1], [3,1]], \n" +
                        "O=[], \n" +
                        "A=[], \n" +
                        "◄=[], \n" +
                        "►=[[2,7]], \n" +
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
                        "Z=[[1,2]], \n" +
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
                        "p=[[4,6]], \n" +
                        "q=[[5,6]], \n" +
                        "r=[], \n" +
                        "t=[], \n" +
                        "v=[], \n" +
                        "X=[[6,1]], \n" +
                        ")=[], \n" +
                        "(=[], \n" +
                        "x=[], \n" +
                        "Y=[], \n" +
                        "y=[], \n" +
                        "g=[], \n" +
                        "s=[], \n" +
                        "b=[], \n" +
                        "G=[[7,5]], \n" +
                        "S=[[4,2]], \n" +
                        "B=[], \n" +
                        "+=[[2,3]], \n" +
                        "-=[[7,6]], \n" +
                        "!=[], \n" +
                        "•=[], \n" +
                        "H=[[2,5], [2,6]], \n" +
                        "~=[[4,3], [5,3], [6,3]], \n" +
                        "W=[[7,4]], \n" +
                        "m=[[1,4]]}",
                Utils.elements(board, Element.values()));
    }

    @Test
    public void shouldWork_getAt() {
        assertEquals(Element.STONE, board.getAt(0, 1));
        assertEquals(Element.NONE, board.getAt(1, 5));
        assertEquals(Element.BRICK, board.getAt(4, 7));
        assertEquals(Element.OTHER_HERO_MASK_LEFT, board.getAt(1, 2));
        assertEquals(Element.PIPE, board.getAt(5, 3));
        assertEquals(Element.NONE, board.getAt(3, 5));
        assertEquals(Element.CLUE_RING, board.getAt(3, 1));
    }

    @Test
    public void shouldWork_getAt_point() {
        assertEquals(Element.NONE, board.getAt(pt(7, 3)));
        assertEquals(Element.STONE, board.getAt(pt(0, 8)));
        assertEquals(Element.ENEMY_HERO_MASK_RIGHT, board.getAt(pt(5, 6)));
    }

    @Test
    public void shouldWork_getNear() {
        assertEquals("[ , ~,  ,  , W, ☼, ☼, ☼]", board.getNear(7, 3).toString());
        assertEquals("[☼,  , ☼]", board.getNear(0, 8).toString());
        assertEquals("[ , p, #, 1, #,  ,  ,  ]", board.getNear(5, 6).toString());
    }

    @Test
    public void shouldWork_getNear_point() {
        assertEquals("[ , ~,  ,  , W, ☼, ☼, ☼]", board.getNear(pt(7, 3)).toString());
        assertEquals("[☼,  , ☼]", board.getNear(pt(0, 8)).toString());
        assertEquals("[ , p, #, 1, #,  ,  ,  ]", board.getNear(pt(5, 6)).toString());
    }

    @Test
    public void shouldWork_getAt_outOfBoard() {
        assertEquals(Element.STONE, board.getAt(-1, 1));
        assertEquals(Element.STONE, board.getAt(1, -1));
        assertEquals(Element.STONE, board.getAt(100, 1));
        assertEquals(Element.STONE, board.getAt(1, 100));

        assertEquals(Element.STONE, board.getAt(pt(-1, 1)));
        assertEquals(Element.STONE, board.getAt(pt(1, -1)));
        assertEquals(Element.STONE, board.getAt(pt(100, 1)));
        assertEquals(Element.STONE, board.getAt(pt(1, 100)));
    }

    @Test
    public void shouldWork_getOtherHeroes() {
        assertEquals("[[1,2]]",
                board.getOtherHeroes().toString());
    }

    @Test
    public void shouldWork_getEnemyHeroes() {
        assertEquals("[[4,6], [5,6]]", board.getEnemyHeroes().toString());
    }

    @Test
    public void shouldWork_getBarriers() {
        assertEquals("[[0,0], [0,1], [0,2], [0,3], [0,4], [0,5], [0,6], [0,7], " +
                    "[0,8], [1,0], [1,8], [2,0], [2,8], [3,0], [3,8], [4,0], " +
                    "[4,7], [4,8], [5,0], [5,7], [5,8], [6,0], [6,8], [7,0], " +
                    "[7,8], [8,0], [8,1], [8,2], [8,3], [8,4], [8,5], [8,6], " +
                    "[8,7], [8,8]]",
                board.getBarriers().toString());
    }

    @Test
    public void shouldWork_isBarrierAt_point() {
        assertEquals(false, board.isBarrierAt(pt(1, 1)));
        assertEquals(true, board.isBarrierAt(pt(0, 0)));
    }

    @Test
    public void shouldWork_getBackWays() {
        assertEquals("[[7,4]]",
                board.getBackWays().toString());
    }

    @Test
    public void shouldWork_getHero() {
        assertEquals("[2,7]", board.getHero().toString());
        assertEquals("[0,0]", board("O").getHero().toString());
        assertEquals("[0,0]", board("A").getHero().toString());
        assertEquals("[0,0]", board("◄").getHero().toString());
        assertEquals("[0,0]", board("►").getHero().toString());
        assertEquals("[0,0]", board("U").getHero().toString());
        assertEquals("[0,0]", board("I").getHero().toString());
        assertEquals("[0,0]", board("E").getHero().toString());

        assertEquals("[0,0]", board("o").getHero().toString());
        assertEquals("[0,0]", board("a").getHero().toString());
        assertEquals("[0,0]", board("h").getHero().toString());
        assertEquals("[0,0]", board("w").getHero().toString());
        assertEquals("[0,0]", board("u").getHero().toString());
        assertEquals("[0,0]", board("i").getHero().toString());
        assertEquals("[0,0]", board("e").getHero().toString());
    }

    @Test
    public void shouldWork_getPotions() {
        assertEquals("[[1,4]]",
                board.getMaskPotions().toString());
    }

    @Test
    public void shouldWork_getLadders() {
        assertEquals("[[2,5], [2,6], [6,1]]",
                board.getLadders().toString());
    }

    @Test
    public void shouldWork_getPipes() {
        assertEquals("[[4,3], [5,3], [6,3]]",
                board.getPipes().toString());
    }

    @Test
    public void shouldWork_getMaskPotions() {
        assertEquals("[[1,4]]", board.getMaskPotions().toString());
    }

    @Test
    public void shouldWork_getClues() {
        assertEquals("[[2,1], [3,1], [5,4], [7,7]]", board.getClues().toString());
    }

    @Test
    public void shouldWork_getDoors() {
        assertEquals("[[4,2], [7,5]]",
                board.getDoors().toString());
    }

    @Test
    public void shouldWork_countNear() {
        assertEquals(1, board.countNear(0, 0, Element.NONE));
        assertEquals(2, board.countNear(2, 1, Element.NONE));
        assertEquals(3, board.countNear(4, 1, Element.NONE));

        assertEquals(5, board.countNear(1, 7, Element.STONE));
        assertEquals(5, board.countNear(1, 1, Element.STONE));
        assertEquals(5, board.countNear(7, 7, Element.STONE));
        assertEquals(5, board.countNear(7, 1, Element.STONE));
        assertEquals(3, board.countNear(1, 6, Element.STONE));
    }

    @Test
    public void shouldWork_countNear_point() {
        assertEquals(1, board.countNear(pt(0, 0), Element.NONE));
        assertEquals(2, board.countNear(pt(2, 1), Element.NONE));
        assertEquals(3, board.countNear(pt(4, 1), Element.NONE));

        assertEquals(5, board.countNear(pt(1, 7), Element.STONE));
        assertEquals(5, board.countNear(pt(1, 1), Element.STONE));
        assertEquals(5, board.countNear(pt(7, 7), Element.STONE));
        assertEquals(5, board.countNear(pt(7, 1), Element.STONE));
        assertEquals(3, board.countNear(pt(1, 6), Element.STONE));
    }

    @Test
    public void shouldWork_isAt() {
        assertEquals(false, board.isAt(3, 1, Element.NONE));
        assertEquals(true, board.isAt(2, 1, Element.CLUE_RING));

        assertEquals(false, board.isAt(3, 1, Element.NONE, Element.STONE));
        assertEquals(true, board.isAt(2, 1, Element.NONE, Element.CLUE_RING));
    }

    @Test
    public void shouldWork_isAt_point() {
        assertEquals(false, board.isAt(pt(3, 1), Element.NONE));
        assertEquals(true, board.isAt(pt(2, 1), Element.CLUE_RING));

        assertEquals(false, board.isAt(pt(3, 1), Element.NONE, Element.STONE));
        assertEquals(true, board.isAt(pt(2, 1), Element.NONE, Element.CLUE_RING));
    }

    @Test
    public void shouldWork_isNear() {
        assertEquals(false, board.isNear(1, 1, Element.NONE));
        assertEquals(true, board.isNear(5, 5, Element.NONE));
    }

    @Test
    public void shouldWork_isNear_point() {
        assertEquals(true, board.isNear(pt(1, 1), Element.OTHER_HERO_MASK_LEFT));
        assertEquals(false, board.isNear(pt(5, 5), Element.PIT_FILL_1));
    }

    @Test
    public void shouldWork_getWalls() {
        assertEquals("[[0,0], [0,1], [0,2], [0,3], [0,4], [0,5], [0,6], " +
                    "[0,7], [0,8], [1,0], [1,8], [2,0], [2,8], [3,0], " +
                    "[3,8], [4,0], [4,7], [4,8], [5,0], [5,7], [5,8], " +
                    "[6,0], [6,8], [7,0], [7,8], [8,0], [8,1], [8,2], " +
                    "[8,3], [8,4], [8,5], [8,6], [8,7], [8,8]]",
                board.getWalls().toString());
    }

    @Test
    public void shouldWork_isGameOver() {
        assertEquals(false, board.isGameOver());
        assertEquals(true, board("O").isGameOver());
        assertEquals(true, board("o").isGameOver());
    }

    @Test
    public void shouldWork_getKeys() {
        board = board(
                "#+ " +
                "#- " +
                "#! ");

        assertEquals("[[1,0], [1,1], [1,2]]",
                board.getKeys().toString());
    }
}
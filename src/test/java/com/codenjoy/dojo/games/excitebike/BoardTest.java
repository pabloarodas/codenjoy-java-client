package com.codenjoy.dojo.games.excitebike;

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

import com.codenjoy.dojo.games.excitebike.element.GameElement;
import com.codenjoy.dojo.games.excitebike.element.BikeElement;
import com.codenjoy.dojo.games.excitebike.element.SpringboardElement;
import com.codenjoy.dojo.services.Direction;
import com.codenjoy.dojo.services.Point;
import org.junit.Test;

import java.util.Arrays;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class BoardTest {

    @Test(expected = IllegalArgumentException.class)
    public void valueOf__shouldThrowIllegalArgumentException__ifElementWithGivenCharDoesntExist() {
        //given
        char givenChar = '%';
        Board board = new Board();

        //when
        board.valueOf(givenChar);

        //then
        //exception
    }

    @Test
    public void getMe__shouldReturnPointWithBikeElement() {
        //given
        Board board = toBoard("■■■■■" +
                " B > " +
                "  Ḃ |" +
                " ḃ ▼ " +
                "■■■■■"
        );

        //when
        Point result = board.getMe();

        //then
        assertThat(result.getX(), is(1));
        assertThat(result.getY(), is(1));
    }

    @Test
    public void getMe__shouldReturnPointWithBikeFallenElement() {
        //given
        Board board = toBoard("b■■■■" +
                "   > " +
                "  Ḃ |" +
                " Ā ▼ " +
                "■■■■■"
        );

        //when
        Point result = board.getMe();

        //then
        assertThat(result.getX(), is(0));
        assertThat(result.getY(), is(0));
    }

    @Test
    public void isGameOver__shouldReturnTrueIfThereIsBikeFallenElement() {
        //given
        Board board = toBoard("■■■■■" +
                "   > " +
                "  Ḃ |" +
                " Ā ▼ " +
                "■■■■■"
        );

        //when
        boolean result = board.isGameOver();

        //then
        assertThat(result, is(true));
    }

    @Test
    public void isGameOver__shouldReturnFalseIfThereIsNoBikeFallenElement() {
        //given
        Board board = toBoard("■■■■■" +
                " B >I" +
                "A Ḃ |" +
                " Ā ▼ " +
                "■■■■■"
        );

        //when
        boolean result = board.isGameOver();

        //then
        assertThat(result, is(false));
    }

    @Test
    public void checkNearMe__shouldReturnTrueIfThereIsOneOfExpectedElementsNearMeAtRightDirection() {
        //given
        Board board = toBoard("■■■■■" +
                " B>  " +
                "  Ḃ |" +
                " Ā ▼ " +
                "■■■■■"
        );

        //when
        boolean result = board.checkNearMe(Direction.RIGHT, GameElement.ACCELERATOR);

        //then
        assertThat(result, is(true));
    }

    @Test
    public void checkNearMe__shouldReturnTrueIfThereIsOneOfExpectedElementsNearMeAtDownDirection() {
        //given
        Board board = toBoard("■■■■■" +
                " Ā   " +
                " Ḃ | " +
                " B ▼ " +
                "■■■■■"
        );

        //when
        boolean result = board.checkNearMe(Direction.UP, BikeElement.OTHER_BIKE);

        //then
        assertThat(result, is(false));
    }

    @Test
    public void checkNearMe__shouldReturnTrueIfThereIsOneOfExpectedElementsNearMeAtLeftDirection() {
        //given
        Board board = toBoard("■■■■■" +
                "╝B>  " +
                " Ḃ | " +
                " Ā ▼ " +
                "■■■■■"
        );

        //when
        boolean result = board.checkNearMe(Direction.LEFT, SpringboardElement.SPRINGBOARD_RIGHT_DOWN);

        //then
        assertThat(result, is(true));
    }

    @Test
    public void checkNearMe__shouldReturnTrueIfThereIsOneOfExpectedElementsNearMeAtUpDirection() {
        //given
        Board board = toBoard("■■■■■" +
                "╝Ī>  " +
                " Ḃ | " +
                " B ▼ " +
                "■■■■■"
        );

        //when
        boolean result = board.checkNearMe(Direction.DOWN, GameElement.FENCE);

        //then
        assertThat(result, is(false));
    }

    @Test
    public void checkNearMe__shouldReturnTrueIfThereIsOneOfExpectedElementsNearMeAtRightRightUpDirection() {
        //given
        Board board = toBoard("■■■■■" +
                " B   " +
                "  Ḃ>|" +
                " Ā ▼ " +
                "■■■■■"
        );

        //when
        boolean result = board.checkNearMe(Arrays.asList(Direction.RIGHT, Direction.RIGHT, Direction.UP), GameElement.ACCELERATOR);

        //then
        assertThat(result, is(true));
    }

    @Test
    public void checkAtMe__shouldReturnTrue__ifAtMeIsGivenElement() {
        //given
        Board board = toBoard("■■■■■" +
                "╝Ī>  " +
                " Ḃ | " +
                " A ▼ " +
                "■■■■■"
        );

        //when
        boolean result = board.checkAtMe(BikeElement.BIKE_AT_ACCELERATOR);

        //then
        assertThat(result, is(true));
    }

    @Test
    public void checkAtMe__shouldReturnFalse__ifAtMeIsNotGivenElement() {
        //given
        Board board = toBoard("■■■■■" +
                "╝Ī>  " +
                " Ḃ | " +
                " B ▼ " +
                "■■■■■"
        );

        //when
        boolean result = board.checkAtMe(BikeElement.BIKE_AT_ACCELERATOR);

        //then
        assertThat(result, is(false));
    }

    private Board toBoard(String board) {
        return (Board) new Board().forString(board);
    }
}

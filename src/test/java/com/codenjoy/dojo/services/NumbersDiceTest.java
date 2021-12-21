package com.codenjoy.dojo.services;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class NumbersDiceTest {

    @Test
    public void shouldReturnAllNumbers_thenDefaultValue() {
        // given
        Dice dice = new NumbersDice(Arrays.asList(1, 2, 3, 4, 5), -1);

        // when then
        assertEquals(1, dice.next(100));
        assertEquals(2, dice.next(100));
        assertEquals(3, dice.next(100));
        assertEquals(4, dice.next(100));
        assertEquals(5, dice.next(100));
        assertEquals(-1, dice.next(100));
        assertEquals(-1, dice.next(100));
        assertEquals(-1, dice.next(100));
        assertEquals(-1, dice.next(100));
    }

    @Test
    public void shouldReturnDefaultValue_whenEmpty() {
        // given
        Dice dice = new NumbersDice(Arrays.asList(), -100);

        // when then
        assertEquals(-100, dice.next(100));
        assertEquals(-100, dice.next(100));
        assertEquals(-100, dice.next(100));
        assertEquals(-100, dice.next(100));
    }

}
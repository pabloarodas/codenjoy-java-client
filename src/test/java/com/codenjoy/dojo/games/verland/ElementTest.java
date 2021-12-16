package com.codenjoy.dojo.games.verland;

/*-
 * #%L
 * Codenjoy - it's a dojo-like platform from developers to developers.
 * %%
 * Copyright (C) 2021 Codenjoy
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
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ElementTest {

    @Test
    public void testElementProperties() {
        assertEquals("CLEAR[ ]                 =\n" +
                    "EIGHT_CONTAGIONS[8]      =contagions\n" +
                    "ENEMY_HERO[♣]            =enemyHeroes\n" +
                    "ENEMY_HERO_DEAD[Z]       =enemyHeroes\n" +
                    "ENEMY_HERO_HEALING[z]    =healing\n" +
                    "FIVE_CONTAGIONS[5]       =contagions\n" +
                    "FOUR_CONTAGIONS[4]       =contagions\n" +
                    "HERO[♥]                  =hero\n" +
                    "HERO_CURE[!]             =cure\n" +
                    "HERO_DEAD[X]             =hero\n" +
                    "HERO_HEALING[x]          =healing\n" +
                    "HIDDEN[*]                =hidden\n" +
                    "INFECTION[o]             =infections\n" +
                    "ONE_CONTAGION[1]         =contagions\n" +
                    "OTHER_HERO[♠]            =otherHero\n" +
                    "OTHER_HERO_CURE[+]       =cure\n" +
                    "OTHER_HERO_DEAD[Y]       =otherHero\n" +
                    "OTHER_HERO_HEALING[y]    =healing\n" +
                    "PATHLESS[☼]              =pathless\n" +
                    "SEVEN_CONTAGIONS[7]      =contagions\n" +
                    "SIX_CONTAGIONS[6]        =contagions\n" +
                    "THREE_CONTAGIONS[3]      =contagions\n" +
                    "TWO_CONTAGIONS[2]        =contagions",
                Utils.elements(Element.values()));
    }
}
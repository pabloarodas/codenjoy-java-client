package com.codenjoy.dojo.games.mollymage;

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


import com.codenjoy.dojo.services.printer.CharElement;

import java.util.Arrays;
import java.util.List;

public enum Element implements CharElement {

/// the potions

    // After Molly set the potion, the timer starts (5 ticks).
    POTION_TIMER_5('5'),

    // This will blow up after 4 ticks.
    POTION_TIMER_4('4'),

    // This after 3...
    POTION_TIMER_3('3'),

    // Two..
    POTION_TIMER_2('2'),

    // One.
    POTION_TIMER_1('1'),

    // Boom! this is what is potion does,
    // everything that is destroyable got destroyed.
    BOOM('҉'),

/// walls

    // Indestructible wall - it will not fall from potion.
    WALL('☼'),

    // this is a treasure box, it opens with an explosion.
    TREASURE_BOX('#'),

    // this is like a treasure box opens looks
    // like, it will disappear on next move.
    // if it's you did it - you'll get score
    // points. Perhaps a prize will appear.
    OPENING_TREASURE_BOX('H'),

/// soulless creatures

    // This guys runs over the board randomly
    // and gets in the way all the time.
    // If it will touch Molly - she will die.
    // You'd better kill this piece of ... soul,
    // you'll get score points for it.
    GHOST('&'),

    // This is ghost corpse.
    DEAD_GHOST('x'),

/// perks

    // Potion blast radius increase.
    // Applicable only to new potions.
    // The perk is temporary.
    POTION_BLAST_RADIUS_INCREASE('+'),

    // Increase available potions count.
    // Number of extra potions can be set
    // in settings. Temporary.
    POTION_COUNT_INCREASE('c'),

    // Potion blast not by timer but by second act.
    // Number of RC triggers is limited and c
    // an be set in settings.
    POTION_REMOTE_CONTROL('r'),

    // Do not die after potion blast
    // (own potion and others as well). Temporary.
    POTION_IMMUNE('i'),

/// a void

    // This is the only place where you can move your Molly.
    NONE(' '),

/// your Molly
    // This is what she usually looks like.
    HERO('☺'),

    // This is if she is sitting on own potion.
    POTION_HERO('☻'),

    // Oops, your Molly is dead (don't worry,
    // she will appear somewhere in next move).
    // You're getting penalty points for each death.
    DEAD_HERO('Ѡ'),

/// other players heroes

    // This is what other heroes looks like.
    OTHER_HERO('♥'),

    // This is if player is sitting on own potion.
    OTHER_POTION_HERO('♠'),

    // Enemy corpse (it will disappear shortly,
    // right on the next move).
    // If you've done it you'll get score points.
    OTHER_DEAD_HERO('♣');

    public static final String POTIONS = "12345";

    final char ch;

    Element(char ch) {
        this.ch = ch;
    }

    public static List<Element> perks() {
        return Arrays.asList(
                POTION_BLAST_RADIUS_INCREASE,
                POTION_COUNT_INCREASE,
                POTION_IMMUNE,
                POTION_REMOTE_CONTROL
        );
    }

    @Override
    public char ch() {
        return ch;
    }

    @Override
    public String toString() {
        return String.valueOf(ch);
    }

    public static Element valueOf(char ch) {
        for (Element el : Element.values()) {
            if (el.ch == ch) {
                return el;
            }
        }
        throw new IllegalArgumentException("No such element for " + ch);
    }

    public boolean isPotion() {
        return POTIONS.indexOf(ch) != -1;
    }

    public boolean isHero() {
        return this == Element.HERO ||
               this == Element.POTION_HERO ||
               this == Element.DEAD_HERO;
    }

}

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


import com.codenjoy.dojo.services.printer.CharElement;

import java.util.Arrays;

public enum Element implements CharElement {

/// Void

    NONE(' ',                   "Empty space - where the hero can move."),

/// Bricks

    BRICK('#',                  "A wall where you can shoot a hole."),

    PIT_FILL_1('1',             "The wall is restored over time. When the " +
                                "process begins, we see a timer."),

    PIT_FILL_2('2',             "The wall is restored over time. When the " +
                                "process begins, we see a timer."),

    PIT_FILL_3('3',             "The wall is restored over time. When the " +
                                "process begins, we see a timer."),

    PIT_FILL_4('4',             "The wall is restored over time. When the " +
                                "process begins, we see a timer."),

    STONE('☼',                  "Indestructible wall - It cannot be destroyed " +
                                "with a shot."),

    CRACK_PIT('*',              "At the moment of the shot, we see the wall " +
                                "like this."),

/// Clues

    CLUE_KNIFE('$',             "Clue knife. Collect a series of clues to " +
                                "get the maximum points."),

    CLUE_GLOVE('&',             "Clue glove. Collect a series of clues to " +
                                "get the maximum points."),

    CLUE_RING('@',              "Clue ring. Collect a series of clues to " +
                                "get the maximum points."),

/// Your hero

    HERO_DIE('Ѡ',               "Your hero is dead. In the next tick, it will " +
                                "disappear and appear in a new location."),

    HERO_LADDER('Y',            "Your hero is climbing the ladder."),

    HERO_LEFT('◄',              "Your hero runs to the left."),

    HERO_RIGHT('►',             "Your hero runs to the right."),

    HERO_FALL(']',              "Your hero is falling."),

    HERO_PIPE('{',              "Your hero is crawling along the pipe."),

    HERO_PIT('⍃',               "Your hero in the pit."),

/// Your hero in shadow mode

    HERO_MASK_DIE('x',          "Your shadow-hero is dead. In the next tick, " +
                                "it will disappear and appear in a new location."),

    HERO_MASK_LADDER('⍬',       "Your shadow-hero is climbing the ladder."),

    HERO_MASK_LEFT('⊲',         "Your shadow-hero runs to the left."),

    HERO_MASK_RIGHT('⊳',        "Your shadow-hero runs to the right."),

    HERO_MASK_FALL('⊅',         "Your shadow-hero is falling."),

    HERO_MASK_PIPE('⋜',         "Your shadow-hero is crawling along the pipe."),

    HERO_MASK_PIT('ᐊ',          "Your shadow-hero in the pit."),

/// Other heroes

    OTHER_HERO_DIE('Z',         "Other hero is dead. In the next tick, it will " +
                                "disappear and appear in a new location."),

    OTHER_HERO_LADDER('U',      "Other hero is climbing the ladder."),

    OTHER_HERO_LEFT(')',        "Other hero runs to the left."),

    OTHER_HERO_RIGHT('(',       "Other hero runs to the right."),

    OTHER_HERO_FALL('⊐',        "Other hero is falling."),

    OTHER_HERO_PIPE('Э',        "Other hero is crawling along the pipe."),

    OTHER_HERO_PIT('ᗉ',         "Other hero in the pit."),

/// Other heroes in shadow mode

    OTHER_HERO_MASK_DIE('⋈',    "Other shadow-hero is dead. In the next tick, " +
                                "it will disappear and appear in a new location."),

    OTHER_HERO_MASK_LADDER('⋕', "Other shadow-hero is climbing the ladder."),

    OTHER_HERO_MASK_LEFT('⋊',   "Other shadow-hero runs to the left."),

    OTHER_HERO_MASK_RIGHT('⋉',  "Other shadow-hero runs to the right."),

    OTHER_HERO_MASK_FALL('⋣',   "Other shadow-hero is falling."),

    OTHER_HERO_MASK_PIPE('⊣',   "Other shadow-hero is crawling along the pipe."),

    OTHER_HERO_MASK_PIT('ᗏ',    "Other shadow-hero in the pit."),

/// Enemy heroes

    ENEMY_HERO_DIE('Ž',         "Enemy hero is dead. In the next tick, it will " +
                                "disappear and appear in a new location."),

    ENEMY_HERO_LADDER('Ǔ',      "Enemy hero is climbing the ladder."),

    ENEMY_HERO_LEFT('❫',        "Enemy hero runs to the left."),

    ENEMY_HERO_RIGHT('❪',       "Enemy hero runs to the right."),

    ENEMY_HERO_FALL('⋥',        "Enemy hero is falling."),

    ENEMY_HERO_PIPE('Ǯ',        "Enemy hero is crawling along the pipe."),

    ENEMY_HERO_PIT('⇇',         "Enemy hero in the pit."),

/// Enemy heroes in shadow mode

    ENEMY_HERO_MASK_DIE('⧓',    "Enemy shadow-hero is dead. In the next tick, " +
                                "it will disappear and appear in a new location."),

    ENEMY_HERO_MASK_LADDER('≠', "Enemy shadow-hero is climbing the ladder."),

    ENEMY_HERO_MASK_LEFT('⧒',   "Enemy shadow-hero runs to the left."),

    ENEMY_HERO_MASK_RIGHT('⧑',  "Enemy shadow-hero runs to the right."),

    ENEMY_HERO_MASK_FALL('⌫',   "Enemy shadow-hero is falling."),

    ENEMY_HERO_MASK_PIPE('❵',    "Enemy shadow-hero is crawling along the pipe."),

    ENEMY_HERO_MASK_PIT('⬱',    "Enemy shadow-hero in the pit."),

// Robbers (dummy AI-bots)

    ROBBER_LADDER('Q',          "Robber is climbing the ladder."),

    ROBBER_LEFT('«',            "Robber runs to the left. Robber picks up the " +
                                "nearest prey and hunts for it until it overtakes it."),

    ROBBER_RIGHT('»',           "Robber runs to the right. Robber picks up the " +
                                "nearest prey and hunts for it until it overtakes it."),

    ROBBER_FALL('‹',            "Robber is falling."),

    ROBBER_PIPE('<',            "Robber is crawling along the pipe."),

    ROBBER_PIT('⍇',             "Robber in the pit."),

/// Doors and keys

    OPENED_DOOR_GOLD('⍙',       "Opened golden gates. Can only be locked " +
                                "with a golden key."),

    OPENED_DOOR_SILVER('⍚',     "Opened silver gates. Can only be locked " +
                                "with a silver key."),

    OPENED_DOOR_BRONZE('⍜',     "Opened bronze gates. Can only be locked " +
                                "with a bronze key."),

    CLOSED_DOOR_GOLD('⍍',       "Closed golden gates. Can only be opened " +
                                "with a golden key."),

    CLOSED_DOOR_SILVER('⌺',     "Closed silver gates. Can only be opened " +
                                "with a silver key."),

    CLOSED_DOOR_BRONZE('⌼',     "Closed bronze gates. Can only be opened " +
                                "with a bronze key."),

    KEY_GOLD('✦',               "Bronze key. Helps open/close golden gates. " +
                                "The key can only be used once."),

    KEY_SILVER('✼',             "Silver key. Helps open/close silver gates. " +
                                "The key can only be used once."),

    KEY_BRONZE('⍟',             "Bronze key. Helps open/close bronze gates. " +
                                "The key can only be used once."),

/// Other stuff

    BULLET('•',                 "Bullet. After the shot by the hero, the bullet " +
                                "flies until it meets an obstacle. The bullet " +
                                "kills the hero. It ricochets from the indestructible " +
                                "wall (no more than 1 time). The bullet destroys " +
                                "the destructible wall."),

    LADDER('H',                 "Ladder - the hero can move along the level " +
                                "along it."),

    PIPE('~',                   "Pipe - the hero can also move along the " +
                                "level along it, but only horizontally."),

    BACKWAY('⊛',                "Back door - allows the hero to secretly " +
                                "move to another random place on the map."),

    MASK_POTION('S',            "Disguise potion - endow the hero with " +
                                "additional abilities. The hero goes into " +
                                "shadow mode.");

    private final char ch;
    private final String info;

    Element(char ch, String info) {
        this.ch = ch;
        this.info = info;
    }

    public static Element[] clues() {
         return new Element[]{
                CLUE_KNIFE,
                CLUE_GLOVE,
                CLUE_RING
         };
    }

    public static Element[] ladders() {
        return new Element[]{
                LADDER,
                HERO_LADDER,
                HERO_MASK_LADDER,
                OTHER_HERO_LADDER,
                OTHER_HERO_MASK_LADDER,
                ROBBER_LADDER
        };
    }

    public static Element[] walls() {
        return new Element[]{
                BRICK,
                STONE
        };
    }

    public static Element[] heroes() {
         return new Element[]{
                HERO_DIE,
                HERO_LADDER,
                HERO_LEFT,
                HERO_RIGHT,
                HERO_FALL,
                HERO_PIPE,
                HERO_PIT,

                HERO_MASK_DIE,
                HERO_MASK_LADDER,
                HERO_MASK_LEFT,
                HERO_MASK_RIGHT,
                HERO_MASK_FALL,
                HERO_MASK_PIPE,
                HERO_MASK_PIT
         };
    }

    public static Element[] robbers() {
         return new Element[]{
                ROBBER_LADDER,
                ROBBER_LEFT,
                ROBBER_RIGHT,
                ROBBER_FALL,
                ROBBER_PIPE,
                ROBBER_PIT
         };
    }

    public static Element[] otherHeroes() {
         return new Element[]{
                OTHER_HERO_DIE,
                OTHER_HERO_LADDER,
                OTHER_HERO_LEFT,
                OTHER_HERO_RIGHT,
                OTHER_HERO_FALL,
                OTHER_HERO_PIPE,
                OTHER_HERO_PIT,

                OTHER_HERO_MASK_DIE,
                OTHER_HERO_MASK_LADDER,
                OTHER_HERO_MASK_LEFT,
                OTHER_HERO_MASK_RIGHT,
                OTHER_HERO_MASK_FALL,
                OTHER_HERO_MASK_PIPE,
                OTHER_HERO_MASK_PIT
         };
    }

    public static Element[] enemyHeroes() {
         return new Element[]{
                ENEMY_HERO_DIE,
                ENEMY_HERO_LADDER,
                ENEMY_HERO_LEFT,
                ENEMY_HERO_RIGHT,
                ENEMY_HERO_FALL,
                ENEMY_HERO_PIPE,
                ENEMY_HERO_PIT,

                ENEMY_HERO_MASK_DIE,
                ENEMY_HERO_MASK_LADDER,
                ENEMY_HERO_MASK_LEFT,
                ENEMY_HERO_MASK_RIGHT,
                ENEMY_HERO_MASK_FALL,
                ENEMY_HERO_MASK_PIPE,
                ENEMY_HERO_MASK_PIT
         };
    }

    public static Element[] pipes() {
         return new Element[]{
                PIPE,
                HERO_PIPE,
                HERO_MASK_PIPE,
                OTHER_HERO_PIPE,
                OTHER_HERO_MASK_PIPE,
                ENEMY_HERO_PIPE,
                ENEMY_HERO_MASK_PIPE
         };
    }

    public static Element[] doors() {
         return new Element[]{
                OPENED_DOOR_GOLD,
                OPENED_DOOR_SILVER,
                OPENED_DOOR_BRONZE,
                CLOSED_DOOR_GOLD,
                CLOSED_DOOR_SILVER,
                CLOSED_DOOR_BRONZE
         };
    }

    public static Element[] keys() {
         return new Element[]{
                KEY_GOLD,
                KEY_SILVER,
                KEY_BRONZE
         };
    }

    @Override
    public char ch() {
        return ch;
    }

    @Override
    public String info() {
        return info;
    }
    
    @Override
    public String toString() {
        return String.valueOf(ch);
    }

    public Element mask() {
        switch (this) {
            case HERO_DIE: return HERO_MASK_DIE;
            case HERO_LADDER: return HERO_MASK_LADDER;
            case HERO_LEFT: return HERO_MASK_LEFT;
            case HERO_RIGHT: return HERO_MASK_RIGHT;
            case HERO_FALL: return HERO_MASK_FALL;
            case HERO_PIPE: return HERO_MASK_PIPE;
            case HERO_PIT: return HERO_MASK_PIT;

            case OTHER_HERO_DIE: return OTHER_HERO_MASK_DIE;
            case OTHER_HERO_LADDER: return OTHER_HERO_MASK_LADDER;
            case OTHER_HERO_LEFT: return OTHER_HERO_MASK_LEFT;
            case OTHER_HERO_RIGHT: return OTHER_HERO_MASK_RIGHT;
            case OTHER_HERO_FALL: return OTHER_HERO_MASK_FALL;
            case OTHER_HERO_PIPE: return OTHER_HERO_MASK_PIPE;
            case OTHER_HERO_PIT: return OTHER_HERO_MASK_PIT;

            case ENEMY_HERO_DIE: return ENEMY_HERO_MASK_DIE;
            case ENEMY_HERO_LADDER: return ENEMY_HERO_MASK_LADDER;
            case ENEMY_HERO_LEFT: return ENEMY_HERO_MASK_LEFT;
            case ENEMY_HERO_RIGHT: return ENEMY_HERO_MASK_RIGHT;
            case ENEMY_HERO_FALL: return ENEMY_HERO_MASK_FALL;
            case ENEMY_HERO_PIPE: return ENEMY_HERO_MASK_PIPE;
            case ENEMY_HERO_PIT: return ENEMY_HERO_MASK_PIT;
        }
        throw new IllegalArgumentException("Bad hero state: " + this);
    }

    public Element otherHero() {
        switch (this) {
            case HERO_DIE: return OTHER_HERO_DIE;
            case HERO_LADDER: return OTHER_HERO_LADDER;
            case HERO_LEFT: return OTHER_HERO_LEFT;
            case HERO_RIGHT: return OTHER_HERO_RIGHT;
            case HERO_FALL: return OTHER_HERO_FALL;
            case HERO_PIPE: return OTHER_HERO_PIPE;
            case HERO_PIT: return OTHER_HERO_PIT;

            case HERO_MASK_DIE: return OTHER_HERO_MASK_DIE;
            case HERO_MASK_LADDER: return OTHER_HERO_MASK_LADDER;
            case HERO_MASK_LEFT: return OTHER_HERO_MASK_LEFT;
            case HERO_MASK_RIGHT: return OTHER_HERO_MASK_RIGHT;
            case HERO_MASK_FALL: return OTHER_HERO_MASK_FALL;
            case HERO_MASK_PIPE: return OTHER_HERO_MASK_PIPE;
            case HERO_MASK_PIT: return OTHER_HERO_MASK_PIT;
        }
        throw new IllegalArgumentException("Bad hero state: " + this);
    }

    public Element enemyHero() {
        switch (this) {
            case HERO_DIE: return ENEMY_HERO_DIE;
            case HERO_LADDER: return ENEMY_HERO_LADDER;
            case HERO_LEFT: return ENEMY_HERO_LEFT;
            case HERO_RIGHT: return ENEMY_HERO_RIGHT;
            case HERO_FALL: return ENEMY_HERO_FALL;
            case HERO_PIPE: return ENEMY_HERO_PIPE;
            case HERO_PIT: return ENEMY_HERO_PIT;

            case HERO_MASK_DIE: return ENEMY_HERO_MASK_DIE;
            case HERO_MASK_LADDER: return ENEMY_HERO_MASK_LADDER;
            case HERO_MASK_LEFT: return ENEMY_HERO_MASK_LEFT;
            case HERO_MASK_RIGHT: return ENEMY_HERO_MASK_RIGHT;
            case HERO_MASK_FALL: return ENEMY_HERO_MASK_FALL;
            case HERO_MASK_PIPE: return ENEMY_HERO_MASK_PIPE;
            case HERO_MASK_PIT: return ENEMY_HERO_PIT;
        }
        throw new IllegalArgumentException("Bad hero state: " + this);
    }

    public static Element valueOf(char ch) {
        for (Element el : Element.values()) {
            if (el.ch == ch) {
                return el;
            }
        }
        throw new IllegalArgumentException("No such element for " + ch);
    }
}

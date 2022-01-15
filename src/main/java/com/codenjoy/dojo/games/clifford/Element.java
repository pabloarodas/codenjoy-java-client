package com.codenjoy.dojo.games.clifford;

/*-
 * #%L
 * Codenjoy - it's a dojo-like platform from developers to developers.
 * %%
 * Copyright (C) 2012 - 2022 Codenjoy
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
import com.codenjoy.dojo.services.printer.TeamElement;

import java.util.HashSet;
import java.util.Set;

public enum Element implements CharElement, TeamElement {

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

    HERO_DIE('O',               "Your hero is dead. In the next tick, it will " +
                                "disappear and appear in a new location."),

    HERO_LADDER('A',            "Your hero is climbing the ladder."),

    HERO_LEFT('◄',              "Your hero runs to the left."),

    HERO_RIGHT('►',             "Your hero runs to the right."),

    HERO_FALL('U',              "Your hero is falling."),

    HERO_PIPE('I',              "Your hero is crawling along the pipe."),

    HERO_PIT('E',               "Your hero in the pit."),

/// Your hero in shadow mode

    HERO_MASK_DIE('o',          "Your shadow-hero is dead. In the next tick, " +
                                "it will disappear and appear in a new location."),

    HERO_MASK_LADDER('a',       "Your shadow-hero is climbing the ladder."),

    HERO_MASK_LEFT('h',         "Your shadow-hero runs to the left."),

    HERO_MASK_RIGHT('w',        "Your shadow-hero runs to the right."),

    HERO_MASK_FALL('u',         "Your shadow-hero is falling."),

    HERO_MASK_PIPE('i',         "Your shadow-hero is crawling along the pipe."),

    HERO_MASK_PIT('e',          "Your shadow-hero in the pit."),

/// Other heroes

    OTHER_HERO_DIE('C',         "Other hero is dead. In the next tick, it will " +
                                "disappear and appear in a new location."),

    OTHER_HERO_LADDER('D',      "Other hero is climbing the ladder."),

    OTHER_HERO_LEFT('«',        "Other hero runs to the left."),

    OTHER_HERO_RIGHT('»',       "Other hero runs to the right."),

    OTHER_HERO_FALL('F',        "Other hero is falling."),

    OTHER_HERO_PIPE('J',        "Other hero is crawling along the pipe."),

    OTHER_HERO_PIT('K',         "Other hero in the pit."),

/// Other heroes in shadow mode

    OTHER_HERO_MASK_DIE('c',    "Other shadow-hero is dead. In the next tick, " +
                                "it will disappear and appear in a new location."),

    OTHER_HERO_MASK_LADDER('d', "Other shadow-hero is climbing the ladder."),

    OTHER_HERO_MASK_LEFT('Z',   "Other shadow-hero runs to the left."),

    OTHER_HERO_MASK_RIGHT('z',  "Other shadow-hero runs to the right."),

    OTHER_HERO_MASK_FALL('f',   "Other shadow-hero is falling."),

    OTHER_HERO_MASK_PIPE('j',   "Other shadow-hero is crawling along the pipe."),

    OTHER_HERO_MASK_PIT('k',    "Other shadow-hero in the pit."),

/// Enemy heroes

    ENEMY_HERO_DIE('L',         "Enemy hero is dead. In the next tick, it will " +
                                "disappear and appear in a new location."),

    ENEMY_HERO_LADDER('N',      "Enemy hero is climbing the ladder."),

    ENEMY_HERO_LEFT('P',        "Enemy hero runs to the left."),

    ENEMY_HERO_RIGHT('Q',       "Enemy hero runs to the right."),

    ENEMY_HERO_FALL('R',        "Enemy hero is falling."),

    ENEMY_HERO_PIPE('T',        "Enemy hero is crawling along the pipe."),

    ENEMY_HERO_PIT('V',         "Enemy hero in the pit."),

/// Enemy heroes in shadow mode

    ENEMY_HERO_MASK_DIE('l',    "Enemy shadow-hero is dead. In the next tick, " +
                                "it will disappear and appear in a new location."),

    ENEMY_HERO_MASK_LADDER('n', "Enemy shadow-hero is climbing the ladder."),

    ENEMY_HERO_MASK_LEFT('p',   "Enemy shadow-hero runs to the left."),

    ENEMY_HERO_MASK_RIGHT('q',  "Enemy shadow-hero runs to the right."),

    ENEMY_HERO_MASK_FALL('r',   "Enemy shadow-hero is falling."),

    ENEMY_HERO_MASK_PIPE('t',   "Enemy shadow-hero is crawling along the pipe."),

    ENEMY_HERO_MASK_PIT('v',    "Enemy shadow-hero in the pit."),

// Robbers (dummy AI-bots)

    ROBBER_LADDER('X',          "Robber is climbing the ladder."),

    ROBBER_LEFT(')',            "Robber runs to the left. Robber picks up the " +
                                "nearest prey and hunts for it until it overtakes it."),

    ROBBER_RIGHT('(',           "Robber runs to the right. Robber picks up the " +
                                "nearest prey and hunts for it until it overtakes it."),

    ROBBER_FALL('x',            "Robber is falling."),

    ROBBER_PIPE('Y',            "Robber is crawling along the pipe."),

    ROBBER_PIT('y',             "Robber in the pit."),

/// Doors and keys

    OPENED_DOOR_GOLD('g',       "Opened golden gates. Can only be locked " +
                                "with a golden key."),

    OPENED_DOOR_SILVER('s',     "Opened silver gates. Can only be locked " +
                                "with a silver key."),

    OPENED_DOOR_BRONZE('b',     "Opened bronze gates. Can only be locked " +
                                "with a bronze key."),

    CLOSED_DOOR_GOLD('G',       "Closed golden gates. Can only be opened " +
                                "with a golden key."),

    CLOSED_DOOR_SILVER('S',     "Closed silver gates. Can only be opened " +
                                "with a silver key."),

    CLOSED_DOOR_BRONZE('B',     "Closed bronze gates. Can only be opened " +
                                "with a bronze key."),

    KEY_GOLD('+',               "Golden key. Helps open/close golden gates. " +
                                "The key can only be used once."),

    KEY_SILVER('-',             "Silver key. Helps open/close silver gates. " +
                                "The key can only be used once."),

    KEY_BRONZE('!',             "Bronze key. Helps open/close bronze gates. " +
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

    BACKWAY('W',                "Back door - allows the hero to secretly " +
                                "move to another random place on the map."),

    MASK_POTION('m',            "Disguise potion - endow the hero with " +
                                "additional abilities. The hero goes into " +
                                "shadow mode.");

    private final char ch;
    private final String info;

    Element(char ch, String info) {
        this.ch = ch;
        this.info = info;
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

    public boolean isClue() {
        return is(clues);
    }

    public boolean isMaskPotion() {
        return is(maskPotions);
    }

    public boolean isBackWay() {
        return is(backWays);
    }

    public boolean isLadder() {
        return is(ladders);
    }

    public boolean isWall() {
        return is(walls);
    }

    public boolean isHero() {
        return is(heroes);
    }

    public boolean isOtherHero() {
        return is(otherHeroes);
    }

    public boolean isEnemyHero() {
        return is(enemyHeroes);
    }

    public boolean isRobber() {
        return is(robbers);
    }

    public boolean isPipe() {
        return is(pipes);
    }

    public boolean isPit() {
        return is(pits);
    }

    public boolean isBullet() {
        return is(bullets);
    }

    public boolean isDoor() {
        return is(doors);
    }

    public boolean isKey() {
        return is(keys);
    }

    public static final Element[] clues = new Element[]{
            CLUE_KNIFE,
            CLUE_GLOVE,
            CLUE_RING,
    };

    public static final Element[] backWays = new Element[]{
            BACKWAY,
    };

    public static final Element[] maskPotions = new Element[]{
            MASK_POTION,
    };

    public static final Element[] ladders = new Element[]{
            LADDER,

            HERO_LADDER,
            OTHER_HERO_LADDER,
            ROBBER_LADDER,
            ENEMY_HERO_LADDER,

            HERO_MASK_LADDER,
            OTHER_HERO_MASK_LADDER,
            ENEMY_HERO_MASK_LADDER,
    };

    public static final Set<Element> ladders2 = new HashSet<>(){{
            add(LADDER);

            add(HERO_LADDER);
            add(OTHER_HERO_LADDER);
            add(ROBBER_LADDER);
            add(ENEMY_HERO_LADDER);

            add(HERO_MASK_LADDER);
            add(OTHER_HERO_MASK_LADDER);
            add(ENEMY_HERO_MASK_LADDER);
    }};

    public static final Set<Element> walls2 = new HashSet<>(){{
            add(STONE);
            add(BRICK);
    }};

    public static final Element[] walls = new Element[]{
            STONE,
            BRICK,
    };

    public static final Element[] heroes = new Element[]{
            HERO_LEFT,
            HERO_RIGHT,
            HERO_LADDER,
            HERO_PIPE,
            HERO_FALL,
            HERO_PIT,
            HERO_DIE,

            HERO_MASK_LEFT,
            HERO_MASK_RIGHT,
            HERO_MASK_LADDER,
            HERO_MASK_PIPE,
            HERO_MASK_FALL,
            HERO_MASK_PIT,
            HERO_MASK_DIE,
    };

    public static final Element[] heroDie = new Element[]{
            HERO_DIE,
            HERO_MASK_DIE,
    };

    public static final Element[] robbers = new Element[]{
            ROBBER_LEFT,
            ROBBER_RIGHT,
            ROBBER_LADDER,
            ROBBER_PIPE,
            ROBBER_FALL,
            ROBBER_PIT,
    };

    public static final Set<Element> robbers2 = new HashSet<>(){{
            add(ROBBER_LEFT);
            add(ROBBER_RIGHT);
            add(ROBBER_LADDER);
            add(ROBBER_PIPE);
            add(ROBBER_FALL);
            add(ROBBER_PIT);
    }};

    public static final Element[] otherHeroes = new Element[]{
            OTHER_HERO_LEFT,
            OTHER_HERO_RIGHT,
            OTHER_HERO_LADDER,
            OTHER_HERO_PIPE,
            OTHER_HERO_FALL,
            OTHER_HERO_PIT,
            OTHER_HERO_DIE,

            OTHER_HERO_MASK_LEFT,
            OTHER_HERO_MASK_RIGHT,
            OTHER_HERO_MASK_LADDER,
            OTHER_HERO_MASK_PIPE,
            OTHER_HERO_MASK_FALL,
            OTHER_HERO_MASK_PIT,
            OTHER_HERO_MASK_DIE,
    };

    public static final Set<Element> otherHeroes2 = new HashSet<>(){{
            add(OTHER_HERO_LEFT);
            add(OTHER_HERO_RIGHT);
            add(OTHER_HERO_LADDER);
            add(OTHER_HERO_PIPE);
            add(OTHER_HERO_FALL);
            add(OTHER_HERO_PIT);
            add(OTHER_HERO_DIE);

            add(OTHER_HERO_MASK_LEFT);
            add(OTHER_HERO_MASK_RIGHT);
            add(OTHER_HERO_MASK_LADDER);
            add(OTHER_HERO_MASK_PIPE);
            add(OTHER_HERO_MASK_FALL);
            add(OTHER_HERO_MASK_PIT);
            add(OTHER_HERO_MASK_DIE);
    }};

    public static final Element[] enemyHeroes =  new Element[]{
            ENEMY_HERO_LEFT,
            ENEMY_HERO_RIGHT,
            ENEMY_HERO_LADDER,
            ENEMY_HERO_PIPE,
            ENEMY_HERO_FALL,
            ENEMY_HERO_PIT,
            ENEMY_HERO_DIE,

            ENEMY_HERO_MASK_LEFT,
            ENEMY_HERO_MASK_RIGHT,
            ENEMY_HERO_MASK_LADDER,
            ENEMY_HERO_MASK_PIPE,
            ENEMY_HERO_MASK_FALL,
            ENEMY_HERO_MASK_PIT,
            ENEMY_HERO_MASK_DIE,
    };

    public static final Set<Element> enemyHeroes2 =  new HashSet<>(){{
            add(ENEMY_HERO_LEFT);
            add(ENEMY_HERO_RIGHT);
            add(ENEMY_HERO_LADDER);
            add(ENEMY_HERO_PIPE);
            add(ENEMY_HERO_FALL);
            add(ENEMY_HERO_PIT);
            add(ENEMY_HERO_DIE);

            add(ENEMY_HERO_MASK_LEFT);
            add(ENEMY_HERO_MASK_RIGHT);
            add(ENEMY_HERO_MASK_LADDER);
            add(ENEMY_HERO_MASK_PIPE);
            add(ENEMY_HERO_MASK_FALL);
            add(ENEMY_HERO_MASK_PIT);
            add(ENEMY_HERO_MASK_DIE);
    }};

    public static final Element[] pipes = new Element[]{
            PIPE,

            HERO_PIPE,
            OTHER_HERO_PIPE,
            ROBBER_PIPE,
            ENEMY_HERO_PIPE,

            HERO_MASK_PIPE,
            OTHER_HERO_MASK_PIPE,
            ENEMY_HERO_MASK_PIPE,
    };

    public static final Set<Element> pipes2 = new HashSet<>(){{
            add(PIPE);

            add(HERO_PIPE);
            add(OTHER_HERO_PIPE);
            add(ROBBER_PIPE);
            add(ENEMY_HERO_PIPE);

            add(HERO_MASK_PIPE);
            add(OTHER_HERO_MASK_PIPE);
            add(ENEMY_HERO_MASK_PIPE);
    }};

    public static final Element[] pits = new Element[]{
            CRACK_PIT,
            PIT_FILL_1,
            PIT_FILL_2,
            PIT_FILL_3,
            PIT_FILL_4,

            HERO_PIT,
            OTHER_HERO_PIT,
            ROBBER_PIT,
            ENEMY_HERO_PIT,

            HERO_MASK_PIT,
            OTHER_HERO_MASK_PIT,
            ENEMY_HERO_MASK_PIT,
    };

    public static final Element[] bullets = new Element[]{
            BULLET,
    };

    public static final Element[] doors = new Element[]{
            CLOSED_DOOR_GOLD,
            CLOSED_DOOR_SILVER,
            CLOSED_DOOR_BRONZE,

            OPENED_DOOR_GOLD,
            OPENED_DOOR_SILVER,
            OPENED_DOOR_BRONZE,
    };

    public static final Element[] keys = new Element[]{
            KEY_GOLD,
            KEY_SILVER,
            KEY_BRONZE,
    };

    public static final Element[] barriers = new Element[]{
            STONE,
            BRICK,
            CLOSED_DOOR_GOLD,
            CLOSED_DOOR_SILVER,
            CLOSED_DOOR_BRONZE,
            BULLET,

            ROBBER_LEFT,
            ROBBER_RIGHT,
            ROBBER_LADDER,
            ROBBER_PIPE,
            ROBBER_FALL,
            ROBBER_PIT,

            OTHER_HERO_LEFT,
            OTHER_HERO_RIGHT,
            OTHER_HERO_LADDER,
            OTHER_HERO_PIPE,
            OTHER_HERO_FALL,
            OTHER_HERO_PIT,
            OTHER_HERO_DIE,

            ENEMY_HERO_LEFT,
            ENEMY_HERO_RIGHT,
            ENEMY_HERO_LADDER,
            ENEMY_HERO_PIPE,
            ENEMY_HERO_FALL,
            ENEMY_HERO_PIT,
            ENEMY_HERO_DIE,

            OTHER_HERO_MASK_LEFT,
            OTHER_HERO_MASK_RIGHT,
            OTHER_HERO_MASK_LADDER,
            OTHER_HERO_MASK_PIPE,
            OTHER_HERO_MASK_FALL,
            OTHER_HERO_MASK_PIT,
            OTHER_HERO_MASK_DIE,

            ENEMY_HERO_MASK_LEFT,
            ENEMY_HERO_MASK_RIGHT,
            ENEMY_HERO_MASK_LADDER,
            ENEMY_HERO_MASK_PIPE,
            ENEMY_HERO_MASK_FALL,
            ENEMY_HERO_MASK_PIT,
            ENEMY_HERO_MASK_DIE,
    };

    public static final Set<Element> barriers2 = new HashSet<>(){{
            add(STONE);
            add(BRICK);
            add(CLOSED_DOOR_GOLD);
            add(CLOSED_DOOR_SILVER);
            add(CLOSED_DOOR_BRONZE);
            add(BULLET);

            add(ROBBER_LEFT);
            add(ROBBER_RIGHT);
            add(ROBBER_LADDER);
            add(ROBBER_PIPE);
            add(ROBBER_FALL);
            add(ROBBER_PIT);

            add(OTHER_HERO_LEFT);
            add(OTHER_HERO_RIGHT);
            add(OTHER_HERO_LADDER);
            add(OTHER_HERO_PIPE);
            add(OTHER_HERO_FALL);
            add(OTHER_HERO_PIT);
            add(OTHER_HERO_DIE);

            add(ENEMY_HERO_LEFT);
            add(ENEMY_HERO_RIGHT);
            add(ENEMY_HERO_LADDER);
            add(ENEMY_HERO_PIPE);
            add(ENEMY_HERO_FALL);
            add(ENEMY_HERO_PIT);
            add(ENEMY_HERO_DIE);

            add(OTHER_HERO_MASK_LEFT);
            add(OTHER_HERO_MASK_RIGHT);
            add(OTHER_HERO_MASK_LADDER);
            add(OTHER_HERO_MASK_PIPE);
            add(OTHER_HERO_MASK_FALL);
            add(OTHER_HERO_MASK_PIT);
            add(OTHER_HERO_MASK_DIE);

            add(ENEMY_HERO_MASK_LEFT);
            add(ENEMY_HERO_MASK_RIGHT);
            add(ENEMY_HERO_MASK_LADDER);
            add(ENEMY_HERO_MASK_PIPE);
            add(ENEMY_HERO_MASK_FALL);
            add(ENEMY_HERO_MASK_PIT);
            add(ENEMY_HERO_MASK_DIE);
    }};

    public Element mask() {
        switch (this) {
            case HERO_DIE:          return HERO_MASK_DIE;
            case HERO_LADDER:       return HERO_MASK_LADDER;
            case HERO_LEFT:         return HERO_MASK_LEFT;
            case HERO_RIGHT:        return HERO_MASK_RIGHT;
            case HERO_FALL:         return HERO_MASK_FALL;
            case HERO_PIPE:         return HERO_MASK_PIPE;
            case HERO_PIT:          return HERO_MASK_PIT;

            case OTHER_HERO_DIE:    return OTHER_HERO_MASK_DIE;
            case OTHER_HERO_LADDER: return OTHER_HERO_MASK_LADDER;
            case OTHER_HERO_LEFT:   return OTHER_HERO_MASK_LEFT;
            case OTHER_HERO_RIGHT:  return OTHER_HERO_MASK_RIGHT;
            case OTHER_HERO_FALL:   return OTHER_HERO_MASK_FALL;
            case OTHER_HERO_PIPE:   return OTHER_HERO_MASK_PIPE;
            case OTHER_HERO_PIT:    return OTHER_HERO_MASK_PIT;

            case ENEMY_HERO_DIE:    return ENEMY_HERO_MASK_DIE;
            case ENEMY_HERO_LADDER: return ENEMY_HERO_MASK_LADDER;
            case ENEMY_HERO_LEFT:   return ENEMY_HERO_MASK_LEFT;
            case ENEMY_HERO_RIGHT:  return ENEMY_HERO_MASK_RIGHT;
            case ENEMY_HERO_FALL:   return ENEMY_HERO_MASK_FALL;
            case ENEMY_HERO_PIPE:   return ENEMY_HERO_MASK_PIPE;
            case ENEMY_HERO_PIT:    return ENEMY_HERO_MASK_PIT;
        }
        throw new IllegalArgumentException("Bad hero state: " + this);
    }

    @Override
    public Element otherHero() {
        switch (this) {
            case HERO_DIE:         return OTHER_HERO_DIE;
            case HERO_LADDER:      return OTHER_HERO_LADDER;
            case HERO_LEFT:        return OTHER_HERO_LEFT;
            case HERO_RIGHT:       return OTHER_HERO_RIGHT;
            case HERO_FALL:        return OTHER_HERO_FALL;
            case HERO_PIPE:        return OTHER_HERO_PIPE;
            case HERO_PIT:         return OTHER_HERO_PIT;

            case HERO_MASK_DIE:    return OTHER_HERO_MASK_DIE;
            case HERO_MASK_LADDER: return OTHER_HERO_MASK_LADDER;
            case HERO_MASK_LEFT:   return OTHER_HERO_MASK_LEFT;
            case HERO_MASK_RIGHT:  return OTHER_HERO_MASK_RIGHT;
            case HERO_MASK_FALL:   return OTHER_HERO_MASK_FALL;
            case HERO_MASK_PIPE:   return OTHER_HERO_MASK_PIPE;
            case HERO_MASK_PIT:    return OTHER_HERO_MASK_PIT;
        }
        throw new IllegalArgumentException("Bad hero state: " + this);
    }

    @Override
    public Element enemyHero() {
        switch (this) {
            case HERO_DIE:         return ENEMY_HERO_DIE;
            case HERO_LADDER:      return ENEMY_HERO_LADDER;
            case HERO_LEFT:        return ENEMY_HERO_LEFT;
            case HERO_RIGHT:       return ENEMY_HERO_RIGHT;
            case HERO_FALL:        return ENEMY_HERO_FALL;
            case HERO_PIPE:        return ENEMY_HERO_PIPE;
            case HERO_PIT:         return ENEMY_HERO_PIT;

            case HERO_MASK_DIE:    return ENEMY_HERO_MASK_DIE;
            case HERO_MASK_LADDER: return ENEMY_HERO_MASK_LADDER;
            case HERO_MASK_LEFT:   return ENEMY_HERO_MASK_LEFT;
            case HERO_MASK_RIGHT:  return ENEMY_HERO_MASK_RIGHT;
            case HERO_MASK_FALL:   return ENEMY_HERO_MASK_FALL;
            case HERO_MASK_PIPE:   return ENEMY_HERO_MASK_PIPE;
            case HERO_MASK_PIT:    return ENEMY_HERO_PIT;
        }
        throw new IllegalArgumentException("Bad hero state: " + this);
    }
}
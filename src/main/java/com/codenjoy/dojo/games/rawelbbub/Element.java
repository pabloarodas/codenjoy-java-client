package com.codenjoy.dojo.games.rawelbbub;

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

public enum Element implements CharElement {

    WATER(' ', 0,    "An empty space where hero can move. " +
                     "If there was an iceberg in this place before, " +
                     "it can grow again "),

    REEFS('☼',       "Underwater reefs. They cannot be destroyed " +
                     "without prize PRIZE_BREAKING_BAD."),

    EXPLOSION('Ѡ',   "Explosion site. It disappears in a second."),

	OIL('#',         "Oil leak, hitting which the hero partially loses control. " +
                     "During the passage, the field of view is limited and " +
                     "the hero will repeat the old commands for several " +
                     "ticks in a row, ignoring the current commands."),

    SEAWEED('%',     "Seaweed hide heroes which can continue to shoot at the " +
                     "same time. The fired shells are also not visible under the " +
                     "weed. Only prizes can be seen from behind seaweed."),

    FISHNET('~',     "Fishnet does not allow to pass through itself without " +
                     "the PRIZE_WALKING_ON_FISHNET prize, but the shells fly freely " +
                     "through the water. Hero stuck in the middle of the fishnet, " +
                     "after canceling the PRIZE_WALKING_ON_FISHNET prize, can move " +
                     "1 cell in the fishnet only every N ticks."),

    ICEBERG_HUGE('╬', 3,              "An iceberg that hasn't been shot yet. It takes 3 shots to completely destroy."),

    ICEBERG_MEDIUM_LEFT('╠', 2,       "Partially destroyed iceberg. For complete destruction, 2 shot is required."),
    ICEBERG_MEDIUM_RIGHT('╣', 2,      "Partially destroyed iceberg. For complete destruction, 2 shot is required."),
    ICEBERG_MEDIUM_UP('╦', 2,         "Partially destroyed iceberg. For complete destruction, 2 shot is required."),
    ICEBERG_MEDIUM_DOWN('╩', 2,       "Partially destroyed iceberg. For complete destruction, 2 shot is required."),

    ICEBERG_SMALL_LEFT_LEFT('╞', 1,   "Almost destroyed iceberg. For complete destruction, 1 shot is required."),
    ICEBERG_SMALL_RIGHT_RIGHT('╡', 1, "Almost destroyed iceberg. For complete destruction, 1 shot is required."),
    ICEBERG_SMALL_UP_UP('╥', 1,       "Almost destroyed iceberg. For complete destruction, 1 shot is required."),
    ICEBERG_SMALL_DOWN_DOWN('╨', 1,   "Almost destroyed iceberg. For complete destruction, 1 shot is required."),

    ICEBERG_SMALL_LEFT_RIGHT('│', 1,  "Almost destroyed iceberg. For complete destruction, 1 shot is required."),
    ICEBERG_SMALL_UP_DOWN('─', 1,     "Almost destroyed iceberg. For complete destruction, 1 shot is required."),

    ICEBERG_SMALL_UP_LEFT('┌', 1,     "Almost destroyed iceberg. For complete destruction, 1 shot is required."),
    ICEBERG_SMALL_UP_RIGHT('┐', 1,    "Almost destroyed iceberg. For complete destruction, 1 shot is required."),
    ICEBERG_SMALL_DOWN_LEFT('└', 1,   "Almost destroyed iceberg. For complete destruction, 1 shot is required."),
    ICEBERG_SMALL_DOWN_RIGHT('┘', 1,  "Almost destroyed iceberg. For complete destruction, 1 shot is required."),

/// classic mode (turn in a given direction with movement)

    TORPEDO_LEFT('•',      "Torpedo - is a self-propelled underwater missile designed to " +
                           "be fired from a submarine and to explode on reaching a target. " +
                           "The target can be an iceberg, another submarine and other " +
                           "elements under water. This torpedo moves to the left."),
    TORPEDO_RIGHT('¤',     "This torpedo moves to the right."),
    TORPEDO_UP('ø',        "This torpedo moves to the up."),
    TORPEDO_DOWN('×',      "This torpedo moves to the down."),

    HERO_LEFT('◄',         "Your hero is pointing left."),
    HERO_RIGHT('►',        "Your hero is pointing right."),
    HERO_UP('▲',           "Your hero is pointing up."),
    HERO_DOWN('▼',         "Your hero is pointing down."),

    OTHER_HERO_LEFT('˂',   "Other hero is pointing left."),
    OTHER_HERO_RIGHT('˃',  "Other hero is pointing right."),
    OTHER_HERO_UP('˄',     "Other hero is pointing up."),
    OTHER_HERO_DOWN('˅',   "Other hero is pointing down."),

    ENEMY_HERO_LEFT('Ð',   "Enemy hero is pointing left."),
    ENEMY_HERO_RIGHT('£',  "Enemy hero is pointing right."),
    ENEMY_HERO_UP('Ô',     "Enemy hero is pointing up."),
    ENEMY_HERO_DOWN('Ç',   "Enemy hero is pointing down."),

    AI_LEFT('«',           "AI is pointing left."),
    AI_RIGHT('»',          "AI is pointing right."),
    AI_UP('?',             "AI is pointing up."),
    AI_DOWN('¿',           "AI is pointing down."),

    AI_PRIZE_LEFT('{',     "AI with prize is pointing left."),
    AI_PRIZE_RIGHT('}',    "AI with prize is pointing right."),
    AI_PRIZE_UP('î',       "AI with prize is pointing up."),
    AI_PRIZE_DOWN('w',     "AI with prize is pointing down."),

/// turn based mode (either turn or move)

    TORPEDO_SIDE_LEFT('t',     "This torpedo moves to the left."),
    TORPEDO_SIDE_RIGHT('T',    "This torpedo moves to the right."),

    HERO_SIDE_LEFT('h',        "Your hero is pointing left."),
    HERO_SIDE_RIGHT('H',       "Your hero is pointing right."),

    OTHER_HERO_SIDE_LEFT('o',  "Other hero is pointing left."),
    OTHER_HERO_SIDE_RIGHT('O', "Other hero is pointing right."),

    ENEMY_HERO_SIDE_LEFT('e',  "Enemy hero is pointing left."),
    ENEMY_HERO_SIDE_RIGHT('E', "Enemy hero is pointing right."),

    AI_SIDE_LEFT('a',          "AI is pointing left."),
    AI_SIDE_RIGHT('A',         "AI is pointing right."),

    AI_PRIZE_SIDE_LEFT('p',    "AI with prize is pointing left."),
    AI_PRIZE_SIDE_RIGHT('P',   "AI with prize is pointing right."),

/// prizes

    PRIZE('!',             "The dropped prize after the destruction of the prize " +
                           "AI flickers on the field every even tick of the game " +
                           "with this sprite."),

    PRIZE_IMMORTALITY('1',        "A prize that gives the hero temporary invulnerability."),

    PRIZE_BREAKING_BAD('2',       "A prize that allows you to temporarily destroy any icebergs " +
                                  "and underwater reefs (but not the border of the field) " +
                                  "with 1 shot."),

    PRIZE_WALKING_ON_FISHNET('3', "A prize that allows the hero to temporarily walk on fishnet."),

    PRIZE_VISIBILITY('4',         "A prize that allows the hero to temporarily see all " +
                                  "enemies and their bullets under the seaweed."),

    PRIZE_NO_SLIDING('5',         "A prize that allows the hero to temporarily not slide " +
                                  "on the ice.");

    private final char ch;
    private final String info;
    private final int power;

    Element(char ch, String info) {
        this(ch, -1, info);
    }

    Element(char ch, int power, String info) {
        this.ch = ch;
        this.info = info;
        this.power = power;
    }

    @Override
    public char ch() {
        return ch;
    }

    @Override
    public String info() {
        return info;
    }

    public int power() {
        return power;
    }

    @Override
    public String toString() {
        return String.valueOf(ch);
    }
}
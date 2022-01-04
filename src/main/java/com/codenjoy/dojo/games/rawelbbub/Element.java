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
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static java.util.stream.Collectors.toList;

public enum Element implements CharElement {

    WATER(' ',       "An empty space where hero can move."),

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

    ICEBERG_MEDIUM_DOWN('╩', 2,       "Partially destroyed iceberg. For complete destruction, 2 shot is required."),
    ICEBERG_MEDIUM_UP('╦', 2,         "Partially destroyed iceberg. For complete destruction, 2 shot is required."),
    ICEBERG_MEDIUM_LEFT('╠', 2,       "Partially destroyed iceberg. For complete destruction, 2 shot is required."),
    ICEBERG_MEDIUM_RIGHT('╣', 2,      "Partially destroyed iceberg. For complete destruction, 2 shot is required."),

    ICEBERG_SMALL_DOWN_DOWN('╨', 1,   "Almost destroyed iceberg. For complete destruction, 1 shot is required."),
    ICEBERG_SMALL_UP_UP('╥', 1,       "Almost destroyed iceberg. For complete destruction, 1 shot is required."),
    ICEBERG_SMALL_LEFT_LEFT('╞', 1,   "Almost destroyed iceberg. For complete destruction, 1 shot is required."),
    ICEBERG_SMALL_RIGHT_RIGHT('╡', 1, "Almost destroyed iceberg. For complete destruction, 1 shot is required."),

    ICEBERG_SMALL_LEFT_RIGHT('│', 1,  "Almost destroyed iceberg. For complete destruction, 1 shot is required."),
    ICEBERG_SMALL_UP_DOWN('─', 1,     "Almost destroyed iceberg. For complete destruction, 1 shot is required."),

    ICEBERG_SMALL_UP_LEFT('┌', 1,     "Almost destroyed iceberg. For complete destruction, 1 shot is required."),
    ICEBERG_SMALL_UP_RIGHT('┐', 1,    "Almost destroyed iceberg. For complete destruction, 1 shot is required."),
    ICEBERG_SMALL_DOWN_LEFT('└', 1,   "Almost destroyed iceberg. For complete destruction, 1 shot is required."),
    ICEBERG_SMALL_DOWN_RIGHT('┘', 1,  "Almost destroyed iceberg. For complete destruction, 1 shot is required."),

    ICEBERG_DESTROYED(' ', 0,         "Completely destroyed iceberg. No different from WATER. " +
                                      "A new one will appear at this place soon."),

    TORPEDO('•',           "Torpedo - is a self-propelled underwater missile designed to " +
                           "be fired from a submarine and to explode on reaching a target. " +
                           "The target can be an iceberg, another submarine and other " +
                           "elements under water."),

    HERO_UP('▲',           "Your hero is pointing up."),
    HERO_RIGHT('►',        "Your hero is pointing right."),
    HERO_DOWN('▼',         "Your hero is pointing down."),
    HERO_LEFT('◄',         "Your hero is pointing left."),

    OTHER_HERO_UP('˄',     "Enemy hero is pointing up."),
    OTHER_HERO_RIGHT('˃',  "Enemy hero is pointing right."),
    OTHER_HERO_DOWN('˅',   "Enemy hero is pointing down."),
    OTHER_HERO_LEFT('˂',   "Enemy hero is pointing left."),

    AI_UP('?',        "AI is pointing up."),
    AI_RIGHT('»',     "AI is pointing right."),
    AI_DOWN('¿',      "AI is pointing down."),
    AI_LEFT('«',      "AI is pointing left."),

    AI_PRIZE('◘',     "AI can also be a prize, then it is highlighted " +
                           "by this sprite every few ticks."),

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

    private static List<Element> result = null;

    public static Collection<Element> icebergs() {
        if (result == null) {
            String prefix = StringUtils.substringBefore(ICEBERG_HUGE.name(), "_");
            result = Arrays.stream(values())
                    .filter(element -> element.name().startsWith(prefix))
                    .collect(toList());
        }
        return result;
    }

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

    public static Element valueOf(char ch) {
        for (Element el : Element.values()) {
            if (el.ch == ch) {
                return el;
            }
        }
        throw new IllegalArgumentException("No such element for " + ch);
    }
}

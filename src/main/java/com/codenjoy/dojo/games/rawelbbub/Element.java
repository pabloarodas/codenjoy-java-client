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


import com.codenjoy.dojo.services.Direction;
import com.codenjoy.dojo.services.printer.CharElement;
import org.apache.commons.lang3.StringUtils;

import java.util.*;

import static com.codenjoy.dojo.services.Direction.*;

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

    OTHER_HERO_LEFT('˂',   "Enemy hero is pointing left."),
    OTHER_HERO_RIGHT('˃',  "Enemy hero is pointing right."),
    OTHER_HERO_UP('˄',     "Enemy hero is pointing up."),
    OTHER_HERO_DOWN('˅',   "Enemy hero is pointing down."),

    AI_LEFT('«',           "AI is pointing left."),
    AI_RIGHT('»',          "AI is pointing right."),
    AI_UP('?',             "AI is pointing up."),
    AI_DOWN('¿',           "AI is pointing down."),

    AI_PRIZE('◘',          "AI can also be a prize, then it is highlighted " +
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

    public final static Map<Character, Element> elements = new LinkedHashMap<>();
    public final static List<Element> icebergs = new LinkedList<>();
    public final static Map<Element, Map<Direction, Element>> icebergsMap = new LinkedHashMap<>();

    static {
        String prefix = StringUtils.substringBefore(ICEBERG_HUGE.name(), "_");
        Arrays.stream(values())
                .peek(element -> elements.put(element.ch, element))
                .filter(element -> element.name().startsWith(prefix))
                .forEach(icebergs::add);

        transform(ICEBERG_HUGE,         LEFT,  ICEBERG_MEDIUM_LEFT);
        transform(ICEBERG_HUGE,         RIGHT, ICEBERG_MEDIUM_RIGHT);
        transform(ICEBERG_HUGE,         UP,    ICEBERG_MEDIUM_UP);
        transform(ICEBERG_HUGE,         DOWN,  ICEBERG_MEDIUM_DOWN);

        transform(ICEBERG_MEDIUM_LEFT,  LEFT,  ICEBERG_SMALL_LEFT_LEFT);
        transform(ICEBERG_MEDIUM_LEFT,  RIGHT, ICEBERG_SMALL_LEFT_RIGHT);
        transform(ICEBERG_MEDIUM_LEFT,  UP,    ICEBERG_SMALL_UP_LEFT);
        transform(ICEBERG_MEDIUM_LEFT,  DOWN,  ICEBERG_SMALL_DOWN_LEFT);

        transform(ICEBERG_MEDIUM_RIGHT, LEFT,  ICEBERG_SMALL_LEFT_RIGHT);
        transform(ICEBERG_MEDIUM_RIGHT, RIGHT, ICEBERG_SMALL_RIGHT_RIGHT);
        transform(ICEBERG_MEDIUM_RIGHT, UP,    ICEBERG_SMALL_UP_RIGHT);
        transform(ICEBERG_MEDIUM_RIGHT, DOWN,  ICEBERG_SMALL_DOWN_RIGHT);

        transform(ICEBERG_MEDIUM_UP,    LEFT,  ICEBERG_SMALL_UP_LEFT);
        transform(ICEBERG_MEDIUM_UP,    RIGHT, ICEBERG_SMALL_UP_RIGHT);
        transform(ICEBERG_MEDIUM_UP,    UP,    ICEBERG_SMALL_UP_UP);
        transform(ICEBERG_MEDIUM_UP,    DOWN,  ICEBERG_SMALL_UP_DOWN);

        transform(ICEBERG_MEDIUM_DOWN, LEFT,  ICEBERG_SMALL_DOWN_LEFT);
        transform(ICEBERG_MEDIUM_DOWN, RIGHT, ICEBERG_SMALL_DOWN_RIGHT);
        transform(ICEBERG_MEDIUM_DOWN, UP,    ICEBERG_SMALL_UP_DOWN);
        transform(ICEBERG_MEDIUM_DOWN, DOWN,  ICEBERG_SMALL_DOWN_DOWN);
    }

    private static void transform(Element from, Direction direction, Element to) {
        if (!icebergsMap.containsKey(from)) {
            icebergsMap.put(from, new LinkedHashMap<>());
        }
        icebergsMap.get(from).put(direction, to);
    }

    public Element destroyFrom(Direction direction) {
        if (power() == 1) {
            return Element.WATER;
        }
        return Element.icebergsMap.get(this).get(direction);
    }

    public static Element[] heroes() {
        return new Element[]{
                HERO_LEFT,
                HERO_RIGHT,
                HERO_UP,
                HERO_DOWN,
        };
    }

    public static Element[] barriers() {
        return new Element[] {
                REEFS,
                FISHNET,

                ICEBERG_HUGE,

                ICEBERG_MEDIUM_LEFT,
                ICEBERG_MEDIUM_RIGHT,
                ICEBERG_MEDIUM_UP,
                ICEBERG_MEDIUM_DOWN,

                ICEBERG_SMALL_LEFT_LEFT,
                ICEBERG_SMALL_RIGHT_RIGHT,
                ICEBERG_SMALL_UP_UP,
                ICEBERG_SMALL_DOWN_DOWN,

                ICEBERG_SMALL_LEFT_RIGHT,
                ICEBERG_SMALL_UP_DOWN,

                ICEBERG_SMALL_UP_LEFT,
                ICEBERG_SMALL_UP_RIGHT,
                ICEBERG_SMALL_DOWN_LEFT,
                ICEBERG_SMALL_DOWN_RIGHT,
        };
    }

    public static Element[] otherHeroes() {
        return new Element[] {
                OTHER_HERO_LEFT,
                OTHER_HERO_RIGHT,
                OTHER_HERO_UP,
                OTHER_HERO_DOWN,
        };
    }

    public static Element[] ais() {
        return new Element[] {
                AI_PRIZE,

                AI_LEFT,
                AI_RIGHT,
                AI_UP,
                AI_DOWN,
        };
    }

    public static Element[] enemies() {
        return new Element[] {
                AI_PRIZE,

                AI_LEFT,
                AI_RIGHT,
                AI_UP,
                AI_DOWN,

                OTHER_HERO_LEFT,
                OTHER_HERO_RIGHT,
                OTHER_HERO_UP,
                OTHER_HERO_DOWN,
        };
    }

    public static Element[] torpedoes() {
        return new Element[] {
                TORPEDO_LEFT,
                TORPEDO_RIGHT,
                TORPEDO_UP,
                TORPEDO_DOWN,
        };
    }

    public static Element[] fishnet() {
        return new Element[] {
                FISHNET,
        };
    }

    public static Element[] oil() {
        return new Element[] {
                OIL,
        };
    }

    public static Element[] icebergs() {
        return new Element[] {
                ICEBERG_HUGE,

                ICEBERG_MEDIUM_LEFT,
                ICEBERG_MEDIUM_RIGHT,
                ICEBERG_MEDIUM_UP,
                ICEBERG_MEDIUM_DOWN,

                ICEBERG_SMALL_LEFT_LEFT,
                ICEBERG_SMALL_RIGHT_RIGHT,
                ICEBERG_SMALL_UP_UP,
                ICEBERG_SMALL_DOWN_DOWN,

                ICEBERG_SMALL_LEFT_RIGHT,
                ICEBERG_SMALL_UP_DOWN,

                ICEBERG_SMALL_UP_LEFT,
                ICEBERG_SMALL_UP_RIGHT,
                ICEBERG_SMALL_DOWN_LEFT,
                ICEBERG_SMALL_DOWN_RIGHT,
        };
    }

    public static Element[] seaweed() {
        return new Element[] {
                SEAWEED,
        };
    }

    public static Element[] prizes() {
        return new Element[] {
                PRIZE,

                PRIZE_IMMORTALITY,
                PRIZE_BREAKING_BAD,
                PRIZE_WALKING_ON_FISHNET,
                PRIZE_VISIBILITY,
                PRIZE_NO_SLIDING,
        };
    }

    public static Element otherHero(Direction direction) {
        switch (direction) {
            case LEFT:  return Element.OTHER_HERO_LEFT;
            case RIGHT: return Element.OTHER_HERO_RIGHT;
            case UP:    return Element.OTHER_HERO_UP;
            case DOWN:  return Element.OTHER_HERO_DOWN;
            default:    throw new RuntimeException("Wrong hero direction!");
        }
    }

    public static Element hero(Direction direction) {
        switch (direction) {
            case LEFT:  return Element.HERO_LEFT;
            case RIGHT: return Element.HERO_RIGHT;
            case UP:    return Element.HERO_UP;
            case DOWN:  return Element.HERO_DOWN;
            default:    throw new RuntimeException("Wrong hero direction!");
        }
    }

    public static Element torpedo(Direction direction) {
        switch (direction) {
            case LEFT:  return Element.TORPEDO_LEFT;
            case RIGHT: return Element.TORPEDO_RIGHT;
            case UP:    return Element.TORPEDO_UP;
            case DOWN:  return Element.TORPEDO_DOWN;
            default:    throw new RuntimeException("Wrong torpedo direction!");
        }
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

    // TODO использовать этот подход во всех играх
    public static Element valueOf(char ch) {
        Element result = elements.get(ch);
        if (result == null) {
            throw new IllegalArgumentException("No such element for " + ch);
        }
        return result;
    }
}
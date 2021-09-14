package com.codenjoy.dojo.games.loderunner;

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

    NONE(' '),                    // Пустое место – по которому может двигаться герой

    BRICK('#'),                   // Cтена в которой можно прострелить дырочку слева или справа от героя
                                  // (в зависимости от того, куда он сейчас смотрит)

    PIT_FILL_1('1'),              // Стена со временем зарастает. Когда процес начинается - мы видим таймер
    PIT_FILL_2('2'),
    PIT_FILL_3('3'),
    PIT_FILL_4('4'),

    STONE('☼'),                   // Неразрушаемая стена - в ней ничего прострелить не получится

    CRACK_PIT('*'),               // В момент выстрела мы видим процесс так

    KNIFE_CLUE('$'),              // Улика нож
    GLOVE_CLUE('&'),              // Улика перчатка
    RED_GOLD('@'),                // Золото красного цвета

    // Твой герой в зависимости от того, чем он сейчас занят отображается следующими символами
    HERO_DIE('Ѡ'),                // Герой переживает процесс умирания
    HERO_CRACK_LEFT('Я'),         // Герой простреливает слева от себя
    HERO_CRACK_RIGHT('R'),        // Герой простреливает справа от себя
    HERO_LADDER('Y'),             // Герой находится на лестнице
    HERO_LEFT('◄'),               // Герой бежит влево
    HERO_RIGHT('►'),              // Герой бежит вправо
    HERO_FALL_LEFT(']'),          // Герой падает, смотря влево
    HERO_FALL_RIGHT('['),         // Герой падает, смотря вправо
    HERO_PIPE_LEFT('{'),          // Герой ползёт по трубе влево
    HERO_PIPE_RIGHT('}'),         // Герой ползёт по трубе вправо

    // Тоже твой герой, но под маскировкой:
    HERO_MASK_DIE('x'),         // Герой-маскировка переживает процесс умирания // TODO test me
    HERO_MASK_CRACK_LEFT('⊰'),  // Герой-маскировка простреливает слева от себя
    HERO_MASK_CRACK_RIGHT('⊱'), // Герой-маскировка простреливает справа от себя
    HERO_MASK_LADDER('⍬'),      // Герой-маскировка находится на лестнице
    HERO_MASK_LEFT('⊲'),        // Герой-маскировка бежит влево
    HERO_MASK_RIGHT('⊳'),       // Герой-маскировка бежит вправо
    HERO_MASK_FALL_LEFT('⊅'),   // Герой-маскировка падает, смотря влево
    HERO_MASK_FALL_RIGHT('⊄'),  // Герой-маскировка падает, смотря вправо
    HERO_MASK_PIPE_LEFT('⋜'),   // Герой-маскировка ползёт по трубе влево
    HERO_MASK_PIPE_RIGHT('⋝'),  // Герой-маскировка ползёт по трубе вправо

    // Герои других игроков отображаются так
    OTHER_HERO_DIE('Z'),          // Герой переживает процесс умирания
    OTHER_HERO_CRACK_LEFT('⌋'),   // Герой простреливает слева от себя       // TODO test me
    OTHER_HERO_CRACK_RIGHT('⌊'),  // Герой простреливает справа от себя      // TODO test me
    OTHER_HERO_LADDER('U'),       // Герой находится на лестнице
    OTHER_HERO_LEFT(')'),         // Герой бежит влево
    OTHER_HERO_RIGHT('('),        // Герой бежит вправо
    OTHER_HERO_FALL_LEFT('⊐'),    // Герой падает, смотря влево        // TODO test me
    OTHER_HERO_FALL_RIGHT('⊏'),   // Герой падает, смотря вправо       // TODO test me
    OTHER_HERO_PIPE_LEFT('Э'),    // Герой ползёт по трубе влево
    OTHER_HERO_PIPE_RIGHT('Є'),   // Герой ползёт по трубе вправо

    // А если герои других игроков под маскировкой, то так
    OTHER_HERO_MASK_DIE('⋈'),         // Другой герой-маскировка переживает процесс умирания
    OTHER_HERO_MASK_CRACK_LEFT('⋰'),  // Другой герой-маскировка простреливает слева от себя       // TODO test me
    OTHER_HERO_MASK_CRACK_RIGHT('⋱'), // Другой герой-маскировка простреливает справа от себя      // TODO test me
    OTHER_HERO_MASK_LEFT('⋊'),        // Другой герой-маскировка находится на лестнице
    OTHER_HERO_MASK_RIGHT('⋉'),       // Другой герой-маскировка бежит влево
    OTHER_HERO_MASK_LADDER('⋕'),      // Другой герой-маскировка бежит вправо
    OTHER_HERO_MASK_FALL_LEFT('⋣'),   // Другой герой-маскировка падает, смотря влево        // TODO test me
    OTHER_HERO_MASK_FALL_RIGHT('⋢'),  // Другой герой-маскировка падает, смотря вправо       // TODO test me
    OTHER_HERO_MASK_PIPE_LEFT('⊣'),   // Другой герой-маскировка ползёт по трубе влево
    OTHER_HERO_MASK_PIPE_RIGHT('⊢'),  // Другой герой-маскировка ползёт по трубе вправо

    // Боты-воры
    ROBBER_LADDER('Q'),
    ROBBER_LEFT('«'),
    ROBBER_RIGHT('»'),
    ROBBER_PIPE_LEFT('<'),
    ROBBER_PIPE_RIGHT('>'),
    ROBBER_MASK('X'),

    LADDER('H'),              // Лестница - по ней можно перемещаться по уровню
    PIPE('~'),                // Труба - по ней так же можно перемещаться по уровню, но только горизонтально

    BACKWAY('⊛'),              // Черный ход - позволяет скрыто перемещаться в иное место на карте

    MASK_POTION('S');         // Маскировочное зелье - наделяют детектива дополнительными способностями

    final char ch;

    public static List<Element> gold() {
        return Arrays.asList(
                KNIFE_CLUE,
                GLOVE_CLUE,
                RED_GOLD);
    }

    public static List<Element> ladders() {
        return Arrays.asList(LADDER,
                HERO_LADDER,
                HERO_MASK_LADDER,
                OTHER_HERO_LADDER,
                OTHER_HERO_MASK_LADDER,
                ROBBER_LADDER);
    }

    public static List<Element> walls() {
        return Arrays.asList(BRICK,
                STONE);
    }

    public static List<Element> heroes() {
        return Arrays.asList(HERO_DIE,
                HERO_CRACK_LEFT,
                HERO_CRACK_RIGHT,
                HERO_LADDER,
                HERO_LEFT,
                HERO_RIGHT,
                HERO_FALL_LEFT,
                HERO_FALL_RIGHT,
                HERO_PIPE_LEFT,
                HERO_PIPE_RIGHT,

                HERO_MASK_DIE,
                HERO_MASK_CRACK_LEFT,
                HERO_MASK_CRACK_RIGHT,
                HERO_MASK_LADDER,
                HERO_MASK_LEFT,
                HERO_MASK_RIGHT,
                HERO_MASK_FALL_LEFT,
                HERO_MASK_FALL_RIGHT,
                HERO_MASK_PIPE_LEFT,
                HERO_MASK_PIPE_RIGHT);
    }

    public static List<Element> robbers() {
        return Arrays.asList(ROBBER_LADDER,
                ROBBER_LEFT,
                ROBBER_PIPE_LEFT,
                ROBBER_PIPE_RIGHT,
                ROBBER_RIGHT,
                ROBBER_MASK);
    }

    public static List<Element> otherHeroes() {
        return Arrays.asList(OTHER_HERO_DIE,
                OTHER_HERO_CRACK_LEFT,
                OTHER_HERO_CRACK_RIGHT,
                OTHER_HERO_LADDER,
                OTHER_HERO_LEFT,
                OTHER_HERO_RIGHT,
                OTHER_HERO_FALL_LEFT,
                OTHER_HERO_FALL_RIGHT,
                OTHER_HERO_PIPE_LEFT,
                OTHER_HERO_PIPE_RIGHT,

                OTHER_HERO_MASK_DIE,
                OTHER_HERO_MASK_CRACK_LEFT,
                OTHER_HERO_MASK_CRACK_RIGHT,
                OTHER_HERO_MASK_LEFT,
                OTHER_HERO_MASK_RIGHT,
                OTHER_HERO_MASK_LADDER,
                OTHER_HERO_MASK_FALL_LEFT,
                OTHER_HERO_MASK_FALL_RIGHT,
                OTHER_HERO_MASK_PIPE_LEFT,
                OTHER_HERO_MASK_PIPE_RIGHT);
    }

    public static List<Element> pipes() {
        return Arrays.asList(PIPE,
                HERO_PIPE_LEFT,
                HERO_PIPE_RIGHT,
                HERO_MASK_PIPE_LEFT,
                HERO_MASK_PIPE_RIGHT,
                OTHER_HERO_PIPE_LEFT,
                OTHER_HERO_PIPE_RIGHT,
                OTHER_HERO_MASK_PIPE_LEFT,
                OTHER_HERO_MASK_PIPE_RIGHT);
    }

    @Override
    public char ch() {
        return ch;
    }

    public char getChar() {
        return ch;
    }

    Element(char ch) {
        this.ch = ch;
    }

    @Override
    public String toString() {
        return String.valueOf(ch);
    }

    public Element mask() {
        switch (this) {
            case HERO_DIE: return HERO_MASK_DIE;
            case HERO_CRACK_LEFT: return HERO_MASK_CRACK_LEFT;
            case HERO_CRACK_RIGHT: return HERO_MASK_CRACK_RIGHT;
            case HERO_LADDER: return HERO_MASK_LADDER;
            case HERO_LEFT: return HERO_MASK_LEFT;
            case HERO_RIGHT: return HERO_MASK_RIGHT;
            case HERO_FALL_LEFT: return HERO_MASK_FALL_LEFT;
            case HERO_FALL_RIGHT: return HERO_MASK_FALL_RIGHT;
            case HERO_PIPE_LEFT: return HERO_MASK_PIPE_LEFT;
            case HERO_PIPE_RIGHT: return HERO_MASK_PIPE_RIGHT;

            case OTHER_HERO_DIE: return OTHER_HERO_MASK_DIE;
            case OTHER_HERO_CRACK_LEFT: return OTHER_HERO_MASK_CRACK_LEFT;
            case OTHER_HERO_CRACK_RIGHT: return OTHER_HERO_MASK_CRACK_RIGHT;
            case OTHER_HERO_LADDER: return OTHER_HERO_MASK_LADDER;
            case OTHER_HERO_LEFT: return OTHER_HERO_MASK_LEFT;
            case OTHER_HERO_RIGHT: return OTHER_HERO_MASK_RIGHT;
            case OTHER_HERO_FALL_LEFT: return OTHER_HERO_MASK_FALL_LEFT;
            case OTHER_HERO_FALL_RIGHT: return OTHER_HERO_MASK_FALL_RIGHT;
            case OTHER_HERO_PIPE_LEFT: return OTHER_HERO_MASK_PIPE_LEFT;
            case OTHER_HERO_PIPE_RIGHT: return OTHER_HERO_MASK_PIPE_RIGHT;
        }
        throw new IllegalArgumentException("Bad hero state: " + this);
    }

    public Element otherHero() {
        switch (this) {
            case HERO_DIE: return OTHER_HERO_DIE;
            case HERO_CRACK_LEFT: return OTHER_HERO_CRACK_LEFT;
            case HERO_CRACK_RIGHT: return OTHER_HERO_CRACK_RIGHT;
            case HERO_LADDER: return OTHER_HERO_LADDER;
            case HERO_LEFT: return OTHER_HERO_LEFT;
            case HERO_RIGHT: return OTHER_HERO_RIGHT;
            case HERO_FALL_LEFT: return OTHER_HERO_FALL_LEFT;
            case HERO_FALL_RIGHT: return OTHER_HERO_FALL_RIGHT;
            case HERO_PIPE_LEFT: return OTHER_HERO_PIPE_LEFT;
            case HERO_PIPE_RIGHT: return OTHER_HERO_PIPE_RIGHT;

            case HERO_MASK_DIE: return OTHER_HERO_MASK_DIE;
            case HERO_MASK_CRACK_LEFT: return OTHER_HERO_MASK_CRACK_LEFT;
            case HERO_MASK_CRACK_RIGHT: return OTHER_HERO_MASK_CRACK_RIGHT;
            case HERO_MASK_LADDER: return OTHER_HERO_MASK_LADDER;
            case HERO_MASK_LEFT: return OTHER_HERO_MASK_LEFT;
            case HERO_MASK_RIGHT: return OTHER_HERO_MASK_RIGHT;
            case HERO_MASK_FALL_LEFT: return OTHER_HERO_MASK_FALL_LEFT;
            case HERO_MASK_FALL_RIGHT: return OTHER_HERO_MASK_FALL_RIGHT;
            case HERO_MASK_PIPE_LEFT: return OTHER_HERO_MASK_PIPE_LEFT;
            case HERO_MASK_PIPE_RIGHT: return OTHER_HERO_MASK_PIPE_RIGHT;
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

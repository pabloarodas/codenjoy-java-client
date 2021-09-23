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
import java.util.List;

public enum Element implements CharElement {

    NONE(' '),                    // Пустое место – по которому может двигаться детектив

    BRICK('#'),                   // Cтена в которой можно прострелить дырочку слева или справа от детектива
                                  // (в зависимости от того, куда он сейчас смотрит)

    PIT_FILL_1('1'),              // Стена со временем зарастает. Когда процес начинается - мы видим таймер
    PIT_FILL_2('2'),
    PIT_FILL_3('3'),
    PIT_FILL_4('4'),

    STONE('☼'),                   // Неразрушаемая стена - в ней ничего прострелить не получится

    CRACK_PIT('*'),               // В момент выстрела мы видим процесс так

    CLUE_KNIFE('$'),              // Улика нож
    CLUE_GLOVE('&'),              // Улика перчатка
    CLUE_RING('@'),               // Улика кольцо

    // Твой детектив в зависимости от того, чем он сейчас занят отображается следующими символами
    HERO_DIE('Ѡ'),                // Детектив переживает процесс умирания
    HERO_CRACK_LEFT('Я'),         // Детектив простреливает слева от себя
    HERO_CRACK_RIGHT('R'),        // Детектив простреливает справа от себя
    HERO_LADDER('Y'),             // Детектив находится на лестнице
    HERO_LEFT('◄'),               // Детектив бежит влево
    HERO_RIGHT('►'),              // Детектив бежит вправо
    HERO_FALL_LEFT(']'),          // Детектив падает, смотря влево
    HERO_FALL_RIGHT('['),         // Детектив падает, смотря вправо
    HERO_PIPE_LEFT('{'),          // Детектив ползёт по трубе влево
    HERO_PIPE_RIGHT('}'),         // Детектив ползёт по трубе вправо
    HERO_PIT_LEFT('⍃'),           // Детектив в яме смотрит влево
    HERO_PIT_RIGHT('⍄'),          // Детектив в яме смотрит вправо

    // Тоже твой детектив, но под маскировкой:
    HERO_MASK_DIE('x'),         // Детектив-маскировка переживает процесс умирания // TODO test me
    HERO_MASK_CRACK_LEFT('⊰'),  // Детектив-маскировка простреливает слева от себя
    HERO_MASK_CRACK_RIGHT('⊱'), // Детектив-маскировка простреливает справа от себя
    HERO_MASK_LADDER('⍬'),      // Детектив-маскировка находится на лестнице
    HERO_MASK_LEFT('⊲'),        // Детектив-маскировка бежит влево
    HERO_MASK_RIGHT('⊳'),       // Детектив-маскировка бежит вправо
    HERO_MASK_FALL_LEFT('⊅'),   // Детектив-маскировка падает, смотря влево
    HERO_MASK_FALL_RIGHT('⊄'),  // Детектив-маскировка падает, смотря вправо
    HERO_MASK_PIPE_LEFT('⋜'),   // Детектив-маскировка ползёт по трубе влево
    HERO_MASK_PIPE_RIGHT('⋝'),  // Детектив-маскировка ползёт по трубе вправо
    HERO_MASK_PIT_LEFT('ᐊ'),    // Детектив-маскировка в яме смотрит влево
    HERO_MASK_PIT_RIGHT('ᐅ'),   // Детектив-маскировка в яме смотрит вправо

    // Детективы других игроков отображаются так
    OTHER_HERO_DIE('Z'),          // Другой детектив переживает процесс умирания
    OTHER_HERO_CRACK_LEFT('⌋'),   // Другой детектив простреливает слева от себя       // TODO test me
    OTHER_HERO_CRACK_RIGHT('⌊'),  // Другой детектив простреливает справа от себя      // TODO test me
    OTHER_HERO_LADDER('U'),       // Другой детектив находится на лестнице
    OTHER_HERO_LEFT(')'),         // Другой детектив бежит влево
    OTHER_HERO_RIGHT('('),        // Другой детектив бежит вправо
    OTHER_HERO_FALL_LEFT('⊐'),    // Другой детектив падает, смотря влево        // TODO test me
    OTHER_HERO_FALL_RIGHT('⊏'),   // Другой детектив падает, смотря вправо       // TODO test me
    OTHER_HERO_PIPE_LEFT('Э'),    // Другой детектив ползёт по трубе влево
    OTHER_HERO_PIPE_RIGHT('Є'),   // Другой детектив ползёт по трубе вправо
    OTHER_HERO_PIT_LEFT('ᗉ'),     // Другой детектив в яме смотрит влево
    OTHER_HERO_PIT_RIGHT('ᗆ'),    // Другой детектив в яме смотрит вправо

    // А если детективы других игроков под маскировкой, то так
    OTHER_HERO_MASK_DIE('⋈'),         // Другой детектив-маскировка переживает процесс умирания
    OTHER_HERO_MASK_CRACK_LEFT('⋰'),  // Другой детектив-маскировка простреливает слева от себя       // TODO test me
    OTHER_HERO_MASK_CRACK_RIGHT('⋱'), // Другой детектив-маскировка простреливает справа от себя      // TODO test me
    OTHER_HERO_MASK_LEFT('⋊'),        // Другой детектив-маскировка находится на лестнице
    OTHER_HERO_MASK_RIGHT('⋉'),       // Другой детектив-маскировка бежит влево
    OTHER_HERO_MASK_LADDER('⋕'),      // Другой детектив-маскировка бежит вправо
    OTHER_HERO_MASK_FALL_LEFT('⋣'),   // Другой детектив-маскировка падает, смотря влево        // TODO test me
    OTHER_HERO_MASK_FALL_RIGHT('⋢'),  // Другой детектив-маскировка падает, смотря вправо       // TODO test me
    OTHER_HERO_MASK_PIPE_LEFT('⊣'),   // Другой детектив-маскировка ползёт по трубе влево
    OTHER_HERO_MASK_PIPE_RIGHT('⊢'),  // Другой детектив-маскировка ползёт по трубе вправо
    OTHER_HERO_MASK_PIT_LEFT('ᗏ'),     // Другой детектив-маскировка в яме смотрит влево
    OTHER_HERO_MASK_PIT_RIGHT('ᗌ'),    // Другой детектив-маскировка в яме смотрит вправо

    // Вражеские детективы других игроков отображаются так
    ENEMY_HERO_DIE('Ž'),          // Вражеский детектив переживает процесс умирания       // TODO test me
    ENEMY_HERO_CRACK_LEFT('⟧'),   // Вражеский детектив простреливает слева от себя       // TODO test me
    ENEMY_HERO_CRACK_RIGHT('⟦'),  // Вражеский детектив простреливает справа от себя      // TODO test me
    ENEMY_HERO_LADDER('Ǔ'),       // Вражеский детектив находится на лестнице       // TODO test me
    ENEMY_HERO_LEFT('❫'),         // Вражеский детектив бежит влево       // TODO test me
    ENEMY_HERO_RIGHT('❪'),        // Вражеский детектив бежит вправо       // TODO test me
    ENEMY_HERO_FALL_LEFT('⋥'),    // Вражеский детектив падает, смотря влево        // TODO test me
    ENEMY_HERO_FALL_RIGHT('⋤'),   // Вражеский детектив падает, смотря вправо       // TODO test me
    ENEMY_HERO_PIPE_LEFT('Ǯ'),    // Вражеский детектив ползёт по трубе влево       // TODO test me
    ENEMY_HERO_PIPE_RIGHT('Ě'),   // Вражеский детектив ползёт по трубе вправо       // TODO test me
    ENEMY_HERO_PIT_LEFT('⇇'),     // Вражеский детектив в яме смотрит влево
    ENEMY_HERO_PIT_RIGHT('⇉'),    // Вражеский детектив в яме смотрит вправо

    // А если вражеские детективы других игроков под маскировкой, то так
    ENEMY_HERO_MASK_DIE('⧓'),         // Вражеский детектив-маскировка переживает процесс умирания       // TODO test me
    ENEMY_HERO_MASK_CRACK_LEFT('⇢'),  // Вражеский детектив-маскировка простреливает слева от себя       // TODO test me
    ENEMY_HERO_MASK_CRACK_RIGHT('⇠'), // Вражеский детектив-маскировка простреливает справа от себя      // TODO test me
    ENEMY_HERO_MASK_LEFT('⧒'),        // Вражеский детектив-маскировка находится на лестнице       // TODO test me
    ENEMY_HERO_MASK_RIGHT('⧑'),       // Вражеский детектив-маскировка бежит влево       // TODO test me
    ENEMY_HERO_MASK_LADDER('≠'),      // Вражеский детектив-маскировка бежит вправо       // TODO test me
    ENEMY_HERO_MASK_FALL_LEFT('⌫'),   // Вражеский детектив-маскировка падает, смотря влево        // TODO test me
    ENEMY_HERO_MASK_FALL_RIGHT('⌦'),  // Вражеский детектив-маскировка падает, смотря вправо       // TODO test me
    ENEMY_HERO_MASK_PIPE_LEFT('❵'),   // Вражеский детектив-маскировка ползёт по трубе влево       // TODO test me
    ENEMY_HERO_MASK_PIPE_RIGHT('❴'),  // Вражеский детектив-маскировка ползёт по трубе вправо       // TODO test me
    ENEMY_HERO_MASK_PIT_LEFT('⬱'),    // Вражеский детектив-маскировка в яме смотрит влево
    ENEMY_HERO_MASK_PIT_RIGHT('⇶'),   // Вражеский детектив-маскировка в яме смотрит вправо

    // Боты-воры
    ROBBER_LADDER('Q'),
    ROBBER_LEFT('«'),
    ROBBER_RIGHT('»'),
    ROBBER_FALL_LEFT('‹'),
    ROBBER_FALL_RIGHT('›'),
    ROBBER_PIPE_LEFT('<'),
    ROBBER_PIPE_RIGHT('>'),
    ROBBER_PIT_LEFT('⍇'),
    ROBBER_PIT_RIGHT('⍈'),

    // Ворота
    OPENED_DOOR_GOLD('⍙'),
    OPENED_DOOR_SILVER('⍚'),
    OPENED_DOOR_BRONZE('⍜'),

    CLOSED_DOOR_GOLD('⍍'),
    CLOSED_DOOR_SILVER('⌺'),
    CLOSED_DOOR_BRONZE('⌼'),

    KEY_GOLD('✦'),
    KEY_SILVER('✼'),
    KEY_BRONZE('⍟'),

    LADDER('H'),              // Лестница - по ней можно перемещаться по уровню
    PIPE('~'),                // Труба - по ней так же можно перемещаться по уровню, но только горизонтально

    BACKWAY('⊛'),              // Черный ход - позволяет скрыто перемещаться в иное место на карте

    MASK_POTION('S');         // Маскировочное зелье - наделяют детектива дополнительными способностями

    final char ch;

    public static List<Element> clues() {
        return Arrays.asList(
                CLUE_KNIFE,
                CLUE_GLOVE,
                CLUE_RING);
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
                HERO_PIT_LEFT,
                HERO_PIT_RIGHT,

                HERO_MASK_DIE,
                HERO_MASK_CRACK_LEFT,
                HERO_MASK_CRACK_RIGHT,
                HERO_MASK_LADDER,
                HERO_MASK_LEFT,
                HERO_MASK_RIGHT,
                HERO_MASK_FALL_LEFT,
                HERO_MASK_FALL_RIGHT,
                HERO_MASK_PIPE_LEFT,
                HERO_MASK_PIPE_RIGHT,
                HERO_MASK_PIT_LEFT,
                HERO_MASK_PIT_RIGHT);
    }

    public static List<Element> robbers() {
        return Arrays.asList(ROBBER_LADDER,
                ROBBER_LEFT,
                ROBBER_RIGHT,
                ROBBER_FALL_LEFT,
                ROBBER_FALL_RIGHT,
                ROBBER_PIPE_LEFT,
                ROBBER_PIPE_RIGHT,
                ROBBER_PIT_LEFT,
                ROBBER_PIT_RIGHT);
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
                OTHER_HERO_PIT_LEFT,
                OTHER_HERO_PIT_RIGHT,

                OTHER_HERO_MASK_DIE,
                OTHER_HERO_MASK_CRACK_LEFT,
                OTHER_HERO_MASK_CRACK_RIGHT,
                OTHER_HERO_MASK_LEFT,
                OTHER_HERO_MASK_RIGHT,
                OTHER_HERO_MASK_LADDER,
                OTHER_HERO_MASK_FALL_LEFT,
                OTHER_HERO_MASK_FALL_RIGHT,
                OTHER_HERO_MASK_PIPE_LEFT,
                OTHER_HERO_MASK_PIPE_RIGHT,
                OTHER_HERO_MASK_PIT_LEFT,
                OTHER_HERO_MASK_PIT_RIGHT);
    }

    public static List<Element> enemyHeroes() {
        return Arrays.asList(ENEMY_HERO_DIE,
                ENEMY_HERO_CRACK_LEFT,
                ENEMY_HERO_CRACK_RIGHT,
                ENEMY_HERO_LADDER,
                ENEMY_HERO_LEFT,
                ENEMY_HERO_RIGHT,
                ENEMY_HERO_FALL_LEFT,
                ENEMY_HERO_FALL_RIGHT,
                ENEMY_HERO_PIPE_LEFT,
                ENEMY_HERO_PIPE_RIGHT,
                ENEMY_HERO_PIT_LEFT,
                ENEMY_HERO_PIT_RIGHT,

                ENEMY_HERO_MASK_DIE,
                ENEMY_HERO_MASK_CRACK_LEFT,
                ENEMY_HERO_MASK_CRACK_RIGHT,
                ENEMY_HERO_MASK_LEFT,
                ENEMY_HERO_MASK_RIGHT,
                ENEMY_HERO_MASK_LADDER,
                ENEMY_HERO_MASK_FALL_LEFT,
                ENEMY_HERO_MASK_FALL_RIGHT,
                ENEMY_HERO_MASK_PIPE_LEFT,
                ENEMY_HERO_MASK_PIPE_RIGHT,
                ENEMY_HERO_MASK_PIT_LEFT,
                ENEMY_HERO_MASK_PIT_RIGHT);
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

    public static List<Element> openDoors() {
        return Arrays.asList(
                OPENED_DOOR_GOLD,
                OPENED_DOOR_SILVER,
                OPENED_DOOR_BRONZE);
    }

    public static List<Element> closedDoors() {
        return Arrays.asList(
                CLOSED_DOOR_GOLD,
                CLOSED_DOOR_SILVER,
                CLOSED_DOOR_BRONZE);
    }

    public static List<Element> keys() {
        return Arrays.asList(
                KEY_GOLD,
                KEY_SILVER,
                KEY_BRONZE);
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
            case HERO_PIT_LEFT: return HERO_MASK_PIT_LEFT;
            case HERO_PIT_RIGHT: return HERO_MASK_PIT_RIGHT;

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
            case OTHER_HERO_PIT_LEFT: return OTHER_HERO_MASK_PIT_LEFT;
            case OTHER_HERO_PIT_RIGHT: return OTHER_HERO_MASK_PIT_RIGHT;

            case ENEMY_HERO_DIE: return ENEMY_HERO_MASK_DIE;
            case ENEMY_HERO_CRACK_LEFT: return ENEMY_HERO_MASK_CRACK_LEFT;
            case ENEMY_HERO_CRACK_RIGHT: return ENEMY_HERO_MASK_CRACK_RIGHT;
            case ENEMY_HERO_LADDER: return ENEMY_HERO_MASK_LADDER;
            case ENEMY_HERO_LEFT: return ENEMY_HERO_MASK_LEFT;
            case ENEMY_HERO_RIGHT: return ENEMY_HERO_MASK_RIGHT;
            case ENEMY_HERO_FALL_LEFT: return ENEMY_HERO_MASK_FALL_LEFT;
            case ENEMY_HERO_FALL_RIGHT: return ENEMY_HERO_MASK_FALL_RIGHT;
            case ENEMY_HERO_PIPE_LEFT: return ENEMY_HERO_MASK_PIPE_LEFT;
            case ENEMY_HERO_PIPE_RIGHT: return ENEMY_HERO_MASK_PIPE_RIGHT;
            case ENEMY_HERO_PIT_LEFT: return ENEMY_HERO_MASK_PIT_LEFT;
            case ENEMY_HERO_PIT_RIGHT: return ENEMY_HERO_MASK_PIT_RIGHT;
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
            case HERO_PIT_LEFT: return OTHER_HERO_PIT_LEFT;
            case HERO_PIT_RIGHT: return OTHER_HERO_PIT_RIGHT;

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
            case HERO_MASK_PIT_LEFT: return OTHER_HERO_MASK_PIT_LEFT;
            case HERO_MASK_PIT_RIGHT: return OTHER_HERO_MASK_PIT_RIGHT;
        }
        throw new IllegalArgumentException("Bad hero state: " + this);
    }

    public Element enemyHero() {
        switch (this) {
            case HERO_DIE: return ENEMY_HERO_DIE;
            case HERO_CRACK_LEFT: return ENEMY_HERO_CRACK_LEFT;
            case HERO_CRACK_RIGHT: return ENEMY_HERO_CRACK_RIGHT;
            case HERO_LADDER: return ENEMY_HERO_LADDER;
            case HERO_LEFT: return ENEMY_HERO_LEFT;
            case HERO_RIGHT: return ENEMY_HERO_RIGHT;
            case HERO_FALL_LEFT: return ENEMY_HERO_FALL_LEFT;
            case HERO_FALL_RIGHT: return ENEMY_HERO_FALL_RIGHT;
            case HERO_PIPE_LEFT: return ENEMY_HERO_PIPE_LEFT;
            case HERO_PIPE_RIGHT: return ENEMY_HERO_PIPE_RIGHT;
            case HERO_PIT_LEFT: return ENEMY_HERO_PIT_LEFT;
            case HERO_PIT_RIGHT: return ENEMY_HERO_PIT_RIGHT;

            case HERO_MASK_DIE: return ENEMY_HERO_MASK_DIE;
            case HERO_MASK_CRACK_LEFT: return ENEMY_HERO_MASK_CRACK_LEFT;
            case HERO_MASK_CRACK_RIGHT: return ENEMY_HERO_MASK_CRACK_RIGHT;
            case HERO_MASK_LADDER: return ENEMY_HERO_MASK_LADDER;
            case HERO_MASK_LEFT: return ENEMY_HERO_MASK_LEFT;
            case HERO_MASK_RIGHT: return ENEMY_HERO_MASK_RIGHT;
            case HERO_MASK_FALL_LEFT: return ENEMY_HERO_MASK_FALL_LEFT;
            case HERO_MASK_FALL_RIGHT: return ENEMY_HERO_MASK_FALL_RIGHT;
            case HERO_MASK_PIPE_LEFT: return ENEMY_HERO_MASK_PIPE_LEFT;
            case HERO_MASK_PIPE_RIGHT: return ENEMY_HERO_MASK_PIPE_RIGHT;
            case HERO_MASK_PIT_LEFT: return ENEMY_HERO_PIT_LEFT;
            case HERO_MASK_PIT_RIGHT: return ENEMY_HERO_PIT_RIGHT;
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

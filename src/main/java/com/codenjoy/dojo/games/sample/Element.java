package com.codenjoy.dojo.games.sample;

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

/**
 * Тут указана легенда всех возможных объектов на поле и их состояний.
 * Важно помнить, что для каждой енумной константы надо создать спрайт в папке \src\main\webapp\resources\sprite.
 */
public enum Element implements CharElement {

    NONE(' ',            "Empty place where the hero can go."),

    WALL('☼',            "Wall you cant walk through."),

    HERO('☺',            "Your hero."),
    HERO_DEAD('X',       "Your hero died. His body will disappear in the next tick."),

    OTHER_HERO('☻',      "Heroes of other players."),
    OTHER_HERO_DEAD('Y', "Another player's hero died."),

    ENEMY_HERO('♥',      "Heroes of other players in other team."),
    ENEMY_HERO_DEAD('Z', "Player's Hero from the other team who died."),

    GOLD('$',            "Gold. It must be picked up."),

    BOMB('x',            "Bomb planted by the hero. You can blow up on it.");

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

}
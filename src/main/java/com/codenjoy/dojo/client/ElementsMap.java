package com.codenjoy.dojo.client;

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

import java.util.LinkedHashMap;
import java.util.Map;

public class ElementsMap<E extends CharElement> {

    private Map<Character, E> characters = new LinkedHashMap<>();
    private Map<String, E> names = new LinkedHashMap<>();

    public ElementsMap(E[] elements) {
        for (E element : elements) {
            characters.put(element.ch(), element);
            names.put(element.name(), element);
        }
    }

    public E get(char ch) {
        E result = characters.get(ch);
        if (result == null) {
            throw new IllegalArgumentException("No such element for char: " + ch);
        }
        return result;
    }

    public E get(String name) {
        E result = names.get(name);
        if (result == null) {
            throw new IllegalArgumentException("No such element for name: " + name);
        }
        return result;
    }

    @Override
    public String toString() {
        return characters.toString() + "\n"
                + names.toString();
    }
}
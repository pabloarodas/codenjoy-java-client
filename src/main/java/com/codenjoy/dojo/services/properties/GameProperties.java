package com.codenjoy.dojo.services.properties;

/*-
 * #%L
 * expansion - it's a dojo-like platform from developers to developers.
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

import java.io.File;
import java.io.FileReader;
import java.util.Properties;

import static org.apache.commons.lang3.StringUtils.capitalize;

public class GameProperties {

    public static final String INFO_PROPERTIES = "/src/main/webapp/resources/${game}/help/info.properties";

    private Properties properties;
    private String canonicalGame;

    public static String get(String base, String game, String name) {
        GameProperties properties = new GameProperties();
        properties.load(base, game);
        return properties.get(name);
    }

    public boolean load(String base, String game) {
        try {
            canonicalGame = game;
            File file = new File(replace(base + INFO_PROPERTIES, canonicalGame));
            properties = new Properties();
            properties.load(new FileReader(file.getAbsolutePath()));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static String getGame(String canonicalGame) {
        return canonicalGame.replaceAll("[-_.]", "");
    }

    public static String replace(String template, String canonicalGame) {
        return template
                .replace("${game}", getGame(canonicalGame))
                .replace("${game-canonical}", canonicalGame)
                .replace("${game-capitalize}", capitalize(canonicalGame));
    }

    public String get(String name) {
        String key = String.format("game.%s.element.%s", canonicalGame, name);
        if (!properties.containsKey(key)) {
            key = key.replace(".element.", ".settings.");
        }
        return properties.getProperty(key);
    }
}

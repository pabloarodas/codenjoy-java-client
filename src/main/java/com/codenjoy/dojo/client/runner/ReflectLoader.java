package com.codenjoy.dojo.client.runner;

/*-
 * #%L
 * Codenjoy - it's a dojo-like platform from developers to developers.
 * %%
 * Copyright (C) 2018 - 2021 Codenjoy
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

import com.codenjoy.dojo.client.ClientBoard;
import com.codenjoy.dojo.client.Solver;
import com.codenjoy.dojo.services.Dice;
import com.codenjoy.dojo.services.RandomDice;
import org.reflections.Reflections;

import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.function.UnaryOperator;

public class ReflectLoader {

    private static final UnaryOperator<String> GAME_PACKAGE_RESOLVER =
            game -> String.format("com.codenjoy.dojo.games.%s", game);

    public static Solver loadJavaSolver(String game) {
        return loadSolver(game, "java");
    }

    public static Solver loadScalaSolver(String game) {
        return loadSolver(game, "scala");
    }

    private static Solver loadSolver(String game, String language) {
        try {
            String gamePackage = GAME_PACKAGE_RESOLVER.apply(game);
            Class<?> solver = new Reflections(gamePackage).getSubTypesOf(Solver.class).stream()
                    .filter(clazz -> clazz.getCanonicalName().contains(game))
                    .filter(clazz -> Objects.nonNull(clazz.getAnnotation(GameSolver.class)))
                    .filter(clazz -> language.equals(clazz.getAnnotation(GameSolver.class).lang()))
                    .findFirst().orElseThrow(NoSuchElementException::new);
            return (Solver) solver.getDeclaredConstructor(Dice.class)
                    .newInstance(new RandomDice());
        } catch (Exception e) {
            String message = "Error loading Solver for: " + game;
            throw new RuntimeException(message, e);
        }
    }

    public static ClientBoard loadJavaBoard(String game) {
        return loadBoard(game, "java");
    }

    public static ClientBoard loadScalaBoard(String game) {
        return loadBoard(game, "scala");
    }

    private static ClientBoard loadBoard(String game, String language) {
        try {
            String gamePackage = GAME_PACKAGE_RESOLVER.apply(game);
            Class<?> board = new Reflections(gamePackage).getSubTypesOf(ClientBoard.class).stream()
                    .filter(clazz -> clazz.getCanonicalName().contains(game))
                    .filter(clazz -> Objects.nonNull(clazz.getAnnotation(GameBoard.class)))
                    .filter(clazz -> language.equals(clazz.getAnnotation(GameBoard.class).lang()))
                    .findFirst().orElseThrow(NoSuchElementException::new);
            return (ClientBoard) board.getDeclaredConstructor()
                    .newInstance();
        } catch (Exception e) {
            String message = "Error loading Board for: " + game;
            throw new RuntimeException(message, e);
        }
    }
}

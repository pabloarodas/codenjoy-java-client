package com.codenjoy.dojo;

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

import com.codenjoy.dojo.client.WebSocketRunner;
import com.codenjoy.dojo.services.RandomDice;

public class Runner {

    // Paste here board page url from browser after registration,
    // or put it as command line parameter.
    public static final String URL =
            "http://codenjoy.com:80/codenjoy-contest/board/player/3edq63tw0bq4w4iem7nb?code=1234567890123456789";

    public static void main(String[] args) {
        WebSocketRunner.runClient(args, URL,

                // This is your solver, please work with it.
                // Do not forget about YourSolverTest for your TDD/unit-testing
                new com.codenjoy.dojo.games.a2048.YourSolver(new RandomDice()),

                // This is board representation.
                new com.codenjoy.dojo.games.a2048.Board()
        );
    }

}

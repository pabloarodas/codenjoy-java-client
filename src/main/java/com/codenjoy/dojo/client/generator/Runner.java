package com.codenjoy.dojo.client.generator;

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

import java.io.File;
import java.util.Arrays;

public class Runner {

    private static String base;
    private static String clients;

    public static void main(String[] args) {
        System.out.println("+-----------------------------+");
        System.out.println("| Starting elements generator |");
        System.out.println("+-----------------------------+");

        if (args != null && args.length == 2) {
            base = args[0];
            clients = args[1];
            System.out.printf("Got 'CLIENTS' from Environment:  '%s'\n", clients);
            System.out.printf("Got 'BASE' from Environment: '%s'\n", base);
        } else {
            base = "";
            clients = "cpp,go,js,php,python";
            System.out.printf("Got 'CLIENTS' from Runner:  '%s'\n", clients);
            System.out.printf("Got 'BASE' from Runner: '%s'\n", base);
        }
        if (!new File(base).isAbsolute()) {
            base = new File(base).getAbsoluteFile().getPath();
            System.out.printf("            (absolute): '%s'\n", base);
        }

        for (String language : Arrays.asList(clients.split(","))) {
            new ElementGenerator("clifford", language).generateToFile(base);
        }
    }
}

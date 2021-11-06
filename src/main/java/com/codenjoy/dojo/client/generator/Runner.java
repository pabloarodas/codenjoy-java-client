package com.codenjoy.dojo.client.generator;

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
            System.out.printf("Got 'BASE' from Environment: '%s'\n", base);
            System.out.printf("Got 'CLIENTS' from Environment:  '%s'\n", clients);
        } else {
            base = "./..";
            clients = "cpp,go,js,php,python";
            System.out.printf("Got 'BASE' from Runner: '%s'\n", base);
            System.out.printf("Got 'CLIENTS' from Runner:  '%s'\n", clients);
        }

        for (String language : Arrays.asList(clients.split(","))) {
            new ElementGenerator("clifford", language).generateToFile();
        }
    }
}

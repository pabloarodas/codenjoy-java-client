package com.codenjoy.dojo.games.mollymage;

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

import com.codenjoy.dojo.JavaRunner;
import com.codenjoy.dojo.client.Solver;
import com.codenjoy.dojo.services.Dice;
import com.codenjoy.dojo.services.Direction;
import com.codenjoy.dojo.services.Point;
import com.codenjoy.dojo.services.PointImpl;
import com.codenjoy.dojo.services.dice.MockDice;
import org.reflections.vfs.Vfs;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Author: your name
 * <p>
 * This is your AI algorithm for the game.
 * Implement it at your own discretion.
 * Pay attention to {@link YourSolverTest} - there is
 * a test framework for you.
 */
public class YourSolver implements Solver<Board> {

    private static Logger log = LoggerFactory.getLogger(JavaRunner.class);

    private Dice dice;
    private Board board;
    private int stopCounts = 0;
    private int deadEndCounts = 0;
    private Map<Point, Integer> currentPotions = new HashMap<>();
    private Map<Element, Integer> perks = new HashMap<>();
    private List<Point> heroesPrevPositions = new ArrayList<>();

    public YourSolver(Dice dice) {
        this.dice = dice;
    }

    static class Perk {
        Element perk;
        int duration;

        public Perk(Element perk, int duration) {
            this.perk = perk;
            this.duration = duration;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Perk perk1 = (Perk) o;
            return perk == perk1.perk;
        }

        @Override
        public int hashCode() {
            return Objects.hash(perk);
        }
    }

    @Override
    public String get(Board board) {
        if (perks.size() > 0) {
            for (Element e : perks.keySet()) {
                perks.put(e, perks.getOrDefault(e, 0) - 1);
            }
        }
        if (!currentPotions.isEmpty()) {
            for (Point p : currentPotions.keySet()) {
                if (perks.getOrDefault(Element.POTION_REMOTE_CONTROL, 0) <= 0) {
                    currentPotions.put(p, currentPotions.getOrDefault(p, 0) - 1);
                    if (currentPotions.get(p) <= 0) {
                        currentPotions.remove(p);
                    }
                }
            }
        }
        this.board = board;
        if (board.isGameOver()) {
            log.info(">>>>> My hero is dead!");
            perks = new HashMap<>();
            currentPotions = new HashMap<>();
            return "";
        }
        //System.out.println(board);
        log.info(">>>>> Current Potion is at: " + currentPotions);
        log.info(">>>>> Hero perks: " + perks);
        Stack<Point> sr = findShortestRoute(board, new ArrayList<>());
        if (sr != null) {
            log.info("Shortest route found: " + sr);
        }
        List<Point> exclude = new ArrayList<>();
        while (currentPotions.isEmpty() && sr != null && (sr.size() == 1
                || (!board.isAt(sr.get(0), ElementUtils.perks)
                && isPointInHeroPotionBlast(board, board.getHero(), true, sr.get(0))))
                && !board.isAt(sr.peek().getX(), sr.peek().getY(), ElementUtils.perks)) {
            exclude.add(sr.get(0));
            sr = findShortestRoute(board, exclude);
        }
        if (sr == null) {
            exclude = new ArrayList<>();
            sr = findShortestRouteToChest(board, exclude);
            while (currentPotions.isEmpty() && sr != null && (sr.size() <= 2
                    || (isPointInHeroPotionBlast(board, board.getHero(), true, sr.get(0))))) {
                exclude.add(sr.get(0));
                sr = findShortestRouteToChest(board, exclude);
            }
        }
        Direction d;
        Integer currentPotionTimer = 5;
        Point currentPotion = null;
        if (!currentPotions.isEmpty()) {
            currentPotion = currentPotions.keySet().stream().findFirst().get();
            currentPotionTimer = currentPotions.get(currentPotion);
        }
        if (sr == null || sr.isEmpty() || (sr.size() > 1 && (!isFreeAt(board, sr.peek()))
                || (currentPotionTimer == 1 && isInHeroPotionBlast(board, currentPotion, false, Element.HERO)))
                || isNear(sr.peek(), Element.GHOST, Element.GHOST_DEAD)
                || (sr.size() < 3 && board.isOtherHeroAt(sr.get(0)) && !heroesPrevPositions.contains(sr.get(0)))
                || (sr.size() == 1 && board.isPerkAt(sr.peek())
                && ((currentPotionTimer == 1
                && perks.getOrDefault(Element.POTION_IMMUNE, 0) <= 0
                && !board.isAt(sr.peek(), Element.POTION_IMMUNE)
                && isPointInHeroPotionBlast(board, currentPotion, false, sr.peek()))
                || (isInBlastOf(board, sr.get(0), Element.POTION_TIMER_1) != null))
        )) {
            d = findDirection(board);
        } else if (sr.size() > 1 || board.isPerkAt(sr.get(0))) {
            d = findDirectionFromToPoint(board, board.getHero(), sr.pop());
        } else {
            d = Direction.STOP;
        }
        Point targetPoint = new PointImpl(board.getHero().getX(), board.getHero().getY());
        targetPoint.move(d);
        Point freeSpot = findClosestFreeSpot(board, targetPoint);
        Point freeSpotForHero = findClosestFreeSpot(board, board.getHero());
        if (d.equals(Direction.STOP) && (isInBlastOf(board, board.getHero(), ElementUtils.potions) != null
                || isNear(targetPoint, Element.GHOST, Element.GHOST_DEAD)
                || isInBlastOf(board, targetPoint, Element.POTION_TIMER_1, Element.HERO_POTION) != null)
                /*|| (isInBlastOf(board, targetPoint, ElementUtils.potions) == null
                && isDeadEnd(board, board.getHero(), d)
                && !board.isPerkAt(targetPoint))*/) {
            targetPoint = new PointImpl(board.getHero().getX(), board.getHero().getY());
            d = findDirectionFromToPoint(board, board.getHero(), freeSpotForHero);
            targetPoint.move(d);
        }
        if (board.isPerkAt(targetPoint)) {
            perks.put(board.getAt(targetPoint.getX(), targetPoint.getY()), 30);
        }
        log.info(">>>>> Target direction: " + d);
        heroesPrevPositions = new ArrayList<>();
        heroesPrevPositions.addAll(board.getOtherHeroes());

        Direction blastDir = isInBlastOf(board, board.getHero(), Element.OTHER_HERO);

        if (blastDir != null) {
            Point otherHero = getOtherHeroInDirection(board, board.getHero(), blastDir);
            if (otherHero != null) {
                Direction blastDirOtherHero = isInBlastOf(board, otherHero, ElementUtils.potions);
                List<Direction> dirsToCheck = List.of(blastDir, findInverseDirection(blastDir), Direction.STOP);
                if (blastDir.equals(blastDirOtherHero) && isDeadEnd(board, otherHero, blastDir)
                        && dirsToCheck.contains(findDirectionFromToPoint(board, otherHero, findClosestFreeSpot(board, otherHero)))
                        && !isNear(board.getHero(), Element.GHOST, Element.GHOST_DEAD)
                        && isInBlastOf(board, board.getHero(), true, ElementUtils.potions) == null) {
                    System.out.println(">>>>> Other hero is trapped, stopping");
                    return Command.MOVE.apply(Direction.STOP);
                }
            }
        }

        if (isTrapped(board, board.getHero())) {
            System.out.println(">>>>> Hero is trapped, dropping potion");
            return Command.DROP_POTION;
        } else if (isInBlastOf(board, board.getHero(), ElementUtils.potions) == null
                && perks.getOrDefault(Element.POTION_EXPLODER, 0) > 0
                && ((currentPotions != null && board.getPotions().size() > 1)
                || (currentPotions == null && board.getPotions().size() > 0))) {
            perks.remove(Element.POTION_EXPLODER);
            return Command.EXPLODE_POTIONS_THEN_MOVE.apply(d);
        } /*else if (isInBlastOf(board, board.getHero(), ElementUtils.potions) == null
                && sr != null && !sr.isEmpty() && board.isAt(sr.get(0), Element.OTHER_HERO)
                && isPointInHeroPotionBlast(board, board.getHero(), false, sr.get(0))
                && !currentPotions.isEmpty()) {
            System.out.println("Hero is close to the other hero, STOP");
            return Command.MOVE.apply(Direction.STOP);
        }*/ else if (!currentPotions.isEmpty() && !isPointInHeroPotionBlast(board, currentPotions.keySet().stream().findFirst().get(), false, targetPoint)
                && perks.getOrDefault(Element.POTION_REMOTE_CONTROL, 0) > 0
                && isInHeroPotionBlast(board, currentPotions.keySet().stream().findFirst().get(), false, Element.OTHER_HERO, Element.GHOST, Element.GHOST_DEAD)) {
            System.out.println("MOVE 3: " + d);
            return Command.DROP_POTION_THEN_MOVE.apply(d);
        } else if (!currentPotions.isEmpty() && perks.getOrDefault(Element.POTION_REMOTE_CONTROL, 0) > 0
                && isPointInHeroPotionBlast(board, currentPotions.keySet().stream().findFirst().get(), false, targetPoint)) {
            System.out.println("MOVE 3: " + d);
            return Command.MOVE.apply(d);
        } else if (!currentPotions.isEmpty() && isInHeroPotionBlast(board, currentPotions.keySet().stream().findFirst().get(), false, Element.HERO)
                && perks.getOrDefault(Element.POTION_REMOTE_CONTROL, 0) > 0) {
            System.out.println("MOVE 3: " + d);
            return Command.MOVE.apply(d);
        } else if (isInHeroPotionBlast(board, board.getHero(), true, ElementUtils.perks)
                || isDeadEnd(board, board.getHero(), d)
                || board.isAt(board.getHero().getX(), board.getHero().getY(), Element.HERO_POTION)
                || (!currentPotions.isEmpty() && isInHeroPotionBlast(board, currentPotions.keySet().stream().findFirst().get(), false, Element.HERO))
                || freeSpot.equals(targetPoint) || !isInHeroPotionBlast(board, board.getHero(), false, Element.OTHER_HERO, Element.OTHER_HERO_POTION, Element.GHOST, Element.GHOST_DEAD, Element.TREASURE_BOX)
                || (sr != null && !sr.isEmpty() && sr.size() <= 4 && !isTreasureBoxInRoute(board, sr)
                && !isInHeroPotionBlast(board, board.getHero(), false, Element.OTHER_HERO))) {
            System.out.println("MOVE 1: " + d);
            return Command.MOVE.apply(d);
        } else if (!currentPotions.isEmpty() && perks.getOrDefault(Element.POTION_COUNT_INCREASE, 0) > 0) {
            System.out.println("MOVE 2: " + d);
            return Command.MOVE.apply(d);
        } else if (perks.getOrDefault(Element.POISON_THROWER, 0) > 0
                && isInBlastOf(board, board.getHero(), Element.POTION_TIMER_1, Element.POTION_TIMER_2, Element.HERO_POTION) == null
                && isInHeroPotionBlast(board, board.getHero(), false, Element.OTHER_HERO)) {
            return Command.THROW_POTION_AT.apply(findDirection(board, board.getHero(), Element.OTHER_HERO));
        } /*else if (perks.getOrDefault(Element.POISON_THROWER, 0) > 0
                && isInBlastOf(board, board.getHero(), Element.POTION_TIMER_1, Element.POTION_TIMER_2, Element.HERO_POTION) == null
                && isInHeroPotionBlast(board, board.getHero(), false, Element.TREASURE_BOX)) {
            return Command.THROW_POTION_AT.apply(findDirection(board, board.getHero(), Element.TREASURE_BOX));
        }*/ else {
            if (currentPotions.isEmpty()) {
                currentPotions.put(board.getHero(), 5);
            }
            System.out.println("DROP_POTION_THEN_MOVE: " + d);
            return Command.DROP_POTION_THEN_MOVE.apply(d);
        }
    }

    public static void main(String[] args) {
        String b =
                "☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼" +
                        "☼#     #    #  &      ☼" +
                        "☼   &   #             ☼" +
                        "☼   #        #  ##    ☼" +
                        "☼      T        & ♥   ☼" +
                        "☼ &  ##  #         #  ☼" +
                        "☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼" +
                        "☼ #   ♥              ♥☼" +
                        "☼      ☼☼☼☼☼          ☼" +
                        "☼    &      ☼☼☼☼☼☼☼ ##☼" +
                        "☼☼☼☼☼☼☼☼☼☼☺           ☼" +
                        "☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼" +
                        "☼          2     #    ☼" +
                        "☼                 # # ☼" +
                        "☼               ##    ☼" +
                        "☼ ##            &     ☼" +
                        "☼   #   #             ☼" +
                        "☼ ##  #          ### #☼" +
                        "☼                 #   ☼" +
                        "☼ # #    # #          ☼" +
                        "☼     #          #    ☼" +
                        "☼              #      ☼" +
                        "☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼☼";
        YourSolver s = new YourSolver(new MockDice());
        Board board = (Board) new Board().forString(b);
        s.get(board);
    }

    private Direction findInverseDirection(Direction dir) {
        switch (dir) {
            case UP:
                return Direction.DOWN;
            case DOWN:
                return Direction.UP;
            case LEFT:
                return Direction.RIGHT;
            case RIGHT:
                return Direction.LEFT;
        }
        return dir;
    }

    private Point getOtherHeroInDirection(Board board, Point point, Direction dir) {
        int radius = 3;
        for (int i = 1; i <= radius; i++) {
            switch (dir) {
                case LEFT:
                    if (board.isAt(point.getX() - i, point.getY(), Element.OTHER_HERO)) {
                        return new PointImpl(point.getX() - i, point.getY());
                    }
                    break;
                case RIGHT:
                    if (board.isAt(point.getX() + i, point.getY(), Element.OTHER_HERO)) {
                        return new PointImpl(point.getX() + i, point.getY());
                    }
                    break;
                case UP:
                    if (board.isAt(point.getX(), point.getY() + i, Element.OTHER_HERO)) {
                        return new PointImpl(point.getX(), point.getY() + i);
                    }
                    break;
                case DOWN:
                    if (board.isAt(point.getX(), point.getY() - i, Element.OTHER_HERO)) {
                        return new PointImpl(point.getX(), point.getY() - i);
                    }
                    break;
            }
        }
        return null;
    }

    private Stack<Point> findShortestRoute(Board board, List<Point> exclude) {
        List<Route> srs = new ArrayList<>();
        if (heroesPrevPositions.isEmpty()) {
            heroesPrevPositions.addAll(board.getOtherHeroes());
        }
        List<Point> heroesToCheck = new ArrayList<>();
        for (Point oh : board.getOtherHeroes()) {
            if (heroesPrevPositions.contains(oh) || perks.getOrDefault(Element.POISON_THROWER, 0) >= 5) {
                heroesToCheck.add(oh);
            }
        }
        for (Point otherHero : heroesToCheck) {
            if (!exclude.contains(otherHero)
                    && (!heroesPrevPositions.contains(otherHero) || isInBlastOf(board, otherHero, ElementUtils.potions) == null)) {
                srs = addNewRouteForPoint(srs, otherHero, true);
                srs = addNewRouteForPoint(srs, otherHero, false);
            }
        }
        for (Point perk : board.getPerks()) {
            if (!board.isAt(perk, Element.POTION_REMOTE_CONTROL)) {
                srs = addNewRouteForPoint(srs, perk, true);
                srs = addNewRouteForPoint(srs, perk, false);
            }
        }
        return srs.isEmpty() ? null : srs.get(0).route;
    }

    private List<Route> addNewRouteForPoint(List<Route> srs, Point point, boolean includeChests) {
        if (!board.isAt(point, Element.POTION_REMOTE_CONTROL)) {
            Route route = findRoute(board, point, includeChests);
            if (!route.route.isEmpty()) {
                if (srs.isEmpty() || srs.get(0).distance == route.distance) {
                    srs.add(route);
                } else if (srs.get(0).distance > route.distance) {
                    srs = new ArrayList<>();
                    srs.add(route);
                }
            }
        }
        return srs;
    }

    private Stack<Point> findShortestRouteToChest(Board board, List<Point> exclude) {
        List<Route> srs = new ArrayList<>();
        for (Point chest : board.getTreasureBoxes()) {
            if (!exclude.contains(chest)) {
                Route route = findRoute(board, chest, false);
                if (isInBlastOf(board, chest, ElementUtils.potions) == null && !route.route.isEmpty()) {
                    if (srs.isEmpty() || srs.get(0).distance == route.distance) {
                        srs.add(route);
                    } else if (srs.get(0).distance > route.distance) {
                        srs = new ArrayList<>();
                        srs.add(route);
                    }
                }
            }
        }
        return srs.isEmpty() ? null : srs.get(0).route;
    }

    private boolean isTrapped(Board board, Point hero) {
        int freeH = 0, freeV = 0;
        List<Element> elementsToCheck = new ArrayList<>();
        elementsToCheck.addAll(Arrays.asList(ElementUtils.perks));
        elementsToCheck.addAll(Arrays.asList(ElementUtils.potions));
        elementsToCheck.addAll(Arrays.asList(Element.NONE, Element.GHOST, Element.GHOST_DEAD, Element.BLAST));

        for (int i = 1; i <= 3; i++) {
            Point point = new PointImpl(hero.getX() + i, hero.getY());
            if (!board.isAt(point, elementsToCheck.toArray(Element[]::new))) {
                break;
            }
            if (board.isAt(point.getX(), point.getY() + 1, elementsToCheck.toArray(Element[]::new))
                    || board.isAt(point.getX(), point.getY() - 1, elementsToCheck.toArray(Element[]::new))
                    || (i == 3 && board.isAt(point.getX() + 1, point.getY(), elementsToCheck.toArray(Element[]::new)))) {
                return false;
            }
            if (board.isAt(point, elementsToCheck.toArray(Element[]::new))) {
                freeH++;
            }
        }
        for (int i = 1; i <= 3; i++) {
            Point point = new PointImpl(hero.getX() - i, hero.getY());
            if (!board.isAt(point, elementsToCheck.toArray(Element[]::new))) {
                break;
            }
            if (board.isAt(point.getX(), point.getY() + 1, elementsToCheck.toArray(Element[]::new))
                    || board.isAt(point.getX(), point.getY() - 1, elementsToCheck.toArray(Element[]::new))
                    || (i == 3 && board.isAt(point.getX() - 1, point.getY(), elementsToCheck.toArray(Element[]::new)))) {
                return false;
            }
            if (board.isAt(point, elementsToCheck.toArray(Element[]::new))) {
                freeH++;
            }
        }
        for (int i = 1; i <= 3; i++) {
            Point point = new PointImpl(hero.getX(), hero.getY() + i);
            if (!board.isAt(point, elementsToCheck.toArray(Element[]::new))) {
                break;
            }
            if (board.isAt(point.getX() + 1, point.getY(), elementsToCheck.toArray(Element[]::new))
                    || board.isAt(point.getX() - 1, point.getY(), elementsToCheck.toArray(Element[]::new))
                    || (i == 3 && board.isAt(point.getX(), point.getY() + 1, elementsToCheck.toArray(Element[]::new)))) {
                return false;
            }
            if (board.isAt(point, elementsToCheck.toArray(Element[]::new))) {
                freeV++;
            }
        }
        for (int i = 1; i <= 3; i++) {
            Point point = new PointImpl(hero.getX(), hero.getY() - i);
            if (!board.isAt(point, elementsToCheck.toArray(Element[]::new))) {
                break;
            }
            if (board.isAt(point.getX() + 1, point.getY(), elementsToCheck.toArray(Element[]::new))
                    || board.isAt(point.getX() - 1, point.getY(), elementsToCheck.toArray(Element[]::new))
                    || (i == 3 && board.isAt(point.getX(), point.getY() - 1, elementsToCheck.toArray(Element[]::new)))) {
                return false;
            }
            if (board.isAt(point, elementsToCheck.toArray(Element[]::new))) {
                freeV++;
            }
        }
        if ((freeH <= 3 && freeV == 0) || (freeV <= 3 && freeH == 0)) {
            return true;
        }
        return false;
    }

    private Direction findDirection(Board board, Point p, Element e) {
        int radius = 3;
        if (perks.getOrDefault(Element.POTION_BLAST_RADIUS_INCREASE, 0) > 0) {
            radius += 2;
        }
        for (int i = 1; i <= radius; i++) {
            Point point = new PointImpl(p.getX() + i, p.getY());
            if (board.isWallAt(point)) {
                break;
            }
            if (board.isAt(point, e)) {
                return Direction.RIGHT;
            }
        }
        for (int i = 1; i <= radius; i++) {
            Point point = new PointImpl(p.getX() - i, p.getY());
            if (board.isWallAt(point)) {
                break;
            }
            if (board.isAt(point, e)) {
                return Direction.LEFT;
            }
        }
        for (int i = 1; i <= radius; i++) {
            Point point = new PointImpl(p.getX(), p.getY() + i);
            if (board.isWallAt(point)) {
                break;
            }
            if (board.isAt(point, e)) {
                return Direction.UP;
            }
        }
        for (int i = 1; i <= radius; i++) {
            Point point = new PointImpl(p.getX(), p.getY() - i);
            if (board.isWallAt(point)) {
                break;
            }
            if (board.isAt(point, e)) {
                return Direction.DOWN;
            }
        }
        return Direction.STOP;
    }

    private boolean isTreasureBoxInRoute(Board board, Stack<Point> route) {
        for (Point p : route) {
            if (board.isTreasureBoxAt(p)) {
                log.info(">>>>> Is treasure box in route: true");
                return true;
            }
        }
        return false;
    }

    public Route findRoute(Board board, Point otherHero, boolean includeChests) {
        LinkedList<Coordinate> nextToVisit
                = new LinkedList<>();
        char[][] field = Arrays.stream(board.field())
                .map(char[]::clone)
                .toArray(char[][]::new);
        Coordinate start = new Coordinate(board.getHero(), null);

        for (int k = -1; k <= 1; k += 2) {
            nextToVisit.add(new Coordinate(new PointImpl(start.point.getX() + k, start.point.getY()), start));
            nextToVisit.add(new Coordinate(new PointImpl(start.point.getX(), start.point.getY() + k), start));
        }

        while (!nextToVisit.isEmpty()) {
            Coordinate cur = nextToVisit.remove();

            if (cur.point.getX() < 0 || cur.point.getX() >= field.length || cur.point.getY() < 0 || cur.point.getY() >= field.length
                    || field[cur.point.getX()][cur.point.getY()] == 'V') {
                continue;
            }

            if (!(new PointImpl(cur.point.getX(), cur.point.getY()).equals(otherHero)) && field[cur.point.getX()][cur.point.getY()] != ' '
                    && (!includeChests || field[cur.point.getX()][cur.point.getY()] != '#')
                    && field[cur.point.getX()][cur.point.getY()] != '҉') {
                field[cur.point.getX()][cur.point.getY()] = 'V';
                continue;
            }

            if (new PointImpl(cur.point.getX(), cur.point.getY()).equals(otherHero)) {
                return backtrackPath(board, cur);
            }

            for (int k = -1; k <= 1; k += 2) {
                nextToVisit.add(new Coordinate(new PointImpl(cur.point.getX() + k, cur.point.getY()), cur));
                nextToVisit.add(new Coordinate(new PointImpl(cur.point.getX(), cur.point.getY() + k), cur));
            }
            field[cur.point.getX()][cur.point.getY()] = 'V';
        }
        return new Route(new Stack<>(), 0);
    }

    static class Coordinate {
        Point point;
        Coordinate parent;

        public Coordinate(Point point, Coordinate parent) {
            this.point = point;
            this.parent = parent;
        }
    }

    static class Route {
        Stack<Point> route;
        int distance;

        public Route(Stack<Point> route, int distance) {
            this.route = route;
            this.distance = distance;
        }
    }

    private Route backtrackPath(Board board, Coordinate cur) {
        Stack<Point> path = new Stack<>();

        Route route = new Route(path, 0);
        Coordinate iter = cur;

        while (iter != null) {
            if (iter.parent != null) {
                int weight = 1;
                if (board.isTreasureBoxAt(iter.point)) {
                    weight += 3;
                }
                if (isNear(iter.point, Element.GHOST) || isNearCorners(iter.point, Element.GHOST)) {
                    weight += 5;
                }
                route.distance += weight;
                path.add(iter.point);
            } else {
                if (board.isAt(iter.point, ElementUtils.perks)) {
                    route.distance += 2;
                }
            }
            iter = iter.parent;
        }

        return route;
    }

    private boolean isDeadEnd(Board board, Point point, Direction dir) {
        if (dir.equals(Direction.STOP)) {
            return false;
        }
        int radius = 3;
        if (perks.getOrDefault(Element.POTION_BLAST_RADIUS_INCREASE, 0) > 0) {
            radius += 2;
        }
        int u, d, l, r;

        for (int i = 1; i <= radius; i++) {
            switch (dir) {
                case LEFT:
                    l = -i;
                    u = 1;
                    d = -1;
                    if (!isFreeAt(board, new PointImpl(point.getX() + l, point.getY()))) {
                        return true;
                    }
                    if (isFreeAt(board, new PointImpl(point.getX() + l, point.getY() + u))
                            || isFreeAt(board, new PointImpl(point.getX() + l, point.getY() + d))) {
                        return false;
                    }
                    if (i == radius && isFreeAt(board, new PointImpl(point.getX() + l - 1, point.getY()))) {
                        return false;
                    }
                    break;
                case RIGHT:
                    r = i;
                    u = 1;
                    d = -1;
                    if (!isFreeAt(board, new PointImpl(point.getX() + r, point.getY()))) {
                        return true;
                    }
                    if (isFreeAt(board, new PointImpl(point.getX() + r, point.getY() + u))
                            || isFreeAt(board, new PointImpl(point.getX() + r, point.getY() + d))) {
                        return false;
                    }
                    if (i == radius && isFreeAt(board, new PointImpl(point.getX() + r + 1, point.getY()))) {
                        return false;
                    }
                    break;
                case UP:
                    u = i;
                    r = 1;
                    l = -1;
                    if (!isFreeAt(board, new PointImpl(point.getX(), point.getY() + u))) {
                        return true;
                    }
                    if (isFreeAt(board, new PointImpl(point.getX() + l, point.getY() + u))
                            || isFreeAt(board, new PointImpl(point.getX() + r, point.getY() + u))) {
                        return false;
                    }
                    if (i == radius && isFreeAt(board, new PointImpl(point.getX(), point.getY() + u + 1))) {
                        return false;
                    }
                    break;
                case DOWN:
                    d = -i;
                    r = 1;
                    l = -1;
                    if (!isFreeAt(board, new PointImpl(point.getX(), point.getY() + d))) {
                        return true;
                    }
                    if (isFreeAt(board, new PointImpl(point.getX() + l, point.getY() + d))
                            || isFreeAt(board, new PointImpl(point.getX() + r, point.getY() + d))) {
                        return false;
                    }
                    if (i == radius && isFreeAt(board, new PointImpl(point.getX(), point.getY() + d - 1))) {
                        return false;
                    }
            }
        }
        return true;
    }

    private boolean isInHeroPotionBlast(Board board, Point p, boolean includeChests, Element... e) {
        int radius = 3;
        if (perks.getOrDefault(Element.POTION_BLAST_RADIUS_INCREASE, 0) > 0) {
            radius += 2;
        }
        for (int i = 1; i <= radius; i++) {
            Point point = new PointImpl(p.getX() + i, p.getY());
            if (board.isWallAt(point) || (includeChests && board.isTreasureBoxAt(point))) {
                break;
            }
            if (board.isAt(point, e)) {
                return true;
            }
        }
        for (int i = 1; i <= radius; i++) {
            Point point = new PointImpl(p.getX() - i, p.getY());
            if (board.isWallAt(point) || (includeChests && board.isTreasureBoxAt(point))) {
                break;
            }
            if (board.isAt(point, e)) {
                return true;
            }
        }
        for (int i = 1; i <= radius; i++) {
            Point point = new PointImpl(p.getX(), p.getY() + i);
            if (board.isWallAt(point) || (includeChests && board.isTreasureBoxAt(point))) {
                break;
            }
            if (board.isAt(point, e)) {
                return true;
            }
        }
        for (int i = 1; i <= radius; i++) {
            Point point = new PointImpl(p.getX(), p.getY() - i);
            if (board.isWallAt(point) || (includeChests && board.isTreasureBoxAt(point))) {
                break;
            }
            if (board.isAt(point, e)) {
                return true;
            }
        }
        return false;
    }

    private boolean isPointInHeroPotionBlast(Board board, Point heroPotion, boolean includeChests, Point target) {
        int radius = 3;
        if (perks.getOrDefault(Element.POTION_BLAST_RADIUS_INCREASE, 0) > 0) {
            radius += 2;
        }
        for (int i = 1; i <= radius; i++) {
            Point point = new PointImpl(heroPotion.getX() + i, heroPotion.getY());
            if (board.isWallAt(point) || (includeChests && board.isTreasureBoxAt(point))) {
                break;
            }
            if (point.equals(target)) {
                return true;
            }
        }
        for (int i = 1; i <= radius; i++) {
            Point point = new PointImpl(heroPotion.getX() - i, heroPotion.getY());
            if (board.isWallAt(point) || (includeChests && board.isTreasureBoxAt(point))) {
                break;
            }
            if (point.equals(target)) {
                return true;
            }
        }
        for (int i = 1; i <= radius; i++) {
            Point point = new PointImpl(heroPotion.getX(), heroPotion.getY() + i);
            if (board.isWallAt(point) || (includeChests && board.isTreasureBoxAt(point))) {
                break;
            }
            if (point.equals(target)) {
                return true;
            }
        }
        for (int i = 1; i <= radius; i++) {
            Point point = new PointImpl(heroPotion.getX(), heroPotion.getY() - i);
            if (board.isWallAt(point) || (includeChests && board.isTreasureBoxAt(point))) {
                break;
            }
            if (point.equals(target)) {
                return true;
            }
        }
        return false;
    }

    private Direction isInBlastOf(Board board, Point p, Element... e) {
        return isInBlastOf(board, p, false, e);
    }

    private Direction isInBlastOf(Board board, Point p, boolean includeOtherHeroes, Element... e) {
        for (int i = 1; i <= 3; i++) {
            Point point = new PointImpl(p.getX() + i, p.getY());
            if (board.isWallAt(point) || board.isTreasureBoxAt(point)
                    || (includeOtherHeroes && board.isAt(point, Element.OTHER_HERO))) {
                break;
            }
            if (board.isAt(point, e)) {
                return Direction.RIGHT;
            }
        }
        for (int i = 1; i <= 3; i++) {
            Point point = new PointImpl(p.getX() - i, p.getY());
            if (board.isWallAt(point) || board.isTreasureBoxAt(point)
                    || (includeOtherHeroes && board.isAt(point, Element.OTHER_HERO))) {
                break;
            }
            if (board.isAt(point, e)) {
                return Direction.LEFT;
            }
        }
        for (int i = 1; i <= 3; i++) {
            Point point = new PointImpl(p.getX(), p.getY() + i);
            if (board.isWallAt(point) || board.isTreasureBoxAt(point)
                    || (includeOtherHeroes && board.isAt(point, Element.OTHER_HERO))) {
                break;
            }
            if (board.isAt(point, e)) {
                return Direction.UP;
            }
        }
        for (int i = 1; i <= 3; i++) {
            Point point = new PointImpl(p.getX(), p.getY() - i);
            if (board.isWallAt(point) || board.isTreasureBoxAt(point)
                    || (includeOtherHeroes && board.isAt(point, Element.OTHER_HERO))) {
                break;
            }
            if (board.isAt(point, e)) {
                return Direction.DOWN;
            }
        }
        return null;
    }

    private boolean isNearCorners(Point p, Element e) {
        for (int i = 1; i <= 1; i++) {
            if (board.isAt(p.getX() + i, p.getY() + i, e)) {
                return true;
            }
            if (board.isAt(p.getX() - i, p.getY() - i, e)) {
                return true;
            }
            if (board.isAt(p.getX() - i, p.getY() + i, e)) {
                return true;
            }
            if (board.isAt(p.getX() + i, p.getY() - i, e)) {
                return true;
            }
        }
        return false;
    }

    private boolean isNear(Point p, Element... e) {
        for (int i = 1; i <= 1; i++) {
            if (board.isAt(p.getX() + i, p.getY(), e)) {
                return true;
            }
            if (board.isAt(p.getX() - i, p.getY(), e)) {
                return true;
            }
            if (board.isAt(p.getX(), p.getY() + i, e)) {
                return true;
            }
            if (board.isAt(p.getX(), p.getY() - i, e)) {
                return true;
            }
        }
        return false;
    }

    private Direction findDirection(Board board) {

        log.info(">>>>> My hero is at: " + board.getHero().getX() + " - " + board.getHero().getY());
        Point freeSpot = findClosestFreeSpot(board, board.getHero());
        int cont = 0;
        while (!freeSpot.equals(board.getHero())
                && findClosestFreeSpot(board, freeSpot).equals(freeSpot)
                && !board.isFutureBlastAt(board.getHero()) && cont < 4
        ) {
            freeSpot = findClosestFreeSpot(board, board.getHero());
            cont++;
        }
        log.info(">>>>> Closest free spot at: " + freeSpot.getX() + " - " + freeSpot.getY());
        Direction d = findDirectionFromToPoint(board, board.getHero(), freeSpot);
        return d;
    }

    private Point findClosestFreeSpot(Board board, Point point) {
        log.info(">>>>> Finding closest free spot for: " + point.getX() + " - " + point.getY());
        List<Point> points = new ArrayList<>();
        int currentX = point.getX(), currentY = point.getY();
        Point p1 = new PointImpl(currentX + 1, currentY);
        if (isFreePointSafe(p1)) {
            points.add(p1);
        }
        Point p2 = new PointImpl(currentX, currentY + 1);
        if (isFreePointSafe(p2)) {
            points.add(p2);
        }
        Point p3 = new PointImpl(currentX - 1, currentY);
        if (isFreePointSafe(p3)) {
            points.add(p3);
        }
        Point p4 = new PointImpl(currentX, currentY - 1);
        if (isFreePointSafe(p4)) {
            points.add(p4);
        }
        log.info(">>>>> Points to check: " + points);
        points = points.stream().filter(p -> !isNear(p, Element.GHOST, Element.GHOST_DEAD)).collect(Collectors.toList());
        List<Point> pointsNotInPotionWay = points.stream().filter(p -> isInBlastOf(board, p, ElementUtils.potions) == null).collect(Collectors.toList());
        log.info(">>>>> Points filtered without ghosts near: " + points);
        if (points.isEmpty()) {
            log.info(">>>>> closest free spot is the same point: " + point);
            return point;
        } else if (!pointsNotInPotionWay.isEmpty()) {
            Collections.shuffle(pointsNotInPotionWay);
            return pointsNotInPotionWay.get(0);
        } else {
            Collections.shuffle(points);
            return points.get(0);
        }
    }

    private boolean isFreePointSafe(Point p) {
        Integer currentPotionTimer = 5;
        Point currentPotion = null;
        if (!currentPotions.isEmpty()) {
            currentPotion = currentPotions.keySet().stream().findFirst().get();
            currentPotionTimer = currentPotions.get(currentPotion);
        }
        return isFreeAt(board, p)
                && (currentPotionTimer > 1 || !isPointInHeroPotionBlast(board, currentPotion, false, p))
                && (isInBlastOf(board, p, ElementUtils.potions) == null
                || (!isDeadEnd(board, board.getHero(), findDirectionFromToPoint(board, board.getHero(), p)) && isInBlastOf(board, p, Element.POTION_TIMER_1) == null));
    }

    private Direction findDirectionFromToPoint(Board board, Point from, Point to) {
        int currentX = from.getX(), currentY = from.getY();

        if (to.getY() > currentY) {
            return Direction.UP;
        }
        if (to.getX() > currentX) {
            return Direction.RIGHT;
        }
        if (to.getY() < currentY) {
            return Direction.DOWN;
        }
        if (to.getX() < currentX) {
            return Direction.LEFT;
        }

        return Direction.STOP;
    }

    private boolean isFreeAt(Board board, Point p) {
        boolean free = !board.isWallAt(p) && !board.isEnemyHeroAt(p) && !board.isBarrierAt(p)
                && !board.isFutureBlastAt(p) && !board.isAt(p.getX(), p.getY(), Element.VISITED)
                && isInBlastOf(board, p, Element.OTHER_HERO_POTION) == null
                && (isInBlastOf(board, p, Element.POTION_TIMER_2) == null || hasSideExit(board, p))
                && !board.isPotionAt(p) && !board.isGhostAt(p) && !board.isTreasureBoxAt(p) && !board.isOtherHeroAt(p) && !board.isHeroAt(p);

        // log.info(">>>>> Is free at: " + p.getX() + " - " + p.getY() + " - " + free);
        return free;
    }

    private boolean hasSideExit(Board board, Point point) {
        Direction dir = findDirectionFromToPoint(board, board.getHero(), point);
        List<Element> elementsToCheck = new ArrayList<>();
        elementsToCheck.addAll(Arrays.asList(ElementUtils.perks));
        elementsToCheck.addAll(Arrays.asList(Element.NONE, Element.BLAST));

        switch (dir) {
            case LEFT:
            case RIGHT:
                if (board.isAt(point.getX(), point.getY() + 1, elementsToCheck.toArray(Element[]::new))
                        || board.isAt(point.getX(), point.getY() - 1, elementsToCheck.toArray(Element[]::new))) {
                    return true;
                }
                break;
            case UP:
            case DOWN:
                if (board.isAt(point.getX() + 1, point.getY(), elementsToCheck.toArray(Element[]::new))
                        || board.isAt(point.getX() - 1, point.getY(), elementsToCheck.toArray(Element[]::new))) {
                    return true;
                }
        }
        return false;
    }
}
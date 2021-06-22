This is the java websocket client for the codenjoy-contest server.
It contains a basic implementation of game components for your quick start to play.\
You should define a desirable game in `Runner.GAME` and your token in `Runner.URL`.

The game runner looks for board and solver in `com.codenjoy.dojo.games.${game}` package.
It takes @GameBoard & @GameSolver candidates.
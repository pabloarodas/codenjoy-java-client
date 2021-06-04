import com.codenjoy.dojo.client.WebSocketRunner;
import com.codenjoy.dojo.services.RandomDice;

public class Runner {

    public static void main(String[] args) {
        WebSocketRunner.runClient(args,
                // Paste here board page url from browser after registration,
                // or put it as command line parameter.
                "http://codenjoy.com:80/codenjoy-contest/board/player/3edq63tw0bq4w4iem7nb?code=1234567890123456789",

                // This is your solver, please work with it.
                // Do not forget about YourSolverTest for your TDD/unit-testing
                new com.codenjoy.dojo.games.a2048.YourSolver(new RandomDice()),

                // This is board representation.
                new com.codenjoy.dojo.games.a2048.Board()
        );
    }

}

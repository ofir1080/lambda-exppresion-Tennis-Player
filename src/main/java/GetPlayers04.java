import java.util.List;
import java.util.function.Predicate;

public class GetPlayers04 {

    /**
     * prints all female players in the list
     *
     * @param players list of players
     */
    public void getReducedList(List<Player> players, Predicate<Player> pred) {
        for (Player p : players) {
            if (pred.test(p))
                printName(p);
        }
    }

    public void printName(Player p) {
        System.out.format("Player's name: %s\n", p.getName());
    }
}



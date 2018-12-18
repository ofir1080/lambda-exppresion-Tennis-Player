import java.util.List;

public class GetPlayers03 {

    /**
     * prints all female players in the list
     *
     * @param players list of players
     */
    public void getReducedList(List<Player> players, ListFunction<Player> imp) {
        for (Player p : players) {
            if (imp.isValid(p))
                printName(p);
        }
    }

    public void printName(Player p) {
        System.out.format("Player's name: %s\n", p.getName());
    }
}


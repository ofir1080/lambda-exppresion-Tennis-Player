import java.util.List;

public class GetPlayers01 {

    /**
     * prints all female players in the list
     * @param players list of players
     */
    public void getFemales(List<Player> players) {
        for (Player player : players) {
            if (player.getGender().equals(Gender.FEMALE))
                printName(player);
        }
    }

    /**
     * prints all players in the list who have ever ranked #1
     * @param players list of players
     */
    public void getWorldChamps(List<Player> players) {
        for (Player player : players) {
            if (player.getBestRank() == 1)
                printName(player);
        }
    }

    /**
     * prints all players in the list whom retired
     * @param players list of players
     */
    public void getRetired(List<Player> players) {
        for (Player player : players) {
            if (player.getAtpRank() == 0)
                printName(player);
        }
    }

    /**
     * prints all players in the list whose height is over 1.80cm
     * @param players list of players
     */
    public void getTallPlayers(List<Player> players) {
        for (Player player : players) {
            if (player.getHeight() >= 1.80)
                printName(player);
        }
    }

    /**
     * prints the player's name
     * @param p player
     */
    public void printName(Player p) {
        System.out.format("Player's name: \t%s\n", p.getName());
    }
}

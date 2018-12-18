import java.util.List;

public class GetPlayers02 {

    /**
     * prints all female players in the list
     *
     * @param players list of players
     */
    public void getFemales(List<Player> players) {
        for (Player p : players) {
            if (isFemale(p))
                printName(p);
        }
    }

    /**
     * prints all players in the list who have ever ranked #1
     *
     * @param players list of players
     */
    public void getWorldChamps(List<Player> players) {
        for (Player p : players) {
            if (everChamp(p))
                printName(p);
        }
    }

    /**
     * prints all players in the list whom retired
     *
     * @param players list of players
     */
    public void getRetired(List<Player> players) {
        for (Player p : players) {
            if (isRetired(p))
                printName(p);
        }
    }

    /**
     * prints all players in the list whose height is over 1.80cm
     *
     * @param players list of players
     */
    public void getTallPlayers(List<Player> players) {
        for (Player p : players) {
            if (isTall(p))
                printName(p);
        }
    }

    public boolean isFemale(Player p) {
        return p.getGender().equals("FEMALE");
    }

    public boolean everChamp(Player p) {
        return p.getBestRank() == 1;
    }

    public boolean isRetired(Player p) {
        return p.getAtpRank() == 0;
    }

    public boolean isTall(Player p) {
        return p.getHeight() >= 1.80;
    }

    public void printName(Player p) {
        System.out.format("Player's name: \t%s\n", p.getName());
    }
}

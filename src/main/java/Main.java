import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class Main {

    /**
     * Creates a list of players
     * @return list of players
     */
    public static List<Player> createList() {
        List<Player> players = new ArrayList<>();
        players.add(new Player("Roger Federer", Gender.MALE, 37, 1.85, 3, 1));
        players.add(new Player("Novak Djokovic", Gender.MALE, 31, 1.88, 1, 1));
        players.add(new Player("Simona Halep", Gender.FEMALE, 27, 1.68, 1, 1));
        players.add(new Player("Huan Martin Del Potro", Gender.MALE, 30, 1.85, 5, 3));
        players.add(new Player("Maria Sharapova", Gender.FEMALE, 31, 1.88, 29, 1));
        players.add(new Player("Justine Henin", Gender.FEMALE, 36, 1.67, 0, 1));
        players.add(new Player("Rod Laver", Gender.MALE, 80, 1.73, 0, 1));
        players.add(new Player("Refael Nadal", Gender.MALE, 32, 1.85, 2, 1));

        return players;
    }

    public static void main(String[] args) {

        List<Player> players = createList();

        // First and second implementation (main)
//        System.out.println("\n----> Female Players <----");
//        new GetPlayers01().getFemales(players);
//
//        System.out.println("\n----> Champions <----");
//        new GetPlayers01().getWorldChamps(players);
//
//        System.out.println("\n----> Retired Players <----");
//        new GetPlayers01().getRetired(players);
//
//        System.out.println("\n----> Over 1.80cm Players <----");
//        new GetPlayers01().getTallPlayers(players);


//         Third implementation - using anonymous classes
    System.out.println("\n----> Female Players <----");
    new GetPlayers03().getReducedList(players, new ListFunction<Player>() {
        @Override
        public boolean isValid(Player p) {
            return p.getGender().ordinal() == 1;
        }
    });

    System.out.println("\n----> Champions <----");
    new GetPlayers03().getReducedList(players, new ListFunction<Player>() {
        @Override
        public boolean isValid(Player p) {
            return p.getBestRank() == 1;
        }
    });

    System.out.println("\n----> Retired Players <----");
    new GetPlayers03().getReducedList(players, new ListFunction<Player>() {
        @Override
        public boolean isValid(Player p) {
            return p.getAtpRank() == 0;
        }
    });

    System.out.println("\n----> Over 1.80cm Players <----");
    new GetPlayers03().getReducedList(players, new ListFunction<Player>() {
        @Override
        public boolean isValid(Player p) {
            return p.getHeight() >= 1.80;
        }
    });

        // Forth implementation - using Predicate interface and Lambda expressions

        System.out.println("\n----> Female Players <----");
        new GetPlayers04().getReducedList(players, p -> p.getGender().ordinal() == 1);

        System.out.println("\n----> Champions <----");
        new GetPlayers04().getReducedList(players, p -> p.getBestRank() == 1);

        System.out.println("\n----> Retired Players <----");
        new GetPlayers04().getReducedList(players, p -> p.getAtpRank() == 0);

        System.out.println("\n----> Over 1.80cm Players <----");
        new GetPlayers04().getReducedList(players, p -> p.getHeight() >= 1.80);
    }
}

## <img width="50" alt="Lambda" src="https://upload.wikimedia.org/wikipedia/commons/3/39/Lambda_lc.svg">Improving Code Using Functional Interfaces & Lambda Expressions<img width="50" alt="Lambda" src="https://upload.wikimedia.org/wikipedia/commons/3/39/Lambda_lc.svg">



Since Java 8, Java allowed Lambda (of Arrow) functions.
This tutorial is a brief example of using it and showing step-by-step how a simple code can be improved.
Our goals:

  - Make our code as DRY as possible (that is, avoiding the 'Don't repeat yourself' principle)
  - Making our code flexible (easily changeable and maintainable).
  - Keep our code clean and short.
  - Adding some 'syntactic sugar' to achieve a professional nice-looking code.

# Tennis Player Card
Consider the following stracture:

Let a 'Player' be an object represents a Tennis player with the following fields:
```sh
private String name;
private Gender gender;
private int age;
private double height;
private int atpRank;
private int bestRank;
```
(where 'Gender' is a enumeration of either Male or Female).
Consider the following function
## Main.createList()

```sh
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
```
This func creates an ArrayList of Players with the required fields (Data is  real, updated as of 12/18/18)

Let 'GetPlayers01' be the following class:

## GetPlayers01.java
```sh
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
```

Each method receives a list of players reduces it as described and returns. For example, the first method return a list of all female players found in a certain list.

As we can see, the methods describe the kind of behavior that is taking place. The search criteria is clearly conveyed and an appropriate call is made to each robo action.
However, this design has some negatives aspects:

  - The DRY principle is not followed.
  - Each method repeats a looping mechanism.
  - The selection criteria must be rewritten for each method
  - A large number of methods are required to implement each use case.
  - The code is inflexible. If the search criteria changed, it would require a number of code changes for an update. Thus, the code is not very maintainable.

## Refactor Methods
Can we improve this code? Can we make it more orgenized and fluence?
We can easily notice the searching criteria in every function. Let's isolate it:
## GetPlayers02.java
```sh
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
```
The search criteria are encapsulated in a method, an improvement over the previous example.
However there is still a lot of repeated code and a separate method is still required for each use case. Is there a better way to pass the search criteria to the methods?

## Using Anonymous Classes
Before lambda expressions, anonymous inner classes would be a nice improvement.
For implementing this, let us define the following interface:
## ListFunction.java
```sh
@FunctionalInterface
public interface ListFunction<Player> {

    /**
     * check weather a certain condition applies on a player
     * @param p player
     * @return true iff the condition applies on the player
     */
    public boolean isValid(Player p);
}
```
 *note that this interface is a functional interface (a single function interface, on which the lambda expression shall be applied).
 
 Now we can pass the ListFunction instace to methods, using one-time implementation:
 
 ## GetPlayers03.java
 ```sh
 public class GetPlayers03 {

    /**
     * prints all players who match the implemented ListFunction<Player>
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
 ```
That is definitely a huge improvement, because only one methods needed to perform operations.
However, there is a slight problem with ugliness when the methods are called. Check out the test class used for this class:

## Main.main(String[] args)
```sh
public static void main(String[] args) {
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
}
```
This is a great example of the "vertical" problem in practice. This code is a little difficult to read. In addition, we have to write custom search criteria for each use case.

A relevant question would be if we really have to create the functional interface every time we write such code. The Answer would be NO. The java.util.function supply us useful interfaces for the exact purpeses. One of them is the Predicate<T> interface:
```sh
public interface Predicate<T> {
    public boolean test(T t);
}
```
So now we can remove ``ListFunction.java`` interface we have created before.
The updated (and final) ``GetPlayers04`` would be:
## GetPlayers04
```sh
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
```
The ``test`` method takes a generic class (in our case Player) and returns a boolean result (if the player matches).

And that's where Lambda Exppressions come in:
## Using the Lambda Expressions
The final version of out `main()` will look as the followig:
```sh
public static void main(String[] args) {

    List<Player> players = createList();
    
    // Forth implementation - using Predicate interface
    Predicate<Player> femalePlayer = p -> p.getGender().ordinal() == 1;
    Predicate<Player> champ = p -> p.getBestRank() == 1;

    System.out.println("\n----> Female Players <----");
    new GetPlayers04().getReducedList(players, femalePlayer);

    System.out.println("\n----> Champions <----");
    new GetPlayers04().getReducedList(players, champ);

    System.out.println("\n----> Retired Players <----");
    new GetPlayers04().getReducedList(players, (p) -> p.getAtpRank() == 0);

    System.out.println("\n----> Over 1.80cm Players <----");
    new GetPlayers04().getReducedList(players, (p) -> p.getHeight() >= 1.80);
```
### Vertical Problem Solved
Lambda expressions solve the vertical problem and allow us the easy reuse of any expression.

## To conclude
We have shown step-by-step how a somple code can be improved using Anoynemos classes, isolating code and Lambda Expressions. Note that the actual result is the same, but our final code is clean, fluent and easy to read.

### Resources
https://www.oracle.com/ tutorial of Java SE 8: Lambda Quick Start

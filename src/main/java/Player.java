public class Player {
    private String name;
    private Gender gender;
    private int age;
    private double height;
    private int atpRank;
    private int bestRank;

    public Player(String name, Gender gender, int age, double height, int atpRank, int bestRank) {
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.height = height;
        this.atpRank = atpRank;
        this.bestRank = bestRank;
    }

    public String getName() {
        return name;
    }

    public Gender getGender() {
        return gender;
    }

    public int getAge() {
        return age;
    }

    public double getHeight() {
        return height;
    }

    public int getAtpRank() {
        return atpRank;
    }

    public int getBestRank() {
        return bestRank;
    }


    @Override
    public String toString() {
        return "Player's name: " + name + '\n' +
                "Gender: " + gender + '\n' +
                "Age: " + age + '\n' +
                "Height: " + height + "cm" + '\n' +
                "AtpRank: " + atpRank + '\n' +
                "BestRank: " + bestRank + '\n';
    }
}

enum Gender {
    MALE,
    FEMALE
}
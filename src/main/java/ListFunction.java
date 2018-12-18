@FunctionalInterface
public interface ListFunction<Player> {

    /**
     * check weather a certain condition applies on a player
     * @param p player
     * @return true iff the condition applies on the player
     */
    public boolean isValid(Player p);
}

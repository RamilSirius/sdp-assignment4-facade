package wow;

import wow.facade.DungeonFacade;
import wow.facade.DungeonRunException;

public class Main {
    public static void main(String[] args) {
        DungeonFacade dungeon = new DungeonFacade();
        String player = "Ramil";

        dungeon.runDungeon(player, "Deadmines");
        System.out.println(player + " level: " + dungeon.getPlayerLevel(player));
        System.out.println();

        dungeon.runDungeon(player, "Shadowfang Keep");
        System.out.println();

        try {
            dungeon.runDungeon(player, "Karazhan");
        } catch (DungeonRunException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}

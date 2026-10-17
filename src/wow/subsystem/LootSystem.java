package wow.subsystem;

import java.util.List;
import java.util.Map;
import java.util.Random;

public class LootSystem {
    private final Random random = new Random();

    private final Map<String, List<String>> lootTables = Map.of(
            "Deadmines", List.of("Cruel Barb", "Blackened Defias Armor"),
            "Ragefire Chasm", List.of("Cursed Felblade", "Ghostshard Talisman"),
            "Shadowfang Keep", List.of("Arugal's Robes", "Butcher's Cleaver")
    );

    public void distributeLoot(Party party, String dungeonName) {
        List<String> items = lootTables.getOrDefault(dungeonName, List.of());
        List<String> members = party.getMembers();

        for (String item : items) {
            String winner = members.get(random.nextInt(members.size()));
            System.out.println("[LootSystem] " + winner + " won the roll for " + item);
        }
    }
}

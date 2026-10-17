package wow.subsystem;

import java.util.Map;

public class Teleporter {
    private static final String HOME_CITY = "Stormwind";

    private final Map<String, String> dungeonLocations = Map.of(
            "Deadmines", "Westfall",
            "Ragefire Chasm", "Orgrimmar",
            "Shadowfang Keep", "Silverpine Forest"
    );

    public void teleportToDungeon(Party party, String dungeonName) {
        String location = dungeonLocations.get(dungeonName);
        if (location == null) {
            throw new IllegalArgumentException("Unknown dungeon: " + dungeonName);
        }
        System.out.println("[Teleporter] " + party.size() + " players teleported to "
                + dungeonName + " (" + location + ")");
    }

    public void returnToCity(Party party) {
        System.out.println("[Teleporter] " + party.size() + " players returned to " + HOME_CITY);
    }
}

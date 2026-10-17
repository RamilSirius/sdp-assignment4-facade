package wow.subsystem;

import java.util.HashMap;
import java.util.Map;

public class ExperienceService {
    private static final int XP_PER_LEVEL = 1000;

    private final Map<String, Integer> experienceByPlayer = new HashMap<>();

    public void grantExperience(Party party, int amount) {
        for (String player : party.getMembers()) {
            experienceByPlayer.merge(player, amount, Integer::sum);
        }
        System.out.println("[ExperienceService] Each player got " + amount + " XP");
    }

    public int getLevel(String playerName) {
        return 1 + experienceByPlayer.getOrDefault(playerName, 0) / XP_PER_LEVEL;
    }
}

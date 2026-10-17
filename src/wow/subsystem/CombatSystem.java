package wow.subsystem;

import java.util.Map;

public class CombatSystem {
    private static final int DAMAGE_PER_PLAYER = 25;
    private static final int MAX_ROUNDS = 3;

    private final Map<String, Integer> bossHealth = Map.of(
            "Deadmines", 300,
            "Ragefire Chasm", 200,
            "Shadowfang Keep", 500
    );

    public boolean fightBoss(Party party, String dungeonName) {
        Integer health = bossHealth.get(dungeonName);
        if (health == null) {
            throw new IllegalArgumentException("No boss in dungeon: " + dungeonName);
        }

        int damagePerRound = party.size() * DAMAGE_PER_PLAYER;
        for (int round = 1; round <= MAX_ROUNDS && health > 0; round++) {
            health = Math.max(0, health - damagePerRound);
            System.out.println("[CombatSystem] Round " + round + ": boss HP = " + health);
        }

        boolean bossDefeated = health == 0;
        System.out.println(bossDefeated ? "[CombatSystem] Boss defeated!" : "[CombatSystem] Party wiped...");
        return bossDefeated;
    }
}

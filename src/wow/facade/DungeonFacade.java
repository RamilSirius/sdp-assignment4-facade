package wow.facade;

import wow.subsystem.CombatSystem;
import wow.subsystem.ExperienceService;
import wow.subsystem.LootSystem;
import wow.subsystem.Party;
import wow.subsystem.PartyFinder;
import wow.subsystem.Teleporter;

public class DungeonFacade {
    private static final int DUNGEON_XP_REWARD = 1500;

    private final PartyFinder partyFinder = new PartyFinder();
    private final Teleporter teleporter = new Teleporter();
    private final CombatSystem combatSystem = new CombatSystem();
    private final LootSystem lootSystem = new LootSystem();
    private final ExperienceService experienceService = new ExperienceService();

    public boolean runDungeon(String playerName, String dungeonName) {
        System.out.println("=== " + playerName + " enters " + dungeonName + " ===");
        try {
            Party party = partyFinder.findParty(playerName);
            teleporter.teleportToDungeon(party, dungeonName);
            try {
                return clearDungeon(party, dungeonName);
            } finally {
                teleporter.returnToCity(party);
            }
        } catch (IllegalArgumentException | IllegalStateException e) {
            throw new DungeonRunException("Dungeon run failed: " + e.getMessage(), e);
        }
    }

    public int getPlayerLevel(String playerName) {
        return experienceService.getLevel(playerName);
    }

    private boolean clearDungeon(Party party, String dungeonName) {
        boolean bossDefeated = combatSystem.fightBoss(party, dungeonName);
        if (bossDefeated) {
            lootSystem.distributeLoot(party, dungeonName);
            experienceService.grantExperience(party, DUNGEON_XP_REWARD);
        }
        return bossDefeated;
    }
}

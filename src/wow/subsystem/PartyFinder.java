package wow.subsystem;

import java.util.List;

public class PartyFinder {
    private static final int PARTY_SIZE = 5;

    private final List<String> playersInQueue = List.of("Thrall", "Jaina", "Arthas", "Sylvanas", "Uther");

    public Party findParty(String leaderName) {
        Party party = new Party();
        party.addMember(leaderName);

        for (String player : playersInQueue) {
            if (party.size() == PARTY_SIZE) {
                break;
            }
            if (!player.equals(leaderName)) {
                party.addMember(player);
            }
        }

        if (party.size() < PARTY_SIZE) {
            throw new IllegalStateException("Not enough players in queue to form a party");
        }

        System.out.println("[PartyFinder] Party formed: " + party.getMembers());
        return party;
    }
}

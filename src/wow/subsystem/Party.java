package wow.subsystem;

import java.util.ArrayList;
import java.util.List;

public class Party {
    private final List<String> members = new ArrayList<>();

    public void addMember(String playerName) {
        members.add(playerName);
    }

    public List<String> getMembers() {
        return List.copyOf(members);
    }

    public int size() {
        return members.size();
    }
}

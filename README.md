# Assignment 4 — Facade Pattern (World of Warcraft)

A player wants to run a dungeon. Behind the scenes this needs a party finder,
a teleporter, a combat system, a loot system and an experience service working
in the correct order. `DungeonFacade` hides all of that behind one call:

```java
DungeonFacade dungeon = new DungeonFacade();
dungeon.runDungeon("Ramil", "Deadmines");
```

## Structure

```
src/wow/
├── Main.java                     // Client: uses only DungeonFacade
├── facade/
│   ├── DungeonFacade.java        // Facade
│   └── DungeonRunException.java  // Error returned to the client
└── subsystem/
    ├── Party.java                // Group of 5 players
    ├── PartyFinder.java          // Forms a party from the queue
    ├── Teleporter.java           // Moves the party to the dungeon and back
    ├── CombatSystem.java         // Fights the boss round by round
    ├── LootSystem.java           // Rolls loot between party members
    └── ExperienceService.java    // Grants XP and calculates levels
```

`runDungeon` order: find party → teleport → fight boss → (if won) loot + XP → return to city.

- Subsystem classes know nothing about the facade (no back-references).
- They are public and can still be used directly by an advanced client.
- The client (`Main`) never creates or calls subsystem classes.

## Run

```bash
javac -d out $(find src -name "*.java")
java -cp out wow.Main
```

## Clean Code principles

1. **Meaningful names** — `runDungeon`, `findParty`, `teleportToDungeon`, `fightBoss`,
   `distributeLoot`, `grantExperience`. The method names explain the code without comments.
2. **Single Responsibility** — each subsystem class does one job (party, teleport, combat,
   loot, XP). The facade only coordinates them and does not reimplement their logic.
3. **No leaking of subsystem details** — the facade API takes and returns only `String`,
   `boolean` and `int`. The client never sees `Party` or any subsystem class.
4. **Small, focused classes and methods** — every class is short; `runDungeon` delegates
   the fight/loot/XP part to the private `clearDungeon` method.
5. **Proper error handling** — subsystem errors (unknown dungeon, not enough players) are
   wrapped into one `DungeonRunException`. `finally` guarantees the party is always
   returned to the city, even after a wipe or an error.
6. **No magic numbers** — `PARTY_SIZE`, `MAX_ROUNDS`, `DAMAGE_PER_PLAYER`,
   `DUNGEON_XP_REWARD`, `XP_PER_LEVEL` are named constants.

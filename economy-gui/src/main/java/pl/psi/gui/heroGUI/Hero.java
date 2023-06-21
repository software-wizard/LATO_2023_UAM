package pl.psi.gui.heroGUI;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Hero {
    private String name;
    private List<Artifact> artifacts;
    private Map<String, Integer> stats;

    public Hero(String name) {
        this.name = name;
        this.artifacts = new ArrayList<>();
        this.stats = new HashMap<>();


        stats.put("attack", 10);
        stats.put("defense", 15);
        stats.put("knowledge", 3);
        stats.put("spell_power", 3);
        stats.put("morale", 1);
        stats.put("luck", 1);
    }

    public String getName() {
        return name;
    }

    public void addArtifact(Artifact artifact) {
        artifacts.add(artifact);
        applyArtifactEffects(artifact);
    }

    public void removeArtifact(Artifact artifact) {
        if (artifacts.remove(artifact)) {
            removeArtifactEffects(artifact);
        }
    }

    private void applyArtifactEffects(Artifact artifact) {
        for (Map.Entry<String, Integer> effect : artifact.getEffect().getAllEffects()) {
            stats.put(effect.getKey(), stats.getOrDefault(effect.getKey(), 0) + effect.getValue());
        }
    }

    private void removeArtifactEffects(Artifact artifact) {
        for (Map.Entry<String, Integer> effect : artifact.getEffect().getAllEffects()) {
            stats.put(effect.getKey(), stats.getOrDefault(effect.getKey(), 0) - effect.getValue());
        }
    }


    public int getStat(String statName) {
        return stats.getOrDefault(statName, 0);
    }

    public Set<Map.Entry<String, Integer>> getAllStats() {
        return new HashMap<>(stats).entrySet();
    }
}

package pl.psi.gui.heroGUI;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Effect {
    private Map<String, Integer> effects;

    public Effect() {
        effects = new HashMap<>();
    }

    public void addEffect(String effectName, int power) {
        effects.put(effectName, power);
    }

    public int getEffect(String effectName) {
        return effects.getOrDefault(effectName, 0);
    }

    public Set<Map.Entry<String, Integer>> getAllEffects() {
        return effects.entrySet();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, Integer> entry : effects.entrySet()) {
            sb.append(entry.getKey()).append(" +").append(entry.getValue()).append(", ");
        }
        return sb.toString();
    }
}

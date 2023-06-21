package pl.psi.gui.heroGUI;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class EffectTest {
    private Effect effect;

    @BeforeEach
    void setUp() {
        effect = new Effect();
        effect.addEffect("attack", 5);
        effect.addEffect("defense", 10);
    }

    @Test
    void addEffect() {
        effect.addEffect("spell_knowledge", 15);
        assertEquals(15, effect.getEffect("spell_knowledge"));
    }

    @Test
    void getEffect() {
        assertEquals(5, effect.getEffect("attack"));
        assertEquals(10, effect.getEffect("defense"));
        assertEquals(0, effect.getEffect("spell_knowledge"));  // This effect wasn't added yet
    }

    @Test
    void getAllEffects() {
        Map<String, Integer> expectedEffects = new HashMap<>();
        expectedEffects.put("attack", 5);
        expectedEffects.put("defense", 10);

        Set<Map.Entry<String, Integer>> actualEffects = effect.getAllEffects();


        assertEquals(expectedEffects.size(), actualEffects.size());

        for (Map.Entry<String, Integer> entry : actualEffects) {
            String effectName = entry.getKey();
            Integer effectValue = entry.getValue();


            assertTrue(expectedEffects.containsKey(effectName));
            assertEquals(expectedEffects.get(effectName), effectValue);
        }
    }


    @Test
    void testToString() {

        String expectedString1 = "attack +5, defense +10, ";
        String expectedString2 = "defense +10, attack +5, ";
        String actualString = effect.toString();

        assertTrue(actualString.equals(expectedString1) || actualString.equals(expectedString2));
    }
}

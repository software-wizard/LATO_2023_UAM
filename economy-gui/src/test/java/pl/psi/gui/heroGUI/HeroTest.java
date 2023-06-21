package pl.psi.gui.heroGUI;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class HeroTest {
    private Hero hero;
    private Artifact artifact;

    @BeforeEach
    void setUp() {
        hero = new Hero("Test Hero");

        Effect effect_apoh = new Effect();
        effect_apoh.addEffect("attack", 5);
        effect_apoh.addEffect("defense", 7);

        artifact = new Artifact("Test Artifact", "Test Artifact", effect_apoh, Artifact.Type.FEET, 500, Artifact.RareClass.RELIC);
    }

    @Test
    void addArtifact() {
        hero.addArtifact(artifact);
        assertEquals(15, hero.getStat("attack"));
        assertEquals(22, hero.getStat("defense"));
    }

    @Test
    void removeArtifact() {
        hero.addArtifact(artifact);
        hero.removeArtifact(artifact);
        assertEquals(10, hero.getStat("attack"));
        assertEquals(15, hero.getStat("defense"));
    }

    @Test
    void applyArtifactEffects() {
        hero.addArtifact(artifact);
        assertEquals(15, hero.getStat("attack"));
        assertEquals(22, hero.getStat("defense"));
    }

    @Test
    void removeArtifactEffects() {
        hero.addArtifact(artifact);
        hero.removeArtifact(artifact);
        assertEquals(10, hero.getStat("attack"));
        assertEquals(15, hero.getStat("defense"));
    }

    @Test
    void getStat() {
        assertEquals(10, hero.getStat("attack"));
        assertEquals(15, hero.getStat("defense"));
    }
}

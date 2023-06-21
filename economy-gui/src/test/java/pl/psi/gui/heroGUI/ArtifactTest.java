package pl.psi.gui.heroGUI;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ArtifactTest {
    private Artifact artifact;
    private Effect effect;

    @BeforeEach
    void setUp() {
        effect = new Effect();
        effect.addEffect("attack", 5);
        artifact = new Artifact("Test Artifact", "Full Test Artifact", effect, Artifact.Type.HEAD, 100, Artifact.RareClass.RELIC);
    }

    @Test
    void getName() {
        assertEquals("Test Artifact", artifact.getName());
    }

    @Test
    void getPrice() {
        assertEquals(100, artifact.getPrice());
    }
    @Test
    void getRareClass() {
        assertEquals(Artifact.RareClass.RELIC, artifact.getRareClass());
    }

    @Test
    void getFullName() {
        assertEquals("Full Test Artifact", artifact.getFullName());
    }

    @Test
    void getEffect() {
        assertEquals(effect, artifact.getEffect());
    }

    @Test
    void getType() {
        assertEquals(Artifact.Type.HEAD, artifact.getType());
    }


}

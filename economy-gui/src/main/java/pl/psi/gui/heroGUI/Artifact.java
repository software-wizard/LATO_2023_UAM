package pl.psi.gui.heroGUI;

public class Artifact {
    private String name;
    private String full_name;
    private Effect effect;
    private int price;
    private Type type;
    private RareClass rare_class; // New field for rare class

    public Artifact(String name, String full_name, Effect effect, Type type, int price, RareClass rare_class) {
        this.name = name;
        this.full_name = full_name;
        this.effect = effect;
        this.type = type;
        this.price = price;
        this.rare_class = rare_class;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public String getFullName() {
        return full_name;
    }

    public Effect getEffect() {
        return effect;
    }

    public Type getType() {
        return type;
    }

    public RareClass getRareClass() {
        return rare_class;
    }

    @Override
    public String toString() {
        return name + " (" + effect.toString() + ")";
    }

    public enum Type {
        HEAD, NECK, TORSO, SHOULDERS, RIGHT_HAND, LEFT_HAND, FINGERS, FEET, MISCELLANEOUS,
        BALLISTA, AMMO_CART, FIRST_AID_TENT, CATAPULT, SPELL_BOOK, BACKPACK
    }

    // Enum for rare class
    public enum RareClass {
        TREASURE, MINOR, MAJOR, RELIC, COMBINATION
    }
}

package pl.psi.creatures;

import com.google.common.collect.Range;

public class SpellProtection {
    private final int airProtection;
    private final int fireProtection;
    private final int earthProtection;
    private final int waterProtection;


    public SpellProtection(int airProtection, int fireProtection, int earthProtection, int waterProtection) {
        this.airProtection = airProtection;
        this.fireProtection = fireProtection;
        this.earthProtection = earthProtection;
        this.waterProtection = waterProtection;
    }

    public int getAirProtection() {
        return airProtection;
    }

    public int getFireProtection() {
        return fireProtection;
    }

    public int getEarthProtection() {
        return earthProtection;
    }

    public int getWaterProtection() {
        return waterProtection;
    }
    public int getProtectionByClass(int numberClass){
        if(numberClass == 0){
            return airProtection;
        }
        else if(numberClass == 1){
            return fireProtection;
        }
        else if(numberClass == 2){
            return earthProtection;
        }
        else{
            return waterProtection;
        }
    };

    @Override
    public String toString() {
        return "SpellProtection{" +
                "airProtection=" + airProtection +
                ", fireProtection=" + fireProtection +
                ", earthProtection=" + earthProtection +
                ", waterProtection=" + waterProtection +
                '}';
    }
    public static class spellProtectionBuilder {

        private int airProtection;
        private int fireProtection;
        private int earthProtection;
        private int waterProtection;
        public spellProtectionBuilder airProtection(final int airProtection) {
            this.airProtection = airProtection;
            return this;
        }

        public spellProtectionBuilder fireProtection(final int fireProtection) {
            this.fireProtection = fireProtection;
            return this;
        }
        public spellProtectionBuilder earthProtection(final int earthProtection) {
            this.earthProtection = earthProtection;
            return this;
        }
        public spellProtectionBuilder waterProtection(final int waterProtection) {
            this.waterProtection = waterProtection;
            return this;
        }
        public SpellProtection build() {
            return new SpellProtection(airProtection, fireProtection, earthProtection, waterProtection);
        }

    }

    public SpellProtection plus(SpellProtection protections) {
        return new spellProtectionBuilder()
                .airProtection(this.getAirProtection()+protections.getAirProtection())
                .fireProtection(this.getFireProtection()+protections.getFireProtection())
                .earthProtection(this.getEarthProtection()+protections.getEarthProtection())
                .waterProtection(this.getWaterProtection()+protections.getWaterProtection())
                .build();
    }

}

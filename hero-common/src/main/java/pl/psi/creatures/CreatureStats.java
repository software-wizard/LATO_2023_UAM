package pl.psi.creatures;

import com.google.common.collect.Range;

import lombok.Builder;
import lombok.Getter;

/**
 * TODO: Describe this class (The first line - until the first dot - will interpret as the brief description).
 */
@Getter
@Builder
public class CreatureStats implements CreatureStatisticIf{
    private final String name;
    private final int attack;
    private final int armor;
    private final int maxHp;
    private final int moveRange;
    private final Range< Integer > damage;
    private final int tier;
    private final SpellProtection spellDamageProtection;
    private final String description;
    private final boolean isUpgraded;

    @Override
    public CreatureStatisticIf plus(CreatureStatisticIf stats) {
        return CreatureStats.builder()
                .name(this.name)
                .attack(this.attack + stats.getAttack())
                .armor(this.armor + stats.getArmor())
                .maxHp(this.maxHp + stats.getMaxHp())
                .moveRange(this.moveRange + stats.getMoveRange())
                .damage(Range.closed(this.damage.lowerEndpoint() + stats.getDamage().lowerEndpoint(),
                        this.damage.upperEndpoint() + stats.getDamage().upperEndpoint()))
                .tier(this.tier)
                .spellDamageProtection(this.spellDamageProtection.plus(stats.getSpellDamageProtection()))
                .description(this.description)
                .isUpgraded(this.isUpgraded)
                .build();
    }
}

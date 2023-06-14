package pl.psi.hero;

import java.util.ArrayList;
import java.util.List;

import pl.psi.creatures.EconomyCreature;
import pl.psi.interfaces.SkillsInterface;

public class EconomyHero
{

    private final Fraction fraction;
    private final List< EconomyCreature > creatureList;
    private int gold;
    private final List<SkillsInterface> skillsList;


    public EconomyHero( final Fraction aFraction, final int aGold )
    {
        fraction = aFraction;
        gold = aGold;
        creatureList = new ArrayList<>();
        skillsList = new ArrayList<>();
    }

    void addCreature( final EconomyCreature aCreature )
    {
        if( creatureList.size() >= 7 )
        {
            throw new IllegalStateException( "Hero has not empty slot for creature" );
        }
        creatureList.add( aCreature );
    }

    public int getGold()
    {
        return gold;
    }

    public void addGold( final int aAmount )
    {
        gold += aAmount;
    }

    public List< EconomyCreature > getCreatures()
    {
        return List.copyOf( creatureList );
    }

    void substractGold( final int aAmount )
    {
        if( aAmount > gold )
        {
            throw new IllegalStateException( "Hero has not enought money" );
        }
        gold -= aAmount;
    }

    public enum Fraction
    {
        NECROPOLIS;
    }

    public List<SkillsInterface> getSkills() {

        return List.copyOf( skillsList );
    }

    void addSkill(final SkillsInterface skillsInterface) {

        if( skillsList.size() >= 28 )
        {
            throw new IllegalStateException( "Too many skills" );
        }
        skillsList.add( skillsInterface );
    }

}

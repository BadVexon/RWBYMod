package theVelvet.cards;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theVelvet.CardIgnore;
import theVelvet.RWBYMod;
import theVelvet.TheVelvet;

@CardIgnore
public class WeaponCard extends CustomCard {
    private static final int COST = -2;
    public String NAME;
    public String IMG;
    public String DESCRIPTION;

    public WeaponCard(String name, String IMG, String description) {
        super(makeID(name), name, IMG, COST, description, CardType.SKILL, TheVelvet.Enums.HUNTRESS_COLOR, CardRarity.SPECIAL, CardTarget.NONE);
        this.NAME = name;
        this.IMG = IMG;
        this.DESCRIPTION = description;
    }

    private static String makeID(String id) {
        return RWBYMod.makeID("WeaponCard" + id);
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {

    }

    @Override
    public void upgrade() {

    }

    @Override
    public AbstractCard makeCopy() {
        return new WeaponCard(NAME, IMG, DESCRIPTION);
    }
}
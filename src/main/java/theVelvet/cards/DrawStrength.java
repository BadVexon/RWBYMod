package theVelvet.cards;

import com.evacipated.cardcrawl.mod.stslib.fields.cards.AbstractCard.GraveField;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theVelvet.powers.DrawStrengthPower;

public class DrawStrength extends AbstractHadesCard {

    public final static String ID = makeID("DrawDamage");

    //stupid intellij stuff POWER, SELF, UNCOMMON

    private static final int MAGIC = 1;

    public DrawStrength() {
        super(ID, 2, CardType.POWER, CardRarity.RARE, CardTarget.SELF);
        baseMagicNumber = magicNumber = MAGIC;
        GraveField.grave.set(this, true);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new DrawStrengthPower(magicNumber));
    }

    public void baseUpgrade() {
        GraveField.grave.set(this, false);
        rawDescription = UPGRADE_DESCRIPTION;
        initializeDescription();
    }


    public void branchUpgrade() {
        upgradeBaseCost(1);
    }
}
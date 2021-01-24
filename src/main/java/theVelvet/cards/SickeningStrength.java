package theVelvet.cards;

import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theVelvet.cardmods.BloodcastModifier;
import theVelvet.powers.SickeningStrengthPower;

public class SickeningStrength extends AbstractHadesCard {

    public final static String ID = makeID("SickeningStrength");

    //stupid intellij stuff POWER, SELF, UNCOMMON

    private static final int MAGIC = 1;

    public SickeningStrength() {
        super(ID, 1, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = MAGIC;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new SickeningStrengthPower(magicNumber));
    }

    public void baseUpgrade() {

        isInnate = true;
        rawDescription = UPGRADE_DESCRIPTION;
        initializeDescription();
    }


    public void branchUpgrade() {

        CardModifierManager.addModifier(this, new BloodcastModifier());
    }
}
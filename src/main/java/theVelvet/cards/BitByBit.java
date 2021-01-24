package theVelvet.cards;

import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theVelvet.cardmods.BloodcastModifier;
import theVelvet.powers.BitByBitPower;

public class BitByBit extends AbstractHadesCard {

    public final static String ID = makeID("BitByBit");

    //stupid intellij stuff POWER, SELF, UNCOMMON

    private static final int MAGIC = 1;

    public BitByBit() {
        super(ID, 1, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = MAGIC;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new BitByBitPower(magicNumber));
        if (upgraded && isBranchUpgrade()) atb(new DrawCardAction(3));
    }

    public void baseUpgrade() {

        upgradeBaseCost(2);
        CardModifierManager.addModifier(this, new BloodcastModifier());
    }


    public void branchUpgrade() {

        rawDescription = UPGRADE_DESCRIPTION;
        initializeDescription();
    }
}

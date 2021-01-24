package theVelvet.cards;

import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theVelvet.cardmods.BloodcastModifier;
import theVelvet.cardmods.GoldcastModifier;
import theVelvet.powers.AlwaysShiftingPower;

public class FullCamera extends AbstractHadesCard {

    public final static String ID = makeID("BestLaidPlans");

    //stupid intellij stuff POWER, SELF, UNCOMMON

    public FullCamera() {
        super(ID, 3, CardType.POWER, CardRarity.RARE, CardTarget.SELF);
        isEthereal = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new AlwaysShiftingPower());
    }

    public void baseUpgrade() {
        upgradeBaseCost(4);
        CardModifierManager.addModifier(this, new GoldcastModifier());
    }


    public void branchUpgrade() {
        upgradeBaseCost(5);
        CardModifierManager.addModifier(this, new BloodcastModifier());
    }
}
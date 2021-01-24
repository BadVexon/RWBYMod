package theVelvet.cards;

import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theVelvet.actions.ChooseWeaponAction;
import theVelvet.cardmods.BloodcastModifier;
import theVelvet.powers.StrikeActivateWeaponPower;

public class SwapTrick extends AbstractHadesCard {

    public final static String ID = makeID("SwapTrick");

    //stupid intellij stuff SKILL, SELF, UNCOMMON

    public SwapTrick() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new ChooseWeaponAction());
        applyToSelf(new StrikeActivateWeaponPower(1));
    }

    public void baseUpgrade() {

        selfRetain = true;
        rawDescription = UPGRADE_DESCRIPTION;
        initializeDescription();
    }


    public void branchUpgrade() {

        CardModifierManager.addModifier(this, new BloodcastModifier());
    }
}
package theVelvet.cards;

import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theVelvet.actions.ChooseWeaponAction;
import theVelvet.cardmods.BloodcastModifier;

public class QuickActivate extends AbstractHadesCard {

    public final static String ID = makeID("QuickActivate");

    //stupid intellij stuff SKILL, SELF, UNCOMMON

    public QuickActivate() {
        super(ID, 2, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        CardModifierManager.addModifier(this, new BloodcastModifier());
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        if (upgraded && isBranchUpgrade())
            atb(new ChooseWeaponAction());
        awep();
    }

    public void baseUpgrade() {

        upgradeBaseCost(1);
    }


    public void branchUpgrade() {

        rawDescription = UPGRADE_DESCRIPTION;
        initializeDescription();
    }
}
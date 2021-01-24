package theVelvet.cards;

import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theVelvet.cardmods.GoldcastModifier;
import theVelvet.powers.SelfieFormPower;

public class SelfieForm extends AbstractHadesCard {

    public final static String ID = makeID("SelfieForm");

    //stupid intellij stuff POWER, SELF, RARE

    public SelfieForm() {
        super(ID, 2, CardType.POWER, CardRarity.RARE, CardTarget.SELF);
        cardsToPreview = new Camera();
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new SelfieFormPower());
    }

    public void baseUpgrade() {

        selfRetain = true;
        rawDescription = UPGRADE_DESCRIPTION;
        initializeDescription();
    }


    public void branchUpgrade() {

        CardModifierManager.addModifier(this, new GoldcastModifier());
        upgradeBaseCost(3);
    }
}
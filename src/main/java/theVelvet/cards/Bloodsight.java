package theVelvet.cards;

import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.tempCards.Insight;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theVelvet.cardmods.BloodcastModifier;

public class Bloodsight extends AbstractHadesCard {

    public final static String ID = makeID("Bloodsight");

    //stupid intellij stuff SKILL, SELF, UNCOMMON

    public Bloodsight() {
        super(ID, 2, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        cardsToPreview = new Insight();
        CardModifierManager.addModifier(this, new BloodcastModifier());
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new GainEnergyAction(1));
        AbstractCard q = new Insight();
        if (upgraded && isBranchUpgrade())
            q.upgrade();
        topDeck(q);
    }

    public void baseUpgrade() {

        upgradeBaseCost(1);
    }


    public void branchUpgrade() {

        AbstractCard q = new Insight();
        q.upgrade();
        cardsToPreview = q;
        rawDescription = UPGRADE_DESCRIPTION;
        initializeDescription();
    }
}

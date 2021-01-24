package theVelvet.cards;

import basemod.helpers.CardModifierManager;
import com.evacipated.cardcrawl.mod.stslib.cards.interfaces.BranchingUpgradesCard;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theVelvet.cardmods.GoldcastModifier;

public class Snapshoot extends AbstractHadesCard implements BranchingUpgradesCard {

    public final static String ID = makeID("Snapshoot");

    //stupid intellij stuff SKILL, SELF, COMMON

    public Snapshoot() {
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        cardsToPreview = new Camera();
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractCard q = new Camera();
        if (upgraded && isBranchUpgrade())
            q.upgrade();
        makeInHand(q);
        awep();
    }

    public void baseUpgrade() {

        CardModifierManager.addModifier(this, new GoldcastModifier());
    }


    public void branchUpgrade() {

        AbstractCard q = new Camera();
        q.upgrade();
        cardsToPreview = q;
        rawDescription = UPGRADE_DESCRIPTION;
        initializeDescription();
    }
}
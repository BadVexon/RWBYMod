package theVelvet.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theVelvet.powers.BestAnglePower;

public class BestAngle extends AbstractHadesCard {

    public final static String ID = makeID("BestAngle");

    //stupid intellij stuff SKILL, SELF, RARE

    public BestAngle() {
        super(ID, 1, CardType.SKILL, CardRarity.RARE, CardTarget.SELF);
        cardsToPreview = new Camera();
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new BestAnglePower(1));
        if (upgraded && isBranchUpgrade()) makeInHand(new Camera());
    }

    public void baseUpgrade() {

        selfRetain = true;
        rawDescription = UPGRADE_DESCRIPTION;
        initializeDescription();
    }


    public void branchUpgrade() {

        rawDescription = EXTENDED_DESCRIPTION[0];
        initializeDescription();
    }
}

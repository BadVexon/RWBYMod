package theVelvet.cards;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theVelvet.powers.CameraSpammaPower;

public class CameraSpamma extends AbstractHadesCard {

    public final static String ID = makeID("CameraSpamma");

    //stupid intellij stuff POWER, SELF, UNCOMMON

    public CameraSpamma() {
        super(ID, 1, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF);
        cardsToPreview = new Camera();
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractCard q = new Camera();
        if (upgraded && isBranchUpgrade()) {
            q.upgrade();
        }
        applyToSelf(new CameraSpammaPower(q, 1));
    }

    public void baseUpgrade() {

        isInnate = true;
        rawDescription = UPGRADE_DESCRIPTION;
        initializeDescription();
    }


    public void branchUpgrade() {

        AbstractCard q = new Camera();
        q.upgrade();
        cardsToPreview = q;
        rawDescription = EXTENDED_DESCRIPTION[0];
        initializeDescription();
    }
}
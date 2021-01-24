package theVelvet.cards;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class CameraCamera extends AbstractHadesCard {

    public final static String ID = makeID("CameraCamera");

    //stupid intellij stuff SKILL, SELF, UNCOMMON

    private static final int MAGIC = 2;
    private static final int UPG_MAGIC = 1;

    public CameraCamera() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = MAGIC;
        cardsToPreview = new Camera();
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractCard q = new Camera();
        if (upgraded && isBranchUpgrade())
            q.upgrade();
        makeInHand(q, magicNumber);
    }

    public void baseUpgrade() {
        upgradeMagicNumber(UPG_MAGIC);
    }


    public void branchUpgrade() {
        AbstractCard q = new Camera();
        q.upgrade();
        cardsToPreview = q;
        rawDescription = UPGRADE_DESCRIPTION;
        initializeDescription();
    }

}
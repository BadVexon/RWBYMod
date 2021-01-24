package theVelvet.cards;

import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theVelvet.cardmods.BloodcastModifier;

public class CarePackage extends AbstractHadesCard {

    public final static String ID = makeID("CarePackage");

    //stupid intellij stuff SKILL, ENEMY, UNCOMMOn

    private static final int MAGIC = 1;
    private static final int UPG_MAGIC = 1;

    public CarePackage() {
        super(ID, 2, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseMagicNumber = magicNumber = MAGIC;
        baseSilly = silly = 1;
        cardsToPreview = new Camera();
        CardModifierManager.addModifier(this, new BloodcastModifier());
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToEnemy(m, autoWeak(m, magicNumber));
        applyToEnemy(m, autoVuln(m, magicNumber));
        makeInHand(new Camera(), silly);
    }

    public void baseUpgrade() {

        upgradeSilly(1);
        rawDescription = UPGRADE_DESCRIPTION;
        initializeDescription();
    }


    public void branchUpgrade() {

        upgradeMagicNumber(UPG_MAGIC);
    }
}
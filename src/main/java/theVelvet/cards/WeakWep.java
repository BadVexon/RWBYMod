package theVelvet.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class WeakWep extends AbstractHadesCard {

    public final static String ID = makeID("WeakWep");

    //stupid intellij stuff SKILL, ENEMY, COMMON

    private static final int MAGIC = 1;
    private static final int UPG_MAGIC = 1;

    public WeakWep() {
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.ENEMY);
        baseMagicNumber = magicNumber = MAGIC;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToEnemy(m, autoWeak(m, magicNumber));
        if (upgraded && isBranchUpgrade())
            applyToEnemy(m, autoVuln(m, 1));
        awep();
    }

    public void baseUpgrade() {

        upgradeMagicNumber(UPG_MAGIC);
    }


    public void branchUpgrade() {

        rawDescription = UPGRADE_DESCRIPTION;
        initializeDescription();
    }
}
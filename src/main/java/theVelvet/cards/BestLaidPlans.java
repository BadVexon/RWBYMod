package theVelvet.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.RetainCardPower;

public class BestLaidPlans extends AbstractHadesCard {

    public final static String ID = makeID("BestLaidPlans");

    //stupid intellij stuff POWER, SELF, UNCOMMON

    private static final int MAGIC = 2;

    public BestLaidPlans() {
        super(ID, 1, CardType.POWER, CardRarity.RARE, CardTarget.SELF);
        baseMagicNumber = magicNumber = MAGIC;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new RetainCardPower(p, magicNumber));
    }

    public void baseUpgrade() {
        upgradeMagicNumber(1);
    }


    public void branchUpgrade() {
        isInnate = true;
        rawDescription = UPGRADE_DESCRIPTION;
        initializeDescription();
    }
}
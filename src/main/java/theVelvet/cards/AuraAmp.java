package theVelvet.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theVelvet.powers.AuraAmpPower;

public class AuraAmp extends AbstractHadesCard {

    public final static String ID = makeID("AuraAmp");

    //stupid intellij stuff POWER, SELF, UNCOMMON

    private static final int MAGIC = 25;
    private static final int UPG_MAGIC = 8;

    public AuraAmp() {
        super(ID, 1, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = MAGIC;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new AuraAmpPower(magicNumber));
    }

    public void baseUpgrade() {

        isInnate = true;
        rawDescription = UPGRADE_DESCRIPTION;
        initializeDescription();
    }


    public void branchUpgrade() {

        upgradeMagicNumber(UPG_MAGIC);
    }
}

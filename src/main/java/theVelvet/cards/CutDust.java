package theVelvet.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theVelvet.powers.StrikeDrawCardPower;
import theVelvet.powers.StrikeGainEnergyPower;

public class CutDust extends AbstractHadesCard {

    public final static String ID = makeID("CutDust");

    //stupid intellij stuff SKILL, SELF, COMMON

    private static final int MAGIC = 1;

    public CutDust() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = MAGIC;
        baseSilly = silly = 2;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new StrikeGainEnergyPower(magicNumber));
        applyToSelf(new StrikeDrawCardPower(silly));
    }

    public void baseUpgrade() {

        upgradeSilly(1);
    }


    public void branchUpgrade() {

        upgradeMagicNumber(1);
        rawDescription = UPGRADE_DESCRIPTION;
        initializeDescription();
    }
}
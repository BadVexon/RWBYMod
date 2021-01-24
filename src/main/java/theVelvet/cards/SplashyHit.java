package theVelvet.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theVelvet.powers.StrikeApplyWeakPower;

public class SplashyHit extends AbstractHadesCard {

    public final static String ID = makeID("SplashyHit");

    //stupid intellij stuff SKILL, SELF_AND_ENEMY, UNCOMMON

    private static final int MAGIC = 1;
    private static final int UPG_MAGIC = 1;

    public SplashyHit() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseMagicNumber = magicNumber = MAGIC;
        baseSilly = silly = 2;
        exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToEnemy(m, autoVuln(m, magicNumber));
        applyToSelf(new StrikeApplyWeakPower(silly));
    }

    public void baseUpgrade() {

        upgradeSilly(1);
    }


    public void branchUpgrade() {

        upgradeMagicNumber(UPG_MAGIC);
    }
}
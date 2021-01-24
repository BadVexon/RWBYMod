package theVelvet.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theVelvet.powers.StrikeExtraDamagePower;

public class PrepareAttack extends AbstractHadesCard {

    public final static String ID = makeID("PrepareAttack");

    //stupid intellij stuff SKILL, ENEMY, COMMON

    private static final int MAGIC = 1;

    public PrepareAttack() {
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.ENEMY);
        baseMagicNumber = magicNumber = MAGIC;
        baseSilly = silly = 5;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToEnemy(m, autoVuln(m, magicNumber));
        applyToSelf(new StrikeExtraDamagePower(silly));
    }

    public void baseUpgrade() {

        upgradeSilly(3);
    }


    public void branchUpgrade() {

        upgradeMagicNumber(1);
    }
}
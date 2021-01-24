package theVelvet.cards;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class FierceCry extends AbstractHadesCard {

    public final static String ID = makeID("FierceCry");

    //stupid intellij stuff SKILL, ENEMY, COMMON

    private static final int MAGIC = 1;
    private static final int UPG_MAGIC = 1;

    public FierceCry() {
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.ENEMY);
        baseMagicNumber = magicNumber = MAGIC;
        baseSilly = silly = MAGIC;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToEnemy(m, autoWeak(m, magicNumber));
        applyToEnemy(m, autoVuln(m, silly));
        atb(new DrawCardAction(1));
    }

    public void baseUpgrade() {
        upgradeMagicNumber(UPG_MAGIC);

    }


    public void branchUpgrade() {
        upgradeSilly(1);
    }
}
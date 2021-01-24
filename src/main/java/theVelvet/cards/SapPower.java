package theVelvet.cards;

import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.PoisonPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import theVelvet.cardmods.BloodcastModifier;

public class SapPower extends AbstractHadesCard {

    public final static String ID = makeID("SapPower");

    //stupid intellij stuff SKILL, ENEMY, RARE

    private static final int MAGIC = 3;
    private static final int UPG_MAGIC = 1;

    public SapPower() {
        super(ID, 2, CardType.SKILL, CardRarity.RARE, CardTarget.ENEMY);
        baseMagicNumber = magicNumber = MAGIC;
        exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        if (m.hasPower(StrengthPower.POWER_ID)) {
            AbstractPower q = m.getPower(StrengthPower.POWER_ID);
            int x = q.amount;
            if (x >= magicNumber) x = magicNumber;
            atb(new ReducePowerAction(m, p, StrengthPower.POWER_ID, x));
            applyToSelf(new StrengthPower(p, x));
        }
        applyToEnemy(m, autoWeak(m, magicNumber));
        applyToEnemy(m, autoVuln(m, magicNumber));
        applyToEnemy(m, new PoisonPower(m, p, magicNumber));
    }

    public void baseUpgrade() {

        upgradeMagicNumber(UPG_MAGIC);
    }


    public void branchUpgrade() {

        upgradeBaseCost(4);
        CardModifierManager.addModifier(this, new BloodcastModifier());
    }
}
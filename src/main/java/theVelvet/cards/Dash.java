package theVelvet.cards;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theVelvet.powers.StrikeApplyPesteredPower;
import theVelvet.powers.StrikeApplyVulnerablePower;

public class Dash extends AbstractHadesCard {

    public final static String ID = makeID("Dash");

    //stupid intellij stuff SKILL, SELF, BASIC

    private static final int MAGIC = 2;
    private static final int UPG_MAGIC = 1;

    public Dash() {
        super(ID, 1, CardType.SKILL, CardRarity.BASIC, CardTarget.SELF);
        baseMagicNumber = magicNumber = MAGIC;
        baseSilly = silly = 1;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new DrawCardAction(p, magicNumber));
        applyToSelf(new StrikeApplyVulnerablePower(silly));
    }

    public void baseUpgrade() {
        upgradeMagicNumber(UPG_MAGIC);
    }

    public void branchUpgrade() {
        upgradeSilly(1);
    }
}
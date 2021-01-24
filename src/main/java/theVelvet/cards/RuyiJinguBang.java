package theVelvet.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class RuyiJinguBang extends AbstractHadesCard {

    public final static String ID = makeID("RuyiJinguBang");

    //stupid intellij stuff ATTACK, ENEMY, BASIC

    private static final int DAMAGE = 9;

    public RuyiJinguBang() {
        super(ID, 2, CardType.ATTACK, CardRarity.BASIC, CardTarget.ENEMY);
        baseDamage = DAMAGE;
        exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.FIRE);
        awep();
    }

    public void baseUpgrade() {

        exhaust = false;
        rawDescription = UPGRADE_DESCRIPTION;
        initializeDescription();
    }


    public void branchUpgrade() {

        upgradeBaseCost(1);
        upgradeDamage(3);
    }
}
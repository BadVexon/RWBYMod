package theVelvet.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theVelvet.powers.BarrageStrikePower;

public class BarrageStrike extends AbstractHadesCard {

    public final static String ID = makeID("BarrageStrike");

    //stupid intellij stuff ATTACK, ENEMY, RARE

    private static final int DAMAGE = 6;
    private static final int UPG_DAMAGE2 = 3;

    public BarrageStrike() {
        super(ID, 1, CardType.ATTACK, CardRarity.RARE, CardTarget.ENEMY);
        baseDamage = DAMAGE;
        exhaust = true;
        isEthereal = true;
        tags.add(CardTags.STRIKE);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.SMASH);
        applyToSelf(new BarrageStrikePower(this));
    }

    public void baseUpgrade() {
        isEthereal = false;
        selfRetain = true;
        rawDescription = UPGRADE_DESCRIPTION;
        initializeDescription();
    }


    public void branchUpgrade() {
        upgradeDamage(UPG_DAMAGE2);
    }
}
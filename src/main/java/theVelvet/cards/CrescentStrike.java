package theVelvet.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class CrescentStrike extends AbstractHadesCard {

    public final static String ID = makeID("CrescentStrike");

    //stupid intellij stuff ATTACK, ENEMY, COMMON

    private static final int DAMAGE = 3;
    private static final int UPG_DAMAGE = 3;

    public CrescentStrike() {
        super(ID, 1, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        baseDamage = DAMAGE;
        baseBlock = 3;
        tags.add(CardTags.STRIKE);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
        dmg(m, AbstractGameAction.AttackEffect.SLASH_VERTICAL);
        awep();
    }

    public void baseUpgrade() {
        upgradeBlock(2);
    }

    public void branchUpgrade() {
        upgradeDamage(UPG_DAMAGE);
    }
}
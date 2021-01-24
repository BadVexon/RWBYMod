package theVelvet.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.RemoveAllBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class ExposeShot extends AbstractHadesCard {

    public final static String ID = makeID("ExposeShot");

    //stupid intellij stuff ATTACK, ENEMY, UNCOMMON

    private static final int DAMAGE = 8;


    public ExposeShot() {
        super(ID, 1, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseDamage = DAMAGE;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        int x = m.currentBlock;
        atb(new RemoveAllBlockAction(m, p));
        if (upgraded && isBranchUpgrade() && x > 0)
            atb(new GainBlockAction(p, x));
        dmg(m, AbstractGameAction.AttackEffect.SLASH_VERTICAL);
    }

    public void baseUpgrade() {
        selfRetain = true;
        rawDescription = UPGRADE_DESCRIPTION;
        initializeDescription();
    }


    public void branchUpgrade() {

        rawDescription = EXTENDED_DESCRIPTION[0];
        initializeDescription();
    }
}
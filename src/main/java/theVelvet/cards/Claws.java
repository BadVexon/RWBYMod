package theVelvet.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.tempCards.Insight;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Claws extends AbstractHadesCard {

    public final static String ID = makeID("Claws");

    //stupid intellij stuff ATTACK, ENEMY, COMMON

    private static final int DAMAGE = 8;
    private static final int UPG_DAMAGE = 3;

    public Claws() {
        super(ID, 1, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        baseDamage = DAMAGE;
        cardsToPreview = new Insight();
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.FIRE);
        AbstractCard q = new Insight();
        if (upgraded && isBranchUpgrade())
            q.upgrade();
        topDeck(q);
    }

    public void baseUpgrade() {

        upgradeDamage(UPG_DAMAGE);
    }


    public void branchUpgrade() {

        AbstractCard q = new Insight();
        q.upgrade();
        cardsToPreview = q;
        rawDescription = UPGRADE_DESCRIPTION;
        initializeDescription();
    }
}
package theVelvet.cards;

import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.WeightyImpactEffect;
import theVelvet.actions.AlistarAction;

public class StrengthOfAlistar extends AbstractHadesCard {

    public final static String ID = makeID("StrengthOfAlistar");

    //stupid intellij stuff ATTACK, ENEMY, RARE

    private static final int DAMAGE = 12;
    private static final int UPG_DAMAGE = 3;

    public StrengthOfAlistar() {
        super(ID, 1, CardType.ATTACK, CardRarity.RARE, CardTarget.ENEMY);
        baseDamage = DAMAGE;
        cardsToPreview = new Camera();
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        if (m != null) {// 35
            this.addToBot(new VFXAction(new WeightyImpactEffect(m.hb.cX, m.hb.cY)));// 36
            this.addToBot(new WaitAction(0.8F));// 37
        }
        atb(new AlistarAction(m, makeInfo(), (upgraded && isBranchUpgrade())));
    }

    public void baseUpgrade() {

        upgradeDamage(UPG_DAMAGE);
    }


    public void branchUpgrade() {

        AbstractCard q = new Camera();
        q.upgrade();
        cardsToPreview = q;
        rawDescription = UPGRADE_DESCRIPTION;
        initializeDescription();
    }
}
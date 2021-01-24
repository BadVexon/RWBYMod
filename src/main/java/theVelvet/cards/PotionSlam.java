package theVelvet.cards;

import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theVelvet.cardmods.RetaincastModifier;

public class PotionSlam extends AbstractHadesCard {

    public final static String ID = makeID("PotionSlam");

    //stupid intellij stuff ATTACK, ENEMY, RARE

    private static final int DAMAGE = 50;
    private static final int UPG_DAMAGE = 20;

    public PotionSlam() {
        super(ID, 6, CardType.ATTACK, CardRarity.RARE, CardTarget.ENEMY);
        baseDamage = DAMAGE;
        CardModifierManager.addModifier(this, new RetaincastModifier());
        exhaust = true;
        isEthereal = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.BLUNT_HEAVY);
    }

    public void baseUpgrade() {
        upgradeDamage(UPG_DAMAGE);
    }

    public void branchUpgrade() {
        upgradeBaseCost(5);
    }
}
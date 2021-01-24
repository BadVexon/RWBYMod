package theVelvet.cards;

import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theVelvet.cardmods.BloodcastModifier;

public class CerberusStrike extends AbstractHadesCard {

    public final static String ID = makeID("CerberusStrike");

    //stupid intellij stuff ATTACK, ENEMY, COMMON

    private static final int DAMAGE = 10;
    private static final int UPG_DAMAGE = 2;

    public CerberusStrike() {
        super(ID, 3, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        baseDamage = DAMAGE;
        CardModifierManager.addModifier(this, new BloodcastModifier());
        tags.add(CardTags.STRIKE);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.FIRE);
    }

    public void baseUpgrade() {

        upgradeDamage(UPG_DAMAGE);
    }


    public void branchUpgrade() {

        upgradeBaseCost(2);
    }
}
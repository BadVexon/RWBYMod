package theVelvet.cards;

import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theVelvet.actions.ChooseWeaponAction;
import theVelvet.cardmods.BloodcastModifier;

public class SwapStrike extends AbstractHadesCard {

    public final static String ID = makeID("SwapStrike");

    //stupid intellij stuff ATTACK, ENEMY, BASIC

    private static final int DAMAGE = 6;
    private static final int UPG_DAMAGE = 3;

    public SwapStrike() {
        super(ID, 1, CardType.ATTACK, CardRarity.BASIC, CardTarget.ENEMY);
        baseDamage = DAMAGE;
        tags.add(CardTags.STRIKE);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.SLASH_HORIZONTAL);
        atb(new ChooseWeaponAction());
    }

    public void baseUpgrade() {
        upgradeDamage(UPG_DAMAGE);
    }


    @Override
    public void branchUpgrade() {
        upgradeDamage(-1);
        CardModifierManager.addModifier(this, new BloodcastModifier());
    }
}
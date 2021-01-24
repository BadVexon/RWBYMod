package theVelvet.cards;

import basemod.helpers.CardModifierManager;
import com.evacipated.cardcrawl.mod.stslib.cards.interfaces.BranchingUpgradesCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theVelvet.cardmods.RetaincastModifier;

public class Flamesmash extends AbstractHadesCard implements BranchingUpgradesCard {

    public final static String ID = makeID("Flamesmash");

    //stupid intellij stuff ATTACK, ENEMY, UNCOMMON

    private static final int DAMAGE = 18;
    private static final int UPG_DAMAGE = 6;

    public Flamesmash() {
        super(ID, 3, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseDamage = DAMAGE;
        CardModifierManager.addModifier(this, new RetaincastModifier());
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.BLUNT_HEAVY);
    }

    public void baseUpgrade() {
        upgradeDamage(UPG_DAMAGE);
    }


    public void branchUpgrade() {
        upgradeBaseCost(2);
    }
}
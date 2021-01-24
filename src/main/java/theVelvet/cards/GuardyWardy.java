package theVelvet.cards;

import basemod.helpers.CardModifierManager;
import com.evacipated.cardcrawl.mod.stslib.actions.tempHp.AddTemporaryHPAction;
import com.evacipated.cardcrawl.mod.stslib.cards.interfaces.BranchingUpgradesCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import theVelvet.cardmods.GoldcastModifier;

public class GuardyWardy extends AbstractHadesCard implements BranchingUpgradesCard {

    public final static String ID = makeID("GuardyWardy");

    //stupid intellij stuff SKILL, SELF_AND_ENEMY, UNCOMMON

    private static final int BLOCK = 10;
    private static final int UPG_BLOCK = 4;

    public GuardyWardy() {
        super(ID, 2, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseBlock = BLOCK;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
        int x = 0;
        for (AbstractPower q : m.powers) {
            if (q.type == AbstractPower.PowerType.DEBUFF)
                x += q.amount;
        }
        atb(new AddTemporaryHPAction(p, p, x));
    }

    public void baseUpgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeBlock(UPG_BLOCK);
        }
    }

    public void branchUpgrade() {
        if (!upgraded) {
            upgradeName();
            CardModifierManager.addModifier(this, new GoldcastModifier());
        }
    }
}
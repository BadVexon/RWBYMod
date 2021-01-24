package theVelvet.cardmods;

import basemod.abstracts.AbstractCardModifier;
import basemod.interfaces.AlternateCardCostModifier;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class BloodcastModifier extends AbstractCardModifier implements AlternateCardCostModifier {

    @Override
    public String modifyDescription(String rawDescription, AbstractCard card) {
        return "hadesmod:Bloodcast. NL " + rawDescription;
    }

    @Override
    public boolean isInherent(AbstractCard card) {
        return true;
    }

    @Override
    public int getAlternateResource(AbstractCard card) {
        return AbstractDungeon.player.currentHealth - 1;
    }

    @Override
    public boolean prioritizeAlternateCost(AbstractCard card) {
        return false;
    }

    @Override
    public boolean canSplitCost(AbstractCard card) {
        return true;
    }

    @Override
    public int spendAlternateCost(AbstractCard card, int costToSpend) {
        AbstractDungeon.player.damage(new DamageInfo(null, costToSpend, DamageInfo.DamageType.HP_LOSS));
        int x = costToSpend - AbstractDungeon.player.currentHealth - 1;
        if (x < 0) x = 0;
        return x;
    }

    @Override
    public AbstractCardModifier makeCopy() {
        return new BloodcastModifier();
    }
}

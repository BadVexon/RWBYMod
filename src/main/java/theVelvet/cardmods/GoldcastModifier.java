package theVelvet.cardmods;

import basemod.abstracts.AbstractCardModifier;
import basemod.interfaces.AlternateCardCostModifier;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class GoldcastModifier extends AbstractCardModifier implements AlternateCardCostModifier {

    @Override
    public String modifyDescription(String rawDescription, AbstractCard card) {
        return "hadesmod:Goldcast. NL " + rawDescription;
    }

    @Override
    public int getAlternateResource(AbstractCard card) {
        int y = (AbstractDungeon.player.gold / 15) * 10;
        return y / 10;
    }

    @Override
    public boolean canSplitCost(AbstractCard card) {
        return true;
    }

    @Override
    public int spendAlternateCost(AbstractCard card, int costToSpend) {
        AbstractDungeon.player.loseGold(costToSpend * 10);
        int y = (AbstractDungeon.player.gold / 10) * 10;
        int x = costToSpend - y;
        if (x < 0) x = 0;
        return x;
    }

    @Override
    public AbstractCardModifier makeCopy() {
        return new GoldcastModifier();
    }
}

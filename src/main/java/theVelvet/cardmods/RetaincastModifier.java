package theVelvet.cardmods;

import basemod.abstracts.AbstractCardModifier;
import basemod.interfaces.AlternateCardCostModifier;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class RetaincastModifier extends AbstractCardModifier implements AlternateCardCostModifier  {

    public RetaincastModifier() {
        this.priority = -999;
    }

    @Override
    public String modifyDescription(String rawDescription, AbstractCard card) {
        return "hadesmod:Retaincast. NL " + rawDescription;
    }

    @Override
    public boolean canPlayCard(AbstractCard card) {
        card.cantUseMessage = "I don't have enough Retain cards!";
        return AbstractDungeon.player.hand.group.stream().filter(c -> c.selfRetain).count() >= card.costForTurn;
    }

    @Override
    public boolean isInherent(AbstractCard card) {
        return true;
    }

    @Override
    public int getAlternateResource(AbstractCard card) {
        int x = 0;
        for (AbstractCard q : AbstractDungeon.player.hand.group) {
            if (q.selfRetain)
                x++;
        }
        return x;
    }

    @Override
    public boolean prioritizeAlternateCost(AbstractCard card) {
        return true;
    }

    @Override
    public boolean canSplitCost(AbstractCard card) {
        return false;
    }

    @Override
    public int spendAlternateCost(AbstractCard card, int costToSpend) {
        int x = costToSpend;
        int i = 0;
        while (x > 0) {
            AbstractCard q = AbstractDungeon.player.hand.group.get(i);
            if (q.selfRetain) {
                AbstractDungeon.player.hand.moveToExhaustPile(q);// 33
                q.exhaustOnUseOnce = false;// 35
                q.freeToPlayOnce = false;// 36
                x--;
            } else i++;
        }
        return 0;
    }

    @Override
    public AbstractCardModifier makeCopy() {
        return new RetaincastModifier();
    }
}

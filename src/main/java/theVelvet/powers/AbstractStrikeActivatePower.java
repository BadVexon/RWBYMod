package theVelvet.powers;

import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.powers.AbstractPower;

public abstract class AbstractStrikeActivatePower extends AbstractPower {

    @Override
    public void onUseCard(AbstractCard card, UseCardAction action) {
        if (card.hasTag(AbstractCard.CardTags.STRIKE) && !card.purgeOnUse) {
            flash();
            onUseStrike(card, action);
            addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, this.ID));// 49
        }
    }

    public abstract void onUseStrike(AbstractCard card, UseCardAction action);
}

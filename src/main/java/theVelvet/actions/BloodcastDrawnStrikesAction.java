package theVelvet.actions;

import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import theVelvet.cardmods.BloodcastThisTurnModifier;

public class BloodcastDrawnStrikesAction extends AbstractGameAction {

    public BloodcastDrawnStrikesAction() {
        this.duration = 0.0F;// 15
        this.actionType = ActionType.WAIT;// 16
    }// 18

    public void update() {
        for (AbstractCard c : DrawCardAction.drawnCards) {
            if (c.hasTag(AbstractCard.CardTags.STRIKE)) {// 23
                CardModifierManager.addModifier(c, new BloodcastThisTurnModifier());
                c.superFlash();
            }
        }
        this.isDone = true;// 29
    }// 30
}

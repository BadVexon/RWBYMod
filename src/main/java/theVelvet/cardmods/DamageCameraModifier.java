package theVelvet.cardmods;

import basemod.abstracts.AbstractCardModifier;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class DamageCameraModifier extends AbstractCardModifier {

    private boolean attackified = false;
    private boolean targetChanged = false;
    private AbstractCard.CardTarget oldTarget = null;
    private AbstractCard.CardType oldType = null;
    private int bonusDamage;
    private String oldName = null;

    public DamageCameraModifier(int bonusDamage) {
        this.bonusDamage = bonusDamage;
    }

    @Override
    public void onUse(AbstractCard card, AbstractCreature m, UseCardAction action) {
        if (attackified && m != null) {
            AbstractDungeon.actionManager.addToTop(new DamageAction(m, new DamageInfo(m, card.damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.FIRE));
        }
    }

    @Override
    public String modifyDescription(String rawDescription, AbstractCard card) {
        if (attackified) {
            if (rawDescription.contains("Retain.")) {
                return rawDescription.replaceAll("Retain. NL ", "Retain. NL Deal !D! damage. NL ");
            } else
                return "Deal !D! damage. NL " + rawDescription;
        }
        return rawDescription;
    }

    @Override
    public void onInitialApplication(AbstractCard card) {
        card.name = "Gun " + card.name;
        if (card.type != AbstractCard.CardType.ATTACK) {
            oldType = card.type;
            card.type = AbstractCard.CardType.ATTACK;
            attackified = true;
            //TODO: crop art here
        }
        if (card.target != AbstractCard.CardTarget.ENEMY) {
            oldTarget = card.target;
            card.target = AbstractCard.CardTarget.ENEMY;
            targetChanged = true;
        }
        if (card.baseDamage == -1) card.baseDamage = bonusDamage;
    }

    @Override
    public void onRemove(AbstractCard card) {
        if (attackified) {
            card.type = oldType;
            oldType = null;
            attackified = false;
        }
        if (targetChanged) {
            card.target = oldTarget;
            oldTarget = null;
            targetChanged = false;
        }
        card.name = oldName;
        card.baseDamage = -1;
    }

    @Override
    public AbstractCardModifier makeCopy() {
        return new DamageCameraModifier(bonusDamage);
    }
}

package theVelvet.cardmods;

import basemod.abstracts.AbstractCardModifier;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class SuperStrikeModifier extends AbstractCardModifier {

    private boolean addedStrike = false;
    private boolean attackified = false;
    private boolean targetChanged = false;
    private boolean costChanged = false;
    private AbstractCard.CardTarget oldTarget = null;
    private AbstractCard.CardType oldType = null;
    private int oldCost = -1;
    private int bonusDamage;
    private String oldName = null;

    public SuperStrikeModifier(int bonusDamage) {
        this.bonusDamage = bonusDamage;
    }

    @Override
    public float modifyDamage(float damage, DamageInfo.DamageType type, AbstractCard card, AbstractMonster target) {
        return damage + bonusDamage;
    }

    @Override
    public void onUse(AbstractCard card, AbstractCreature m, UseCardAction action) {
        if (attackified && m != null) {
            AbstractDungeon.actionManager.addToBottom(new DamageAction(m, new DamageInfo(m, card.damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.SLASH_HEAVY));
        }
    }

    @Override
    public String modifyDescription(String rawDescription, AbstractCard card) {
        if (attackified) {
            return rawDescription + " NL Deal !D! damage.";
        }
        return rawDescription;
    }

    @Override
    public void onInitialApplication(AbstractCard card) {
        card.name = "Hero-" + card.name;
        if (!card.hasTag(AbstractCard.CardTags.STRIKE)) {
            card.name = card.name + " Strike";
            card.tags.add(AbstractCard.CardTags.STRIKE);
            addedStrike = true;
        }
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
        if (card.cost > -1) {
            oldCost = card.cost;
            card.cost = 1;// 35
            card.costForTurn = card.cost;// 36
            card.isCostModified = true;// 37
            costChanged = true;
        }
        if (card.baseDamage < 0) card.baseDamage = 0;
    }

    @Override
    public void onRemove(AbstractCard card) {
        if (addedStrike) {
            card.tags.remove(AbstractCard.CardTags.STRIKE);
            addedStrike = false;
        }
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
        if (costChanged) {
            card.cost = oldCost;
            oldCost = -1;
            costChanged = false;
        }
        card.name = oldName;
    }

    @Override
    public AbstractCardModifier makeCopy() {
        return new SuperStrikeModifier(bonusDamage);
    }
}

package theVelvet.patches;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import theVelvet.WeaponHelper;
import theVelvet.blights.*;

@SpirePatch(
        clz = CardGroup.class,
        method = "triggerOnOtherCardPlayed"
)
public class TriggerWeaponsPatch {
    public static void Prefix(CardGroup __instance, AbstractCard abstractCard) {
        if (WeaponHelper.usedWeapon instanceof Gianduja) {
            AbstractWeapon b = WeaponHelper.usedWeapon;
            ++b.counter;// 25
            if (b.counter == 3) {// 27
                b.counter = 0;// 28
                b.flash();// 29
                AbstractDungeon.actionManager.addToBottom(new DamageAllEnemiesAction(AbstractDungeon.player, DamageInfo.createDamageMatrix(3, true), DamageInfo.DamageType.THORNS, AbstractGameAction.AttackEffect.FIRE));
            }
        }
        if (WeaponHelper.usedWeapon instanceof CroceaMors) {
            AbstractDungeon.actionManager.addToBottom(new GainBlockAction(AbstractDungeon.player, 1));
        }
        if (WeaponHelper.usedWeapon instanceof SharpRetribution) {
            AbstractWeapon b = WeaponHelper.usedWeapon;
            ++b.counter;
            if (b.counter == 3) {
                b.counter = 0;
                b.flash();
                AbstractDungeon.actionManager.addToBottom(new DrawCardAction(1));
            }
        }
    }
}
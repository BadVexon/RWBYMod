package theVelvet.blights;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.WeakPower;
import theVelvet.RWBYMod;

public class CroceaMors extends AbstractWeapon {
    public static String ID = RWBYMod.makeID("CroceaMors");

    public CroceaMors() {
        super(ID, "Crocea Mors", "#gPassive: Whenever you play a card, gain #b1 #yBlock. NL #pActivate: Apply #b1 #yWeak to ALL enemies.");
    }

    @Override
    public void activate() {
        for (AbstractMonster q : AbstractDungeon.getCurrRoom().monsters.monsters) {
            if (!q.isDying && !q.isDead) {
                AbstractDungeon.actionManager.addToTop(new ApplyPowerAction(q, AbstractDungeon.player, new WeakPower(q, 1, false), 1));
            }
        }
    }
}

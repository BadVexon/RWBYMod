//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package theVelvet.actions;

import basemod.BaseMod;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.FlashAtkImgEffect;
import theVelvet.cards.Camera;

public class AlistarAction extends AbstractGameAction {
    private boolean upgraded;
    private DamageInfo info;

    public AlistarAction(AbstractCreature target, DamageInfo info, boolean upgraded) {
        this.info = info;// 20
        this.setValues(target, info);// 21
        this.upgraded = upgraded;
        this.actionType = ActionType.DAMAGE;// 23
        this.duration = Settings.ACTION_DUR_FASTER;// 24
    }// 25

    public void update() {
        if (this.duration == Settings.ACTION_DUR_FASTER && this.target != null) {// 29 30
            AbstractDungeon.effectList.add(new FlashAtkImgEffect(this.target.hb.cX, this.target.hb.cY, AttackEffect.BLUNT_HEAVY));// 31
            this.target.damage(this.info);// 33
            if (((AbstractMonster) this.target).isDying || this.target.currentHealth <= 0) {// 35
                int x = BaseMod.MAX_HAND_SIZE - AbstractDungeon.player.hand.size();
                AbstractCard q = new Camera();
                if (upgraded) q.upgrade();
                addToBot(new MakeTempCardInHandAction(q, x));
            }

            if (AbstractDungeon.getCurrRoom().monsters.areMonstersBasicallyDead()) {// 39
                AbstractDungeon.actionManager.clearPostCombatActions();// 40
            }
        }

        this.tickDuration();// 45
    }// 46
}

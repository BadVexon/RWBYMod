package theVelvet.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import theVelvet.blights.AbstractWeapon;
import theVelvet.blights.CrescentRose;

public class GainEnergyIfActiveAction extends AbstractGameAction {

    public GainEnergyIfActiveAction() {
        this.setValues(AbstractDungeon.player, AbstractDungeon.player, 1);// 12
        this.duration = Settings.ACTION_DUR_FAST;// 13
    }// 16

    public void update() {
        if (this.duration == Settings.ACTION_DUR_FAST) {// 20
            if (AbstractDungeon.player.hasBlight(CrescentRose.ID))
                if (((AbstractWeapon) AbstractDungeon.player.getBlight(CrescentRose.ID)).active()) {
                    AbstractDungeon.player.gainEnergy(1);// 21
                    AbstractDungeon.actionManager.updateEnergyGain(1);// 22
                    for (AbstractCard c : AbstractDungeon.player.hand.group) {
                        c.triggerOnGainEnergy(1, true);// 24
                    }
                }
        }

        this.tickDuration();// 28
    }// 29
}

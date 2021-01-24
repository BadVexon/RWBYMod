package theVelvet.blights;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.StrengthPower;
import theVelvet.RWBYMod;

public class Fulcrum extends AbstractWeapon {
    public static String ID = RWBYMod.makeID("Fulcrum");

    public Fulcrum() {
        super(ID, "Fulcrum", "#gPassive: You have #b1 #yStrength. NL #pActivate: Gain #b1 #yStrength.");
    }

    @Override
    public void onSwapped() {
        super.onSwapped();
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new StrengthPower(AbstractDungeon.player, 1), 1));
    }

    @Override
    public void onUnswapped() {
        super.onUnswapped();
        AbstractDungeon.actionManager.addToBottom(new ReducePowerAction(AbstractDungeon.player, AbstractDungeon.player, StrengthPower.POWER_ID, 1));
    }

    @Override
    public void activate() {
        AbstractDungeon.actionManager.addToTop(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new StrengthPower(AbstractDungeon.player, 1), 1));
    }
}

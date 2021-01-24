package theVelvet.blights;

import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import theVelvet.RWBYMod;

public class SharpRetribution extends AbstractWeapon {
    public static String ID = RWBYMod.makeID("SharpRetribution");

    public SharpRetribution() {
        super(ID, "Sharp Retribution", "#gPassive: Every time you play #b3 cards, draw #b1 card. NL #pActivate: Gain [E] .");
        counter = 0;
    }

    @Override
    public void onVictory() {
        counter = 0;
    }

    @Override
    public void onUnswapped() {
        super.onUnswapped();
        counter = 0;
    }

    @Override
    public void activate() {
        AbstractDungeon.actionManager.addToTop(new GainEnergyAction(1));
    }
}

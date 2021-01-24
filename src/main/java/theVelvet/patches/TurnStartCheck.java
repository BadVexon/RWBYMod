package theVelvet.patches;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.blights.AbstractBlight;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import theVelvet.RWBYMod;
import theVelvet.actions.ChooseWeaponAction;
import theVelvet.actions.GainEnergyIfActiveAction;
import theVelvet.blights.AbstractWeapon;
import theVelvet.powers.AlwaysShiftingPower;
import theVelvet.powers.DugInPower;

@SpirePatch(
        clz = AbstractPlayer.class,
        method = "applyStartOfTurnPostDrawRelics"
)
public class TurnStartCheck {

    public static boolean yesKitten = false;

    public static void Prefix(AbstractPlayer __instance) {
        if (getWeaponRelics()) {
            if (yesKitten || __instance.hasPower(DugInPower.POWER_ID) || __instance.hasPower(AlwaysShiftingPower.POWER_ID)) {
                AbstractDungeon.actionManager.addToBottom(new ChooseWeaponAction());
                yesKitten = false;
            }
            AbstractDungeon.actionManager.addToBottom(new GainEnergyIfActiveAction());
        }
        AbstractDungeon.actionManager.addToBottom(new AbstractGameAction() {
            @Override
            public void update() {
                isDone = true;
                RWBYMod.drawnCards = 0;
                RWBYMod.countDraws = true;
            }
        });
    }

    public static boolean getWeaponRelics() {
        for (AbstractBlight r : AbstractDungeon.player.blights) {
            if (r instanceof AbstractWeapon)
                return true;
        }
        return false;
    }
}
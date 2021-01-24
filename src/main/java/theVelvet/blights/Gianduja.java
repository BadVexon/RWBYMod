package theVelvet.blights;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import theVelvet.RWBYMod;
import theVelvet.powers.PesteredPower;

public class Gianduja extends AbstractWeapon {
    public static String ID = RWBYMod.makeID("Gianduja");

    public Gianduja() {
        super(ID, "Gianduja", "#gPassive: Whenever you play #b3 cards, deal #b3 damage to ALL enemies. NL #pActivate: Apply #b1 #yVulnerable to ALL enemies.");
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
        for (AbstractMonster q : AbstractDungeon.getCurrRoom().monsters.monsters) {
            if (!q.isDying && !q.isDead) {
                AbstractDungeon.actionManager.addToTop(new ApplyPowerAction(q, AbstractDungeon.player, new VulnerablePower(q, 1, false), 1));
            }
        }
    }
}

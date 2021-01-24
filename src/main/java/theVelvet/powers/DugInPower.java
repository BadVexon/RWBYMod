package theVelvet.powers;

import basemod.interfaces.CloneablePowerInterface;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import theVelvet.RWBYMod;
import theVelvet.util.TextureLoader;

public class DugInPower extends AbstractPower implements CloneablePowerInterface {
    public static final String POWER_ID = RWBYMod.makeID("DugInPower");
    private static final Texture tex84 = TextureLoader.getTexture("hadesmodResources/images/powers/Cursed_power84.png");
    private static final Texture tex32 = TextureLoader.getTexture("hadesmodResources/images/powers/Cursed_power32.png");

    public DugInPower(final int amount) {
        name = "Dug In";
        ID = POWER_ID;

        this.owner = AbstractDungeon.player;
        this.amount = amount;

        isTurnBased = true;
        type = PowerType.BUFF;

        this.region128 = new TextureAtlas.AtlasRegion(tex84, 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(tex32, 0, 0, 32, 32);

        updateDescription();
    }

    public void atEndOfRound() {
        if (this.amount == 0) {// 46
            this.addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, POWER_ID));// 47
        } else {
            this.addToBot(new ReducePowerAction(this.owner, this.owner, POWER_ID, 1));// 49
        }
    }

    @Override
    public AbstractPower makeCopy() {
        return new DugInPower(amount);
    }

    @Override
    public void updateDescription() {
        if (amount == 1)
            description = "Swap Weapons at the start of your next turn.";
        else
            description = "Swap Weapons at the start of your next #b" + amount + " turns.";
    }
}
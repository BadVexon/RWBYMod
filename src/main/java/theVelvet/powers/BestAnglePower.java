package theVelvet.powers;

import basemod.interfaces.CloneablePowerInterface;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import theVelvet.RWBYMod;
import theVelvet.util.TextureLoader;

public class BestAnglePower extends AbstractPower implements CloneablePowerInterface {
    public static final String POWER_ID = RWBYMod.makeID("BestAnglePower");
    private static final Texture tex84 = TextureLoader.getTexture("hadesmodResources/images/powers/Cursed_power84.png");
    private static final Texture tex32 = TextureLoader.getTexture("hadesmodResources/images/powers/Cursed_power32.png");

    public BestAnglePower(final int amount) {
        name = "Best Angle";
        ID = POWER_ID;

        this.owner = AbstractDungeon.player;
        this.amount = amount;

        isTurnBased = true;
        type = PowerType.BUFF;

        this.region128 = new TextureAtlas.AtlasRegion(tex84, 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(tex32, 0, 0, 32, 32);

        updateDescription();
    }

    public void atEndOfTurn(boolean isPlayer) {
        if (isPlayer) {// 73
            this.addToBot(new ReducePowerAction(owner, owner, this, 1));
        }
    }// 76

    @Override
    public AbstractPower makeCopy() {
        return new BestAnglePower(amount);
    }

    @Override
    public void updateDescription() {
        if (amount == 1)
            description = "Cards from #yCameras cost #b0 this turn.";
        else
            description = "Cards from #yCameras cost #b0 for the next #b" + amount + " turns.";
    }
}
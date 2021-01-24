package theVelvet.powers;

import basemod.interfaces.CloneablePowerInterface;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import theVelvet.RWBYMod;
import theVelvet.util.OnActivateSubscriber;
import theVelvet.util.TextureLoader;

public class ArraysEmbracePower extends AbstractPower implements CloneablePowerInterface, OnActivateSubscriber {
    public static final String POWER_ID = RWBYMod.makeID("ArraysEmbracePower");
    private static final Texture tex84 = TextureLoader.getTexture("hadesmodResources/images/powers/Cursed_power84.png");
    private static final Texture tex32 = TextureLoader.getTexture("hadesmodResources/images/powers/Cursed_power32.png");

    public ArraysEmbracePower(final int amount) {
        name = "Array's Embrace";
        ID = POWER_ID;

        this.owner = AbstractDungeon.player;
        this.amount = amount;

        type = PowerType.BUFF;
        isTurnBased = false;


        this.region128 = new TextureAtlas.AtlasRegion(tex84, 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(tex32, 0, 0, 32, 32);

        updateDescription();
    }

    @Override
    public void receiveActivate() {
        flash();
        AbstractDungeon.actionManager.addToTop(new DrawCardAction(owner, amount));
    }

    @Override
    public AbstractPower makeCopy() {
        return new ArraysEmbracePower(amount);
    }

    @Override
    public void updateDescription() {
        if (amount == 1)
            description = "Whenever you Activate your Weapon, draw #b" + amount + " card.";
        else
            description = "Whenever you Activate your Weapon, draw #b" + amount + " cards.";
    }
}
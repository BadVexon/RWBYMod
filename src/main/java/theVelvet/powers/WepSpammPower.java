package theVelvet.powers;

import basemod.interfaces.CloneablePowerInterface;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import theVelvet.RWBYMod;
import theVelvet.actions.ActivateWeaponAction;
import theVelvet.util.TextureLoader;

public class WepSpammPower extends AbstractPower implements CloneablePowerInterface {
    public static final String POWER_ID = RWBYMod.makeID("WepSpammPower");
    private static final Texture tex84 = TextureLoader.getTexture("hadesmodResources/images/powers/Cursed_power84.png");
    private static final Texture tex32 = TextureLoader.getTexture("hadesmodResources/images/powers/Cursed_power32.png");

    public WepSpammPower(final int amount) {
        name = "Weapon Spam";
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
    public void atEndOfTurn(boolean isPlayer) {
        if (isPlayer) {
            flash();
            for (int i = 0; i < amount; i++)
                addToBot(new ActivateWeaponAction());
        }
    }

    @Override
    public AbstractPower makeCopy() {
        return new WepSpammPower(amount);
    }

    @Override
    public void updateDescription() {
        if (amount == 1)
            description = "At the end of your turn, Activate your Weapon.";
        else
            description = "At the end of your turn, Activate your Weapon #b" + amount + " times.";
    }
}
package theVelvet.powers;

import basemod.interfaces.CloneablePowerInterface;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import theVelvet.RWBYMod;
import theVelvet.actions.ActivateWeaponAction;
import theVelvet.util.TextureLoader;

public class StrikeActivateWeaponPower extends AbstractStrikeActivatePower implements CloneablePowerInterface {

    public static final String POWER_ID = RWBYMod.makeID("StrikeActivateWeaponPower");

    private static final Texture tex84 = TextureLoader.getTexture(RWBYMod.getModID() + "Resources/images/powers/Cursed_power84.png");
    private static final Texture tex32 = TextureLoader.getTexture(RWBYMod.getModID() + "Resources/images/powers/Cursed_power32.png");

    public StrikeActivateWeaponPower(final int amount) {
        this.name = "Strike Activate Weapon+";
        this.ID = POWER_ID;
        this.owner = AbstractDungeon.player;
        this.amount = amount;
        this.type = PowerType.BUFF;
        this.isTurnBased = true;

        this.region128 = new TextureAtlas.AtlasRegion(tex84, 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(tex32, 0, 0, 32, 32);

        this.updateDescription();
    }

    @Override
    public void onUseStrike(AbstractCard card, UseCardAction action) {
        for (int i = 0; i < amount; i++)
            addToBot(new ActivateWeaponAction());
    }

    @Override
    public void updateDescription() {
        if (amount == 1)
            description = "The next #yStrike you play Activates your Weapon.";
        else
            description = "The next #yStrike you play Activates your Weapon #b" + amount + " times.";
    }

    @Override
    public AbstractPower makeCopy() {
        return new StrikeActivateWeaponPower(amount);
    }
}
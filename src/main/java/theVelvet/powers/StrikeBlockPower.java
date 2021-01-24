package theVelvet.powers;

import basemod.interfaces.CloneablePowerInterface;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import theVelvet.RWBYMod;
import theVelvet.util.TextureLoader;

public class StrikeBlockPower extends AbstractStrikeActivatePower implements CloneablePowerInterface {

    public static final String POWER_ID = RWBYMod.makeID("StrikeBlockPower");

    private static final Texture tex84 = TextureLoader.getTexture(RWBYMod.getModID() + "Resources/images/powers/Cursed_power84.png");
    private static final Texture tex32 = TextureLoader.getTexture(RWBYMod.getModID() + "Resources/images/powers/Cursed_power32.png");

    public StrikeBlockPower(final int amount) {
        this.name = "Strike Block+";
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
        addToBot(new GainBlockAction(owner, amount));
    }

    @Override
    public void updateDescription() {
        if (amount == 1)
            description = "The next #yStrike you play grants #b" + amount + " #yBlock.";
        else
            description = "The next #yStrike you play grants #b" + amount + " #yBlock.";
    }

    @Override
    public AbstractPower makeCopy() {
        return new StrikeBlockPower(amount);
    }
}
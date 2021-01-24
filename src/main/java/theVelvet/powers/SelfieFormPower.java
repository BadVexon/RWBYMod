package theVelvet.powers;

import basemod.interfaces.CloneablePowerInterface;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import theVelvet.RWBYMod;
import theVelvet.cards.Camera;
import theVelvet.util.ModifyDamagePower;
import theVelvet.util.TextureLoader;

public class SelfieFormPower extends AbstractPower {
    public static final String POWER_ID = RWBYMod.makeID("SelfieFormPower");
    private static final Texture tex84 = TextureLoader.getTexture("hadesmodResources/images/powers/Cursed_power84.png");
    private static final Texture tex32 = TextureLoader.getTexture("hadesmodResources/images/powers/Cursed_power32.png");

    public SelfieFormPower() {
        name = "Selfie Form";
        ID = POWER_ID;

        this.owner = AbstractDungeon.player;
        this.amount = -1;

        isTurnBased = false;
        type = PowerType.BUFF;

        this.region128 = new TextureAtlas.AtlasRegion(tex84, 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(tex32, 0, 0, 32, 32);

        updateDescription();
    }

    @Override
    public void updateDescription() {
        description = "Cards from #yCameras are always #yUpgraded.";
    }
}
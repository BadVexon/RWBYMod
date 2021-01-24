package theVelvet.powers;

import basemod.interfaces.CloneablePowerInterface;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.NonStackablePower;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import theVelvet.RWBYMod;
import theVelvet.util.TextureLoader;

public class CameraSpammaPower extends AbstractPower implements CloneablePowerInterface, NonStackablePower {
    public static final String POWER_ID = RWBYMod.makeID("CameraSpammaPower");
    private static final Texture tex84 = TextureLoader.getTexture("hadesmodResources/images/powers/Cursed_power84.png");
    private static final Texture tex32 = TextureLoader.getTexture("hadesmodResources/images/powers/Cursed_power32.png");

    private AbstractCard c;

    public CameraSpammaPower(AbstractCard q, int amount) {
        name = "Camera Spamma";
        ID = POWER_ID;

        this.owner = AbstractDungeon.player;
        this.c = q;

        type = PowerType.BUFF;
        this.amount = amount;
        canGoNegative = false;


        this.region128 = new TextureAtlas.AtlasRegion(tex84, 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(tex32, 0, 0, 32, 32);

        updateDescription();
    }

    @Override
    public boolean isStackable(AbstractPower power) {
        if (power instanceof CameraSpammaPower) {
            return ((CameraSpammaPower) power).c.name.equals(this.c.name) && ((CameraSpammaPower) power).c.upgraded == this.c.upgraded;
        }
        return false;
    }

    public void atStartOfTurn() {
        AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(c, amount));
    }

    @Override
    public AbstractPower makeCopy() {
        return new CameraSpammaPower(c, amount);
    }

    @Override
    public void updateDescription() {
        description = "At the start of your turn, add #b" + amount + " " + c.name + " into your hand.";
    }
}
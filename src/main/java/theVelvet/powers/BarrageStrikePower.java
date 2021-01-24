package theVelvet.powers;

import basemod.interfaces.CloneablePowerInterface;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.NonStackablePower;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import theVelvet.RWBYMod;
import theVelvet.util.TextureLoader;

public class BarrageStrikePower extends AbstractPower implements CloneablePowerInterface, NonStackablePower {
    public static final String POWER_ID = RWBYMod.makeID("BarrageStrikePower");
    private static final Texture tex84 = TextureLoader.getTexture("hadesmodResources/images/powers/Cursed_power84.png");
    private static final Texture tex32 = TextureLoader.getTexture("hadesmodResources/images/powers/Cursed_power32.png");

    private AbstractCard c;

    public BarrageStrikePower(AbstractCard q) {
        name = "Barrage Strike";
        ID = POWER_ID;

        this.owner = AbstractDungeon.player;
        this.c = q;

        type = PowerType.BUFF;
        isTurnBased = true;
        amount = -1;
        canGoNegative = false;


        this.region128 = new TextureAtlas.AtlasRegion(tex84, 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(tex32, 0, 0, 32, 32);

        updateDescription();
    }

    public void atStartOfTurn() {
        AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(owner, owner, this));
        AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(c));
    }

    @Override
    public AbstractPower makeCopy() {
        return new BarrageStrikePower(c);
    }

    @Override
    public void updateDescription() {
        description = "At the start of your next turn, add a " + c.name + " into your hand.";
    }
}
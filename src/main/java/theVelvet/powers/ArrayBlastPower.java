package theVelvet.powers;

import basemod.interfaces.CloneablePowerInterface;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import theVelvet.RWBYMod;
import theVelvet.util.OnActivateSubscriber;
import theVelvet.util.TextureLoader;

public class ArrayBlastPower extends AbstractPower implements CloneablePowerInterface, OnActivateSubscriber {
    public static final String POWER_ID = RWBYMod.makeID("ArrayBlastPower");
    private static final Texture tex84 = TextureLoader.getTexture("hadesmodResources/images/powers/Cursed_power84.png");
    private static final Texture tex32 = TextureLoader.getTexture("hadesmodResources/images/powers/Cursed_power32.png");

    public ArrayBlastPower(final int amount) {
        name = "Array Blast";
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
        AbstractDungeon.actionManager.addToTop(new DamageAction(AbstractDungeon.getRandomMonster(), new DamageInfo(owner, amount, DamageInfo.DamageType.THORNS), AbstractGameAction.AttackEffect.LIGHTNING));
    }

    @Override
    public AbstractPower makeCopy() {
        return new ArrayBlastPower(amount);
    }

    @Override
    public void updateDescription() {
        description = "Whenever you Activate your Weapon, deal #b" + amount + " damage to a random enemy.";
    }
}
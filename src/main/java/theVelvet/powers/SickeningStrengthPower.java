package theVelvet.powers;

import basemod.interfaces.CloneablePowerInterface;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.BetterOnApplyPowerPower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.*;
import theVelvet.RWBYMod;
import theVelvet.util.TextureLoader;

public class SickeningStrengthPower extends AbstractPower implements CloneablePowerInterface, BetterOnApplyPowerPower {
    public static final String POWER_ID = RWBYMod.makeID("SickeningStrengthPower");
    private static final Texture tex84 = TextureLoader.getTexture("hadesmodResources/images/powers/Cursed_power84.png");
    private static final Texture tex32 = TextureLoader.getTexture("hadesmodResources/images/powers/Cursed_power32.png");

    public SickeningStrengthPower(final int amount) {
        name = "Sickening Strength";
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
    public boolean betterOnApplyPower(AbstractPower abstractPower, AbstractCreature abstractCreature, AbstractCreature abstractCreature1) {
        return true;
    }

    public int betterOnApplyPowerStacks(AbstractPower power, AbstractCreature target, AbstractCreature source, int stacks) {
        if (power.type == PowerType.DEBUFF && !power.ID.equals(GainStrengthPower.POWER_ID) && source == this.owner && target != this.owner && !target.hasPower(ArtifactPower.POWER_ID)) {// 33 34
            this.flash();// 35
            addToBot(new ApplyPowerAction(owner, owner, new StrengthPower(owner, amount), amount));
            addToBot(new ApplyPowerAction(owner, owner, new LoseStrengthPower(owner, amount), amount));
        }
        return stacks;
    }

    @Override
    public AbstractPower makeCopy() {
        return new SickeningStrengthPower(amount);
    }

    @Override
    public void updateDescription() {
        description = "Whenever you apply a Debuff, gain #b" + amount + " #yStrength this turn.";
    }
}
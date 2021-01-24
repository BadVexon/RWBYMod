package theVelvet.powers;

import basemod.interfaces.CloneablePowerInterface;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;
import theVelvet.RWBYMod;
import theVelvet.util.TextureLoader;

public class PesteredPower extends AbstractPower implements CloneablePowerInterface {
    public static final String POWER_ID = RWBYMod.makeID("PesteredPower");
    private static final Texture tex84 = TextureLoader.getTexture("hadesmodResources/images/powers/Pestered_84.png");
    private static final Texture tex32 = TextureLoader.getTexture("hadesmodResources/images/powers/Pestered_32.png");

    public PesteredPower(AbstractCreature c, final int amount) {
        name = "Pestered";
        ID = POWER_ID;

        this.owner = c;
        this.amount = amount;

        isTurnBased = true;
        type = PowerType.DEBUFF;

        this.region128 = new TextureAtlas.AtlasRegion(tex84, 0, 0, 87, 82);
        this.region48 = new TextureAtlas.AtlasRegion(tex32, 0, 0, 34, 31);

        updateDescription();
    }

    public void atEndOfRound() {
        if (this.amount == 0) {// 46
            this.addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, POWER_ID));// 47
        } else {
            this.addToBot(new ReducePowerAction(this.owner, this.owner, POWER_ID, 1));// 49
        }
    }

    public float atDamageReceive(float damage, DamageInfo.DamageType type) {
        if (type == DamageInfo.DamageType.NORMAL) {// 80
            return damage * 1.25F;// 84
        } else {
            return damage;// 93
        }
    }

    @Override
    public AbstractPower makeCopy() {
        return new PesteredPower(owner, amount);
    }

    @Override
    public void updateDescription() {
        if (amount == 1)
            description = "Receive #b25% more damage from #yAttacks for #b" + amount + " turn.";
        else
            description = "Receive #b25% more damage from #yAttacks for #b" + amount + " turns.";
    }
}
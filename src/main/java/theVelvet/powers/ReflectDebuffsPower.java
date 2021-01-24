package theVelvet.powers;

import basemod.interfaces.CloneablePowerInterface;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.OnReceivePowerPower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import theVelvet.RWBYMod;
import theVelvet.util.TextureLoader;

public class ReflectDebuffsPower extends AbstractPower implements CloneablePowerInterface, OnReceivePowerPower {
    public static final String POWER_ID = RWBYMod.makeID("ReflectDebuffsPower");
    private static final Texture tex84 = TextureLoader.getTexture("hadesmodResources/images/powers/Cursed_power84.png");
    private static final Texture tex32 = TextureLoader.getTexture("hadesmodResources/images/powers/Cursed_power32.png");

    public ReflectDebuffsPower(final int amount) {
        name = "Reflect Debuffs";
        ID = POWER_ID;

        this.owner = AbstractDungeon.player;
        this.amount = amount;

        type = PowerType.BUFF;
        isTurnBased = false;


        this.region128 = new TextureAtlas.AtlasRegion(tex84, 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(tex32, 0, 0, 32, 32);

        updateDescription();
    }

    public void atEndOfRound() {
        AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(this.owner, this.owner, this.ID));
    }

    @Override
    public boolean onReceivePower(AbstractPower p, AbstractCreature target, AbstractCreature source) {
        if (p.type == PowerType.DEBUFF) {
            this.flash();
            AbstractDungeon.actionManager.addToTop(new RemoveSpecificPowerAction(owner, owner, this));
            if (source instanceof AbstractMonster) {
                p.owner = source;
                AbstractDungeon.actionManager.addToTop(new ApplyPowerAction(source, source, p, p.amount));
            }
            return false;
        }
        return true;
    }

    @Override
    public AbstractPower makeCopy() {
        return new ReflectDebuffsPower(amount);
    }

    @Override
    public void updateDescription() {
        if (amount == 1) description = "Reflect the next debuff applied to you this turn.";
        else
            description = "Reflect the next #b" + amount + " debuffs applied to you this turn.";
    }
}
package theVelvet.powers;

import basemod.interfaces.CloneablePowerInterface;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.OnLoseBlockPower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import theVelvet.RWBYMod;
import theVelvet.util.TextureLoader;

public class HolyShieldPower extends AbstractPower implements CloneablePowerInterface, OnLoseBlockPower {

    public static final String POWER_ID = RWBYMod.makeID("HolyShieldPower");

    private static final Texture tex84 = TextureLoader.getTexture(RWBYMod.getModID() + "Resources/images/powers/Cursed_power84.png");
    private static final Texture tex32 = TextureLoader.getTexture(RWBYMod.getModID() + "Resources/images/powers/Cursed_power32.png");

    public HolyShieldPower(final int amount) {
        this.name = "Holy Shield";
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
    public int onLoseBlock(DamageInfo damageInfo, int damageAmount) {
        if (damageAmount > 0)
            if (damageInfo.owner instanceof AbstractMonster && damageInfo.type == DamageInfo.DamageType.NORMAL) {
                flash();
                addToTop(new ApplyPowerAction(damageInfo.owner, owner, new VulnerablePower(damageInfo.owner, amount, false), amount));
            }
        return damageAmount;
    }

    @Override
    public void updateDescription() {
        description = "When enemy attacks damage your Block, apply #b" + amount + " #yVulnerable back.";
    }

    @Override
    public AbstractPower makeCopy() {
        return new HolyShieldPower(amount);
    }
}
package theVelvet.powers;

import basemod.interfaces.CloneablePowerInterface;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import theVelvet.RWBYMod;
import theVelvet.util.ModifyDamagePower;
import theVelvet.util.TextureLoader;

public class AuraAmpPower extends AbstractPower implements CloneablePowerInterface, ModifyDamagePower {
    public static final String POWER_ID = RWBYMod.makeID("AuraAmpPower");
    private static final Texture tex84 = TextureLoader.getTexture("hadesmodResources/images/powers/Cursed_power84.png");
    private static final Texture tex32 = TextureLoader.getTexture("hadesmodResources/images/powers/Cursed_power32.png");

    public AuraAmpPower(final int amount) {
        name = "Aura Amp";
        ID = POWER_ID;

        this.owner = AbstractDungeon.player;
        this.amount = amount;

        isTurnBased = false;
        type = PowerType.BUFF;

        this.region128 = new TextureAtlas.AtlasRegion(tex84, 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(tex32, 0, 0, 32, 32);

        updateDescription();
    }

    @Override
    public float calculateCardDamagePower(float damageAmount, AbstractCard c, AbstractMonster m) {
        int x = 0;
        for (AbstractPower p : m.powers) {
            if (p.type == PowerType.DEBUFF)
                x++;
        }
        if (x >= 2)
            return damageAmount * (1 + amount / 100F);
        return damageAmount;
    }

    @Override
    public AbstractPower makeCopy() {
        return new AuraAmpPower(amount);
    }

    @Override
    public void updateDescription() {
        description = "Deal #b" + amount + "% bonus damage to enemies with at least #b types of debuffs.";
    }
}
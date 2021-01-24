package theVelvet.powers;

import basemod.interfaces.CloneablePowerInterface;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import theVelvet.RWBYMod;
import theVelvet.util.TextureLoader;

public class IncreaseStrikeDamagePower extends AbstractPower implements CloneablePowerInterface {
    public static final String POWER_ID = RWBYMod.makeID("IncreaseStrikeDamagePower");
    private static final Texture tex84 = TextureLoader.getTexture("hadesmodResources/images/powers/Cursed_power84.png");
    private static final Texture tex32 = TextureLoader.getTexture("hadesmodResources/images/powers/Cursed_power32.png");

    public IncreaseStrikeDamagePower(final int amount) {
        name = "Strike PLUS ULTRA!";
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
    public float atDamageGive(float damage, DamageInfo.DamageType type, AbstractCard card) {
        if (card.hasTag(AbstractCard.CardTags.STRIKE))
            return damage + amount;
        return damage;
    }

    @Override
    public AbstractPower makeCopy() {
        return new IncreaseStrikeDamagePower(amount);
    }

    @Override
    public void updateDescription() {
        description = "Strikes deal #b" + amount + " bonus damage.";
    }
}
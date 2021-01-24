package theVelvet.powers;

import basemod.interfaces.CloneablePowerInterface;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import theVelvet.RWBYMod;
import theVelvet.util.TextureLoader;

public class TemporaryBonusStrikeDamagePower extends AbstractStrikeActivatePower implements CloneablePowerInterface {

    public static final String POWER_ID = RWBYMod.makeID("TemporaryBonusStrikeDamagePower");

    private static final Texture tex84 = TextureLoader.getTexture(RWBYMod.getModID() + "Resources/images/powers/Cursed_power84.png");
    private static final Texture tex32 = TextureLoader.getTexture(RWBYMod.getModID() + "Resources/images/powers/Cursed_power32.png");

    public TemporaryBonusStrikeDamagePower(final int amount) {
        this.name = "Strike Damage+";
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
    public float atDamageGive(float damage, DamageInfo.DamageType type, AbstractCard card) {
        if (card.hasTag(AbstractCard.CardTags.STRIKE)) {
            return damage + amount;
        }
        return super.atDamageGive(damage, type);
    }

    @Override
    public void atEndOfTurn(boolean isPlayer) {
        if (isPlayer) {
            addToBot(new RemoveSpecificPowerAction(owner, owner, this));
        }
    }

    @Override
    public void onUseStrike(AbstractCard card, UseCardAction action) {

    }

    @Override
    public void updateDescription() {
        description = "The next #yStrike you play this turn deals #b" + amount + " additional damage.";
    }

    @Override
    public AbstractPower makeCopy() {
        return new TemporaryBonusStrikeDamagePower(amount);
    }
}
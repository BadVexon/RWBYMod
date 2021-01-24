package theVelvet.powers;

import basemod.interfaces.CloneablePowerInterface;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardQueueItem;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import theVelvet.RWBYMod;
import theVelvet.util.TextureLoader;

public class StrikeDupePower extends AbstractStrikeActivatePower implements CloneablePowerInterface {

    public static final String POWER_ID = RWBYMod.makeID("StrikeDupePower");

    private static final Texture tex84 = TextureLoader.getTexture(RWBYMod.getModID() + "Resources/images/powers/Cursed_power84.png");
    private static final Texture tex32 = TextureLoader.getTexture(RWBYMod.getModID() + "Resources/images/powers/Cursed_power32.png");

    public StrikeDupePower(final int amount) {
        this.name = "Strike Duplicate";
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
    public void onUseStrike(AbstractCard card, UseCardAction action) {
        for (int i = 0; i < amount; i++) {
            this.flash();// 49
            AbstractMonster m = null;// 50
            if (action.target != null) {// 52
                m = (AbstractMonster) action.target;// 53
            }

            AbstractCard tmp = card.makeSameInstanceOf();// 56
            AbstractDungeon.player.limbo.addToBottom(tmp);// 57
            tmp.current_x = card.current_x;// 58
            tmp.current_y = card.current_y;// 59
            tmp.target_x = (float) Settings.WIDTH / 2.0F - 300.0F * Settings.scale;// 60
            tmp.target_y = (float) Settings.HEIGHT / 2.0F;// 61
            if (m != null) {// 63
                tmp.calculateCardDamage(m);// 64
            }

            tmp.purgeOnUse = true;// 67
            AbstractDungeon.actionManager.addCardQueueItem(new CardQueueItem(tmp, m, card.energyOnUse, true, true), true);// 68
        }
    }

    @Override
    public void updateDescription() {
        if (amount == 1)
        description = "The next #yStrike you play is played twice.";
        else
            description = "The next #yStrike you play is played #b" + amount + " times.";
    }

    @Override
    public AbstractPower makeCopy() {
        return new StrikeDupePower(amount);
    }
}
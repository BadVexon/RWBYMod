package theVelvet.powers;

import basemod.interfaces.CloneablePowerInterface;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.evacipated.cardcrawl.mod.stslib.powers.abstracts.TwoAmountPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.tempCards.Insight;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import theVelvet.RWBYMod;
import theVelvet.util.TextureLoader;

public class AlternateAnesidoraPower extends TwoAmountPower implements CloneablePowerInterface {
    public static final String POWER_ID = RWBYMod.makeID("AlternateAnesidoraPower");
    private static final Texture tex84 = TextureLoader.getTexture("hadesmodResources/images/powers/Cursed_power84.png");
    private static final Texture tex32 = TextureLoader.getTexture("hadesmodResources/images/powers/Cursed_power32.png");

    public AlternateAnesidoraPower(final int amount) {
        name = "Alternate Anesidora";
        ID = POWER_ID;

        this.owner = AbstractDungeon.player;
        this.amount = amount;
        this.amount2 = 3;

        isTurnBased = false;

        this.region128 = new TextureAtlas.AtlasRegion(tex84, 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(tex32, 0, 0, 32, 32);

        updateDescription();
    }

    @Override
    public void atStartOfTurnPostDraw() {
        addToBot(new AbstractGameAction() {
            @Override
            public void update() {
                isDone = true;
                amount2 = 3;
            }
        });
    }

    @Override
    public void onUseCard(AbstractCard card, UseCardAction action) {
        if (amount2 > 0) {
            amount2--;
            if (amount2 == 0) {
                flash();
                addToTop(new MakeTempCardInDrawPileAction(new Insight(), amount, false, true));
            }
        }
    }

    @Override
    public AbstractPower makeCopy() {
        return new AlternateAnesidoraPower(amount);
    }

    @Override
    public void updateDescription() {
        description = "After you play #b3 cards in a turn, add #b" + amount + " #yInsight to the top of your draw pile.";
    }
}
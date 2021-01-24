package theVelvet.powers;

import basemod.interfaces.CloneablePowerInterface;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import theVelvet.RWBYMod;
import theVelvet.util.TextureLoader;

public class BitByBitPower extends AbstractPower implements CloneablePowerInterface {
    public static final String POWER_ID = RWBYMod.makeID("BitByBitPower");
    private static final Texture tex84 = TextureLoader.getTexture("hadesmodResources/images/powers/Cursed_power84.png");
    private static final Texture tex32 = TextureLoader.getTexture("hadesmodResources/images/powers/Cursed_power32.png");

    public BitByBitPower(final int amount) {
        name = "Bit By Bit";
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
    public void atEndOfTurn(boolean isPlayer) {
        int x = this.amount;
        if (isPlayer) {
            flash();
            addToBot(new AbstractGameAction() {
                @Override
                public void update() {
                    isDone = true;
                    for (AbstractCard q : AbstractDungeon.player.hand.group) {
                        addToTop(new DamageAllEnemiesAction(owner, DamageInfo.createDamageMatrix(x, true), DamageInfo.DamageType.THORNS, AttackEffect.BLUNT_LIGHT));
                        addToTop(new AbstractGameAction() {
                            @Override
                            public void update() {
                                q.superFlash();
                                isDone = true;
                            }
                        });
                    }
                }
            });
        }
    }

    @Override
    public AbstractPower makeCopy() {
        return new BitByBitPower(amount);
    }

    @Override
    public void updateDescription() {
        description = "At the end of your turn, deal #b" + amount + " damage to ALL enemies for each card in your hand.";
    }
}
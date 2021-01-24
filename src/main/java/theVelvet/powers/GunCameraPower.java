package theVelvet.powers;

import basemod.interfaces.CloneablePowerInterface;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import theVelvet.RWBYMod;
import theVelvet.cards.Camera;
import theVelvet.util.TextureLoader;

public class GunCameraPower extends AbstractPower implements CloneablePowerInterface {
    public static final String POWER_ID = RWBYMod.makeID("GunCameraPower");
    private static final Texture tex84 = TextureLoader.getTexture("hadesmodResources/images/powers/Cursed_power84.png");
    private static final Texture tex32 = TextureLoader.getTexture("hadesmodResources/images/powers/Cursed_power32.png");

    public GunCameraPower(final int amount) {
        name = "Tabloids";
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
    public void onUseCard(AbstractCard card, UseCardAction action) {
        if (card instanceof Camera) {
            flash();
            for (AbstractMonster q : AbstractDungeon.getCurrRoom().monsters.monsters) {
                if (!q.isDeadOrEscaped() && !q.isDying) {
                    addToBot(new ApplyPowerAction(q, owner, new PesteredPower(q, amount), amount));
                }
            }
        }
    }

    @Override
    public AbstractPower makeCopy() {
        return new GunCameraPower(amount);
    }

    @Override
    public void updateDescription() {
        description = "Whenever you play a #yCamera, apply #b" + amount + " #yPestered to ALL enemies.";
    }
}
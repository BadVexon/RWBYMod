package theVelvet.powers;

import basemod.interfaces.CloneablePowerInterface;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.HealthBarRenderPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.unique.PoisonLoseHpAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import theVelvet.RWBYMod;
import theVelvet.util.TextureLoader;

public class DrainingDoomPower extends AbstractPower implements CloneablePowerInterface, HealthBarRenderPower {
    public static final String POWER_ID = RWBYMod.makeID("DrainingDoomPower");
    private static final Texture tex84 = TextureLoader.getTexture("hadesmodResources/images/powers/Cursed_power84.png");
    private static final Texture tex32 = TextureLoader.getTexture("hadesmodResources/images/powers/Cursed_power32.png");

    public DrainingDoomPower(final int amount) {
        name = "Draining Doom";
        ID = POWER_ID;

        this.owner = AbstractDungeon.player;
        this.amount = amount;

        type = PowerType.DEBUFF;
        isTurnBased = false;


        this.region128 = new TextureAtlas.AtlasRegion(tex84, 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(tex32, 0, 0, 32, 32);

        updateDescription();
    }

    public void atStartOfTurn() {
        if (AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT && !AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
            this.flashWithoutSound();// 67
            int x = 0;
            for (AbstractPower p : owner.powers) {
                if (p.type == PowerType.DEBUFF) x += p.amount;
            }
            this.addToBot(new PoisonLoseHpAction(this.owner, owner, x, AbstractGameAction.AttackEffect.POISON));
        }
    }

    @Override
    public AbstractPower makeCopy() {
        return new DrainingDoomPower(amount);
    }

    @Override
    public int getHealthBarAmount() {
        int x = 0;
        for (AbstractPower p : owner.powers) {
            if (p.type == PowerType.DEBUFF) x += p.amount;
        }
        return x;
    }

    @Override
    public Color getColor() {
        return Color.NAVY;
    }

    @Override
    public void updateDescription() {
        if (amount == 1)
            description = "Whenever you Activate your Weapon, draw #b" + amount + " card.";
        else
            description = "Whenever you Activate your Weapon, draw #b" + amount + " cards.";
    }
}
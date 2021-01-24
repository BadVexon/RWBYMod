package theVelvet.blights;

import com.megacrit.cardcrawl.blights.AbstractBlight;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import theVelvet.WeaponHelper;
import theVelvet.util.OnActivateSubscriber;
import theVelvet.util.TextureLoader;

public abstract class AbstractWeapon extends AbstractBlight {

    public AbstractWeapon(String id, String name, String description) {
        super(id, name, description, "", true);
        loadImages(name.replaceAll(" ", ""));
    }

    protected void loadImages(String imgName) {
        img = TextureLoader.getTexture("hadesmodResources/images/relics/" + imgName + ".png");
        outlineImg = TextureLoader.getTexture("hadesmodResources/images/relics/outline/" + imgName + ".png");
    }

    public void charge() {
        flash();
        for (AbstractPower r : AbstractDungeon.player.powers) {
            if (r instanceof OnActivateSubscriber) {
                ((OnActivateSubscriber) r).receiveActivate();
            }
        }
        for (AbstractRelic r : AbstractDungeon.player.relics) {
            if (r instanceof OnActivateSubscriber) {
                ((OnActivateSubscriber) r).receiveActivate();
            }
        }
        activate();
    }

    public void onSwapped() {
        this.currentY = (float) Settings.HEIGHT - 250.0F * Settings.scale;
        this.hb.move(currentX, currentY);
    }

    public void onUnswapped() {
        this.currentY = (float) Settings.HEIGHT - 176.0F * Settings.scale;
        this.hb.move(currentX, currentY);
    }

    public boolean active() {
        return WeaponHelper.usedWeapon == this;
    }

    public abstract void activate();
}

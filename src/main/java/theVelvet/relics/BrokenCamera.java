package theVelvet.relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.blights.AbstractBlight;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import theVelvet.RWBYMod;
import theVelvet.blights.AbstractWeapon;
import theVelvet.util.TextureLoader;

import java.util.ArrayList;

import static theVelvet.RWBYMod.makeRelicOutlinePath;
import static theVelvet.RWBYMod.makeRelicPath;

public class BrokenCamera extends CustomRelic {
    public static String ID = RWBYMod.makeID("BrokenCamera");
    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("BrokenCamera.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("WeaponCharger.png"));

    public BrokenCamera() {
        super(ID, IMG, OUTLINE, RelicTier.BOSS, LandingSound.FLAT);
    }

    @Override
    public void onEquip() {
        ++AbstractDungeon.player.energy.energyMaster;
        ArrayList<AbstractBlight> manyLittleThings = new ArrayList<>();
        for (AbstractBlight q : AbstractDungeon.player.blights) {
            if (q instanceof AbstractWeapon) {
                manyLittleThings.add(q);
            }
        }
        for (int i = 0; i < 2; i++) {
            if (!manyLittleThings.isEmpty()) {
                AbstractDungeon.player.blights.remove(manyLittleThings.get(AbstractDungeon.cardRandomRng.random(manyLittleThings.size() - 1)));
            }
        }
    }

    @Override
    public void onUnequip() {
        --AbstractDungeon.player.energy.energyMaster;
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }
}

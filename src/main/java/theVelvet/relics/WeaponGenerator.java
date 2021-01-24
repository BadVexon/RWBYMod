package theVelvet.relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import theVelvet.RWBYMod;
import theVelvet.cards.Camera;
import theVelvet.util.TextureLoader;

import static theVelvet.RWBYMod.makeRelicOutlinePath;
import static theVelvet.RWBYMod.makeRelicPath;

public class WeaponGenerator extends CustomRelic {
    public static String ID = RWBYMod.makeID("WeaponGenerator");
    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("WeaponCharger.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("WeaponCharger.png"));

    public WeaponGenerator() {
        super(ID, IMG, OUTLINE, RelicTier.BOSS, LandingSound.FLAT);
    }

    public void atTurnStart() {
        flash();
        this.addToBot(new MakeTempCardInHandAction(new Camera(), 1, false));// 25
    }// 26

    @Override
    public void obtain() {
        if (AbstractDungeon.player.hasRelic(WeaponCharger.ID)) {
            for (int i = 0; i < AbstractDungeon.player.relics.size(); ++i) {
                if (AbstractDungeon.player.relics.get(i).relicId.equals(WeaponCharger.ID)) {
                    instantObtain(AbstractDungeon.player, i, true);
                    break;
                }
            }
        } else {
            super.obtain();
        }
    }

    @Override
    public boolean canSpawn() {
        return AbstractDungeon.player.hasRelic(WeaponCharger.ID);
    }

    @Override
    public String getUpdatedDescription() {
        String name = new WeaponCharger().name;
        StringBuilder sb = new StringBuilder();
        for (String word : name.split(" ")) {
            sb.append("[#").append(RWBYMod.chemColor.toString()).append("]").append(word).append("[] ");
        }
        sb.setLength(sb.length() - 1);
        sb.append("[#").append(RWBYMod.chemColor.toString()).append("]");

        return DESCRIPTIONS[0] + sb.toString() + DESCRIPTIONS[1];
    }
}

package theVelvet.relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.tempCards.Shiv;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import theVelvet.RWBYMod;
import theVelvet.WeaponHelper;
import theVelvet.actions.ActivateWeaponAction;
import theVelvet.blights.SharpRetribution;
import theVelvet.cards.Camera;
import theVelvet.util.OnActivateSubscriber;
import theVelvet.util.TextureLoader;

import static theVelvet.RWBYMod.makeRelicOutlinePath;
import static theVelvet.RWBYMod.makeRelicPath;

public class WeaponCharger extends CustomRelic {
    public static String ID = RWBYMod.makeID("WeaponCharger");
    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("WeaponCharger.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("WeaponCharger.png"));

    public WeaponCharger() {
        super(ID, IMG, OUTLINE, RelicTier.STARTER, LandingSound.FLAT);
    }

    public void atBattleStartPreDraw() {
        this.addToBot(new RelicAboveCreatureAction(AbstractDungeon.player, this));// 24
        this.addToBot(new MakeTempCardInHandAction(new Camera(), 1, false));// 25
    }// 26

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }
}

package theVelvet;

import basemod.abstracts.CustomEnergyOrb;
import basemod.abstracts.CustomPlayer;
import basemod.animations.SpriterAnimation;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.MathUtils;
import com.evacipated.cardcrawl.modthespire.lib.SpireEnum;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.EnergyManager;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.ScreenShake;
import com.megacrit.cardcrawl.localization.CharacterStrings;
import com.megacrit.cardcrawl.screens.CharSelectInfo;
import theVelvet.cards.*;
import theVelvet.relics.WeaponCharger;

import java.util.ArrayList;

import static theVelvet.RWBYMod.*;
import static theVelvet.TheVelvet.Enums.HUNTRESS_COLOR;

public class TheVelvet extends CustomPlayer {
    private static final String[] orbTextures = {
            "hadesmodResources/images/char/mainChar/orb/layer1.png",
            "hadesmodResources/images/char/mainChar/orb/layer2.png",
            "hadesmodResources/images/char/mainChar/orb/layer3.png",
            "hadesmodResources/images/char/mainChar/orb/layer4.png",
            "hadesmodResources/images/char/mainChar/orb/layer5.png",
            "hadesmodResources/images/char/mainChar/orb/layer6.png",
            "hadesmodResources/images/char/mainChar/orb/layer1d.png",
            "hadesmodResources/images/char/mainChar/orb/layer2d.png",
            "hadesmodResources/images/char/mainChar/orb/layer3d.png",
            "hadesmodResources/images/char/mainChar/orb/layer4d.png",
            "hadesmodResources/images/char/mainChar/orb/layer5d.png",};
    private static final String ID = makeID("theVelvet");
    private static final CharacterStrings characterStrings = CardCrawlGame.languagePack.getCharacterString(ID);
    private static final String[] NAMES = characterStrings.NAMES;
    private static final String[] TEXT = characterStrings.TEXT;

    public TheVelvet(String name, PlayerClass setClass) {
        super(name, setClass, new CustomEnergyOrb(orbTextures, "hadesmodResources/images/char/mainChar/orb/vfx.png", null), new SpriterAnimation(
                "hadesmodResources/images/char/mainChar/artist.scml"));
        initializeClass(null,
                SHOULDER1,
                SHOULDER2,
                CORPSE,
                getLoadout(), 20.0F, -10.0F, 166.0F, 327.0F, new EnergyManager(3));


        dialogX = (drawX + 0.0F * Settings.scale);
        dialogY = (drawY + 240.0F * Settings.scale);
    }

    @Override
    public CharSelectInfo getLoadout() {
        return new CharSelectInfo(NAMES[0], TEXT[0],
                60, 60, 0, 99, 5, this, getStartingRelics(),
                getStartingDeck(), false);
    }

    @Override
    public ArrayList<String> getStartingDeck() {
        ArrayList<String> retVal = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            retVal.add(Strike.ID);
        }
        retVal.add(SwapStrike.ID);
        for (int i = 0; i < 4; i++) {
            retVal.add(Defend.ID);
        }
        retVal.add(Dash.ID);
        retVal.add(RuyiJinguBang.ID);
        return retVal;
    }

    public ArrayList<String> getStartingRelics() {
        ArrayList<String> retVal = new ArrayList<>();
        retVal.add(WeaponCharger.ID);
        return retVal;
    }

    @Override
    public void doCharSelectScreenSelectEffect() {
        CardCrawlGame.sound.playA("UNLOCK_PING", MathUtils.random(-0.2F, 0.2F));
        CardCrawlGame.screenShake.shake(ScreenShake.ShakeIntensity.LOW, ScreenShake.ShakeDur.SHORT,
                false);
    }

    @Override
    public String getCustomModeCharacterButtonSoundKey() {
        return "UNLOCK_PING";
    }

    @Override
    public int getAscensionMaxHPLoss() {
        return 7;
    }

    @Override
    public AbstractCard.CardColor getCardColor() {
        return HUNTRESS_COLOR;
    }

    @Override
    public Color getCardTrailColor() {
        return chemColor.cpy();
    }

    @Override
    public BitmapFont getEnergyNumFont() {
        return FontHelper.energyNumFontRed;
    }

    @Override
    public String getLocalizedCharacterName() {
        return NAMES[0];
    }

    @Override
    public AbstractCard getStartCardForEvent() {
        return new RuyiJinguBang();
    }

    @Override
    public String getTitle(AbstractPlayer.PlayerClass playerClass) {
        return NAMES[1];
    }

    @Override
    public AbstractPlayer newInstance() {
        return new TheVelvet(name, chosenClass);
    }

    @Override
    public Color getCardRenderColor() {
        return chemColor.cpy();
    }

    @Override
    public Color getSlashAttackColor() {
        return chemColor.cpy();
    }

    @Override
    public AbstractGameAction.AttackEffect[] getSpireHeartSlashEffect() {
        return new AbstractGameAction.AttackEffect[]{
                AbstractGameAction.AttackEffect.FIRE,
                AbstractGameAction.AttackEffect.BLUNT_HEAVY,
                AbstractGameAction.AttackEffect.FIRE};
    }

    @Override
    public String getSpireHeartText() {
        return TEXT[1];
    }

    @Override
    public String getVampireText() {
        return TEXT[2];
    }

    public static class Enums {
        @SpireEnum
        public static AbstractPlayer.PlayerClass THE_HUNTRESS;
        @SpireEnum(name = "HUNTRESS_COLOR")
        public static AbstractCard.CardColor HUNTRESS_COLOR;
        @SpireEnum(name = "HUNTRESS_COLOR")
        @SuppressWarnings("unused")
        public static CardLibrary.LibraryType LIBRARY_COLOR;
    }
}

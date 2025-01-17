package theVelvet;

import basemod.BaseMod;
import basemod.ReflectionHacks;
import basemod.interfaces.*;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;
import com.evacipated.cardcrawl.mod.stslib.Keyword;
import com.evacipated.cardcrawl.modthespire.Loader;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.google.gson.Gson;
import com.megacrit.cardcrawl.blights.AbstractBlight;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.GameCursor;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.localization.CharacterStrings;
import com.megacrit.cardcrawl.localization.RelicStrings;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.unlock.UnlockTracker;
import javassist.CtClass;
import javassist.Modifier;
import javassist.NotFoundException;
import org.clapper.util.classutil.*;
import theVelvet.blights.*;
import theVelvet.patches.TurnStartCheck;
import theVelvet.relics.BrokenCamera;
import theVelvet.relics.WeaponCharger;
import theVelvet.relics.WeaponGenerator;
import theVelvet.util.SecondDamage;
import theVelvet.util.SillyVariable;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collection;

@SuppressWarnings({"ConstantConditions", "unused", "WeakerAccess"})
@SpireInitializer
public class RWBYMod implements
        EditCardsSubscriber,
        EditRelicsSubscriber,
        EditStringsSubscriber,
        EditKeywordsSubscriber,
        EditCharactersSubscriber,
        PostBattleSubscriber,
        StartGameSubscriber,
        OnCardUseSubscriber,
        PostDrawSubscriber,
        OnStartBattleSubscriber {
    public static final String SHOULDER1 = "hadesmodResources/images/char/mainChar/shoulder.png";
    public static final String SHOULDER2 = "hadesmodResources/images/char/mainChar/shoulder2.png";
    public static final String CORPSE = "hadesmodResources/images/char/mainChar/corpse.png";
    private static final String ATTACK_S_ART = "hadesmodResources/images/512/canvas_attack_s.png";
    private static final String SKILL_S_ART = "hadesmodResources/images/512/canvas_skill_s.png";
    private static final String POWER_S_ART = "hadesmodResources/images/512/canvas_power_s.png";
    private static final String CARD_ENERGY_S = "hadesmodResources/images/512/card_default_gray_orb.png";
    private static final String TEXT_ENERGY = "hadesmodResources/images/512/card_small_orb.png";
    private static final String ATTACK_L_ART = "hadesmodResources/images/1024/canvas_attack.png";
    private static final String SKILL_L_ART = "hadesmodResources/images/1024/canvas_skill.png";
    private static final String POWER_L_ART = "hadesmodResources/images/1024/canvas_power.png";
    private static final String CARD_ENERGY_L = "hadesmodResources/images/1024/card_default_gray_orb.png";
    private static final String CHARSELECT_BUTTON = "hadesmodResources/images/charSelect/charButton.png";
    private static final String CHARSELECT_PORTRAIT = "hadesmodResources/images/charSelect/charBG.png";
    private static String modID;

    public static boolean countDraws = false;
    public static int drawnCards = 0;

    public static Color chemColor = new Color(MathUtils.random(), MathUtils.random(), MathUtils.random(), 1);

    public RWBYMod() {

        BaseMod.subscribe(this);

        modID = "hadesmod";

        BaseMod.addColor(TheVelvet.Enums.HUNTRESS_COLOR, chemColor, chemColor, chemColor,
                chemColor, chemColor, chemColor, chemColor,
                ATTACK_S_ART, SKILL_S_ART, POWER_S_ART, CARD_ENERGY_S,
                ATTACK_L_ART, SKILL_L_ART, POWER_L_ART,
                CARD_ENERGY_L, TEXT_ENERGY);
    }

    public static String makeCardPath(String resourcePath) {
        return getModID() + "Resources/images/cards/" + resourcePath;
    }

    public static String makeRelicPath(String resourcePath) {
        return getModID() + "Resources/images/relics/" + resourcePath;
    }

    public static String makeRelicOutlinePath(String resourcePath) {
        return getModID() + "Resources/images/relics/outline/" + resourcePath;
    }

    public static String makePowerPath(String resourcePath) {
        return getModID() + "Resources/images/powers/" + resourcePath;
    }

    public static String getModID() {
        return modID;
    }

    public static void initialize() {
        RWBYMod chemistMod = new RWBYMod();
    }

    public static String makeID(String idText) {
        return getModID() + ":" + idText;
    }

    @Override
    public void receiveEditCharacters() {
        BaseMod.addCharacter(new TheVelvet("the Velvet", TheVelvet.Enums.THE_HUNTRESS),
                CHARSELECT_BUTTON, CHARSELECT_PORTRAIT, TheVelvet.Enums.THE_HUNTRESS);
    }

    @Override
    public void receiveEditRelics() {
        BaseMod.addRelicToCustomPool(new WeaponCharger(), TheVelvet.Enums.HUNTRESS_COLOR);
        BaseMod.addRelicToCustomPool(new BrokenCamera(), TheVelvet.Enums.HUNTRESS_COLOR);
        BaseMod.addRelicToCustomPool(new WeaponGenerator(), TheVelvet.Enums.HUNTRESS_COLOR);
    }

    @Override
    public void receiveEditCards() {
        BaseMod.addDynamicVariable(new SillyVariable());
        BaseMod.addDynamicVariable(new SecondDamage());
        try {
            autoAddCards();
        } catch (URISyntaxException | IllegalAccessException | InstantiationException | NotFoundException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private static void autoAddCards()
            throws URISyntaxException, IllegalAccessException, InstantiationException, NotFoundException, ClassNotFoundException {
        ClassFinder finder = new ClassFinder();
        URL url = RWBYMod.class.getProtectionDomain().getCodeSource().getLocation();
        finder.add(new File(url.toURI()));

        ClassFilter filter =
                new AndClassFilter(
                        new NotClassFilter(new InterfaceOnlyClassFilter()),
                        new NotClassFilter(new AbstractClassFilter()),
                        new ClassModifiersClassFilter(Modifier.PUBLIC),
                        new CardFilter()
                );
        Collection<ClassInfo> foundClasses = new ArrayList<>();
        finder.findClasses(foundClasses, filter);

        for (ClassInfo classInfo : foundClasses) {
            CtClass cls = Loader.getClassPool().get(classInfo.getClassName());
            if (cls.hasAnnotation(CardIgnore.class)) {
                continue;
            }
            boolean isCard = false;
            CtClass superCls = cls;
            while (superCls != null) {
                superCls = superCls.getSuperclass();
                if (superCls == null) {
                    break;
                }
                if (superCls.getName().equals(AbstractCard.class.getName())) {
                    isCard = true;
                    break;
                }
            }
            if (!isCard) {
                continue;
            }
            System.out.println(classInfo.getClassName());
            AbstractCard card = (AbstractCard) Loader.getClassPool().getClassLoader().loadClass(cls.getName()).newInstance();
            BaseMod.addCard(card);
            if (cls.hasAnnotation(CardNoSeen.class)) {
                UnlockTracker.hardUnlockOverride(card.cardID);
            } else {
                UnlockTracker.unlockCard(card.cardID);
            }
        }
    }


    @Override
    public void receiveEditStrings() {
        BaseMod.loadCustomStringsFile(CardStrings.class, getModID() + "Resources/localization/eng/Cardstrings.json");

        BaseMod.loadCustomStringsFile(RelicStrings.class, getModID() + "Resources/localization/eng/Relicstrings.json");

        BaseMod.loadCustomStringsFile(CharacterStrings.class, getModID() + "Resources/localization/eng/Charstrings.json");
    }

    @Override
    public void receiveEditKeywords() {
        Gson gson = new Gson();
        String json = Gdx.files.internal(getModID() + "Resources/localization/eng/Keywordstrings.json").readString(String.valueOf(StandardCharsets.UTF_8));
        com.evacipated.cardcrawl.mod.stslib.Keyword[] keywords = gson.fromJson(json, com.evacipated.cardcrawl.mod.stslib.Keyword[].class);

        if (keywords != null) {
            for (Keyword keyword : keywords) {
                BaseMod.addKeyword(modID, keyword.PROPER_NAME, keyword.NAMES, keyword.DESCRIPTION);
            }
        }
    }

    @Override
    public void receiveCardUsed(AbstractCard abstractCard) {
        if (WeaponHelper.usedWeapon instanceof CrescentRose && abstractCard.type == AbstractCard.CardType.ATTACK) {
            AbstractWeapon b = WeaponHelper.usedWeapon;
            b.flash();
            abstractCard.exhaust = true;
        }
    }

    @Override
    public void receivePostDraw(AbstractCard abstractCard) {
        if (countDraws)
            drawnCards++;
    }

    @Override
    public void receiveStartGame() {
        if (AbstractDungeon.player.chosenClass == TheVelvet.Enums.THE_HUNTRESS) {
            ReflectionHacks.setPrivate(CardCrawlGame.cursor, GameCursor.class, "img", ImageMaster.loadImage("hadesmodResources/images/cursor/thiccCursor.png"));
        }
        if (AbstractDungeon.player instanceof TheVelvet && !CardCrawlGame.loadingSave) {
            spawnBlightAndObtain(new Fulcrum());
            spawnBlightAndObtain(new Gianduja());
            spawnBlightAndObtain(new CroceaMors());
            spawnBlightAndObtain(new SharpRetribution());
            spawnBlightAndObtain(new CrescentRose());
        }
    }

    private void spawnBlightAndObtain(AbstractBlight blight) {
        blight.spawn(Settings.WIDTH / 2F, Settings.HEIGHT);// 693
        blight.obtain();// 694
        blight.isObtained = true;// 695
        blight.isAnimating = false;// 696
        blight.isDone = false;// 697
        blight.flash();// 698
    }

    @Override
    public void receiveOnBattleStart(AbstractRoom abstractRoom) {
        TurnStartCheck.yesKitten = true;
    }

    @Override
    public void receivePostBattle(AbstractRoom abstractRoom) {
        WeaponHelper.weaponsOff();
    }
}

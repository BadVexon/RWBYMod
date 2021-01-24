package theVelvet.patches;


import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.blights.AbstractBlight;
import com.megacrit.cardcrawl.helpers.BlightHelper;
import theVelvet.blights.*;

@SpirePatch(clz = BlightHelper.class, method = "getBlight")
public class WeaponSavePatches {
    @SpirePrefixPatch
    public static SpireReturn<AbstractBlight> returnBlight(String ID) {
        switch (ID) {
            case "hadesmod:CrescentRose":
                return SpireReturn.Return(new CrescentRose());
            case "hadesmod:CroceaMors":
                return SpireReturn.Return(new CroceaMors());
            case "hadesmod:Fulcrum":
                return SpireReturn.Return(new Fulcrum());
            case "hadesmod:Gianduja":
                return SpireReturn.Return(new Gianduja());
            case "hadesmod:SharpRetribution":
                return SpireReturn.Return(new SharpRetribution());
        }

        return SpireReturn.Continue();
    }
}
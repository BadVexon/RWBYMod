package theVelvet.patches;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import javassist.CannotCompileException;
import javassist.expr.ExprEditor;
import javassist.expr.MethodCall;
import theVelvet.util.ModifyDamagePower;

@SpirePatch(clz = AbstractCard.class, method = "calculateCardDamage")
public class ModifyDamagePowerHook {
    private static boolean firstMatch = true;

    public static ExprEditor Instrument() {
        return new ExprEditor() {
            @Override
            public void edit(MethodCall m) throws CannotCompileException {
                if (m.getClassName().equals(AbstractPower.class.getName()) && m.getMethodName().equals("atDamageGive")) {
                    if (firstMatch) {
                        firstMatch = false;
                        m.replace("{" +
                                "tmp = $proceed(tmp, this.damageTypeForTurn, this);" +
                                "$_ = " + ModifyDamagePowerHook.class.getName() + ".Do($0, tmp, this, mo);" +
                                "}");
                    } else {
                        m.replace("{" +
                                "tmp[i] = $proceed(tmp[i], this.damageTypeForTurn, this);" +
                                "$_ = " + ModifyDamagePowerHook.class.getName() + ".Do($0, tmp[i], this, mo);" +
                                "}");
                    }
                }
            }
        };
    }

    @SuppressWarnings("unused")
    public static float Do(AbstractPower p, float damageAmount, AbstractCard c, AbstractMonster m) {
        if (p instanceof ModifyDamagePower && m != null) {
            return ((ModifyDamagePower) p).calculateCardDamagePower(damageAmount, c, m);
        }
        return damageAmount;
    }
}
package theVelvet.util;

import basemod.abstracts.DynamicVariable;
import com.megacrit.cardcrawl.cards.AbstractCard;
import theVelvet.cards.AbstractHadesCard;

public class SillyVariable extends DynamicVariable {

    @Override
    public String key() {
        return "zzz";
    }

    @Override
    public boolean isModified(AbstractCard card) {
        return ((AbstractHadesCard) card).isSillyModified;
    }

    @Override
    public int value(AbstractCard card) {
        return ((AbstractHadesCard) card).silly;
    }

    public void setIsModified(AbstractCard card, boolean v) {
        if (card instanceof AbstractHadesCard) {
            ((AbstractHadesCard) card).isSillyModified = v;
        }
    }

    @Override
    public int baseValue(AbstractCard card) {
        return ((AbstractHadesCard) card).baseSilly;
    }

    @Override
    public boolean upgraded(AbstractCard card) {
        return ((AbstractHadesCard) card).upgradedSilly;
    }
}
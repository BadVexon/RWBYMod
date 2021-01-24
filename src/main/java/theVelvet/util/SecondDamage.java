package theVelvet.util;

import basemod.abstracts.DynamicVariable;
import com.megacrit.cardcrawl.cards.AbstractCard;
import theVelvet.cards.AbstractHadesCard;

public class SecondDamage extends DynamicVariable {

    @Override
    public String key() {
        return "secondDamage";
    }

    @Override
    public boolean isModified(AbstractCard card) {
        return ((AbstractHadesCard) card).isSecondDamageModified;
    }

    @Override
    public int value(AbstractCard card) {
        return ((AbstractHadesCard) card).secondDamage;
    }

    @Override
    public int baseValue(AbstractCard card) {
        return ((AbstractHadesCard) card).baseSecondDamage;
    }

    @Override
    public boolean upgraded(AbstractCard card) {
        return ((AbstractHadesCard) card).upgradedSecondDamage;
    }
}
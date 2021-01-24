package theVelvet.cardmods;

import basemod.abstracts.AbstractCardModifier;
import com.evacipated.cardcrawl.mod.stslib.fields.cards.AbstractCard.ExhaustiveField;
import com.megacrit.cardcrawl.cards.AbstractCard;

public class TemporaryCardModifier extends AbstractCardModifier {

    private boolean addedExhaust = false;
    private boolean addedEthereal = false;
    private boolean removedRetain = false;
    private boolean removedExhaustive = false;
    private int oldExhaustive = -1;

    private String oldDescription;

    @Override
    public String modifyDescription(String rawDescription, AbstractCard card) {
        String s = rawDescription;
        oldDescription = s;
        if (addedEthereal) {
            s = "Ethereal. NL " + s;
        }
        if (addedExhaust) {
            s = s + " NL Exhaust.";
        }
        if (removedRetain) {
            s = s.replaceAll("Retain. NL ", "");
        }
        if (removedExhaustive) {
            s = s.replaceAll("Exhaustive !stslib:ex!.", "");
        }
        return s;
    }

    @Override
    public void onInitialApplication(AbstractCard card) {
        if (!card.isEthereal) {
            addedEthereal = true;
            card.isEthereal = true;
        }
        if (!card.exhaust) {
            addedExhaust = true;
            card.exhaust = true;
        }
        if (card.selfRetain) {
            removedRetain = true;
            card.selfRetain = false;
        }
        if (ExhaustiveField.ExhaustiveFields.exhaustive.get(card) != -1) {
            removedExhaustive = true;
            oldExhaustive = ExhaustiveField.ExhaustiveFields.exhaustive.get(card);
            ExhaustiveField.ExhaustiveFields.exhaustive.set(card, -1);
        }
    }

    @Override
    public void onRemove(AbstractCard card) {
        if (addedEthereal) {
            card.isEthereal = false;
        }
        if (addedExhaust) {
            card.exhaust = false;
        }
        if (removedRetain) {
            card.selfRetain = true;
        }
        if (removedExhaustive) {
            ExhaustiveField.ExhaustiveFields.exhaustive.set(card, oldExhaustive);
        }
        card.rawDescription = oldDescription;
    }

    @Override
    public AbstractCardModifier makeCopy() {
        return new TemporaryCardModifier();
    }
}

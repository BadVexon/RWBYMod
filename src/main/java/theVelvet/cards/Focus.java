package theVelvet.cards;

import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theVelvet.cardmods.BloodcastModifier;

import java.util.ArrayList;

import static com.megacrit.cardcrawl.dungeons.AbstractDungeon.cardRandomRng;

public class Focus extends AbstractHadesCard {

    public final static String ID = makeID("Focus");

    //stupid intellij stuff SKILL, SELF, UNCOMMON

    public Focus() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        exhaust = true;
    }

    public static AbstractCard returnTrulyRandomStrikeInCombat() {
        ArrayList<AbstractCard> list = new ArrayList<>();// 1201
        for (AbstractCard q : CardLibrary.getAllCards()) {
            if (q.color != CardColor.CURSE && q.type != CardType.CURSE && q.type != CardType.STATUS && q.hasTag(CardTags.STRIKE) && !q.hasTag(CardTags.HEALING)) {
                list.add(q.makeCopy());
            }
        }

        return list.get(cardRandomRng.random(list.size() - 1));// 1217
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractCard c = returnTrulyRandomStrikeInCombat();
        if (upgraded && isBranchUpgrade()) {
            c.upgrade();
        }
        c.setCostForTurn(0);
        makeInHand(c);
    }

    public void baseUpgrade() {
        CardModifierManager.addModifier(this, new BloodcastModifier());
    }


    public void branchUpgrade() {
        rawDescription = UPGRADE_DESCRIPTION;
        initializeDescription();
    }
}
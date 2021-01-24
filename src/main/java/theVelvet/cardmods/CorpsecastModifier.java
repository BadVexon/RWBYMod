/*
package theVelvet.cardmods;

import basemod.abstracts.AbstractCardModifier;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theVelvet.patches.StatuePatchue;

public class CorpsecastModifier extends AbstractCardModifier {

    @Override
    public String modifyDescription(String rawDescription, AbstractCard card) {
        return "hadesmod:Corpsecast. NL " + rawDescription;
    }

    @Override
    public boolean isInherent(AbstractCard card) {
        return true;
    }

    @Override
    public int getAlternateResource(AbstractCard card) {
        int x = 0;
        for (AbstractMonster q : AbstractDungeon.getCurrRoom().monsters.monsters) {
            if ((q.isDying || q.isDead) && !StatuePatchue.nonStatueList.contains(q))
                x++;
        }
        return x;
    }

    @Override
    public boolean prioritizeAlternateCost(AbstractCard card) {
        return true;
    }

    @Override
    public boolean canSplitCost(AbstractCard card) {
        return true;
    }

    @Override
    public int spendAlternateCost(AbstractCard card, int costToSpend) {
        int x = costToSpend;
        for (AbstractMonster m : AbstractDungeon.getCurrRoom().monsters.monsters) {
            if ((m.isDying || m.isDead) && !StatuePatchue.nonStatueList.contains(m)) {
                StatuePatchue.nonStatueList.add(m);
                x--;
            }
        }
        return x;
    }

    @Override
    public AbstractCardModifier makeCopy() {
        return new CorpsecastModifier();
    }
}
*/
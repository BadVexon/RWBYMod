package theVelvet.cards;

import com.evacipated.cardcrawl.mod.stslib.cards.interfaces.BranchingUpgradesCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.actions.unique.CalculatedGambleAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class SlotSlash extends AbstractHadesCard implements BranchingUpgradesCard {

    public final static String ID = makeID("SlotSlash");

    //stupid intellij stuff ATTACK, ALL, UNCOMMON

    private static final int DAMAGE = 3;
    private static final int UPG_DAMAGE = 1;

    public SlotSlash() {
        super(ID, 1, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ALL);
        baseDamage = DAMAGE;
        isMultiDamage = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new CalculatedGambleAction(false));
        for (AbstractCard q : AbstractDungeon.player.hand.group) {
            if (q.selfRetain && q != this) {
                atb(new ExhaustSpecificCardAction(q, p.hand));
                atb(new ExhaustSpecificCardAction(q, p.discardPile));
                atb(new ExhaustSpecificCardAction(q, p.drawPile));
                allDmg(AbstractGameAction.AttackEffect.SLASH_HORIZONTAL);
            }
        }
    }

    public void baseUpgrade() {
        upgradeDamage(UPG_DAMAGE);
    }


    public void branchUpgrade() {
        selfRetain = true;
        rawDescription = UPGRADE_DESCRIPTION;
        initializeDescription();
    }
}
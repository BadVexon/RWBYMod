package theVelvet.cards;

import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theVelvet.actions.BloodcastDrawnStrikesAction;
import theVelvet.cardmods.BloodcastModifier;

public class CrescentDexterity extends AbstractHadesCard {

    public final static String ID = makeID("CrescentDexterity");

    //stupid intellij stuff SKILL, SELF, UNCOMMON

    private static final int MAGIC = 2;
    private static final int UPG_MAGIC = 1;

    public CrescentDexterity() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = MAGIC;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new DrawCardAction(magicNumber));
        atb(new BloodcastDrawnStrikesAction());
    }

    public void baseUpgrade() {

        upgradeMagicNumber(UPG_MAGIC);
    }


    public void branchUpgrade() {

        upgradeBaseCost(2);
        CardModifierManager.addModifier(this, new BloodcastModifier());
    }
}
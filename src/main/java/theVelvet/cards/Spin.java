package theVelvet.cards;

import com.evacipated.cardcrawl.mod.stslib.cards.interfaces.BranchingUpgradesCard;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Spin extends AbstractHadesCard implements BranchingUpgradesCard {

    public final static String ID = makeID("Spin");

    //stupid intellij stuff SKILL, SELF, UNCOMMON

    private static final int MAGIC = 3;
    private static final int UPG_MAGIC = 1;

    public Spin() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = MAGIC;
        selfRetain = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new DrawCardAction(magicNumber));
    }

    public void baseUpgrade() {

        upgradeMagicNumber(UPG_MAGIC);
    }


    public void branchUpgrade() {

        isInnate = true;
        rawDescription = UPGRADE_DESCRIPTION;
        initializeDescription();
    }
}
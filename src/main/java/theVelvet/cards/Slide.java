package theVelvet.cards;

import com.evacipated.cardcrawl.mod.stslib.cards.interfaces.BranchingUpgradesCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theVelvet.actions.ChooseWeaponAction;

public class Slide extends AbstractHadesCard implements BranchingUpgradesCard {

    public final static String ID = makeID("Slide");

    //stupid intellij stuff SKILL, SELF, COMMON

    private static final int BLOCK = 7;
    private static final int UPG_BLOCK = 3;
    private static final int UPG_BLOCK2 = -3;

    public Slide() {
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        baseBlock = BLOCK;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
        atb(new ChooseWeaponAction());
    }

    public void baseUpgrade() {

        upgradeBlock(UPG_BLOCK);
    }


    public void branchUpgrade() {

        upgradeBlock(UPG_BLOCK2);
        upgradeBaseCost(0);
    }
}
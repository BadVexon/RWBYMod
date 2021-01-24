package theVelvet.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theVelvet.powers.DugInPower;

public class DigIn extends AbstractHadesCard {

    public final static String ID = makeID("DigIn");

    //stupid intellij stuff Skill, SELF, UNCOMMON

    private static final int BLOCK = 12;
    private static final int UPG_BLOCK = 4;

    public DigIn() {
        super(ID, 2, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        baseBlock = BLOCK;
        selfRetain = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
        applyToSelf(new DugInPower(1));
    }

    public void baseUpgrade() {
        upgradeBaseCost(1);
        upgradeBlock(-3);
    }


    public void branchUpgrade() {

        upgradeBlock(UPG_BLOCK);
    }
}
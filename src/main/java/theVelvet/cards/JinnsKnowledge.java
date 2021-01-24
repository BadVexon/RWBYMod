package theVelvet.cards;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class JinnsKnowledge extends AbstractHadesCard {

    public final static String ID = makeID("JinnsKnowledge");

    //stupid intellij stuff SKILL, SELF, COMMON

    private static final int BLOCK = 4;
    private static final int UPG_BLOCK = 3;
    private static final int MAGIC = 2;
    private static final int UPG_MAGIC2 = 1;

    public JinnsKnowledge() {
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        baseBlock = BLOCK;
        baseMagicNumber = magicNumber = MAGIC;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
        atb(new DrawCardAction(p, magicNumber));
    }

    public void baseUpgrade() {

        upgradeBlock(UPG_BLOCK);
    }


    public void branchUpgrade() {

        upgradeMagicNumber(UPG_MAGIC2);
    }
}
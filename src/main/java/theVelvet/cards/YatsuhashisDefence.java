package theVelvet.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class YatsuhashisDefence extends AbstractHadesCard {

    public final static String ID = makeID("YatsuhashisDefence");

    //stupid intellij stuff SKILL, SELF, COMMON

    private static final int BLOCK = 6;
    private static final int UPG_BLOCK = 4;
    private static final int MAGIC = 1;
    private static final int UPG_MAGIC2 = 1;

    public YatsuhashisDefence() {
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF_AND_ENEMY);
        baseBlock = BLOCK;
        baseMagicNumber = magicNumber = MAGIC;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
        applyToEnemy(m, autoVuln(m, magicNumber));
    }

    public void baseUpgrade() {
        upgradeBlock(UPG_BLOCK);
    }


    public void branchUpgrade() {
        upgradeMagicNumber(UPG_MAGIC2);
    }
}
package theVelvet.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theVelvet.CardIgnore;

@CardIgnore
public class PhotographicMemory extends AbstractHadesCard {

    public final static String ID = makeID("PhotographicMemory");

    //stupid intellij stuff SKILL, SELF, RARE

    private static final int MAGIC = 4;
    private static final int UPG_MAGIC = 2;

    public PhotographicMemory() {
        super(ID, 3, CardType.SKILL, CardRarity.RARE, CardTarget.SELF);
        baseMagicNumber = magicNumber = MAGIC;
        exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        for (int i = 0; i < magicNumber; i++) {
            awep();
        }
    }

    public void baseUpgrade() {

        upgradeMagicNumber(UPG_MAGIC);
    }


    public void branchUpgrade() {

        upgradeBaseCost(2);
    }
}
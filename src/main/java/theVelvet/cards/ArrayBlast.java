package theVelvet.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theVelvet.powers.ArrayBlastPower;

public class ArrayBlast extends AbstractHadesCard {

    public final static String ID = makeID("ArrayBlast");

    //stupid intellij stuff POWER, SELF, UNCOMMON

    private static final int MAGIC = 4;

    public ArrayBlast() {
        super(ID, 1, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = MAGIC;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new ArrayBlastPower(magicNumber));
        if (upgraded && isBranchUpgrade()) {
            awep();
        }
    }

    public void baseUpgrade() {

        upgradeMagicNumber(1);
    }


    public void branchUpgrade() {

        rawDescription = UPGRADE_DESCRIPTION;
        initializeDescription();
    }

}
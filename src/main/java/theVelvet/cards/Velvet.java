package theVelvet.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theVelvet.powers.VelvetPower;

public class Velvet extends AbstractHadesCard {

    public final static String ID = makeID("Velvet");

    //stupid intellij stuff POWER, SELF, RARE

    public Velvet() {
        super(ID, 2, CardType.POWER, CardRarity.RARE, CardTarget.SELF);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new VelvetPower(2));
    }

    public void baseUpgrade() {

        isInnate = true;
        rawDescription = UPGRADE_DESCRIPTION;
        initializeDescription();
    }


    public void branchUpgrade() {

        upgradeBaseCost(1);
    }
}
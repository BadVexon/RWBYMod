package theVelvet.cards;

import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theVelvet.cardmods.BloodcastModifier;
import theVelvet.powers.HolyShieldPower;

public class MiloAndAkouo extends AbstractHadesCard {

    public final static String ID = makeID("MiloAndAkouo");

    //stupid intellij stuff POWER, SELF, RARE

    private static final int MAGIC = 1;
    private static final int UPG_MAGIC = 1;

    public MiloAndAkouo() {
        super(ID, 2, CardType.POWER, CardRarity.RARE, CardTarget.SELF);
        baseMagicNumber = magicNumber = MAGIC;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new HolyShieldPower(magicNumber));
    }

    public void baseUpgrade() {

        upgradeMagicNumber(UPG_MAGIC);
    }


    public void branchUpgrade() {

        CardModifierManager.addModifier(this, new BloodcastModifier());
    }
}
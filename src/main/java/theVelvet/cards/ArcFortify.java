package theVelvet.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theVelvet.powers.ReflectDebuffsPower;

public class ArcFortify extends AbstractHadesCard {

    public final static String ID = makeID("ArcFortify");

    //stupid intellij stuff SKILL, SELF, UNCOMMON

    private static final int BLOCK = 7;
    private static final int UPG_BLOCK2 = 5;

    public ArcFortify() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        baseBlock = BLOCK;
        exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
        applyToSelf(new ReflectDebuffsPower(1));
    }

    public void baseUpgrade() {

        upgradeBlock(UPG_BLOCK2);
    }


    public void branchUpgrade() {

        selfRetain = true;
        rawDescription = UPGRADE_DESCRIPTION;
        initializeDescription();
    }

}
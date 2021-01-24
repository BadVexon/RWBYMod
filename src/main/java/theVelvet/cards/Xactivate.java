package theVelvet.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import theVelvet.actions.ChooseWeaponAction;
import theVelvet.actions.PerformXAction;
import theVelvet.actions.XactivateAction;

public class Xactivate extends AbstractHadesCard {

    public final static String ID = makeID("Xactivate");

    //stupid intellij stuff SKILL, SELF, UNCOMMON

    private static final int MAGIC = 0;
    private static final int UPG_MAGIC = 1;

    public Xactivate() {
        super(ID, -1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = MAGIC;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        if (upgraded && isBranchUpgrade())
            atb(new ChooseWeaponAction());
        if (energyOnUse < EnergyPanel.totalCount) {
            energyOnUse = EnergyPanel.totalCount;
        }
        XactivateAction r = new XactivateAction(magicNumber);
        atb(new PerformXAction(r, p, energyOnUse, freeToPlayOnce));
    }

    public void baseUpgrade() {

        upgradeMagicNumber(UPG_MAGIC);
        rawDescription = UPGRADE_DESCRIPTION;
        initializeDescription();
    }


    public void branchUpgrade() {

        rawDescription = EXTENDED_DESCRIPTION[0];
        initializeDescription();
    }
}
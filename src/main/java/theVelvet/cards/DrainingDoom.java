package theVelvet.cards;

import com.evacipated.cardcrawl.mod.stslib.fields.cards.AbstractCard.GraveField;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theVelvet.powers.DrainingDoomPower;

public class DrainingDoom extends AbstractHadesCard {

    public final static String ID = makeID("DrainingDoom");

    //stupid intellij stuff POWER, SELF, UNCOMMON

    public DrainingDoom() {
        super(ID, 3, CardType.POWER, CardRarity.RARE, CardTarget.SELF);
        isEthereal = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        for (AbstractMonster q : monsterList()) {
            applyToEnemy(q, new DrainingDoomPower(1));
        }
    }

    public void baseUpgrade() {
        upgradeBaseCost(2);
    }

    public void branchUpgrade() {
        isEthereal = false;
        GraveField.grave.set(this, true);
        rawDescription = UPGRADE_DESCRIPTION;
        initializeDescription();
    }
}
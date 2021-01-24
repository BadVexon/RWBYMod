package theVelvet.cards;

import com.megacrit.cardcrawl.cards.tempCards.Insight;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theVelvet.powers.AlternateAnesidoraPower;

public class AlternateAnesidora extends AbstractHadesCard {

    public final static String ID = makeID("AlternateAnesidora");

    //stupid intellij stuff POWER, SELF, UNCOMMON

    private static final int MAGIC = 1;

    public AlternateAnesidora() {
        super(ID, 2, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = MAGIC;
        cardsToPreview = new Insight();
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new AlternateAnesidoraPower(magicNumber));
    }

    public void baseUpgrade() {
        upgradeBaseCost(1);
    }


    public void branchUpgrade() {
        isInnate = true;
        rawDescription = UPGRADE_DESCRIPTION;
        initializeDescription();
    }
}
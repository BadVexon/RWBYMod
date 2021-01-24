package theVelvet.cards;

import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theVelvet.cardmods.GoldcastModifier;
import theVelvet.powers.GunCameraPower;

public class GunCameras extends AbstractHadesCard {

    public final static String ID = makeID("GunCameras");

    //stupid intellij stuff POWER, SELF, UNCOMMON

    private static final int MAGIC = 1;

    public GunCameras() {
        super(ID, 1, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = MAGIC;
        cardsToPreview = new Camera();
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new GunCameraPower(magicNumber));
        if (upgraded && isBranchUpgrade())
            makeInHand(new Camera());
    }

    public void baseUpgrade() {

        CardModifierManager.addModifier(this, new GoldcastModifier());
    }


    public void branchUpgrade() {

        rawDescription = UPGRADE_DESCRIPTION;
        initializeDescription();
    }
}
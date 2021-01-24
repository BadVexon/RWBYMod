package theVelvet.cards;

import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theVelvet.cardmods.GoldcastModifier;
import theVelvet.powers.StrikeBlockPower;

public class Strikeshield extends AbstractHadesCard {

    public final static String ID = makeID("Strikeshield");

    //stupid intellij stuff SKILL, SELF, COMMON

    private static final int BLOCK = 4;
    private static final int UPG_BLOCK = 1;
    private static final int MAGIC = 4;
    private static final int UPG_MAGIC2 = 4;

    public Strikeshield() {
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        baseBlock = BLOCK;
        baseMagicNumber = magicNumber = MAGIC;
        tags.add(CardTags.STRIKE);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
        applyToSelf(new StrikeBlockPower(magicNumber));
    }

    public void baseUpgrade() {

        upgradeBlock(UPG_BLOCK);
        upgradeMagicNumber(1);
    }


    public void branchUpgrade() {

        CardModifierManager.addModifier(this, new GoldcastModifier());
    }
}
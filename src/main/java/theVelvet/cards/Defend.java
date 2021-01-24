package theVelvet.cards;

import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theVelvet.cardmods.BloodcastModifier;

public class Defend extends AbstractHadesCard {

    public final static String ID = makeID("Defend");

    //stupid intellij stuff SKILL, SELF, BASIC

    private static final int BLOCK = 5;
    private static final int UPG_BLOCK = 3;

    public Defend() {
        super(ID, 1, CardType.SKILL, CardRarity.BASIC, CardTarget.SELF);
        baseBlock = BLOCK;
        tags.add(CardTags.STARTER_DEFEND);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
    }

    public void baseUpgrade() {

        upgradeBlock(UPG_BLOCK);
    }


    public void branchUpgrade() {

        CardModifierManager.addModifier(this, new BloodcastModifier());
        upgradeBlock(-1);
    }
}
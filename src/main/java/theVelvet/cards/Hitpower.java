package theVelvet.cards;

import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theVelvet.cardmods.BloodcastModifier;

public class Hitpower extends AbstractHadesCard {

    public final static String ID = makeID("Hitpower");

    //stupid intellij stuff SKILL, SELF, UNCOMMON

    public Hitpower() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new AbstractGameAction() {
            @Override
            public void update() {
                isDone = true;
                int i = 0;
                for (AbstractCard q : AbstractDungeon.actionManager.cardsPlayedThisTurn) {
                    if (q.hasTag(CardTags.STRIKE)) {
                        i++;
                    }
                }
                addToTop(new GainEnergyAction(i));
            }
        });
    }

    public void baseUpgrade() {

        selfRetain = true;
        rawDescription = UPGRADE_DESCRIPTION;
        initializeDescription();
    }


    public void branchUpgrade() {

        CardModifierManager.addModifier(this, new BloodcastModifier());
    }
}
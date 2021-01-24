package theVelvet.cards;

import basemod.helpers.CardModifierManager;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theVelvet.cardmods.BloodcastModifier;
import theVelvet.cardmods.GoldcastModifier;

public class MidasTouch extends AbstractHadesCard {

    public final static String ID = makeID("MidasTouch");

    //stupid intellij stuff SKILL, SELF, RARE

    public MidasTouch() {
        super(ID, 2, CardType.SKILL, CardRarity.RARE, CardTarget.SELF);
        isEthereal = true;
        exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new AbstractGameAction() {
            @Override
            public void update() {
                isDone = true;
                for (AbstractCard q : AbstractDungeon.player.drawPile.group) {
                    CardModifierManager.addModifier(q, new GoldcastModifier());
                    q.superFlash(Color.GOLD);
                }
                for (AbstractCard q : AbstractDungeon.player.hand.group) {
                    CardModifierManager.addModifier(q, new GoldcastModifier());
                    q.superFlash(Color.GOLD);
                }
                for (AbstractCard q : AbstractDungeon.player.discardPile.group) {
                    CardModifierManager.addModifier(q, new GoldcastModifier());
                    q.superFlash(Color.GOLD);
                }
            }
        });
    }

    public void baseUpgrade() {

        isInnate = true;
        rawDescription = UPGRADE_DESCRIPTION;
        initializeDescription();
    }


    public void branchUpgrade() {

        CardModifierManager.addModifier(this, new BloodcastModifier());
        upgradeBaseCost(3);
    }
}
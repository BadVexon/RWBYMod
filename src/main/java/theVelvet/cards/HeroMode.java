package theVelvet.cards;

import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theVelvet.cardmods.SuperStrikeModifier;

public class HeroMode extends AbstractHadesCard {

    public final static String ID = makeID("HeroMode");

    //stupid intellij stuff SKILL, SELF, RARE

    private static final int MAGIC = 6;
    private static final int UPG_MAGIC = 3;

    public HeroMode() {
        super(ID, 2, CardType.SKILL, CardRarity.RARE, CardTarget.SELF);
        baseMagicNumber = magicNumber = MAGIC;
        exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new AbstractGameAction() {
            @Override
            public void update() {
                isDone = true;
                for (AbstractCard q : AbstractDungeon.player.hand.group) {
                    CardModifierManager.addModifier(q, new SuperStrikeModifier(magicNumber));
                    q.superFlash();
                }
            }
        });
    }

    public void baseUpgrade() {

        upgradeMagicNumber(UPG_MAGIC);
    }


    public void branchUpgrade() {

        upgradeBaseCost(1);
    }
}
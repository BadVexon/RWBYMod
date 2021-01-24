package theVelvet.cards;

import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theVelvet.cardmods.GoldcastModifier;

public class Snipe extends AbstractHadesCard {

    public final static String ID = makeID("Snipe");

    //stupid intellij stuff ATTACK, ENEMY, COMMON

    private static final int DAMAGE = 7;
    private static final int UPG_DAMAGE = 3;

    public Snipe() {
        super(ID, 1, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        baseDamage = DAMAGE;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.SLASH_HORIZONTAL);
        atb(new AbstractGameAction() {
            @Override
            public void update() {
                isDone = true;
                if (!AbstractDungeon.player.drawPile.isEmpty()) {
                    AbstractCard card = AbstractDungeon.player.drawPile.getTopCard();
                    card.setCostForTurn(0);
                }
            }
        });
    }

    public void baseUpgrade() {
        upgradeDamage(UPG_DAMAGE);
    }

    public void branchUpgrade() {

        CardModifierManager.addModifier(this, new GoldcastModifier());
        upgradeBaseCost(2);
    }
}
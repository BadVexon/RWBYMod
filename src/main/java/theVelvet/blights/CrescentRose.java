package theVelvet.blights;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theVelvet.RWBYMod;
import theVelvet.actions.DiscoverNoReduceAction;
import theVelvet.actions.GainEnergyIfActiveAction;

public class CrescentRose extends AbstractWeapon {
    public static String ID = RWBYMod.makeID("CrescentRose");

    public CrescentRose() {
        super(ID, "Crescent Rose", "#gPassive: Gain [E] at the start of your turn. NL Played #yAttacks #yExhaust. NL #pActivate: Choose #b1 of #b3 #yAttacks to add into your hand.");
    }

    @Override
    public void activate() {
        AbstractDungeon.actionManager.addToTop(new DiscoverNoReduceAction(AbstractCard.CardType.ATTACK, 1));
    }
}

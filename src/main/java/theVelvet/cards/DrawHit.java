package theVelvet.cards;

import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theVelvet.RWBYMod;
import theVelvet.cardmods.BloodcastModifier;

public class DrawHit extends AbstractHadesCard {

    public final static String ID = makeID("DrawHit");

    //stupid intellij stuff ATTACK, ENEMY, UNCOMMON

    private static final int DAMAGE = 7;
    private static final int UPG_DAMAGE2 = -2;

    public DrawHit() {
        super(ID, 1, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseDamage = DAMAGE;
        baseMagicNumber = magicNumber = 2;
    }

    @Override
    public void applyPowers() {
        int oldBase = baseDamage;
        baseDamage += RWBYMod.drawnCards * magicNumber;
        super.applyPowers();
        baseDamage = oldBase;
        if (baseDamage != damage) {
            isDamageModified = true;
        }
    }

    @Override
    public void calculateCardDamage(AbstractMonster mo) {
        int oldBase = baseDamage;
        baseDamage += RWBYMod.drawnCards * magicNumber;
        super.calculateCardDamage(mo);
        baseDamage = oldBase;
        if (baseDamage != damage) {
            isDamageModified = true;
        }
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.BLUNT_HEAVY);
    }

    public void baseUpgrade() {

        upgradeMagicNumber(1);
    }


    public void branchUpgrade() {

        CardModifierManager.addModifier(this, new BloodcastModifier());
        upgradeDamage(UPG_DAMAGE2);
    }
}
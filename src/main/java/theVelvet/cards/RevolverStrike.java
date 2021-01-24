package theVelvet.cards;

import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import theVelvet.cardmods.BloodcastModifier;

public class RevolverStrike extends AbstractHadesCard {

    public final static String ID = makeID("RevolverStrike");

    //stupid intellij stuff ATTACK, ENEMY, UNCOMMON

    private static final int DAMAGE = 7;
    private static final int UPG_DAMAGE = -3;
    private static final int MAGIC = 1;
    private static final int UPG_MAGIC2 = 1;

    public RevolverStrike() {
        super(ID, 1, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseDamage = DAMAGE;
        baseMagicNumber = magicNumber = MAGIC;
        tags.add(CardTags.STRIKE);
    }

    @Override
    public void calculateCardDamage(AbstractMonster mo) {
        int oldBase = baseDamage;
        for (AbstractPower p : mo.powers) {
            if (p.type == AbstractPower.PowerType.DEBUFF) {
                baseDamage += p.amount * magicNumber;
            }
        }
        super.calculateCardDamage(mo);
        baseDamage = oldBase;
        if (baseDamage != damage) {
            isDamageModified = true;
        }
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.FIRE);
    }

    public void baseUpgrade() {

        CardModifierManager.addModifier(this, new BloodcastModifier());
        upgradeDamage(UPG_DAMAGE);
    }


    public void branchUpgrade() {

        upgradeMagicNumber(UPG_MAGIC2);
    }
}
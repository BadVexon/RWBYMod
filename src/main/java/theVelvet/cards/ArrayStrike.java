package theVelvet.cards;

import com.evacipated.cardcrawl.mod.stslib.fields.cards.AbstractCard.ExhaustiveField;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class ArrayStrike extends AbstractHadesCard {

    public final static String ID = makeID("ArrayStrike");

    //stupid intellij stuff ATTACK, ENEMY, RARE

    private static final int DAMAGE = 3;
    private static final int UPG_DAMAGE = 1;
    private static final int MAGIC = 2;
    private static final int UPG_MAGIC = 1;

    public ArrayStrike() {
        super(ID, 0, CardType.ATTACK, CardRarity.RARE, CardTarget.ENEMY);
        baseDamage = DAMAGE;
        baseMagicNumber = magicNumber = MAGIC;
        baseSilly = silly = 1;
        exhaust = true;
        tags.add(CardTags.STRIKE);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.SLASH_HORIZONTAL);
        atb(new DrawCardAction(p, magicNumber));
        atb(new GainEnergyAction(silly));
    }

    public void baseUpgrade() {

        exhaust = false;
        ExhaustiveField.ExhaustiveFields.baseExhaustive.set(this, 2);
        ExhaustiveField.ExhaustiveFields.exhaustive.set(this, 2);
        ExhaustiveField.ExhaustiveFields.isExhaustiveUpgraded.set(this, true);
        rawDescription = EXTENDED_DESCRIPTION[0];
        initializeDescription();
    }


    public void branchUpgrade() {

        upgradeDamage(UPG_DAMAGE);
        upgradeMagicNumber(UPG_MAGIC);
        upgradeSilly(1);
        rawDescription = UPGRADE_DESCRIPTION;
        initializeDescription();
    }
}

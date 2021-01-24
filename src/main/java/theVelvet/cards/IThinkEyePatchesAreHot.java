package theVelvet.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.evacipated.cardcrawl.mod.stslib.cards.interfaces.BranchingUpgradesCard;

public class IThinkEyePatchesAreHot extends AbstractHadesCard implements BranchingUpgradesCard {

    public final static String ID = makeID("IThinkEyePatchesAreHot");

    //stupid intellij stuff ATTACK, ENEMY, COMMON

    private static final int DAMAGE = 14;
    private static final int UPG_DAMAGE = 4;
    private static final int MAGIC = 2;

    public IThinkEyePatchesAreHot() {
        super(ID, 2, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        baseDamage = DAMAGE;
        baseMagicNumber = magicNumber = MAGIC;
        tags.add(CardTags.STRIKE);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.BLUNT_HEAVY);
        if (m.getIntentBaseDmg() > -1)
            applyToEnemy(m, autoWeak(m, magicNumber));
    }

    public void baseUpgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeDamage(UPG_DAMAGE);
        }
    }

    public void branchUpgrade() {
        if (!upgraded) {
            upgradeName();
            selfRetain = true;
            exhaust = true;
            rawDescription = UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }
}
package theVelvet.cards;

import com.evacipated.cardcrawl.mod.stslib.cards.interfaces.BranchingUpgradesCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class MailBomb extends AbstractHadesCard implements BranchingUpgradesCard {

    public final static String ID = makeID("MailBomb");

    //stupid intellij stuff ATTACK, ENEMY, UNCOMMON

    private static final int DAMAGE = 8;
    private static final int UPG_DAMAGE = 2;
    private static final int BLOCK = 6;
    private static final int UPG_BLOCK = 2;
    private static final int MAGIC = 2;
    private static final int UPG_MAGIC2 = 2;

    public MailBomb() {
        super(ID, 2, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseDamage = DAMAGE;
        baseBlock = BLOCK;
        baseMagicNumber = magicNumber = MAGIC;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
        dmg(m, AbstractGameAction.AttackEffect.BLUNT_LIGHT);
        awep();
        atb(new DrawCardAction(magicNumber));
    }

    public void baseUpgrade() {

        upgradeDamage(UPG_DAMAGE);
        upgradeBlock(UPG_BLOCK);
    }


    public void branchUpgrade() {

        upgradeMagicNumber(UPG_MAGIC2);
    }
}
package theVelvet.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class SpookySplit extends AbstractHadesCard {

    public final static String ID = makeID("SpookySplit");

    //stupid intellij stuff SKILL, ALL_ENEMY, UNCOMMON

    private static final int MAGIC = 3;
    private static final int UPG_MAGIC = 2;

    public SpookySplit() {
        super(ID, 2, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.ALL_ENEMY);
        baseMagicNumber = magicNumber = MAGIC;
        baseSilly = silly = MAGIC;
        exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        for (int i = 0; i < magicNumber; i++) {
            AbstractMonster q = AbstractDungeon.getRandomMonster();
            applyToEnemy(q, autoWeak(q, 1));
        }
        for (int i = 0; i < silly; i++) {
            AbstractMonster q = AbstractDungeon.getRandomMonster();
            applyToEnemy(q, autoVuln(q, 1));
        }
    }

    public void baseUpgrade() {

        upgradeSilly(UPG_MAGIC);
    }


    public void branchUpgrade() {

        upgradeMagicNumber(UPG_MAGIC);
    }
}
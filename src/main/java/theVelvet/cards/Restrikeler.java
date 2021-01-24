package theVelvet.cards;

import com.evacipated.cardcrawl.mod.stslib.actions.common.FetchAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.ArrayList;

public class Restrikeler extends AbstractHadesCard {

    public final static String ID = makeID("Restrikeler");

    //stupid intellij stuff ATTACK, ENEMY, COMMON

    private static final int DAMAGE = 6;
    private static final int UPG_DAMAGE = 4;

    public Restrikeler() {
        super(ID, 1, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        baseDamage = DAMAGE;
        tags.add(CardTags.STRIKE);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.BLUNT_HEAVY);
        if (upgraded && isBranchUpgrade()) {
            atb(new FetchAction(p.drawPile, c -> c.hasTag(CardTags.STRIKE), 1));
        } else {
            atb(new AbstractGameAction() {
                @Override
                public void update() {
                    isDone = true;
                    ArrayList<AbstractCard> strikeList = new ArrayList<>();
                    for (AbstractCard q : AbstractDungeon.player.drawPile.group) {
                        if (q.hasTag(CardTags.STRIKE))
                            strikeList.add(q);
                    }
                    if (!strikeList.isEmpty()) {
                        p.drawPile.moveToHand(strikeList.get(AbstractDungeon.cardRandomRng.random(strikeList.size() - 1)));
                    }
                }
            });
        }
    }

    public void baseUpgrade() {

        upgradeDamage(UPG_DAMAGE);
    }

    public void branchUpgrade() {
        rawDescription = UPGRADE_DESCRIPTION;
        initializeDescription();
    }
}
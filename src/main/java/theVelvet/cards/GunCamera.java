package theVelvet.cards;

import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theVelvet.cardmods.DamageCameraModifier;

public class GunCamera extends AbstractHadesCard {

    public final static String ID = makeID("GunCamera");

    //stupid intellij stuff ATTACK, ENEMY, UNCOMMON

    private static final int DAMAGE = 6;
    private static final int MAGIC = 4;
    private static final int UPG_MAGIC = 2;

    public GunCamera() {
        super(ID, 1, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseDamage = DAMAGE;
        baseMagicNumber = magicNumber = MAGIC;
        AbstractCard q = new Camera();
        CardModifierManager.addModifier(q, new DamageCameraModifier(magicNumber));
        cardsToPreview = q;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.FIRE);
        atb(new AbstractGameAction() {
            @Override
            public void update() {
                isDone = true;
                for (AbstractCard q : AbstractDungeon.player.hand.group) {
                    if (q instanceof Camera) {
                        CardModifierManager.addModifier(q, new DamageCameraModifier(magicNumber));
                        q.superFlash();
                    }
                }
            }
        });
    }

    public void baseUpgrade() {

        selfRetain = true;
        rawDescription = UPGRADE_DESCRIPTION;
        initializeDescription();
    }


    public void branchUpgrade() {

        upgradeMagicNumber(UPG_MAGIC);
        AbstractCard q = new Camera();
        CardModifierManager.addModifier(q, new DamageCameraModifier(magicNumber));
        cardsToPreview = q;
    }
}
package theVelvet.cards;

import basemod.abstracts.CustomCard;
import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theVelvet.cardmods.TemporaryCardModifier;
import theVelvet.powers.BestAnglePower;
import theVelvet.powers.SelfieFormPower;

import static theVelvet.cards.AbstractHadesCard.getCorrectPlaceholderImage;
import static theVelvet.cards.AbstractHadesCard.makeID;

public class Camera extends CustomCard {

    public final static String ID = makeID("Camera");
    protected final CardStrings cardStrings;
    protected final String NAME;
    protected final String DESCRIPTION;
    protected final String UPGRADE_DESCRIPTION;
    protected final String[] EXTENDED_DESCRIPTION;

    //stupid intellij stuff SKILL, SELF, SPECIAL

    public Camera() {
        super(ID, "ERROR", getCorrectPlaceholderImage(CardType.SKILL, ID),
                0, "ERROR", CardType.SKILL, CardColor.COLORLESS, CardRarity.SPECIAL, CardTarget.SELF);
        cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
        name = NAME = cardStrings.NAME;
        originalName = NAME;
        rawDescription = DESCRIPTION = cardStrings.DESCRIPTION;
        UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
        EXTENDED_DESCRIPTION = cardStrings.EXTENDED_DESCRIPTION;
        initializeTitle();
        initializeDescription();
        selfRetain = true;
        exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractCard c = AbstractDungeon.returnTrulyRandomCardInCombat(CardType.ATTACK).makeCopy();
        if (upgraded || p.hasPower(SelfieFormPower.POWER_ID))
            c.upgrade();
        if (p.hasPower(BestAnglePower.POWER_ID)) c.setCostForTurn(0);
        CardModifierManager.addModifier(c, new TemporaryCardModifier());
        addToBot(new MakeTempCardInHandAction(c));
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            rawDescription = UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }
}

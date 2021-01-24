package theVelvet.util;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public interface ModifyDamagePower {
    float calculateCardDamagePower(float damageAmount, AbstractCard c, AbstractMonster m);
}

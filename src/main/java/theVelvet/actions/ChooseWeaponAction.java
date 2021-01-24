package theVelvet.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.blights.AbstractBlight;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import theVelvet.RWBYMod;
import theVelvet.WeaponHelper;
import theVelvet.blights.AbstractWeapon;
import theVelvet.cards.WeaponCard;
import theVelvet.util.CenterGridCardSelectScreen;

public class ChooseWeaponAction extends AbstractGameAction {
    private boolean pickCard = false;
    private CardGroup group = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);

    public ChooseWeaponAction() {
        duration = Settings.ACTION_DUR_XFAST;
        actionType = ActionType.WAIT;
    }

    @Override
    public void update() {
        for (AbstractBlight r : AbstractDungeon.player.blights) {
            if (r instanceof AbstractWeapon) {
                if (!((AbstractWeapon) r).active())
                    group.addToTop(new WeaponCard(r.name, "hadesmodResources/images/cards/" + r.blightID.replaceAll(RWBYMod.getModID() + ":", "") + ".png", "Swap to hadesmod:" + r.name.replaceAll(" ", "_") + "."));
                System.out.println(r.name);
            }
        }
        if (duration == Settings.ACTION_DUR_XFAST && !group.isEmpty()) {
            pickCard = true;
            CenterGridCardSelectScreen.centerGridSelect = true;
            AbstractDungeon.gridSelectScreen.open(group, 1, ("Choose a Weapon to Swap to."), false);
            System.out.println("opened grid");
        } else if ((pickCard && !AbstractDungeon.gridSelectScreen.selectedCards.isEmpty())) {
            pickCard = false;
            System.out.println("card picked");
            AbstractCard cardChoice = AbstractDungeon.gridSelectScreen.selectedCards.get(0);
            AbstractDungeon.gridSelectScreen.selectedCards.clear();
            CenterGridCardSelectScreen.centerGridSelect = false;
            doStuff(cardChoice);
            isDone = true;
        } else if (group.isEmpty()) {
            isDone = true;
            System.out.println("empty grid");
        }
        tickDuration();
    }

    public void doStuff(AbstractCard cardChoice) {
        for (AbstractBlight r : AbstractDungeon.player.blights) {
            if (r.name.equals(cardChoice.name) && WeaponHelper.usedWeapon != r) {
                WeaponHelper.swapWeapon((AbstractWeapon) r);
                System.out.println("swapped to" + r.name);
            }
        }
    }
}
package theVelvet.actions;

import com.megacrit.cardcrawl.core.Settings;

public class XactivateAction extends AbstractXAction {

    private int bonusAmt;

    public XactivateAction(int bonusAmt) {
        this.bonusAmt = bonusAmt;
        this.duration = Settings.ACTION_DUR_XFAST;
        this.actionType = ActionType.SPECIAL;
    }

    @Override
    public void initialize(int totalAmount) {
        super.initialize(totalAmount);
        this.amount += bonusAmt;
    }

    public void update() {
        for (int i = 0; i < amount; i++) {
            addToTop(new ActivateWeaponAction());
        }
        this.isDone = true;
    }
}
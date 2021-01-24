package theVelvet.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import theVelvet.WeaponHelper;

public class ActivateWeaponAction extends AbstractGameAction {
    @Override
    public void update() {
        isDone = true;
        if (WeaponHelper.usedWeapon != null)
            WeaponHelper.usedWeapon.charge();
    }
}

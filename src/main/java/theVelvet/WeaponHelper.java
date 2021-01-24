package theVelvet;

import theVelvet.blights.AbstractWeapon;

public class WeaponHelper {
    public static AbstractWeapon usedWeapon;

    public static void swapWeapon(AbstractWeapon newWeapon) {
        if (usedWeapon != null) {
            usedWeapon.onUnswapped();
        }
        usedWeapon = newWeapon;
        usedWeapon.onSwapped();
    }

    public static void weaponsOff() {
        if (usedWeapon != null) {
            usedWeapon.onUnswapped();
        }
        usedWeapon = null;
    }
}

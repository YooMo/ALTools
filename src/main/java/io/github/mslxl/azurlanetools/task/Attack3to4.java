package io.github.mslxl.azurlanetools.task;

import io.github.mslxl.azurlanetools.config.Resources;
import io.github.mslxl.azurlanetools.operatioer.Task;
import io.github.mslxl.azurlanetools.util.AzurLane;
import io.github.mslxl.azurlanetools.util.KotlinUtilKt;
import io.github.mslxl.azurlanetools.util.Phone;

public class Attack3to4 extends Task {
    @Override
    public void run() {
        for (; ; ) {
            AzurLane.INSTANCE.goWeighAnchor();
            AzurLane.INSTANCE.nexcChapter(true);
            AzurLane.INSTANCE.nexcChapter(false);
            Phone.INSTANCE.tap(Resources.Companion.getLocation("3-4"));
            try {
                Thread.sleep(1 * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Phone.INSTANCE.tap(Phone.INSTANCE.searchOnScreen(Resources.Companion.get("went.png"), true));
            try {
                Thread.sleep(2 * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Phone.INSTANCE.tap(Phone.INSTANCE.searchOnScreen(Resources.Companion.get("went.png"), true));

            for (; ; ) {
                try {
                    Thread.sleep(5 * 1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                if (Phone.INSTANCE.canFindOnScreen(Resources.Companion.get("attack.png"), true)) {
                    Phone.INSTANCE.tap(Phone.INSTANCE.searchOnScreen(Resources.Companion.get("attack.png"), false));
                    continue;
                }

                if (Phone.INSTANCE.canFindOnScreen(Resources.Companion.get("enemy_boss.png"), false)) {
                    Phone.INSTANCE.tap(Phone.INSTANCE.searchOnScreen(Resources.Companion.get("enemy_boss.png"), false));
                    Phone.INSTANCE.waitFor(Resources.Companion.get("right.png"), 2000, 2 * 60 * 1000);

                    Phone.INSTANCE.tap(500, 500);
                    try {
                        Thread.sleep(5 * 100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    Phone.INSTANCE.tap(500, 500);
                    try {
                        Thread.sleep(5 * 100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    Phone.INSTANCE.tap(500, 500);
                    try {
                        Thread.sleep(5 * 100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    break;
                }
                if (Phone.INSTANCE.canFindOnScreen(Resources.Companion.get("enemy_money_place.png"), false)) {
                    Phone.INSTANCE.tap(Phone.INSTANCE.searchOnScreen(Resources.Companion.get("enemy_money_place.png"), false));
                    continue;
                }

                if (Phone.INSTANCE.canFindOnScreen(Resources.Companion.get("evade.png"), false)) {
                    Phone.INSTANCE.tap(Phone.INSTANCE.searchOnScreen(Resources.Companion.get("evade.png"), false));
                    continue;
                }

                if (Phone.INSTANCE.canFindOnScreen(Resources.Companion.get("enemy_carrier_place.png"), false)) {
                    Phone.INSTANCE.tap(Phone.INSTANCE.searchOnScreen(Resources.Companion.get("enemy_carrier_place.png"), false));
                    continue;
                }

                if (Phone.INSTANCE.canFindOnScreen(Resources.Companion.get("enemy_main_carrier_place.png"), false)) {
                    Phone.INSTANCE.tap(Phone.INSTANCE.searchOnScreen(Resources.Companion.get("enemy_main_carrier_place.png"), false));
                    continue;
                }

                if (Phone.INSTANCE.canFindOnScreen(Resources.Companion.get("enemy_aircraft_carrier_place.png"), false)) {
                    Phone.INSTANCE.tap(Phone.INSTANCE.searchOnScreen(Resources.Companion.get("enemy_aircraft_carrier_place.png"), false));
                    continue;
                }
            }

        }

    }
}

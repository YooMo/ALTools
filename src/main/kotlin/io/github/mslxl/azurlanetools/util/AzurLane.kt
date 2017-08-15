package io.github.mslxl.azurlanetools.util

import io.github.mslxl.azurlanetools.config.Resources

object AzurLane {
    fun stop() {
        Phone.exc("am force-stop com.bilibili.azurlane")
    }

    fun start() {
        Phone.exc("am start -n com.bilibili.azurlane/com.manjuu.azurlane.MainActivity")
    }

    fun restart() {
        println("Restart game...")
        AzurLane.stop()
        println("Wait for start...")
        AzurLane.start()
        Phone.waitFor(Resources["loginUI.png"], timeOut = 60 * 1000)
        Phone.tap(200, 100)
        println("Init resources...")
        Thread.sleep(10 * 1000)
        AzurLane.showMain()
    }

    fun showMain() {
        println("Try to open main activity...")
        if (isMainUI(true)) {
            println("Already is main activity")
            return
        }
        if (isSystemAnnounceUI(newScreencap = false)) {
            Phone.tap(Phone.searchOnScreen(Resources["closeButton.png"], false))
            return
        }


        while (hasBackButton(true)) {
            clickBackButton(false)
            Thread.sleep(2 * 1000) // Wait game load...
        }
    }


fun isSystemAnnounceUI(newScreencap: Boolean = true): Boolean {
    println("Check whether the system announce...")
    return Phone.canFindOnScreen(Resources["systemAnnounce.png"], newScreencap)
}

fun isMainUI(newScreencap: Boolean = true): Boolean {
    println("Check whether the main activity...")
    return Phone.canFindOnScreen(Resources["weigh_anchor.png"], newScreencap)
}

@Deprecated("TODO")
fun isAttackUI(newScreencap: Boolean = true): Boolean {
    println("Check whether the attack...")
    //return Phone.searchOnScreen(Resources["back_button_attack.png"],new = newScreencap).exists()
    TODO("f**k")
}

fun hasBackButton(newScreencap: Boolean = true): Boolean {
    println("Check has back button...")
    return Phone.searchOnScreen(Resources["back_button_1.png"], newScreencap).exists() or Phone.searchOnScreen(Resources["back_button_2.png"], false).exists()
}

fun clickBackButton(newScreencap: Boolean = true) {
    if (!hasBackButton(newScreencap)) error("Back button was not exists")
    val t1 = Phone.searchOnScreen(Resources["back_button_1.png"], new = false)
    val t2 = Phone.searchOnScreen(Resources["back_button_2.png"], new = false)
    val rect = if (t1.exists()) t1 else t2
    Phone.tap(rect)
}

fun isPrecombat(newScreencap: Boolean = true): Boolean {

    println("Check whether the precombat...")
    return Phone.searchOnScreen(Resources["daily_raid.png"], newScreencap).exists()
}

fun goWeighAnchor() {
    if (!isMainUI()) {
        showMain()
    }
    val rectangle = Phone.searchOnScreen(Resources["weigh_anchor.png"])
    Phone.tap(rectangle)
    Thread.sleep(5 * 1000)
    if (Phone.searchOnScreen(Resources["sub_chapter.png"]).exists()) {
        Phone.tap(Resources.getLocation("withdraw_button"))
        Thread.sleep(1 * 1000)
        Phone.tap(Phone.searchOnScreen(Resources["amd_yes.png"]))
    }

    while (canLastChapter()) {
        lastChapter(false)
        Phone.tap(Resources.getLocation("none"))
    }
}

fun canLastChapter(): Boolean {
    print("Check whether can click last chapter")
    return Phone.searchOnScreen(Resources["last_chapter.png"], true).exists().println()
}

fun canNextChapter() = Phone.searchOnScreen(Resources["next_chapter.png"], true).exists()

fun nexcChapter(newScreencap: Boolean = true) {
    Phone.tap(Phone.searchOnScreen(Resources["next_chapter.png"], newScreencap))
}

fun lastChapter(newScreencap: Boolean = true) {
    Phone.tap(Phone.searchOnScreen(Resources["last_chapter.png"], newScreencap))
}
}
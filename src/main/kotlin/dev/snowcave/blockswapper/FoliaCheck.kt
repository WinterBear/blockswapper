package dev.snowcave.blockswapper

class FoliaCheck {

    companion object {
        fun isFolia(): Boolean {
            try {
                Class.forName("io.papermc.paper.threadedregions.RegionizedServer")
                return true
            } catch (e: ClassNotFoundException) {
                return false
            }
        }
    }
}
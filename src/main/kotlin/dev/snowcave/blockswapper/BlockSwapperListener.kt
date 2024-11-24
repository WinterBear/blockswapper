package dev.snowcave.blockswapper

import com.google.common.collect.Lists
import org.bstats.bukkit.Metrics
import org.bukkit.Bukkit
import org.bukkit.GameMode
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.BlockPlaceEvent
import org.bukkit.inventory.ItemStack
import org.bukkit.plugin.java.JavaPlugin

class BlockSwapperListener : JavaPlugin(), Listener {

    object Settings {
        const val USE_PERMISSION = "blockswapper.use"
        val GAMEMODES = listOf(GameMode.SURVIVAL, GameMode.ADVENTURE)
        const val BSTATS_ID = 23990
    }

    override fun onEnable(){
        Bukkit.getPluginManager().registerEvents(this, this)
        Metrics(this, Settings.BSTATS_ID)
        Bukkit.getLogger().info("Plugin has been enabled!")
    }

    private fun swap(swapItem: ItemStack, player: Player){
        if(player.inventory.itemInMainHand.type != Material.AIR){
            return
        }
        val swapSlot = player.inventory.first(swapItem.type)
        if(swapSlot > 0) {
            player.inventory.setItemInMainHand(player.inventory.getItem(swapSlot))
            player.inventory.setItem(swapSlot, ItemStack(Material.AIR))
        }
    }

    @EventHandler
    fun onBlockPlace(event: BlockPlaceEvent){
        val player = event.player
        if(Settings.GAMEMODES.contains(player.gameMode) && player.hasPermission(Settings.USE_PERMISSION)){
            val swapItem = player.inventory.itemInMainHand.clone()
            Bukkit.getScheduler().runTaskLater(this, Runnable { swap(swapItem, player) }, 1L)
        }
    }

}
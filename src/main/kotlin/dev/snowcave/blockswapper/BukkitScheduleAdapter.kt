package dev.snowcave.blockswapper

import org.bukkit.Bukkit
import org.bukkit.entity.Entity
import org.bukkit.plugin.java.JavaPlugin

class BukkitScheduleAdapter(private val plugin: JavaPlugin) : ScheduleAdapter {
    override fun scheduleDelayedTask(task: Runnable, delay: Long, entity: Entity) {
        Bukkit.getScheduler().runTaskLater(plugin, task, delay)
    }
}
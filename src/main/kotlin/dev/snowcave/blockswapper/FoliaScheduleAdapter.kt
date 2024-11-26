package dev.snowcave.blockswapper

import org.bukkit.entity.Entity
import org.bukkit.plugin.java.JavaPlugin

class FoliaScheduleAdapter(private val plugin: JavaPlugin) : ScheduleAdapter {

    override fun scheduleDelayedTask(task: Runnable, delay: Long, entity: Entity){
        entity.scheduler.runDelayed(this.plugin, { task.run() }, task,  delay)
    }

}
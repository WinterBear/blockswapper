package dev.snowcave.blockswapper

import org.bukkit.entity.Entity

interface ScheduleAdapter {

    fun scheduleDelayedTask(task: Runnable, delay: Long, entity: Entity)

}
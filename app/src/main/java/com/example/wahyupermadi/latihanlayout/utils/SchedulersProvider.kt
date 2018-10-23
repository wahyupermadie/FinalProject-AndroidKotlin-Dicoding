package com.example.wahyupermadi.latihanlayout.utils

import io.reactivex.Scheduler

interface SchedulersProvider {
    fun computation() : Scheduler
    fun trampoline() : Scheduler
    fun newThread() : Scheduler
    fun io() : Scheduler
    fun ui() : Scheduler
}
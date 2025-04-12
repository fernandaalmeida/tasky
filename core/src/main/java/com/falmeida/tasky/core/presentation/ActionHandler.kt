package com.falmeida.tasky.core.presentation

interface ActionHandler<Action> {
    fun onAction(event: Action)
}
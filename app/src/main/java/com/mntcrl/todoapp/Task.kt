package com.mntcrl.todoapp

data class Task (val name:String, val descripcion:String, val category: TaskCategory, var isSelected: Boolean = false)
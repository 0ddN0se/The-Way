package com.gamescript.theway.models

import android.content.res.Resources

data class DialogItem(
    val resources: Resources,
    val packageName: String,
    val scene: String,
    val numberDialog: Int
) {
    fun getImageId(): Int {
        val personName = resources.getString(getTextId()).split("|")[0].lowercase()
        return resources.getIdentifier(personName, "drawable", packageName)
    }

    fun getTextId(): Int {
        var idDialog: String = "d$numberDialog"
        return resources.getIdentifier(idDialog, "string", packageName)
    }

    fun getText(): String {
        return resources.getString(getTextId()).split("|")[1]
    }
}
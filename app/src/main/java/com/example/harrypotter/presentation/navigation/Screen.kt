package com.example.harrypotter.presentation.navigation

import android.media.MediaRouter

sealed class Screen(val router: String){

    object CharacterList : Screen("character_list")

    object AlterNameList : Screen("alter_name_list/{characterId}") {
        fun createRoute(characterId: String) = "alter_name_list/$characterId"
    }

    object AlterNameEdit : Screen("alter_name_edit?nameId={nameId}&characterId={characterId}") {
        fun createRoute(nameId: Int?, characterId: String): String {
            return if (nameId == null) {
                "alter_name_edit?characterId=$characterId"
            } else {
                "alter_name_edit?nameId=$nameId&characterId=$characterId"
            }
        }
    }
}

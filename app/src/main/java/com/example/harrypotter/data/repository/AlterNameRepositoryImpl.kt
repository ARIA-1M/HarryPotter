package com.example.harrypotter.data.repository

import com.example.harrypotter.domain.model.AlternateName
import com.example.harrypotter.domain.repository.AlternateNameRepository
import jakarta.inject.Inject

class AlternateNameRepositoryImpl @Inject constructor(): AlternateNameRepository {
    private val alterName = mutableListOf(
        AlternateName(
            id = 1,
            title = "The Boy Who Lived",
            description = "Мальчик, который выжил — тот, кто выжил после проклятия Волан-де-Морта",
            characterId = "9e3f7ce4-b9a7-4244-b709-dae5c1f1d4a8"
        ),
        AlternateName(
            id = 2,
            title = "The Chosen One",
            description = "Избранный — тот, кто по пророчеству должен победить Тёмного Лорда",
            characterId = "9e3f7ce4-b9a7-4244-b709-dae5c1f1d4a8"
        ),
        AlternateName(
            id = 3,
            title = "Undesirable No. 1",
            description = "Нежелательный № 1 — так его называли в Министерстве магии после захвата власти Волан-де-Мортом",
            characterId = "9e3f7ce4-b9a7-4244-b709-dae5c1f1d4a8"
        ),
        AlternateName(
            id = 4,
            title = "Potty",
            description = "Горшок — насмешливое прозвище от Драко Малфоя",
            characterId = "9e3f7ce4-b9a7-4244-b709-dae5c1f1d4a8"
        ),
        AlternateName(
            id = 5,
            title = "Hermy",
            description = "Герми — ласковое прозвище, которым её называли некоторые друзья",
            characterId = "4c7e6819-a91a-45b2-a454-f931e4a7cce3"
        ),
        AlternateName(
            id = 6,
            title = "Know-it-all",
            description = "Всезнайка — прозвище за её любовь к учёбе и знаниям",
            characterId = "4c7e6819-a91a-45b2-a454-f931e4a7cce3"
        ),
        AlternateName(
            id = 7,
            title = "Miss Grant",
            description = "Мисс Грант — вымышленное имя, которое она использовала в Министерстве магии",
            characterId = "4c7e6819-a91a-45b2-a454-f931e4a7cce3"
        ),
        AlternateName(
            id = 8,
            title = "Herm-own-ninny",
            description = "Гер-оун-нинни — как её называл Виктор Крам из-за акцента",
            characterId = "4c7e6819-a91a-45b2-a454-f931e4a7cce3"
        ),
        AlternateName(
            id = 9,
            title = "Dragomir Despard",
            description = "Драгомир Деспард — вымышленное имя, которое Рон использовал для проникновения в Министерство",
            characterId = "c3b1f9a5-b87b-48bf-b00d-95b093ea6390"
        ),
        AlternateName(
            id = 10,
            title = "Ronald",
            description = "Рональд — полное имя, которым его называли родители и преподаватели",
            characterId = "c3b1f9a5-b87b-48bf-b00d-95b093ea6390"
        ),
        AlternateName(
            id = 11,
            title = "Ickle Ronniekins",
            description = "Маленький Ронни — ласковое прозвище от близнецов Уизли",
            characterId = "c3b1f9a5-b87b-48bf-b00d-95b093ea6390"
        ),
        AlternateName(
            id = 12,
            title = "Ronnie",
            description = "Ронни — короткое прозвище",
            characterId = "c3b1f9a5-b87b-48bf-b00d-95b093ea6390"
        ),
        AlternateName(
            id = 13,
            title = "Wheezy",
            description = "Свистулька — насмешливое прозвище от Драко Малфоя",
            characterId = "c3b1f9a5-b87b-48bf-b00d-95b093ea6390"
        ),
        AlternateName(
            id = 14,
            title = "Won-Won",
            description = "Бон -Бон — ласковое прозвище от Лаванды Браун",
            characterId = "c3b1f9a5-b87b-48bf-b00d-95b093ea6390"
        ),
        AlternateName(
            id = 15,
            title = "Roonil Wazlib",
            description = "Рунил Вазлиб — опечатка в книге заклинаний, которую Рон выдал за своё имя",
            characterId = "c3b1f9a5-b87b-48bf-b00d-95b093ea6390"
        ),
    )

    override suspend fun getAlternateNames(characterId: String): List<AlternateName> {
        return alterName.filter { it.characterId == characterId }
    }

    override suspend fun addAlternateName(name: AlternateName) {
        alterName.add(name)
    }

    override suspend fun updateAlternateName(name: AlternateName) {
        val index = alterName.indexOfFirst { it.id == name.id }
        if (index != -1) alterName[index] = name
    }

    override suspend fun deleteAlternateName(name: AlternateName) {
        alterName.removeAll { it.id == name.id }
    }


}
package com.example.harrypotter.domain.usecase

import com.example.harrypotter.domain.model.Character
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class GetSortedCharacterUseCaseTest {

    private val fakeRepository = FakeCharacterRepository()
    private val useCase = GetSortedCharacterUseCase(fakeRepository)

    private fun character(
        id: String,
        name: String,
        house: String = "Gryffindor"
    ) = Character(
        id = id,
        name = name,
        species = "human",
        gender = "male",
        house = house,
        yearOfBirth = 1980,
        imagePath = ""
    )

    //пустой фильтр (все факультеты)
    @Test
    fun GetSortedCharacterUseCaseTestNull() = runTest {
        fakeRepository.setCharacters(listOf(
            character("1", "Harry", "Gryffindor"),
            character("2", "Draco", "Slytherin"),
            character("3", "Luna", "Ravenclaw")
        ))

        val result = useCase(house = null)

        assertEquals(3, result.size)
    }

    //фильтр по грифиндору
    @Test
    fun GetSortedCharacterUseCaseTestGryffindor() = runTest {
        fakeRepository.setCharacters(listOf(
            character("1", "Harry", "Gryffindor"),
            character("2", "Ron", "Gryffindor"),
            character("3", "Draco", "Slytherin")
        ))

        val result = useCase(house = "Gryffindor")

        assertEquals(2, result.size)
        assertTrue(result.all { it.house == "Gryffindor" })
    }

    //фильтр по слизерину
    @Test
    fun GetSortedCharacterUseCaseTestSlytherin() = runTest {
        fakeRepository.setCharacters(listOf(
            character("1", "Harry", "Gryffindor"),
            character("2", "Draco", "Slytherin"),
            character("3", "Snape", "Slytherin")
        ))

        val result = useCase(house = "Slytherin")

        assertEquals(2, result.size)
        assertEquals(listOf("2", "3"), result.map { it.id })
    }

    //фильтр по хафлпафу
    @Test
    fun GetSortedCharacterUseCaseTestHufflepuff() = runTest {
        fakeRepository.setCharacters(listOf(
            character("1", "Harry", "Gryffindor"),
            character("2", "Cedric", "Hufflepuff"),
            character("3", "Susan", "Hufflepuff"),
            character("4", "Draco", "Slytherin")
        ))

        val result = useCase(house = "Hufflepuff")

        assertEquals(2, result.size)
        assertEquals(listOf("2", "3"), result.map { it.id })
    }

    //фильтр по рейвенкло
    @Test
    fun GetSortedCharacterUseCaseTestRavenclaw() = runTest {
        fakeRepository.setCharacters(listOf(
            character("1", "Harry", "Gryffindor"),
            character("2", "Luna", "Ravenclaw"),
            character("3", "Cho", "Ravenclaw"),
            character("4", "Draco", "Slytherin")
        ))

        val result = useCase(house = "Ravenclaw")

        assertEquals(2, result.size)
        assertEquals(listOf("2", "3"), result.map { it.id })
    }

    //несуществующий факультет
    @Test
    fun GetSortedCharacterUseCaseTestDefunctHouse() = runTest {
        fakeRepository.setCharacters(listOf(
            character("1", "Harry", "Gryffindor"),
            character("2", "Draco", "Slytherin")
        ))

        val result = useCase(house = "Hufflepuff")

        assertEquals(emptyList<Character>(), result)
    }

    //пустой список
    @Test
    fun GetSortedCharacterUseCaseTestEmptyList() = runTest {
        fakeRepository.setCharacters(emptyList())

        val result = useCase(house = null)

        assertEquals(emptyList<Character>(), result)
    }

    //фильтр не теряет персонажей с одинаковым факультетом
    @Test
    fun GetSortedCharacterUseCaseTestSameHouse() = runTest {
        fakeRepository.setCharacters(listOf(
            character("1", "Harry", "Gryffindor"),
            character("2", "Ron", "Gryffindor"),
            character("3", "Hermione", "Gryffindor")
        ))

        val result = useCase(house = "Gryffindor")

        assertEquals(3, result.size)
        assertEquals(setOf("1", "2", "3"), result.map { it.id }.toSet())
    }


}
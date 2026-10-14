package com.example.harrypotter.presentation.characterlist

import app.cash.turbine.test
import com.example.harrypotter.domain.model.Character
import com.example.harrypotter.domain.usecase.FakeCharacterRepository
import com.example.harrypotter.domain.usecase.GetSortedCharacterUseCase
import com.example.moviesdemo.MainDispatcherRule
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test

class CharacterListViewModelLoadingTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule(StandardTestDispatcher())

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

    //загрузка персонажей
    @Test
    fun CharacterListViewModelIsLoadingTest() = runTest {
        val fake = FakeCharacterRepository().apply {
            setCharacters(listOf(character("1", "Harry")))
        }
        val viewModel = CharacterListViewModel(
            getSortedCharacter = GetSortedCharacterUseCase(fake),
            repository = fake
        )

        viewModel.uiState.test {
            assertTrue(awaitItem().isLoading)
            advanceUntilIdle()
            val loaded = awaitItem()
            assertFalse(loaded.isLoading)
            assertEquals(1, loaded.characters.size)
            cancelAndIgnoreRemainingEvents()
        }
    }
}
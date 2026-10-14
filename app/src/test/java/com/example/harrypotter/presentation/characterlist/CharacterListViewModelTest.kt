package com.example.harrypotter.presentation.characterlist

import app.cash.turbine.test
import com.example.harrypotter.domain.model.Character
import com.example.harrypotter.domain.usecase.FakeCharacterRepository
import com.example.harrypotter.domain.usecase.GetSortedCharacterUseCase
import com.example.moviesdemo.MainDispatcherRule
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class CharacterListViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private lateinit var fakeRepository: FakeCharacterRepository
    private lateinit var viewModel: CharacterListViewModel

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

    private fun createViewModel() {
        viewModel = CharacterListViewModel(
            getSortedCharacter = GetSortedCharacterUseCase(fakeRepository),
            repository = fakeRepository
        )
    }

    @Before
    fun setUp() {
        fakeRepository = FakeCharacterRepository()
        fakeRepository.setCharacters(listOf(
            character("1", "Harry", "Gryffindor"),
            character("2", "Draco", "Slytherin"),
        ))
        createViewModel()
    }

    //после загрузки isLoading false
    @Test
    fun CharacterListViewModelTestIsLoadingFalse() = runTest {
        viewModel.uiState.test {
            val state = awaitItem()
            assertFalse(state.isLoading)
            assertEquals(2, state.characters.size)
            cancelAndIgnoreRemainingEvents()
        }
    }

    // персонажи отсортированы по умолчанию
    @Test
    fun CharacterListViewModelTestSortHouse() = runTest {
        viewModel.uiState.test {
            val state = awaitItem()
            assertEquals("Harry", state.characters.first().name)
            cancelAndIgnoreRemainingEvents()
        }
    }

    //выбор факультета и фильтрация
    @Test
    fun CharacterListViewModelTestSelectHouse() = runTest {
        viewModel.onHouseSelected("Slytherin")

        viewModel.uiState.test {
            val state = awaitItem()
            assertEquals(1, state.characters.size)
            assertEquals("Draco", state.characters.first().name)
            assertEquals("Slytherin", state.selectedHouse)
            cancelAndIgnoreRemainingEvents()
        }
    }

    //сброс фильтра
    @Test
    fun CharacterListViewModelTestResetSort() = runTest {
        viewModel.onHouseSelected("Slytherin")
        viewModel.onHouseSelected(null)

        viewModel.uiState.test {
            val state = awaitItem()
            assertEquals(2, state.characters.size)
            assertEquals(null, state.selectedHouse)
            cancelAndIgnoreRemainingEvents()
        }
    }

    //обнавление пути картинки
    @Test
    fun CharacterListViewModelTestUpdatePathImage() = runTest {
        viewModel.onImageSelected("1", "content://photo/123")

        viewModel.uiState.test {
            val state = awaitItem()
            val harry = state.characters.first { it.id == "1" }
            val draco = state.characters.first { it.id == "2" }
            assertEquals("file://covers/1.jpg", harry.imagePath)
            assertEquals("", draco.imagePath)
            cancelAndIgnoreRemainingEvents()
        }
    }

    //сохранение данных обнавления картинки
    @Test
    fun CharacterListViewModelTestUpdateImageSaveRepositori() = runTest {
        viewModel.onImageSelected("1", "content://photo/123")

        assertEquals(
            "1" to "content://photo/123",
            fakeRepository.lastImageUpdate
        )
    }

    //ошибка загрузки
    @Test
    fun CharacterListViewModelTestIsLoadingError() = runTest {
        fakeRepository.shouldThrow = true
        createViewModel()

        viewModel.uiState.test {
            val state = awaitItem()
            assertFalse(state.isLoading)
            assertNotNull(state.errorMessage)
            cancelAndIgnoreRemainingEvents()
        }
    }
}
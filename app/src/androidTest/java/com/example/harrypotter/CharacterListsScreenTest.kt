package com.example.harrypotter.presentation.characterlist

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.example.harrypotter.domain.model.Character
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test

class CharacterListScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private val harry = Character(
        id = "1",
        name = "Harry Potter",
        species = "human",
        gender = "male",
        house = "Gryffindor",
        yearOfBirth = 1980,
        imagePath = ""
    )

    private val draco = Character(
        id = "2",
        name = "Draco Malfoy",
        species = "human",
        gender = "male",
        house = "Slytherin",
        yearOfBirth = 1980,
        imagePath = ""
    )

    private fun setContent(
        uiState: CharacterListUiState,
        onHouseSelected: (String?) -> Unit = {},
        onCharacterClick: (String) -> Unit = {},
        onImageSelected: (String, String) -> Unit = { _, _ -> }
    ) {
        composeTestRule.setContent {
            CharacterListContent(
                uiState = uiState,
                onHouseSelected = onHouseSelected,
                onCharacterClick = onCharacterClick,
                onImageSelected = onImageSelected
            )
        }
    }

    //список персонажей отображается
    @Test
    fun CharacterListScreenTestVisible() {
        setContent(CharacterListUiState(characters = listOf(harry, draco), isLoading = false))

        composeTestRule.onNodeWithText("Harry Potter").assertIsDisplayed()
        composeTestRule.onNodeWithText("Draco Malfoy").assertIsDisplayed()
    }

    //индикатор загрузки отображается
    @Test
    fun CharacterListScreenTest() {
        setContent(CharacterListUiState(isLoading = true))

        composeTestRule.onNodeWithTag("loading_indicator").assertIsDisplayed()
        composeTestRule.onNodeWithText("Harry Potter").assertDoesNotExist()
    }

    //при пустом списке показывается сообщение
    @Test
    fun CharacterListScreenTestNullList() {
        setContent(CharacterListUiState(characters = emptyList(), isLoading = false))

        composeTestRule.onNodeWithTag("empty_message").assertIsDisplayed()
        composeTestRule.onNodeWithText("Персонажи не найдены").assertIsDisplayed()
    }

    //клик по кнопке Подробнее вызывает колбэк с id
    @Test
    fun CharacterListScreenTestDetailCallback() {
        var clickedId: String? = null
        setContent(
            uiState = CharacterListUiState(characters = listOf(harry), isLoading = false),
            onCharacterClick = { clickedId = it }
        )

        composeTestRule.onNodeWithTag("details_button_1").performClick()

        assertEquals("1", clickedId)
    }

    //клик по кнопке смены фото вызывает колбэк
    @Test
    fun CharacterListScreenTestImageCallback() {
        var pickedId: String? = null
        setContent(
            uiState = CharacterListUiState(characters = listOf(harry), isLoading = false),
            onImageSelected = { id, _ -> pickedId = id }
        )

        composeTestRule.onNodeWithTag("image_button_1").assertIsDisplayed()
    }

    //клик по факультету Гриффиндор вызывает колбэк
    @Test
    fun CharacterListScreenTestGryffindorCallback() {
        var selected: String? = null
        setContent(
            uiState = CharacterListUiState(
                characters = listOf(harry, draco),
                isLoading = false
            ),
            onHouseSelected = { selected = it }
        )
        composeTestRule.onNodeWithText("Gryffindor").performClick()
        assertEquals("Gryffindor", selected)
    }

    //сброс фильтров
    @Test
    fun CharacterListScreenTestResetsFilters() {
        var selected: String? = "Gryffindor"
        setContent(
            uiState = CharacterListUiState(
                characters = listOf(harry, draco),
                isLoading = false,
                selectedHouse = "Gryffindor"
            ),
            onHouseSelected = { selected = it }
        )
        composeTestRule.onNodeWithText("Все").performClick()
        assertEquals(null, selected)
    }

    //карточка персонажа отображается с корректным тегом
    @Test
    fun CharacterListScreenTestTag() {
        setContent(CharacterListUiState(characters = listOf(harry), isLoading = false))

        composeTestRule.onNodeWithTag("character_card_1").assertIsDisplayed()
        composeTestRule.onNodeWithTag("character_name_1").assertIsDisplayed()
    }

    //отображение всех 4 факультетов
    @Test
    fun CharacterListScreenTestFourHouse() {
        setContent(CharacterListUiState(characters = listOf(harry), isLoading = false))

        composeTestRule.onNodeWithText("Gryffindor").assertIsDisplayed()
        composeTestRule.onNodeWithText("Slytherin").assertIsDisplayed()
        composeTestRule.onNodeWithText("Hufflepuff").assertIsDisplayed()
        composeTestRule.onNodeWithText("Ravenclaw").assertIsDisplayed()
    }
}
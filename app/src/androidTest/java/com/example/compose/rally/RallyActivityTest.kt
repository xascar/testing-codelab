package com.example.compose.rally

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import com.example.compose.rally.ui.overview.OverviewBody
import org.junit.Assert.*
import org.junit.Rule

import org.junit.Test

class RallyActivityTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun rallyApp_selectionChangedOnClickingTabs() {
        composeTestRule.setContent {
            RallyApp()
        }

        composeTestRule.onRoot(useUnmergedTree = true).printToLog("selectionChangedOnClickingTabs")

        composeTestRule
            .onNode(
                hasText(RallyScreen.Overview .name.uppercase()) and
                        hasParent(
                            hasContentDescription(RallyScreen.Overview .name)
                        ),
                useUnmergedTree = true
            ).performClick()

        composeTestRule
            .onNode(
                hasText(RallyScreen.Overview.name.uppercase()) and
                        hasParent(
                            hasContentDescription(RallyScreen.Overview.name)
                        ),
                useUnmergedTree = true
            )
            .assertIsSelected()

    }


}
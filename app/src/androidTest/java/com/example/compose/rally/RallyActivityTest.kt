package com.example.compose.rally

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 *   Q1: Should I need to split into smaller functions or keep all in one function?
 *   Q2: Is there any way to print just the portion I'm testing for the Semantics tree?   (Not to include Text = '[Car Savings]'logged in details)
 */

@RunWith(AndroidJUnit4::class)  // To indicate that we've to run it with AndroidJUnit4 runner
class RallyActivityTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Before
    fun setUp() {
        composeTestRule.setContent {    // setting our composable as content for test
            RallyApp()
        }
    }

    @Test
    fun rallyApp_overviewExistAndIsSelected() {

        composeTestRule.onRoot(useUnmergedTree = true).printToLog("rallyApp_overviewExistAndIsSelected")

        composeTestRule
            .onNode(
                hasText(RallyScreen.Overview.name.uppercase()) and
                        hasParent(
                                    isSelected()
                        ),
                useUnmergedTree = true
            ).assertExists()

    }

    @Test
    fun rallyApp_accountsTabIsNotSelected() {

        composeTestRule.onRoot(useUnmergedTree = true).printToLog("rallyApp_accountsTabIsNotSelected")

        composeTestRule
            .onNode(
                hasContentDescription(RallyScreen.Accounts .name) and
                        isNotSelected(),
                useUnmergedTree = true
            ).assertExists()

    }

    @Test
    fun rallyApp_tabChangedWhenClickPerformed() {
        rallyApp_overviewExistAndIsSelected()
        rallyApp_accountsTabIsNotSelected()

        composeTestRule.onRoot(useUnmergedTree = true).printToLog("rallyApp_tabChangedWhenClickPerformed")

        composeTestRule
            .onNode(
                hasContentDescription(RallyScreen.Accounts .name) and
                        isNotSelected(),
                useUnmergedTree = true
            ).performClick()

        Thread.sleep(3000)

        composeTestRule
            .onNode(
                hasText(RallyScreen.Accounts.name.uppercase()) and
                        hasParent(
                            isSelected()
                        ),
                useUnmergedTree = true
            ).assertExists()

        Thread.sleep(3000)

        composeTestRule
            .onNode(
                hasContentDescription(RallyScreen.Overview .name) and
                        isNotSelected(),
                useUnmergedTree = true
            ).assertExists()

        composeTestRule.onRoot(useUnmergedTree = true).printToLog("rallyApp_tabChangedWhenClickPerformed")


    }


}
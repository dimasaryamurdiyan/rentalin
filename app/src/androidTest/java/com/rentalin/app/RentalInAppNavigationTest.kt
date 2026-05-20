package com.rentalin.app

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.v2.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import org.junit.Rule
import org.junit.Test

class RentalInAppNavigationTest {
    @get:Rule
    val composeRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun launchesDashboardAndSwitchesMainTabs() {
        composeRule.onNodeWithText("RentalIn").assertIsDisplayed()
        composeRule.onNodeWithText("Good morning, Alex").assertIsDisplayed()

        composeRule.onNodeWithText("Rentals").performClick()
        composeRule.onNodeWithText("Daniel Green").assertIsDisplayed()

        composeRule.onNodeWithText("Inventory").performClick()
        composeRule.onNodeWithText("Serialized Items").assertIsDisplayed()

        composeRule.onNodeWithText("Customers").performClick()
        composeRule.onNodeWithText("Account Overview").assertIsDisplayed()
    }
}

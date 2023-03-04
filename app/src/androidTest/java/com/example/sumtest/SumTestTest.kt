package com.example.sumtest

import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.hamcrest.CoreMatchers.allOf

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule

val robot = BaseRobot()

const val ONE_THOUSAND = 1000
const val ONE_THOUSAND_ONE = 1001
const val VALID_NUM_STR = ONE_THOUSAND.toString()
const val NEGATIVE_VALID_NUM_STR = (-ONE_THOUSAND_ONE).toString()
const val INVALID_NUM_STR = ONE_THOUSAND_ONE.toString()
const val INVALID_INPUT = "a"
const val EMPTY_INPUT_ERROR_TEXT = "One or more fields are empty"
const val INVALID_INPUT_ERROR_TEXT = "Only integers are allowed"

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class SumTestTest {
    @get:Rule
    val activityRule = ActivityScenarioRule(SumActivity::class.java)

    @Before
    fun before() {
        robot.assertOnView(withId(R.id.firstNumber), matches(withText("")))
        robot.assertOnView(withId(R.id.secondNumber), matches(withText("")))
    }

    @Test
    fun scenarioOne() {
        robot.doOnView(withId(R.id.firstNumber), typeText(VALID_NUM_STR))
        robot.doOnView(withId(R.id.secondNumber), typeText(VALID_NUM_STR), closeSoftKeyboard())
        clickAddAndWaitForLoading()

        robot.assertOnView(withId(R.id.result), matches(allOf(
            isDisplayed(),
            withText((VALID_NUM_STR.toInt()*2).toString())))
        )
    }

    @Test
    fun scenarioTwo() {
        robot.doOnView(withId(R.id.firstNumber), typeText(NEGATIVE_VALID_NUM_STR))
        robot.doOnView(withId(R.id.secondNumber), typeText(NEGATIVE_VALID_NUM_STR), closeSoftKeyboard())
        clickAddAndWaitForLoading()

        robot.assertOnView(withId(R.id.result), matches(allOf(
            isDisplayed(),
            withText((NEGATIVE_VALID_NUM_STR.toInt()*2).toString())))
        )
    }

    @Test
    fun scenarioThree() {
        robot.doOnView(withId(R.id.firstNumber), typeText(INVALID_NUM_STR))
        robot.doOnView(withId(R.id.secondNumber), typeText(VALID_NUM_STR), closeSoftKeyboard())
        clickAddAndWaitForLoading()

        robot.assertOnView(withId(R.id.error), matches(isDisplayed()))
    }

    @Test
    fun scenarioFour() {
        robot.doOnView(withId(R.id.firstNumber), typeText(VALID_NUM_STR))
        robot.doOnView(withId(R.id.secondNumber), typeText(INVALID_NUM_STR), closeSoftKeyboard())
        clickAddAndWaitForLoading()

        robot.assertOnView(withId(R.id.error), matches(allOf(isDisplayed())))
    }

    @Test
    fun scenarioFive() {
        robot.doOnView(withId(R.id.secondNumber), typeText(VALID_NUM_STR), closeSoftKeyboard())
        clickAddAndWaitForLoading()

        robot.assertOnView(withId(R.id.error), matches(allOf(
            isDisplayed(),
            withText(EMPTY_INPUT_ERROR_TEXT)))
        )
    }

    @Test
    fun scenarioSix() {
        robot.doOnView(withId(R.id.firstNumber), typeText(INVALID_NUM_STR), closeSoftKeyboard())
        clickAddAndWaitForLoading()

        robot.assertOnView(withId(R.id.error), matches(allOf(
            isDisplayed(),
            withText(EMPTY_INPUT_ERROR_TEXT)))
        )
    }

    @Test
    fun scenarioSeven() {
        robot.doOnView(withId(R.id.firstNumber), typeText(INVALID_INPUT))
        robot.doOnView(withId(R.id.secondNumber), typeText(INVALID_INPUT), closeSoftKeyboard())
        clickAddAndWaitForLoading()

        robot.assertOnView(withId(R.id.error), matches(allOf(
            isDisplayed(),
            withText(INVALID_INPUT_ERROR_TEXT)))
        )
    }

    private fun clickAddAndWaitForLoading() {
        robot.doOnView(withId(R.id.cta), click())
        robot.assertOnView(withId(R.id.loading), matches(isDisplayed()))
    }
}
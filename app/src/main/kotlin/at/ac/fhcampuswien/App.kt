/*
 * This Kotlin source file was generated by the Gradle 'init' task.
 */
package at.ac.fhcampuswien

import kotlin.IllegalArgumentException
import kotlin.math.pow
import kotlin.random.Random

class App {
    // Game logic for a number guessing game
    fun playNumberGame(digitsToGuess: Int = 4) {
        var input: Int = 0
        var won = false
        var generatedNumber = -1
        while (!won) {
            while (true) {
                print("Guess a number: ")
                try {
                    input = readln().toInt()
                } catch (e: IllegalArgumentException) {
                    println("Not a number!")
                    continue
                }
                break
            }
            val numberLength = input.toString().length
            if (generatedNumber == -1) {
                generatedNumber = generateRandomNonRepeatingNumber(numberLength)
            }
            try {
                val result = checkUserInputAgainstGeneratedNumber(input, generatedNumber)
                println(result)
                if (result.m == numberLength) {
                    println("You won!")
                    won = true;
                }
            } catch (e: IllegalArgumentException) {
                println("Number has to be ${generatedNumber.toString().length} digits long!")
                continue
            }
        }
    }

    /**
     * Generates a non-repeating number of a specified length between 1-9.
     *
     * Note: The function is designed to generate a number where each digit is unique and does not repeat.
     * It is important to ensure that the length parameter does not exceed the maximum possible length
     * for non-repeating digits (which is 9 excluding 0 for base-10 numbers).
     *
     * @param length The length of the non-repeating number to be generated.
     *               This dictates how many digits the generated number will have.
     * @return An integer of generated non-repeating number.
     *         The generated number will have a number of digits equal to the specified length and will
     *         contain unique, non-repeating digits.
     * @throws IllegalArgumentException if the length is more than 9 or less than 1.
     */
    val generateRandomNonRepeatingNumber: (Int) -> Int = { length ->
        if (length < 1 || length > 9) {
            throw IllegalArgumentException()
        }
        val numbers = mutableListOf<Int>()
        var i = 0
        while (i < length) {
            var rand = Random.nextInt(1, 10)
            var repeat = false;
            for (it in numbers) {
                if (it == rand) {
                    repeat = true;
                }
            }
            if (!repeat) {
                numbers.add(rand)
                i++
            }
        }
        var number = 0;
        for(j in 0 until length) {
            number += numbers[j] * 10.0.pow(j.toDouble()).toInt()
        }
        number
    }

    /**
     * Compares the user's input integer against a generated number for a guessing game.
     * This function evaluates how many digits the user guessed correctly and how many of those
     * are in the correct position. The game generates number with non-repeating digits.
     *
     * Note: The input and the generated number must both be numbers.
     * If the inputs do not meet these criteria, an IllegalArgumentException is thrown.
     *
     * @param input The user's input integer. It should be a number with non-repeating digits.
     * @param generatedNumber The generated number with non-repeating digits to compare against.
     * @return [CompareResult] with two properties:
     *         1. `n`: The number of digits guessed correctly (regardless of their position).
     *         2. `m`: The number of digits guessed correctly and in the correct position.
     *         The result is formatted as "Output: m:n", where "m" and "n" represent the above values, respectively.
     * @throws IllegalArgumentException if the inputs do not have the same number of digits.
     */
    val checkUserInputAgainstGeneratedNumber: (Int, Int) -> CompareResult = { input, generatedNumber ->
        //TODO implement the function
        if (input.toString().length != generatedNumber.toString().length) {
            throw IllegalArgumentException()
        }
        var n = 0
        var m = 0
        var inputs = input.toDigits()
        val randoms = generatedNumber.toDigits()

        for (i in 0 until inputs.size) {
            if (inputs[i] == randoms[i]) {
                m++
            }
        }
        inputs = inputs.distinct();
        for (i in inputs) {
            for (j in randoms) {
                if (i == j) {
                    n++
                    break
                }
            }
        }

        CompareResult(n, m)   // return value is a placeholder
    }
    // Integer to List of digits. From: https://discuss.kotlinlang.org/t/todigits-function-for-int/15298
    fun Int.toDigits(): List<Int> = toString().map { it.toString().toInt() }
}

fun main() {
    // TODO: call the App.playNumberGame function with and without default arguments
    val numberGame = App()
    numberGame.playNumberGame()

}

package dev.study.bowling

class Game {
    private val rolls = IntArray(21) // 10 frames, 2 rolls each + 1 bonus roll for strike in last frame
    private var currentRoll = 0

    fun roll(pins: Int) {
        rolls[currentRoll++] = pins
    }

    fun score(): Int {
        var score = 0
        var frameIndex = 0

        for (frame in 0 until 10) {
            if (isStrike(frameIndex)) {
                score += 10 + strikeBonus(frameIndex)
                frameIndex += 1
            } else if (isSpare(frameIndex)) {
                score += 10 + spareBonus(frameIndex)
                frameIndex += 2
            } else {
                score += sumOfPinsInFrame(frameIndex)
                frameIndex += 2
            }
        }
        return score
    }

    private fun strikeBonus(frameIndex: Int): Int = rolls[frameIndex + 1] + rolls[frameIndex + 2]

    private fun spareBonus(frameIndex: Int) = rolls[frameIndex + 2]

    private fun isStrike(frameIndex: Int) = rolls[frameIndex] == 10

    private fun isSpare(frameIndex: Int) = sumOfPinsInFrame(frameIndex) == 10

    private fun sumOfPinsInFrame(frameIndex: Int) = rolls[frameIndex] + rolls[frameIndex + 1]
}

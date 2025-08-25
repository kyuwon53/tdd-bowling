package bowling

import dev.study.bowling.Game
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

// gutter game
class BowlingGameTest {
    lateinit var game: Game

    @BeforeEach
    fun setUp() {
        game = Game()
    }

    @Test
    fun `gutter game`() {
        rollMany(20, 0)
        assertThat(game.score()).isEqualTo(0)
    }

    private fun rollMany(n: Int, pins: Int) {
        repeat(n) {
            game.roll(pins)
        }
    }

    @Test
    fun `all ones`() {
        rollMany(20, 1)
        assertThat(game.score()).isEqualTo(20)
    }

    @Test
    fun `one spare`() {
        rollSpare()
        game.roll(3)
        rollMany(17, 0)
        assertThat(game.score()).isEqualTo(16) // 5 + 5 + 3 + 3 (bonus for spare)
    }

    @Test
    fun `one strike`() {
        game.roll(10) // Strike
        game.roll(3)
        game.roll(4)
        rollMany(16, 0)
        assertThat(game.score()).isEqualTo(24) // 10 + 3 + 4 + 3 + 4 (bonus for strike)
    }

    @Test
    fun `perfect game`() {
        rollMany(12, 10) // 10 strikes
        assertThat(game.score()).isEqualTo(300) // 10 * (10 + 10 + 10)
    }

    private fun rollSpare() {
        game.roll(5)
        game.roll(5)
    }
}

package bowling

import grails.testing.services.ServiceUnitTest
import spock.lang.Specification

import static bowling.Frame.Status.OPEN
import static bowling.Frame.Status.SPARE
import static bowling.Frame.Status.STRIKE

class BowlingRulesServiceSpec extends Specification implements ServiceUnitTest<BowlingRulesService>{

    BowlingRulesService bowlingRulesService

    def setup() {
        bowlingRulesService = new BowlingRulesService()
    }

    def cleanup() {
    }

    void "test validate normal roll"() {
        given:
        def game = new BowlingGame()

        when:
        def errors = bowlingRulesService.validateRoll(game, 11)

        then:"validation should fail"
        errors.hasErrors()
    }

    void "test validate strike over unfinished frame"() {
        given:
        def game = bowlingRulesService.parse("3 7 8")

        when:
        def errors = bowlingRulesService.validateRoll(game, 10)

        then:"validation should fail"
        errors.hasErrors()
    }

    void "test validate success over missed first frame"() {
        given:
        def game = bowlingRulesService.parse("3 7 8 2 0")

        when:
        def errors = bowlingRulesService.validateRoll(game, 10)

        then:"validation should pass"
        !errors.hasErrors()
    }

    void "test validate over last frame"() {
        given:
        def game = bowlingRulesService.parse("X X X X X X X X X 3")

        when:
        def errors = bowlingRulesService.validateRoll(game, 9)

        then:"validation should fail"
        errors.hasErrors()
    }

    void "test validate finished game 1"() {
        given:
        def game = bowlingRulesService.parse("X X X X X X X X X 0 4")

        when:
        def errors = bowlingRulesService.validateRoll(game, 9)

        then:"validation should fail"
        errors.hasErrors()
    }

    void "test validate finished game 2"() {
        given:
        def game = bowlingRulesService.parse("X X X X X X X X X 6 4 1")

        when:
        def errors = bowlingRulesService.validateRoll(game, 9)

        then:"validation should fail"
        errors.hasErrors()
    }

    void "test 4 strikes"() {
        when:
        def game = bowlingRulesService.parse("X X X X")

        then:
        game.getFrames().size() == 4
        game.getFrames().every { it.status() == STRIKE}
    }

    void "test perfect game"() {
        when:
        def game = bowlingRulesService.parse("X X X X X X X X X X X X")

        then:
        game.getFrames().size() == 9
        game.getLastFrame().first == 10
        game.getLastFrame().second == 10
        game.getLastFrame().third == 10
    }

    void "test strike throw"() {
        given:
        def game = new BowlingGame()

        when:
        bowlingRulesService.rollBall(game, 10)

        then:
        game.getFrames().size() == 1
        game.getFrames()[0].getFirst() == 10
        game.getFrames()[0].status() == STRIKE
    }

}

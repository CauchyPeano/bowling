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

    void "test strike throw"() {
        given:
        def game = new BowlingGame()

        when:
        bowlingRulesService.rollBall(game, 10)


        then:"fix me"
        game.getFrames().size() == 1
        game.getFrames()[0].getFirst() == 10
        game.getFrames()[0].status() == STRIKE
    }

    void "test 4 strikes"() {
        when:
        def game = bowlingRulesService.parse("X X X X")

        then:
        game.getFrames().size() == 4
        game.getFrames().every { it.status() == STRIKE}

    }

    void "test unfinished sample game 1"() {
        when:
        def game = bowlingRulesService.parse("7 3 6 4 2 7 X 6 3 2 6 7 1 3 7")

        then:
        game.getFrames().size() == 8
        game.frames[0].status() == SPARE
        game.score(0) == 16
        game.frames[1].status() == SPARE
        game.score(1) == 28
        game.frames[2].status() == OPEN
        game.score(2) == 37
        game.frames[3].status() == STRIKE
        game.score(3) == 56
        game.frames[4].status() == OPEN
        game.score(4) == 65
        game.frames[5].status() == OPEN
        game.score(5) == 73
        game.frames[6].status() == OPEN
        game.score(6) == 81
        game.frames[7].status() == SPARE
        game.score(7) == null
    }

    void "test finished sample game 2"() {
        when:
        def game = bowlingRulesService.parse("7 3 6 4 2 7 X 6 3 2 6 7 1 3 7")

        then:
        game.getFrames().size() == 8
        game.frames[0].status() == SPARE
        game.frames[1].status() == SPARE
        game.frames[2].status() == OPEN
        game.frames[3].status() == STRIKE
        game.frames[4].status() == OPEN
        game.frames[5].status() == OPEN
        game.frames[6].status() == OPEN
        game.frames[7].status() == SPARE
    }
}

package bowling

import grails.testing.gorm.DomainUnitTest
import spock.lang.Specification

import static bowling.Frame.Status.OPEN
import static bowling.Frame.Status.OPEN
import static bowling.Frame.Status.OPEN
import static bowling.Frame.Status.OPEN
import static bowling.Frame.Status.SPARE
import static bowling.Frame.Status.SPARE
import static bowling.Frame.Status.SPARE
import static bowling.Frame.Status.STRIKE

class LastFrameSpec extends Specification implements DomainUnitTest<LastFrame> {

    BowlingRulesService bowlingRulesService

    def setup() {
        bowlingRulesService = new BowlingRulesService()
    }

    def cleanup() {
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

    void "test simple strike score calculation"() {
        when:
        def game = bowlingRulesService.parse("X 2 6")

        then:
        game.score(0) == 18
        game.score(1) == 8
    }

    void "test simple open strike score calculation"() {
        when:
        def game = bowlingRulesService.parse("X X 6")

        then:
        game.score(0) == 26
        game.score(1) == null
        game.score(2) == null
    }

    void "test simple spare score calculation"() {
        when:
        def game = bowlingRulesService.parse("5 5 2 6")

        then:
        game.score(0) == 12
        game.score(1) == 8
    }

    void "test simple open spare score calculation"() {
        when:
        def game = bowlingRulesService.parse("5 5 2 8")

        then:
        game.score(0) == 12
        game.score(1) == null
    }
}

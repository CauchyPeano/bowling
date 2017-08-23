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
        game.score(1) == 12
        game.frames[2].status() == OPEN
        game.score(2) == 9
        game.frames[3].status() == STRIKE
        game.score(3) == 19
        game.frames[4].status() == OPEN
        game.score(4) == 9
        game.frames[5].status() == OPEN
        game.score(5) == 8
        game.frames[6].status() == OPEN
        game.score(6) == 8
        game.frames[7].status() == SPARE
        game.score(7) == null
    }

    void "test finished sample game 1"() {
        when:
        def game = bowlingRulesService.parse("0 5 8 2 5 5 X 6 4 3 1 3 3 X X 8 2 X")

        then:
        game.aggregatedScore(0) == 5
        game.aggregatedScore(1) == 20
        game.aggregatedScore(2) == 40
        game.aggregatedScore(3) == 60
        game.aggregatedScore(4) == 73
        game.aggregatedScore(5) == 77
        game.aggregatedScore(6) == 83
        game.aggregatedScore(7) == 111
        game.aggregatedScore(8) == 131
        game.aggregatedScore(9) == 151
    }

    void "test finished sample game 2"() {
        when:
        def game = bowlingRulesService.parse("X 7 3 9 0 X 0 8 8 2 0 6 X X X 8 1")

        then:
        game.aggregatedScore(0) == 20
        game.aggregatedScore(1) == 39
        game.aggregatedScore(2) == 48
        game.aggregatedScore(3) == 66
        game.aggregatedScore(4) == 74
        game.aggregatedScore(5) == 84
        game.aggregatedScore(6) == 90
        game.aggregatedScore(7) == 120
        game.aggregatedScore(8) == 148
        game.aggregatedScore(9) == 167
    }

    void "test perfect game "() {
        when:
        def game = bowlingRulesService.parse("X X X X X X X X X X X X")

        then:
        game.aggregatedScore(0) == 30
        game.aggregatedScore(1) == 60
        game.aggregatedScore(2) == 90
        game.aggregatedScore(3) == 120
        game.aggregatedScore(4) == 150
        game.aggregatedScore(5) == 180
        game.aggregatedScore(6) == 210
        game.aggregatedScore(7) == 240
        game.aggregatedScore(8) == 270
        game.aggregatedScore(9) == 300
    }

    void "test unfinished last frame"() {
        when:
        def game = bowlingRulesService.parse("X X X X X X X X X X X")

        then:
        game.aggregatedScore(8) == 270
        game.aggregatedScore(9) == null
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

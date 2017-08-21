package bowling

import grails.testing.services.ServiceUnitTest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.validation.Errors
import spock.lang.Specification

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
        game = bowlingRulesService.rollBall(game, 10)


        then:"fix me"
        game.getFrames().last().status() == FrameStatus.STRIKE

    }
}

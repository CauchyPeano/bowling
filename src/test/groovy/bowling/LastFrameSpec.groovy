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


}

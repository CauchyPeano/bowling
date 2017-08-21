package bowling

import grails.testing.gorm.DomainUnitTest
import spock.lang.Specification

import static bowling.Frame.FrameStatus.OPEN
import static bowling.Frame.FrameStatus.SPARE
import static bowling.Frame.FrameStatus.STRIKE
import static bowling.Frame.FrameStatus.UNFINISHED

class FrameSpec extends Specification implements DomainUnitTest<Frame> {

    def setup() {
    }

    def cleanup() {
    }

    void "frame strike"() {
        given:
        Frame frame = new Frame()

        when:
        frame.first = 10

        then:
        frame.status() == STRIKE
    }

    void "frame open"() {
        given:
        Frame frame = new Frame()

        when:
        frame.first = 4
        frame.second = 3

        then:
        frame.status() == OPEN
    }

    void "frame unfinished"() {
        given:
        Frame frame = new Frame()

        when:
        frame.first = 4

        then:
        frame.status() == UNFINISHED
    }

    void "frame spare"() {
        given:
        Frame frame = new Frame()

        when:
        frame.first = 4
        frame.second = 6

        then:
        frame.status() == SPARE
    }
}

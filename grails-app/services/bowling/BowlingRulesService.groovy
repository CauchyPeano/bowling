package bowling

import grails.validation.ValidationErrors
import org.springframework.validation.Errors
import org.springframework.validation.ObjectError

class BowlingRulesService {

    def void rollBall(BowlingGame game, Integer pins) {
        if (game.frames.size() < 9) {
            if (game.frames.isEmpty() || game.frames.last().status() != Frame.Status.UNFINISHED) {
                def frame = new Frame()
                frame.setFirst(pins)
                game.frames.add(frame)
            } else if (game.frames.last().status() == Frame.Status.UNFINISHED) {
                game.frames.last().setSecond(pins)
            }
        } else if (game.frames.size() == 9) {
            if (game.lastFrame == null) {
                game.lastFrame = new LastFrame()
            }
            if (game.lastFrame.first == null) {
                game.lastFrame.first = pins
            } else if (game.lastFrame.second == null) {
                game.lastFrame.second = pins
            } else if (game.lastFrame.third == null) {
                game.lastFrame.third = pins
            } else {
                throw new IllegalStateException("Game finished")
            }
        }
    }

    Errors validateRoll(BowlingGame game, Integer amountOfPins) {
        def errors = new ValidationErrors(game)

        if (amountOfPins < 0 || amountOfPins > 10) {
            addError(errors, "Wrong amount of pins")
        }

        return errors
    }

    private addError(ValidationErrors errors, String message) {
        errors.addError(new ObjectError("BowlingGame", message))
    }

    BowlingGame parse(String game) {

        def rolls = game.tokenize(" ")

        BowlingGame bowlingGame = new BowlingGame()

        for (String pins : rolls) {
            int amount
            if (pins == "X") {
                amount = 10
            } else {
                amount = Integer.valueOf(pins)
            }

            rollBall(bowlingGame, amount)
        }

        bowlingGame
    }
}

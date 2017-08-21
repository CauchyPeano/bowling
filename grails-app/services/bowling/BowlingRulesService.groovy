package bowling

import grails.validation.ValidationErrors
import org.springframework.validation.Errors
import org.springframework.validation.ObjectError

class BowlingRulesService {

    BowlingGame rollBall(BowlingGame game, Integer pins) {
        BowlingGame next = new BowlingGame()

        if (game.frames.size() > 0) {
            def lastFrame = game.frames.last()
        }
        next
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
}

package bowling

import grails.validation.ValidationErrors
import org.springframework.validation.Errors
import org.springframework.validation.ObjectError

class BowlingRulesService {

    def rollBall(BowlingGame game, Integer pins) {
        if (game.frames.size() < 9) {
            if (game.frames.isEmpty() || game.frames.last().status() != Frame.Status.UNFINISHED) {
                def frame = new Frame()
                frame.setFirst(pins)
                game.frames.add(frame)
            } else if (game.frames.last().status() == Frame.Status.UNFINISHED) {
                game.frames.last().setSecond(pins)
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

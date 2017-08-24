package bowling

import grails.validation.ValidationErrors
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.validation.Errors
import org.springframework.validation.ObjectError

class BowlingController {

    @Autowired
    BowlingRulesService bowlingRulesService

    def index() {
        session["game"] = new BowlingGame()
        render(view: "index", model: [test: "wooot", game: session["game"]])
    }

    def roll() {
        def game = session["game"]
        if (!params.pins_amount) {
            def errors = new ValidationErrors(game)
            errors.addError(new ObjectError("BowlingGame", "Please enter amount of pins you want to hit"))
            game.setErrors(errors)

        } else {
            Integer pinAmount = Integer.valueOf(params.pins_amount)

            game.setErrors(null)

            def validation = bowlingRulesService.validateRoll(game, pinAmount)
            if (!validation.hasErrors()) {
                bowlingRulesService.rollBall(game, pinAmount)
            } else {
                game.setErrors(validation)
            }
        }

        render(view: "index", model: [test: "wooot", game: game])
    }
}

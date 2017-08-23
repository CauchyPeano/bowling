package bowling

import org.springframework.beans.factory.annotation.Autowired

class BowlingController {

    @Autowired
    BowlingRulesService bowlingRulesService

    def index() {
        session["game"] = new BowlingGame()
        render(view: "index", model: [test: "wooot", game: session["game"]])
    }

    def roll() {
        Integer pinAmount = Integer.valueOf(params.pins_amount)
        def game = session["game"]

//        if (bowlingRulesService.validateRoll(game, pinAmount)) {
        bowlingRulesService.rollBall(game,  pinAmount)
//        }


        params.pins_amount
        render(view: "index", model: [test: "wooot", game: game])
    }
}

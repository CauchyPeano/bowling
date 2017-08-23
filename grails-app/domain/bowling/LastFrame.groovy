package bowling

class LastFrame {

    Integer first
    Integer second
    Integer third

    Integer score() {

        if (first && second && first + second < 10) {
            return first + second
        } else if (first && second && third) {
            return first + second + third
        } else {
            return null
        }

    }

    static constraints = {
    }
}

package bowling

class LastFrame {

    Integer first
    Integer second
    Integer third

    Integer score() {

        if (first != null && second != null && first + second < 10) {
            return first + second
        } else if (first != null && second != null && third != null) {
            return first + second + third
        } else {
            return null
        }

    }

    static constraints = {
    }
}

package bowling

class Frame {

    enum Status {
        UNFINISHED, OPEN, STRIKE, SPARE
    }

    Integer first = null
    Integer second = null

    static constraints = {
        first range: 0..10
        second range: 0..10
    }

    Status status() {
        if (first == null && second == null) {
            return Status.UNFINISHED
        } else if (first != null && first < 10 && second == null) {
            return Status.UNFINISHED
        } else if (first == 10) {
            return Status.STRIKE
        } else if (second != null && first + second == 10) {
            return Status.SPARE
        } else if (second != null && first + second < 10) {
            return Status.OPEN
        }
        throw new IllegalStateException("Can't infer status of frame ${first}, ${second}")
    }

    @Override
    String toString() {
        return "Frame{" +
                "first=" + first +
                ", second=" + second +
                '}'
    }

}

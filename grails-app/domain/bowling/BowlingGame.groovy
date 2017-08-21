package bowling

class BowlingGame {

    List<Frame> frames = []
    LastFrame lastFrame
    Integer totalScore = 0

    static constraints = {

    }

    @Override
    String toString() {
        return "BowlingGame{" +
                "frames=" + frames +
                ", totalScore=" + totalScore +
                '}'
    }
}

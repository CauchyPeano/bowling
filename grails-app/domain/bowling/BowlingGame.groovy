package bowling

class BowlingGame {

    List<Frame> frames
    LastFrame lastFrame

    BowlingGame() {
        frames = new ArrayList<>(9)
        lastFrame = new LastFrame()
    }

    static constraints = {

    }

    @Override
    String toString() {
        return "BowlingGame{${frames}}"
    }

    def score(int frameIndex) {

    }
}

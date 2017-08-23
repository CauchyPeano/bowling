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
        validateFrameIndex(frameIndex)
        if (frameIndex == 9) {

        } else {

            def currentFrame = frames[frameIndex]
            if (currentFrame.status() == Frame.Status.STRIKE) {

            } else if (currentFrame.status() == Frame.Status.SPARE) {

                def rightScore = getRightScore(frameIndex)
                if (rightScore) {
                    return 10 + rightScore
                } else {
                    return null
                }

            } else if (currentFrame.status() == Frame.Status.OPEN) {
                return currentFrame.first + currentFrame.second
            } else {
                return null
            }
        }
    }

    private void validateFrameIndex(int frameIndex) {
        if (frameIndex < 0 || frameIndex > 9 || (frameIndex != 9 && frames.size() < frameIndex)) {
            throw new IllegalArgumentException("Index out of bounds ${frameIndex}")
        }
    }

    private Integer getRightScore(Integer frameIndex) {
        validateFrameIndex(frameIndex)
        if (frameIndex == 8) {
            return lastFrame.first
        } else if (frames[frameIndex + 1] && frames[frameIndex + 1].first) {
            return frames[frameIndex + 1].first
        } else {
            return null
        }
    }
}

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
        def currentFrame = frames[frameIndex]
        if (currentFrame == null) {
            return null
        } else if (frameIndex == 9) {
           return lastFrame.score()
        } else {

            if (currentFrame.status() == Frame.Status.STRIKE) {

                def rightScore = getRightScore(frameIndex)
                def secondRightScore = getSecondRightScore(frameIndex)
                if (rightScore != null && secondRightScore != null) {
                    return 10 + rightScore + secondRightScore
                } else {
                    null
                }

            } else if (currentFrame.status() == Frame.Status.SPARE) {

                def rightScore = getRightScore(frameIndex)
                if (rightScore != null) {
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
        } else if (frames[frameIndex + 1] && frames[frameIndex + 1].first != null) {
            return frames[frameIndex + 1].first
        } else {
            return null
        }
    }

    private Integer getSecondRightScore(Integer frameIndex) {
        validateFrameIndex(frameIndex)
        def nextFrame = frames[frameIndex + 1]
        if (frameIndex == 8) {
            return lastFrame.second
        } else if (nextFrame) {

            if (nextFrame.status() == Frame.Status.STRIKE) {
                return getRightScore(frameIndex + 1)
            } else {
                return nextFrame.second
            }

        } else {
            return null
        }
    }

    Integer aggregatedScore(Integer frameIndex) {

        int result = 0
        for (i in 0..frameIndex) {
            def s = score(i)
            if (s) {
                result += s
            } else {
                return null
            }
        }
        result
    }
}

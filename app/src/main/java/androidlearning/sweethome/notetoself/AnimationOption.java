package androidlearning.sweethome.notetoself;

/**
 * Created by sweethome on 13/03/16.
 */
enum AnimationOption {
    FAST(0), SLOW(1), NONE(2);
    private int animationSpeed;
    AnimationOption(final int animationSpeed) {
        this.animationSpeed = animationSpeed;
    }

    final int value() {
        return animationSpeed;
    }

    final static AnimationOption valueOf(final int animationSpeed) {
        switch (animationSpeed) {
            case 0:
                return FAST;
            case 1:
                return SLOW;
            default:
                return NONE;
        }
    }
}

package androidlearning.sweethome.notetoself;

/**
 * Created by sweethome on 13/03/16.
 */
final class SettingsProperties {
    private SettingsProperties() throws IllegalAccessException {
        throw new IllegalAccessException("Creating an instance of this class is not allowed.");
    }
    final static String SOUND = "sound";
    final static String ANIM_OPTION = "animation option";
    final static String APP_NAME = "Note to self";
}

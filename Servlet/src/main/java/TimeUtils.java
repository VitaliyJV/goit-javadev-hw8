import java.time.ZoneId;

public class TimeUtils {
    public static boolean validateTimeZone(String timeZone) {
        if (timeZone == null || timeZone.isEmpty()) {
            return false;
        }

        timeZone = timeZone.replace(" ", "+");
        try {
            ZoneId.of(timeZone);
        } catch (RuntimeException e) {
            return false;
        }
        return true;
    }
}

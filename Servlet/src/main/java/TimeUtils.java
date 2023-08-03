import java.time.ZoneId;

public class TimeUtils {

    public static ZoneId getTimeZone(String timeZone) {
            if (timeZone == null || timeZone.isEmpty()) {
            return ZoneId.of("UTC");
            }

            ZoneId zoneId;
            timeZone = timeZone.replace(" ", "+");
            try {
            zoneId = ZoneId.of(timeZone);
            } catch (RuntimeException e) {
            throw new RuntimeException();
            }
            return zoneId;
    }

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

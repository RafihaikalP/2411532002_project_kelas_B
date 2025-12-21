package helper;

import java.time.Duration;
import java.time.LocalDateTime;

public final class TimeCalculator {
    private TimeCalculator() {}

    public static long hoursBetween(LocalDateTime start, LocalDateTime end) {
        if (start == null || end == null) return 0;
        long minutes = Duration.between(start, end).toMinutes();
        
        long hours = (minutes + 59) / 60;
        return Math.max(hours, 1);
    }
}

package grasp.highcohesion;

import java.util.LinkedHashMap;
import java.util.Map;

import common.Console;
import grasp.highcohesion.Domain.Classroom;

/** One job: booking rooms. Its own booking-reference sequence. */
public class ClassroomScheduler {

    private int nextBookingNumber = 1;
    private final Map<String, String> bookings = new LinkedHashMap<>();

    public String scheduleClassroom(Classroom room, String slot) {
        String ref = "BK-" + String.format("%04d", nextBookingNumber++);
        bookings.put(ref, room.code() + "@" + slot);
        Console.ok("booking " + ref + ": " + room.code() + " at " + slot);
        return ref;
    }

    public int bookingCount() {
        return bookings.size();
    }
}

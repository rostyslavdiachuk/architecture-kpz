package grasp.highcohesion;

import common.Console;

/** One job: the facilities safety report. Reads the scheduler for room usage. */
public class FacilitiesReportService {

    private static final int EXTINGUISHERS_PER_SWEEP = 42;

    private final ClassroomScheduler scheduler;
    private int extinguishersChecked;

    public FacilitiesReportService(ClassroomScheduler scheduler) {
        this.scheduler = scheduler;
    }

    public void generateFireSafetyReport() {
        extinguishersChecked += EXTINGUISHERS_PER_SWEEP;
        Console.ok("fire-safety report: " + extinguishersChecked + " extinguishers checked, "
                + scheduler.bookingCount() + " rooms in use");
    }
}

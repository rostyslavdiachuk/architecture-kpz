package grasp.highcohesion;

import java.util.ArrayList;
import java.util.List;

import common.Console;
import grasp.highcohesion.Domain.Lecturer;

/** One job: paying lecturers. Its own payslip sequence. */
public class PayrollService {

    private int nextPayslipNumber = 1;
    private final List<String> payslips = new ArrayList<>();

    public void runPayroll(List<Lecturer> lecturers) {
        for (Lecturer l : lecturers) {
            String payslipNo = "PS-" + String.format("%04d", nextPayslipNumber++);
            payslips.add(payslipNo);
            Console.ok("payslip " + payslipNo + ": " + l.fullName() + " " + l.monthlySalaryUah() + " UAH");
        }
    }

    public List<String> payslips() {
        return List.copyOf(payslips);
    }
}

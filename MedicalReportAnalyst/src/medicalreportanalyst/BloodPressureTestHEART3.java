/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package medicalreportanalyst;

/**
 *
 * @author HAROON TRADERS
 */
public class BloodPressureTestHEART3 extends Heart{
     public BloodPressureTestHEART3(double systolic, double diastolic)
     {
        super(systolic, diastolic);
    }

    @Override
    public void analyze() {
        if (testValue1 >= 180 || testValue2 >= 120) {
            severity = "Critical"; // Hypertensive crisis
        } else if (testValue1 >= 140 || testValue2 >= 90) {
            severity = "Warning"; // Hypertension stage 2
        } else {
            severity = "Normal"; // Healthy BP
        }
    }

    @Override
    public String advice() {
        switch (severity) {
            case "Critical":
                return "Hypertensive crisis. Immediate medical attention needed.";
            case "Warning":
                return "High blood pressure. Reduce salt, exercise, and see a doctor.";
            default:
                return "Blood pressure is normal. Maintain a healthy lifestyle.";
        }
    }

    @Override
    public String severity() {
        return severity;
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package medicalreportanalyst;

/**
 *
 * @author HAROON TRADERS
 */
public class EGFRandCreatinineTestKIDNEY3 extends Kidney{
     public EGFRandCreatinineTestKIDNEY3(double egfr, double creatinine) {
        super(egfr, creatinine);
    }

    /**
     *
     */
    @Override
    public void analyze() {
        if (testValue1 < 15 || testValue2 > 5.0) {
            severity = "Critical"; // Kidney failure
        } else if (testValue1 < 60 || testValue2 > 1.3) {
            severity = "Warning";  // Moderate or mild kidney dysfunction
        } else {
            severity = "Normal";   // Good kidney function
        }
    }

    @Override
    public String advice() {
        switch (severity) {
            case "Critical":
                return "Kidney failure suspected. Urgent transplant or dialysis needed.";
            case "Warning":
                return "Impaired kidney function. Monitor eGFR, consult nephrologist.";
            default:
                return "Kidney function is healthy. Maintain hydration and a balanced diet.";
        }
    }

    @Override
    public String severity() {
        return severity;
    }
}

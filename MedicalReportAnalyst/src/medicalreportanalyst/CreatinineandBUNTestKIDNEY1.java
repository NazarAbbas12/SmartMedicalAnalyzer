/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package medicalreportanalyst;

/**
 *
 * @author HAROON TRADERS
 */
public class CreatinineandBUNTestKIDNEY1 extends Kidney{
    
    CreatinineandBUNTestKIDNEY1(double creatinine, double bun) {
        super( creatinine, bun); // testValue1 = creatinine, testValue2 = BUN
    }
     @Override
    public void analyze() {
        if (testValue1 > 2.0 || testValue2 > 40) {
            severity = "Critical";  // Possible acute or severe chronic kidney failure
        } else if (testValue1 > 1.3 || testValue2 > 20) {
            severity = "Warning";   // Possible early kidney dysfunction or dehydration
        } else {
            severity = "Normal";    // Healthy kidney function
        }
    }

    @Override
    public String advice() {
        switch (severity) {
            case "Critical":
                return "Severe kidney impairment. See nephrologist urgently.";
            case "Warning":
                return "Monitor kidneys, stay hydrated, limit protein, consult doctor.";
            default:
                return "Kidney function is within normal limits. Stay hydrated and healthy.";
        }
    }

    @Override
    public String severity() {
        return severity;
    }
}

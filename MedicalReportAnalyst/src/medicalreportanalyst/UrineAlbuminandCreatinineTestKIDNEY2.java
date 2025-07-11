/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package medicalreportanalyst;

/**
 *
 * @author HAROON TRADERS
 */
public class UrineAlbuminandCreatinineTestKIDNEY2 extends Kidney{
     public UrineAlbuminandCreatinineTestKIDNEY2(double urineAlbumin, double urineCreatinine) {
        super(urineAlbumin, urineCreatinine);
    }

    /**
     *
     */
    @Override
    public void analyze() {
        double uacr = (testValue1 / testValue2) * 1000; // UACR in mg/g

        if (uacr > 300) {
            severity = "Critical";  // Severe kidney damage
        } else if (uacr >= 30) {
            severity = "Warning";   // Early kidney damage
        } else {
            severity = "Normal";    // Healthy kidney function
        }
    }

    @Override
    public String advice() {
        switch (severity) {
            case "Critical":
                return "Severe protein loss in urine. Consult a nephrologist urgently.";
            case "Warning":
                return "Early kidney damage. Monitor sugar/pressure, consult doctor.";
            default:
                return "Kidney filtration is working well. Maintain healthy habits.";
        }
    }

    @Override
    public String severity() {
        return severity;
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package medicalreportanalyst;

/**
 *
 * @author HAROON TRADERS
 */
public class BilirubinTestLIVER3 extends Liver{
  //  private String severity;
    
    BilirubinTestLIVER3(double directBilirubin, double indirectBilirubin)
    {
     super(directBilirubin,indirectBilirubin);
    }
    
    @Override
    public void analyze()
    {
        if (testValue1 > 1.0 || testValue2 > 2.0) {
            severity = "Critical";
        } else if (testValue1 > 0.3 || testValue2 > 1.0) {
            severity = "Warning";
        } else {
            severity = "Normal";
        }
    }
    @Override
    public String advice()
    {
        switch(severity)
        {
             case "Critical":
                return "Immediate evaluation required for possible hemolytic disease.";
            case "Warning":
                return "Monitor bilirubin levels and consult a doctor if symptoms worsen.";
            default:
                return "Bilirubin levels are normal. No immediate concerns.";
        }
    }

    /**
     *
     * @return
     */
    @Override
    public String severity()
    {
        return severity;
    }
}

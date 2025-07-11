/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package medicalreportanalyst;

/**
 *
 * @author HAROON TRADERS
 */
public class ALPandGGTtestLIVER2 extends Liver{
    //private String severity;
    
     public ALPandGGTtestLIVER2(double alp, double ggt) {
        super(alp, ggt);
    }
    
    @Override
    public void analyze() {
       
        if (testValue1 > 450 || testValue2 > 200) {
            severity = "Critical";
        } else if (testValue1 > 147 || testValue2 > 48) {
            severity = "Warning";
        } else {
            severity = "Normal";
        }
    }
    
    @Override
    public String advice() {
        switch (severity) {
            case "Critical":
                return "Seek immediate medical attention for possible serious liver issues.";
            case "Warning":
                return "Avoid alcohol, review medications, and consider liver-friendly diet.";
            default:
                return "Liver enzymes are within normal limits. Maintain healthy habits.";
        }
    }
    
    @Override
    public String severity() {
        return severity;
    }
}

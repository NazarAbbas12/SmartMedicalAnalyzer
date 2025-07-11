/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package medicalreportanalyst;

/**
 *
 * @author HAROON TRADERS
 */
public class ASTandALTtestLIVER1 extends Liver{
   // private String severity;
    
    ASTandALTtestLIVER1(double alt,double ast)
    {
       super(alt,ast);
    }
    
    @Override
    public void analyze()
    {
        if (testValue1 > 56 || testValue2 > 56) {
            severity = "Critical";
        } else if (testValue1 > 40 || testValue2 > 40) {
            severity = "Warning";
        } else {
            severity = "Normal";
        }
    }
    
    /**
     *
     * @return
     */
    @Override
    public String advice() {
        switch (severity) {
            case "Critical":
                return "Consult a Hepatologist immediately.";
            case "Warning":
                return "Take liver-protecting diet. Avoid alcohol.";
            default:
                return "Liver condition is normal. Keep up the good lifestyle!";
        }
    }
    @Override
    public String severity()
    {
        return severity;
    }
}

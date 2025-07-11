/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package medicalreportanalyst;

/**
 *
 * @author HAROON TRADERS
 */
public class LDLandHDLTestHEART1 extends Heart{
  
    
    LDLandHDLTestHEART1(double ldl,double hdl)
    {
       super(ldl,hdl);      
    }

    /**
     *
     */
    @Override
    public void analyze()
    {
          if (testValue1 > 190 || testValue2 < 40) {
            severity = "Critical";  // Very high LDL or very low HDL
        } else if (testValue1 > 130 || testValue2 < 50) {
            severity = "Warning";   // Borderline high LDL or low HDL
        } else {
            severity = "Normal";    // Healthy cholesterol balance
        }
    }
    @Override
    public String advice()
    {
        switch(severity)
        {
            case "Critical":
                return "High heart disease risk. Urgent lifestyle changes and medical care needed.";
            case "Warning":
                return "Monitor diet, exercise regularly, and consult a doctor for lipid management.";
            default:
                return "Cholesterol levels are heart-healthy. Maintain your routine!";
        
        }
    }
    @Override
    public String severity()
    {
        return severity;
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package medicalreportanalyst;

/**
 *
 * @author HAROON TRADERS
 */
public class HeartRateandQTTestHEART2 extends Heart{
    HeartRateandQTTestHEART2(double heart,double qtInterval)
    {
        super(heart,qtInterval);
    }
    
    @Override
    public void analyze()
    {
           if (testValue1 < 50 || testValue1 > 120 || testValue2 > 500) {
            severity = "Critical"; // Bradycardia/Tachycardia or prolonged QT
        } else if ((testValue1 >= 100 && testValue1 <= 120) || testValue2 > 440) {
            severity = "Warning"; // Borderline fast heart rate or slightly prolonged QT
        } else {
            severity = "Normal";
        }
    }
    
    /**
     *
     * @return
     */
    @Override
    public String advice()
    {
        switch(severity)
        {
             case "Critical":
                return "Abnormal heart rhythm. Seek immediate cardiac evaluation.";
            case "Warning":
                return "Monitor heart rhythm. Avoid stress, caffeine, and consult a cardiologist.";
            default:
                return "Heart rhythm is stable. Keep monitoring regularly.";
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

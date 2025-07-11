/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package medicalreportanalyst;

/**
 *
 * @author HAROON TRADERS
 */
public abstract class BodyPart implements Analyzable{
    protected double testValue1;
    protected double testValue2;
    
    public BodyPart(double testValue1,double testValue2)
    {
        this.testValue1=testValue1;
        this.testValue2=testValue2;
    }
    protected String severity;
}

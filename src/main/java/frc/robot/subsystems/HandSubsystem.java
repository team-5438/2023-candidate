package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.AddressableLED;
import edu.wpi.first.wpilibj.AddressableLEDBuffer;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticHub;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;

public class HandSubsystem {
    public CANSparkMax leftIntake = new CANSparkMax(7, MotorType.kBrushless);
    public CANSparkMax rightIntake = new CANSparkMax(8, MotorType.kBrushless);
    public CANSparkMax wrist = new CANSparkMax(9, MotorType.kBrushless);
    public PneumaticHub RoboComp = new PneumaticHub(10);
    public static DoubleSolenoid HandSolenoid = new DoubleSolenoid(10, PneumaticsModuleType.REVPH, 1, 2);
    // LEDS!!
    public static AddressableLED sponsorStrip1;
    public AddressableLED sponsorStrip2;
    public AddressableLED electronicStrip;

    public static AddressableLEDBuffer sponsorStrip1Buffer;
    public AddressableLEDBuffer sponsorStrip2Buffer;
    public AddressableLEDBuffer electronicLedBuffer;

    public HandSubsystem() {
        HandSolenoid.set(Value.kOff);
        HandSolenoid.set(Value.kReverse);    
        RoboComp.enableCompressorAnalog(80, 110);

        sponsorStrip1 = new AddressableLED(0);
        sponsorStrip1Buffer = new AddressableLEDBuffer(56);
        sponsorStrip1.setLength(56);
        sponsorStrip1.setData(sponsorStrip1Buffer);
        
        sponsorStrip1.start();
        if (HandSolenoid.get() == Value.kReverse) {
            setStripColor(sponsorStrip1, sponsorStrip1Buffer, 255, 128, 0);
        } else {
            setStripColor(sponsorStrip1, sponsorStrip1Buffer, 255, 0, 255);
        }
    }
    
    public static void setStripColor(AddressableLED m_led, AddressableLEDBuffer m_ledbuffer, int r, int g, int b) {
        for (int i = 0; i < m_ledbuffer.getLength(); i++) {
            m_ledbuffer.setRGB(i, r, g, b);
        }
        m_led.setData(m_ledbuffer);
    }


    public static void solenoidToggle() {
        HandSolenoid.toggle();
        if (HandSolenoid.get() == Value.kReverse) {
            setStripColor(sponsorStrip1, sponsorStrip1Buffer, 255, 128, 0);
        } else {
            setStripColor(sponsorStrip1, sponsorStrip1Buffer, 255, 0, 255);
        }
    }

}

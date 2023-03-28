package frc.robot.utils;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;

public class MotorController {

    MotorControllerGroup m_left = new MotorControllerGroup(null);
    MotorControllerGroup m_right = new MotorControllerGroup(null);

    private int deviceId;
    private boolean inverted;
    private char side;

    public MotorController(int deviceId, boolean inverted, char side) {
        this.deviceId = deviceId;
        this.inverted = inverted;
        this.side = side;
    }

    public CANSparkMax createSpark() {
        CANSparkMax canSparkMax = new CANSparkMax(this.deviceId, CANSparkMaxLowLevel.MotorType.kBrushless);
        canSparkMax.restoreFactoryDefaults();
        canSparkMax.setInverted(this.inverted);

        if (this.side == 'L') {
            MotorControllerGroup m_left = new MotorControllerGroup(canSparkMax);
        } else if (this.side == 'R') {
            MotorControllerGroup m_right = new MotorControllerGroup(canSparkMax);
        }
        return canSparkMax;
    }
    
}
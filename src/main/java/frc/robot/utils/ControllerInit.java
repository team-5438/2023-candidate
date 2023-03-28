package frc.robot.utils;

import edu.wpi.first.wpilibj.PS4Controller;
import edu.wpi.first.wpilibj.XboxController;

public class ControllerInit {
    
    public static PS4Controller operator;
    public static XboxController driver;

    public ControllerInit(int deviceId, char type) {
        if (type == 'P') {
            PS4Controller operator = new PS4Controller(deviceId);
        } else if (type == 'X') {
            XboxController driver = new XboxController(deviceId);
        }
    }
}
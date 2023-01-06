package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Sensor
{
        private DigitalChannel sensor;
        public boolean finish;
        private boolean reverse;

        public void init(HardwareMap hardwaremap, String name, boolean reverse)
        {
                sensor = hardwaremap.get(DigitalChannel.class, name);
                sensor.setMode(DigitalChannel.Mode.INPUT);
                this.reverse = reverse;
                finish = false;
        }
        public void update()
        {
                if(!reverse && sensor.getState())
                {
                        finish = true;
                }
                else if(reverse && sensor.getState())
                {
                        finish = true;
                }
        }
        public boolean finish()
        {
                return finish;
        }
}
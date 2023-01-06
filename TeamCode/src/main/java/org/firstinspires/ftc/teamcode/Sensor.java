package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Sensor
{
        private DigitalChannel sensor;
        public boolean finish;
        private boolean reverse;
        private boolean active;

        public void init(HardwareMap hardwaremap, String name, boolean reverse)
        {
                sensor = hardwaremap.get(DigitalChannel.class, name);
                sensor.setMode(DigitalChannel.Mode.INPUT);
                this.reverse = reverse;
                active = false;
                finish = false;
        }
        public void update()
        {
                if(active)
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
        }
        public void activate()
        {
                active = true;
        }
        public boolean finish()
        {
                return finish;
        }
}
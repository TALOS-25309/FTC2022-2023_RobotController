package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.HardwareMap;
import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Sensor
{
        private DigitalChannel sensor;
        public boolean finish;
        private boolean reverse;
        private boolean active;
        private Telemetry telemetry;

        public void init(HardwareMap hardwaremap, Telemetry telemetry, String name, boolean reverse)
        {
                sensor = hardwaremap.get(DigitalChannel.class, name);
                sensor.setMode(DigitalChannel.Mode.INPUT);
                this.reverse = reverse;
                active = false;
                finish = false;
                this.telemetry = telemetry;
        }
        public void update()
        {
                if(active)
                {
                        if(!reverse && sensor.getState())
                        {
                                finish = true;
                                active = false;
                        }
                        else if(reverse && !sensor.getState())
                        {
                                finish = true;
                                active = false;
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
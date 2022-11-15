/*
 * Copyright 2021 jrosmoveit project
 * 
 * Website: https://github.com/pinorobotics/jros2moveit
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package pinorobotics.ros2robotstate.tests;

import id.jros2client.JRos2ClientFactory;
import id.xfunction.ResourceUtils;
import id.xfunction.XJson;
import id.xfunction.logging.XLogger;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import pinorobotics.robotstate.RobotStateMonitor;
import pinorobotics.robotstate.tests.AbstractRobotStateMonitorIT;
import pinorobotics.ros2robotstate.Ros2RobotStateMonitor;

/**
 * @author aeon_flux aeon_flux@eclipso.ch
 */
@Order(1)
public class Ros2RobotStateMonitorIT extends AbstractRobotStateMonitorIT {

    private static final ResourceUtils resourceUtils = new ResourceUtils();
    private JRos2ClientFactory clientFactory = new JRos2ClientFactory();

    @BeforeAll
    public static void setupAll() {
        XLogger.load("jros2moveit-test.properties");
        XJson.setLimitDecimalPlaces(2);
        XJson.setNegativeZero(false);
    }

    @Override
    protected RobotStateMonitor<?> createMonitor() {
        return new Ros2RobotStateMonitor(clientFactory.createClient());
    }

    @Override
    protected String expectedState() {
        return resourceUtils.readResource("robot-state");
    }
}

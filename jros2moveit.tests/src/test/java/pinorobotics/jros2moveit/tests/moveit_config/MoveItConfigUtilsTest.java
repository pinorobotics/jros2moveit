/*
 * Copyright 2025 jrosmoveit project
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
package pinorobotics.jros2moveit.tests.moveit_config;

import java.nio.file.Paths;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pinorobotics.jros2moveit.moveit_config.MoveItConfigUtils;

public class MoveItConfigUtilsTest {

    @Test
    public void test() {
        var config = new MoveItConfigUtils().read(Paths.get("samples/hello_moveit_config"));
        Assertions.assertEquals(
                true,
                config.ros2Controllers()
                        .get("controller_manager")
                        .ros__parameters()
                        .joints()
                        .isEmpty());
        Assertions.assertEquals(
                """
MoveItConfig[initialPositions=InitialPositions[initialPositions={Joint_0=3.0815, Joint_1=3.0815, Joint_2=-2.3783, Joint_3=2.261, Joint_4=0.0}], ros2Controllers={dorna2_arm_controller=Ros2Controller[ros__parameters=RosParameters[joints=[Joint_0, Joint_1, Joint_2, Joint_3, Joint_4], commandInterfaces=[position], stateInterfaces=[position, velocity], updateRate=0]], controller_manager=Ros2Controller[ros__parameters=RosParameters[joints=null, commandInterfaces=null, stateInterfaces=null, updateRate=100]]}]
"""
                        .strip(),
                config.toString());
    }
}

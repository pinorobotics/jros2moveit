/*
 * Copyright 2024 jrosmoveit project
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
package pinorobotics.jros2moveit.tests;

import id.jros2client.*;
import id.jrosmessages.geometry_msgs.*;
import pinorobotics.jros2moveit.*;
import pinorobotics.robotstate.*;

/**
 * @author aeon_flux aeon_flux@eclipso.ch
 */
public class JRos2MoveItExample {

    public static void main(String[] args) {
        var configBuilder = new JRos2ClientConfiguration.Builder();
        // use configBuilder to override default parameters if needed
        try (var client = new JRos2ClientFactory().createClient(configBuilder.build()); ) {
            var moveIt =
                    new JRos2MoveItFactory()
                            .createMoveItClient(client, "panda_arm", new RobotModel("world"));
            var targetPose =
                    new PoseMessage()
                            .withPosition(new PointMessage(0.28, -0.2, 0.5))
                            .withQuaternion(new QuaternionMessage().withW(-1.0));
            moveIt.setPoseTarget(targetPose, "panda_hand");
            var plan = moveIt.plan();
            System.out.println("Calculated plan: " + plan);
            moveIt.execute(plan);
        }
    }
}

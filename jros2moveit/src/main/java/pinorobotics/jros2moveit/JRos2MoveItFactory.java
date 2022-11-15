/*
 * Copyright 2022 jrosmoveit project
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
package pinorobotics.jros2moveit;

import id.jros2client.JRos2Client;
import pinorobotics.jros2actionlib.JRos2ActionClientFactory;
import pinorobotics.jros2moveit.moveit_msgs.ExecuteTrajectoryActionDefinition;
import pinorobotics.jros2moveit.moveit_msgs.MoveGroupActionDefinition;
import pinorobotics.jrosmoveit.impl.JRosMoveItConstants;
import pinorobotics.robotstate.RobotModel;

/**
 * Factory methods for {@link JRos2MoveIt}
 *
 * @author aeon_flux aeon_flux@eclipso.ch
 */
public class JRos2MoveItFactory {

    /**
     * Creates a new instance of the client which will interact with MoveIt
     *
     * @param client ROS2 client
     */
    public JRos2MoveIt createMoveItClient(JRos2Client client, String groupName, RobotModel model) {
        var moveGroup =
                new JRos2ActionClientFactory()
                        .createClient(
                                client,
                                new MoveGroupActionDefinition(),
                                JRosMoveItConstants.MOVE_GROUP_ACTION_NAME);
        var executeTrajectory =
                new JRos2ActionClientFactory()
                        .createClient(
                                client,
                                new ExecuteTrajectoryActionDefinition(),
                                JRosMoveItConstants.EXECUTE_TRAJECTORY_ACTION_NAME);
        return new JRos2MoveIt(moveGroup, executeTrajectory, groupName, model);
    }
}

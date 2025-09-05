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
import id.jroscommon.RosRelease;
import id.jroscommon.RosVersion;
import id.xfunction.Preconditions;
import pinorobotics.jros2actionlib.JRos2ActionLibFactory;
import pinorobotics.jrosmoveit.entities.Plan;
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
    public <P extends Plan> JRos2MoveIt<P> createMoveItClient(
            JRos2Client client, RosRelease rosRelease, String groupName, RobotModel model) {
        Preconditions.equals(RosVersion.ROS2, rosRelease.getVersion(), "ROS2 release required");
        String actionServerName = "/move_action";
        var moveGroup =
                switch (rosRelease) {
                    case ROS2_HUMBLE ->
                            new JRos2ActionLibFactory()
                                    .createClient(
                                            client,
                                            new pinorobotics.jros2moveit.moveit_msgs.humble
                                                    .MoveGroupActionDefinition(),
                                            actionServerName);
                    default ->
                            new JRos2ActionLibFactory()
                                    .createClient(
                                            client,
                                            new pinorobotics.jros2moveit.moveit_msgs
                                                    .MoveGroupActionDefinition(),
                                            actionServerName);
                };
        var executeTrajectory =
                switch (rosRelease) {
                    case ROS2_HUMBLE ->
                            new JRos2ActionLibFactory()
                                    .createClient(
                                            client,
                                            new pinorobotics.jros2moveit.moveit_msgs.humble
                                                    .ExecuteTrajectoryActionDefinition(),
                                            JRosMoveItConstants.EXECUTE_TRAJECTORY_ACTION_NAME);
                    default ->
                            new JRos2ActionLibFactory()
                                    .createClient(
                                            client,
                                            new pinorobotics.jros2moveit.moveit_msgs
                                                    .ExecuteTrajectoryActionDefinition(),
                                            JRosMoveItConstants.EXECUTE_TRAJECTORY_ACTION_NAME);
                };
        return new JRos2MoveIt(moveGroup, executeTrajectory, groupName, model);
    }
}

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
package pinorobotics.jros2moveit.moveit_msgs;

import id.jrosmessages.Message;
import id.jrosmessages.MessageMetadata;
import id.jrosmessages.std_msgs.StringMessage;
import id.xfunction.XJson;
import java.util.Arrays;
import java.util.Objects;
import pinorobotics.jrosmoveit.impl.clients.executetrajectory.ExecuteTrajectoryGoal;

/** Definition for moveit_msgs/ExecuteTrajectoryGoal */
@MessageMetadata(
        name = ExecuteTrajectoryGoalMessage.NAME,
        fields = {"trajectory", "controller_names"})
public class ExecuteTrajectoryGoalMessage implements ExecuteTrajectoryGoal, Message {

    static final String NAME = "moveit_msgs/ExecuteTrajectoryGoal";

    /** The trajectory to execute */
    public RobotTrajectoryMessage trajectory = new RobotTrajectoryMessage();

    /** The controller names to use for execution */
    public StringMessage[] controller_names = new StringMessage[0];

    public ExecuteTrajectoryGoalMessage withTrajectory(RobotTrajectoryMessage trajectory) {
        this.trajectory = trajectory;
        return this;
    }

    public ExecuteTrajectoryGoalMessage withControllerNames(StringMessage... controller_names) {
        this.controller_names = controller_names;
        return this;
    }

    @Override
    public int hashCode() {
        return Objects.hash(trajectory, Arrays.hashCode(controller_names));
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof ExecuteTrajectoryGoalMessage other)
            return Objects.equals(trajectory, other.trajectory)
                    && Arrays.equals(controller_names, other.controller_names);
        return false;
    }

    @Override
    public String toString() {
        return XJson.asString(
                "trajectory", trajectory,
                "controller_names", controller_names);
    }
}

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
package pinorobotics.jros2moveit.impl.clients.executetrajectory;

import pinorobotics.jros2moveit.moveit_msgs.humble.ExecuteTrajectoryActionGoalMessage;
import pinorobotics.jros2moveit.moveit_msgs.humble.ExecuteTrajectoryGoalMessage;
import pinorobotics.jros2moveit.moveit_msgs.humble.MoveGroupResultMessage;
import pinorobotics.jrosactionlib.JRosActionClient;
import pinorobotics.jrosactionlib.msgs.ActionDefinition;
import pinorobotics.jrosmoveit.impl.clients.executetrajectory.AbstractExecuteTrajectoryClient;
import pinorobotics.jrosmoveit.impl.clients.executetrajectory.ExecuteTrajectoryGoal;
import pinorobotics.jrosmoveit.impl.clients.executetrajectory.ExecuteTrajectoryResult;

/**
 * @author aeon_flux aeon_flux@eclipso.ch
 */
public class Ros2ExecuteTrajectoryClient<
                G extends ExecuteTrajectoryGoal, R extends ExecuteTrajectoryResult>
        extends AbstractExecuteTrajectoryClient<MoveGroupResultMessage, G, R> {

    private ActionDefinition<?, G, R> actionDefinition;

    public Ros2ExecuteTrajectoryClient(JRosActionClient<G, R> executeTrajectoryActionClient) {
        super(executeTrajectoryActionClient);
        this.actionDefinition = executeTrajectoryActionClient.getActionDefinition();
    }

    @Override
    protected G createExecuteTrajectoryRequest(MoveGroupResultMessage plan) {
        var goalClass = actionDefinition.getActionGoalMessage().getMessageClass();
        if (goalClass.isAssignableFrom(ExecuteTrajectoryActionGoalMessage.class)) {
            var goal = new ExecuteTrajectoryGoalMessage();
            goal.trajectory = plan.planned_trajectory;
            return (G) goal;
        } else throw new UnsupportedOperationException("" + actionDefinition);
    }
}

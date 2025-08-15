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
package pinorobotics.jros2moveit.impl.clients.movegroup;

import pinorobotics.jros2moveit.moveit_msgs.MoveGroupGoalMessage;
import pinorobotics.jros2moveit.moveit_msgs.PlanningOptionsMessage;
import pinorobotics.jros2moveit.moveit_msgs.PlanningSceneMessage;
import pinorobotics.jros2moveit.moveit_msgs.RobotStateMessage;
import pinorobotics.jrosactionlib.JRosActionClient;
import pinorobotics.jrosmoveit.impl.MotionRequest;
import pinorobotics.jrosmoveit.impl.clients.movegroup.AbstractMoveGroupClient;
import pinorobotics.jrosmoveit.impl.clients.movegroup.MoveGroupGoal;
import pinorobotics.jrosmoveit.impl.clients.movegroup.MoveGroupResult;
import pinorobotics.robotstate.RobotModel;

/**
 * @author aeon_flux aeon_flux@eclipso.ch
 */
public class Ros2MoveGroupClient<G extends MoveGroupGoal, R extends MoveGroupResult>
        extends AbstractMoveGroupClient<G, R> {

    private MotionPlanRequestFactory motionRequestFactory;
    private MoveGroupGoalMessage moveGroupGoal =
            new MoveGroupGoalMessage()
                    .withPlanningOptions(
                            new PlanningOptionsMessage()
                                    .withLookAround(false)
                                    .withReplan(false)
                                    .withPlanningSceneDiff(
                                            new PlanningSceneMessage()
                                                    .withIsDiff(true)
                                                    .withRobotState(
                                                            new RobotStateMessage()
                                                                    .withIsDiff(true))));

    public Ros2MoveGroupClient(
            JRosActionClient<G, R> moveGroupActionClient, String groupName, RobotModel model) {
        super(moveGroupActionClient);
        motionRequestFactory = new MotionPlanRequestFactory(groupName, model);
    }

    @Override
    protected G createGoalRequest(boolean planOnly, MotionRequest motionRequest) {
        moveGroupGoal.planning_options.plan_only = planOnly;
        var request = motionRequestFactory.populateMotionPlanRequest(motionRequest);
        moveGroupGoal.withRequest(request);
        return (G) moveGroupGoal;
    }
}

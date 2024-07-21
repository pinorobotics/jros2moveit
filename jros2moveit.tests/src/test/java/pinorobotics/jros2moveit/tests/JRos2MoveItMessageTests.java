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
package pinorobotics.jros2moveit.tests;

import id.jros2messages.Ros2MessageSerializationUtils;
import id.jros2messages.octomap_msgs.OctomapMessage;
import id.jros2messages.octomap_msgs.OctomapWithPoseMessage;
import id.jros2messages.sensor_msgs.JointStateMessage;
import id.jros2messages.sensor_msgs.MultiDOFJointStateMessage;
import id.jros2messages.std_msgs.HeaderMessage;
import id.jrosmessages.geometry_msgs.PointMessage;
import id.jrosmessages.geometry_msgs.PoseMessage;
import id.jrosmessages.geometry_msgs.QuaternionMessage;
import id.jrosmessages.std_msgs.Int32Message;
import id.jrosmessages.std_msgs.StringMessage;
import id.jrosmessages.tests.MessageTests;
import java.util.stream.Stream;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pinorobotics.jros2moveit.moveit_msgs.PlanningOptionsMessage;
import pinorobotics.jros2moveit.moveit_msgs.PlanningSceneMessage;
import pinorobotics.jros2moveit.moveit_msgs.PlanningSceneWorldMessage;
import pinorobotics.jros2moveit.moveit_msgs.RobotStateMessage;

/**
 * @author aeon_flux aeon_flux@eclipso.ch
 */
public class JRos2MoveItMessageTests extends MessageTests {

    public JRos2MoveItMessageTests() {
        super(new Ros2MessageSerializationUtils());
    }

    static Stream<TestCase> dataProvider() {
        return Stream.of(
                /*
                 *
                ros2 topic pub -r 10 helloRos "moveit_msgs/PlanningOptions" '
                planning_scene_diff:
                  name: ''
                  robot_state:
                    joint_state:
                      header:
                        stamp:
                          sec: 0
                        frame_id: 'test'
                      name: []
                      position: []
                      velocity: []
                      effort: []
                    multi_dof_joint_state:
                      header:
                        stamp:
                          sec: 0
                        frame_id: ''
                      joint_names: []
                      transforms: []
                      twist: []
                      wrench: []
                    attached_collision_objects: []
                    is_diff: False
                  robot_model_name: ''
                  fixed_frame_transforms: []
                  allowed_collision_matrix:
                    entry_names: []
                    entry_values: []
                    default_entry_names: []
                    default_entry_values: []
                  link_padding: []
                  link_scale: []
                  object_colors: []
                  world:
                    collision_objects: []
                    octomap:
                      header:
                        stamp:
                          sec: 0
                        frame_id: ''
                      origin:
                        position:
                          x: 1.0
                          y: 2.0
                          z: 3.0
                        orientation:
                          x: 4.0
                          y: 5.0
                          z: 6.0
                          w: 7.0
                      octomap:
                        header:
                          stamp:
                            sec: 0
                          frame_id: ''
                        binary: False
                        id: ''
                        resolution: 0.0
                        data: []
                  is_diff: False
                plan_only: True
                look_around: False
                look_around_attempts: 123
                max_safe_execution_cost: 0.0
                replan: False
                replan_attempts: 2
                replan_delay: 0.0
                '
                *
                */
                new TestCase(
                        "PlanningOptions",
                        new PlanningOptionsMessage()
                                .withPlanningSceneDiff(
                                        new PlanningSceneMessage()
                                                .withName(new StringMessage("None"))
                                                .withRobotState(
                                                        new RobotStateMessage()
                                                                .withMultiDofJointState(
                                                                        new MultiDOFJointStateMessage()
                                                                                .withHeader(
                                                                                        new HeaderMessage()
                                                                                                .withFrameId(
                                                                                                        "None")))
                                                                .withJointState(
                                                                        new JointStateMessage()
                                                                                .withHeader(
                                                                                        new HeaderMessage()
                                                                                                .withFrameId(
                                                                                                        "test"))))
                                                .withRobotModelName(new StringMessage("None"))
                                                .withWorld(
                                                        new PlanningSceneWorldMessage()
                                                                .withOctomap(
                                                                        new OctomapWithPoseMessage()
                                                                                .withHeader(
                                                                                        new HeaderMessage()
                                                                                                .withFrameId(
                                                                                                        "None"))
                                                                                .withOctomap(
                                                                                        new OctomapMessage()
                                                                                                .withHeader(
                                                                                                        new HeaderMessage()
                                                                                                                .withFrameId(
                                                                                                                        "None"))
                                                                                                .withId(
                                                                                                        new StringMessage(
                                                                                                                "None")))
                                                                                .withOrigin(
                                                                                        new PoseMessage()
                                                                                                .withPosition(
                                                                                                        new PointMessage(
                                                                                                                1,
                                                                                                                2,
                                                                                                                3))
                                                                                                .withQuaternion(
                                                                                                        new QuaternionMessage(
                                                                                                                4,
                                                                                                                5,
                                                                                                                6,
                                                                                                                7))))))
                                .withPlanOnly(true)
                                .withReplanAttempts(new Int32Message().withData(2))
                                .withLookAroundAttempts(new Int32Message().withData(123))));
    }

    @Test
    public void test_PlanningOptionsMessage_equals() {
        Assertions.assertTrue(new PlanningOptionsMessage().equals(new PlanningOptionsMessage()));
        Assertions.assertFalse(
                new PlanningOptionsMessage()
                        .equals(new PlanningOptionsMessage().withPlanOnly(true)));
    }
}

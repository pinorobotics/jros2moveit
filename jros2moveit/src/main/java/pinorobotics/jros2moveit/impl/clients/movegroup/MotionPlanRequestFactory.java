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

import id.jros2messages.JRos2MessagesTransformer;
import id.jros2messages.geometry_msgs.PoseStampedMessage;
import id.jros2messages.shape_msgs.SolidPrimitiveMessage;
import id.jrosmessages.geometry_msgs.PoseMessage;
import id.jrosmessages.std_msgs.Int32Message;
import java.util.stream.Stream;
import pinorobotics.jros2moveit.moveit_msgs.ConstraintsMessage;
import pinorobotics.jros2moveit.moveit_msgs.MotionPlanRequestMessage;
import pinorobotics.jros2moveit.moveit_msgs.OrientationConstraintMessage;
import pinorobotics.jros2moveit.moveit_msgs.PositionConstraintMessage;
import pinorobotics.jrosmoveit.impl.JRosMoveItConstants;
import pinorobotics.jrosmoveit.impl.MotionRequest;
import pinorobotics.robotstate.RobotModel;

/**
 * @author aeon_flux aeon_flux@eclipso.ch
 */
public class MotionPlanRequestFactory {

    private JRos2MessagesTransformer transformer = new JRos2MessagesTransformer();
    private MotionPlanRequestMessage request =
            new MotionPlanRequestMessage()
                    .withNumPlanningAttempts(new Int32Message().withData(1))
                    .withMaxVelocityScalingFactor(0.1)
                    .withMaxAccelerationScalingFactor(0.1)
                    .withAllowedPlanningTime(5.0);
    private RobotModel model;

    public MotionPlanRequestFactory(String groupName, RobotModel model) {
        this.model = model;
        request.group_name.data = groupName;
    }

    /** http://docs.ros.org/en/melodic/api/moveit_msgs/html/definePlanningRequest.html */
    public MotionPlanRequestMessage populateMotionPlanRequest(MotionRequest motionRequest) {
        switch (motionRequest.activeTarget()) {
            case POSE:
                {
                    for (var entry : motionRequest.poseTargets().entrySet()) {
                        var goalConstraints = request.goal_constraints;
                        if (goalConstraints.length != 1) {
                            goalConstraints =
                                    Stream.of(new ConstraintsMessage())
                                            .toArray(ConstraintsMessage[]::new);
                            request.goal_constraints = goalConstraints;
                        }
                        populateGoalConstraints(
                                goalConstraints[0],
                                entry.getKey(),
                                transformer.toStampedPose(model.getModelFrame(), entry.getValue()));
                    }
                    break;
                }
            default:
                throw new UnsupportedOperationException();
        }
        return request;
    }

    private void populateGoalConstraints(
            ConstraintsMessage goalConstraints, String linkName, PoseStampedMessage pose) {
        if (goalConstraints.position_constraints.length != 1) {
            goalConstraints.position_constraints =
                    Stream.of(new PositionConstraintMessage())
                            .toArray(PositionConstraintMessage[]::new);
        }
        populatePositionConstraints(goalConstraints.position_constraints[0], linkName, pose);

        if (goalConstraints.orientation_constraints.length != 1) {
            goalConstraints.orientation_constraints =
                    Stream.of(new OrientationConstraintMessage())
                            .toArray(OrientationConstraintMessage[]::new);
        }
        populateOrientationConstraints(goalConstraints.orientation_constraints[0], linkName, pose);
    }

    private void populateOrientationConstraints(
            OrientationConstraintMessage orientationConstraint,
            String linkName,
            PoseStampedMessage pose) {
        orientationConstraint.link_name.withData(linkName);
        orientationConstraint.header = pose.header;
        orientationConstraint.orientation = pose.pose.orientation;
        orientationConstraint.absolute_x_axis_tolerance =
                JRosMoveItConstants.DEFAULT_TOLERANCE_ANGLE_IN_DEG;
        orientationConstraint.absolute_y_axis_tolerance =
                JRosMoveItConstants.DEFAULT_TOLERANCE_ANGLE_IN_DEG;
        orientationConstraint.absolute_z_axis_tolerance =
                JRosMoveItConstants.DEFAULT_TOLERANCE_ANGLE_IN_DEG;
        orientationConstraint.weight = 1.0;
    }

    private void populatePositionConstraints(
            PositionConstraintMessage positionConstraint,
            String linkName,
            PoseStampedMessage pose) {
        positionConstraint.link_name.withData(linkName);

        // reset all to 0 since those values could be changed previously
        positionConstraint.target_point_offset.x = 0;
        positionConstraint.target_point_offset.y = 0;
        positionConstraint.target_point_offset.z = 0;
        var primitives = positionConstraint.constraint_region.primitives;
        if (primitives.length != 1) {
            primitives = new SolidPrimitiveMessage[1];
            positionConstraint.constraint_region.primitives = primitives;
        }

        var sphere = primitives[0];
        if (sphere == null) {
            sphere = new SolidPrimitiveMessage();
            primitives[0] = sphere;
        }
        sphere.withShapeType(SolidPrimitiveMessage.ShapeType.SPHERE);
        int sphereDimsCount = SolidPrimitiveMessage.SphereDimensionType.values().length;
        var sphereDims = sphere.dimensions;
        if (sphereDims.length != sphereDimsCount) {
            sphereDims = new double[sphereDimsCount];
            sphere.dimensions = sphereDims;
        }
        sphereDims[SolidPrimitiveMessage.SphereDimensionType.SPHERE_RADIUS.ordinal()] =
                JRosMoveItConstants.DEFAULT_TOLERANCE_POSE_IN_MM;

        positionConstraint.header = pose.header;
        var primitivePoses = positionConstraint.constraint_region.primitive_poses;
        if (primitivePoses.length != 1) {
            primitivePoses = Stream.of(new PoseMessage()).toArray(PoseMessage[]::new);
            positionConstraint.constraint_region.primitive_poses = primitivePoses;
        }
        primitivePoses[0].position = pose.pose.position;

        // orientation of constraint region does not affect anything, since it is a sphere
        positionConstraint.constraint_region.primitive_poses[0].orientation.x = 0.0;
        positionConstraint.constraint_region.primitive_poses[0].orientation.y = 0.0;
        positionConstraint.constraint_region.primitive_poses[0].orientation.z = 0.0;
        positionConstraint.constraint_region.primitive_poses[0].orientation.w = 1.0;
        positionConstraint.weight = 1.0;
    }
}

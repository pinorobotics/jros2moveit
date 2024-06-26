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
import id.jrosmessages.std_msgs.Int32Message;
import id.jrosmessages.std_msgs.StringMessage;
import id.xfunction.XJson;
import java.util.Arrays;
import java.util.Objects;

/**
 * Definition for moveit_msgs/MotionPlanRequest
 *
 * <p>This service contains the definition for a request to the motion planner and the output it
 * provides
 */
@MessageMetadata(
        name = MotionPlanRequestMessage.NAME,
        fields = {
            "workspace_parameters",
            "start_state",
            "goal_constraints",
            "path_constraints",
            "trajectory_constraints",
            "reference_trajectories",
            "pipeline_id",
            "planner_id",
            "group_name",
            "num_planning_attempts",
            "allowed_planning_time",
            "max_velocity_scaling_factor",
            "max_acceleration_scaling_factor",
            "cartesian_speed_end_effector_link",
            "max_cartesian_speed"
        })
public class MotionPlanRequestMessage implements Message {

    static final String NAME = "moveit_msgs/MotionPlanRequest";

    /** Parameters for the workspace that the planner should work inside */
    public WorkspaceParametersMessage workspace_parameters = new WorkspaceParametersMessage();

    /**
     * Starting state updates. If certain joints should be considered at positions other than the
     * current ones, these positions should be set here
     */
    public RobotStateMessage start_state = new RobotStateMessage();

    /**
     * The possible goal states for the model to plan for. Each element of the array defines a goal
     * region. The goal is achieved if the constraints for a particular region are satisfied
     */
    public ConstraintsMessage[] goal_constraints = new ConstraintsMessage[0];

    /**
     * No state at any point along the path in the produced motion plan will violate these
     * constraints (this applies to all points, not just waypoints)
     */
    public ConstraintsMessage path_constraints = new ConstraintsMessage();

    /** The constraints the resulting trajectory must satisfy */
    public TrajectoryConstraintsMessage trajectory_constraints = new TrajectoryConstraintsMessage();

    /**
     * A set of trajectories that may be used as reference or initial trajectories for (typically
     * optimization-based) planners These trajectories do not override start_state or
     * goal_constraints
     */
    public GenericTrajectoryMessage[] reference_trajectories = new GenericTrajectoryMessage[0];

    /**
     * The name of the planning pipeline to use. If no name is specified, the configured planning
     * pipeline will be used
     */
    public StringMessage pipeline_id = new StringMessage();

    /**
     * The name of the planning algorithm to use. If no name is specified, the default planner of
     * the planning pipeline will be used
     */
    public StringMessage planner_id = new StringMessage();

    /** The name of the group of joints on which this planner is operating */
    public StringMessage group_name = new StringMessage();

    /** The number of times this plan is to be computed. Shortest solution will be reported. */
    public Int32Message num_planning_attempts = new Int32Message();

    /** The maximum amount of time the motion planner is allowed to plan for (in seconds) */
    public double allowed_planning_time;

    /**
     * Scaling factors for optionally reducing the maximum joint velocities and accelerations.
     * Allowed values are in (0,1]. The maximum joint velocity and acceleration specified in the
     * robot model are multiplied by thier respective factors. If either are outside their valid
     * ranges (importantly, this includes being set to 0.0), the factor is set to the default value
     * of 1.0 internally (i.e., maximum joint velocity or maximum joint acceleration).
     */
    public double max_velocity_scaling_factor;

    public double max_acceleration_scaling_factor;

    /**
     * Maximum cartesian speed for the given end effector. If max_cartesian_speed &lt;= 0 the
     * trajectory is not modified. These fields require the following planning request adapter:
     * default_planner_request_adapters/SetMaxCartesianEndEffectorSpeed
     */
    public StringMessage cartesian_speed_end_effector_link = new StringMessage();

    /** m/s */
    public double max_cartesian_speed;

    public MotionPlanRequestMessage withWorkspaceParameters(
            WorkspaceParametersMessage workspace_parameters) {
        this.workspace_parameters = workspace_parameters;
        return this;
    }

    public MotionPlanRequestMessage withStartState(RobotStateMessage start_state) {
        this.start_state = start_state;
        return this;
    }

    public MotionPlanRequestMessage withGoalConstraints(ConstraintsMessage... goal_constraints) {
        this.goal_constraints = goal_constraints;
        return this;
    }

    public MotionPlanRequestMessage withPathConstraints(ConstraintsMessage path_constraints) {
        this.path_constraints = path_constraints;
        return this;
    }

    public MotionPlanRequestMessage withTrajectoryConstraints(
            TrajectoryConstraintsMessage trajectory_constraints) {
        this.trajectory_constraints = trajectory_constraints;
        return this;
    }

    public MotionPlanRequestMessage withReferenceTrajectories(
            GenericTrajectoryMessage... reference_trajectories) {
        this.reference_trajectories = reference_trajectories;
        return this;
    }

    public MotionPlanRequestMessage withPipelineId(StringMessage pipeline_id) {
        this.pipeline_id = pipeline_id;
        return this;
    }

    public MotionPlanRequestMessage withPlannerId(StringMessage planner_id) {
        this.planner_id = planner_id;
        return this;
    }

    public MotionPlanRequestMessage withGroupName(StringMessage group_name) {
        this.group_name = group_name;
        return this;
    }

    public MotionPlanRequestMessage withNumPlanningAttempts(Int32Message num_planning_attempts) {
        this.num_planning_attempts = num_planning_attempts;
        return this;
    }

    public MotionPlanRequestMessage withAllowedPlanningTime(double allowed_planning_time) {
        this.allowed_planning_time = allowed_planning_time;
        return this;
    }

    public MotionPlanRequestMessage withMaxVelocityScalingFactor(
            double max_velocity_scaling_factor) {
        this.max_velocity_scaling_factor = max_velocity_scaling_factor;
        return this;
    }

    public MotionPlanRequestMessage withMaxAccelerationScalingFactor(
            double max_acceleration_scaling_factor) {
        this.max_acceleration_scaling_factor = max_acceleration_scaling_factor;
        return this;
    }

    public MotionPlanRequestMessage withCartesianSpeedEndEffectorLink(
            StringMessage cartesian_speed_end_effector_link) {
        this.cartesian_speed_end_effector_link = cartesian_speed_end_effector_link;
        return this;
    }

    public MotionPlanRequestMessage withMaxCartesianSpeed(double max_cartesian_speed) {
        this.max_cartesian_speed = max_cartesian_speed;
        return this;
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                workspace_parameters,
                start_state,
                Arrays.hashCode(goal_constraints),
                path_constraints,
                trajectory_constraints,
                Arrays.hashCode(reference_trajectories),
                pipeline_id,
                planner_id,
                group_name,
                num_planning_attempts,
                allowed_planning_time,
                max_velocity_scaling_factor,
                max_acceleration_scaling_factor,
                cartesian_speed_end_effector_link,
                max_cartesian_speed);
    }

    @Override
    public boolean equals(Object obj) {
        var other = (MotionPlanRequestMessage) obj;
        return Objects.equals(workspace_parameters, other.workspace_parameters)
                && Objects.equals(start_state, other.start_state)
                && Arrays.equals(goal_constraints, other.goal_constraints)
                && Objects.equals(path_constraints, other.path_constraints)
                && Objects.equals(trajectory_constraints, other.trajectory_constraints)
                && Arrays.equals(reference_trajectories, other.reference_trajectories)
                && Objects.equals(pipeline_id, other.pipeline_id)
                && Objects.equals(planner_id, other.planner_id)
                && Objects.equals(group_name, other.group_name)
                && Objects.equals(num_planning_attempts, other.num_planning_attempts)
                && allowed_planning_time == other.allowed_planning_time
                && max_velocity_scaling_factor == other.max_velocity_scaling_factor
                && max_acceleration_scaling_factor == other.max_acceleration_scaling_factor
                && Objects.equals(
                        cartesian_speed_end_effector_link, other.cartesian_speed_end_effector_link)
                && max_cartesian_speed == other.max_cartesian_speed;
    }

    @Override
    public String toString() {
        return XJson.asString(
                "workspace_parameters", workspace_parameters,
                "start_state", start_state,
                "goal_constraints", goal_constraints,
                "path_constraints", path_constraints,
                "trajectory_constraints", trajectory_constraints,
                "reference_trajectories", reference_trajectories,
                "pipeline_id", pipeline_id,
                "planner_id", planner_id,
                "group_name", group_name,
                "num_planning_attempts", num_planning_attempts,
                "allowed_planning_time", allowed_planning_time,
                "max_velocity_scaling_factor", max_velocity_scaling_factor,
                "max_acceleration_scaling_factor", max_acceleration_scaling_factor,
                "cartesian_speed_end_effector_link", cartesian_speed_end_effector_link,
                "max_cartesian_speed", max_cartesian_speed);
    }
}

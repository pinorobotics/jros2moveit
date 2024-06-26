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

import id.jros2messages.geometry_msgs.TransformStampedMessage;
import id.jrosmessages.Message;
import id.jrosmessages.MessageMetadata;
import id.jrosmessages.std_msgs.StringMessage;
import id.xfunction.XJson;
import java.util.Arrays;
import java.util.Objects;
import pinorobotics.jrosmoveit.moveit_msgs.AllowedCollisionMatrixMessage;
import pinorobotics.jrosmoveit.moveit_msgs.LinkPaddingMessage;
import pinorobotics.jrosmoveit.moveit_msgs.LinkScaleMessage;
import pinorobotics.jrosmoveit.moveit_msgs.ObjectColorMessage;

/** Definition for moveit_msgs/PlanningScene */
@MessageMetadata(
        name = PlanningSceneMessage.NAME,
        fields = {
            "name",
            "robot_state",
            "robot_model_name",
            "fixed_frame_transforms",
            "allowed_collision_matrix",
            "link_padding",
            "link_scale",
            "object_colors",
            "world",
            "is_diff"
        })
public class PlanningSceneMessage implements Message {

    static final String NAME = "moveit_msgs/PlanningScene";

    /** name of planning scene */
    public StringMessage name = new StringMessage();

    /** full robot state */
    public RobotStateMessage robot_state = new RobotStateMessage();

    /** The name of the robot model this scene is for */
    public StringMessage robot_model_name = new StringMessage();

    /** additional frames for duplicating tf (with respect to the planning frame) */
    public TransformStampedMessage[] fixed_frame_transforms = new TransformStampedMessage[0];

    /** full allowed collision matrix */
    public AllowedCollisionMatrixMessage allowed_collision_matrix =
            new AllowedCollisionMatrixMessage();

    /** all link paddings */
    public LinkPaddingMessage[] link_padding = new LinkPaddingMessage[0];

    /** all link scales */
    public LinkScaleMessage[] link_scale = new LinkScaleMessage[0];

    /**
     * Attached objects, collision objects, even the octomap or collision map can have colors
     * associated to them. This array specifies them.
     */
    public ObjectColorMessage[] object_colors = new ObjectColorMessage[0];

    /** the collision map */
    public PlanningSceneWorldMessage world = new PlanningSceneWorldMessage();

    /**
     * Flag indicating whether this scene is to be interpreted as a diff with respect to some other
     * scene
     */
    public boolean is_diff;

    public PlanningSceneMessage withName(StringMessage name) {
        this.name = name;
        return this;
    }

    public PlanningSceneMessage withRobotState(RobotStateMessage robot_state) {
        this.robot_state = robot_state;
        return this;
    }

    public PlanningSceneMessage withRobotModelName(StringMessage robot_model_name) {
        this.robot_model_name = robot_model_name;
        return this;
    }

    public PlanningSceneMessage withFixedFrameTransforms(
            TransformStampedMessage... fixed_frame_transforms) {
        this.fixed_frame_transforms = fixed_frame_transforms;
        return this;
    }

    public PlanningSceneMessage withAllowedCollisionMatrix(
            AllowedCollisionMatrixMessage allowed_collision_matrix) {
        this.allowed_collision_matrix = allowed_collision_matrix;
        return this;
    }

    public PlanningSceneMessage withLinkPadding(LinkPaddingMessage... link_padding) {
        this.link_padding = link_padding;
        return this;
    }

    public PlanningSceneMessage withLinkScale(LinkScaleMessage... link_scale) {
        this.link_scale = link_scale;
        return this;
    }

    public PlanningSceneMessage withObjectColors(ObjectColorMessage... object_colors) {
        this.object_colors = object_colors;
        return this;
    }

    public PlanningSceneMessage withWorld(PlanningSceneWorldMessage world) {
        this.world = world;
        return this;
    }

    public PlanningSceneMessage withIsDiff(boolean is_diff) {
        this.is_diff = is_diff;
        return this;
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                name,
                robot_state,
                robot_model_name,
                Arrays.hashCode(fixed_frame_transforms),
                allowed_collision_matrix,
                Arrays.hashCode(link_padding),
                Arrays.hashCode(link_scale),
                Arrays.hashCode(object_colors),
                world,
                is_diff);
    }

    @Override
    public boolean equals(Object obj) {
        var other = (PlanningSceneMessage) obj;
        return Objects.equals(name, other.name)
                && Objects.equals(robot_state, other.robot_state)
                && Objects.equals(robot_model_name, other.robot_model_name)
                && Arrays.equals(fixed_frame_transforms, other.fixed_frame_transforms)
                && Objects.equals(allowed_collision_matrix, other.allowed_collision_matrix)
                && Arrays.equals(link_padding, other.link_padding)
                && Arrays.equals(link_scale, other.link_scale)
                && Arrays.equals(object_colors, other.object_colors)
                && Objects.equals(world, other.world)
                && is_diff == other.is_diff;
    }

    @Override
    public String toString() {
        return XJson.asString(
                "name", name,
                "robot_state", robot_state,
                "robot_model_name", robot_model_name,
                "fixed_frame_transforms", fixed_frame_transforms,
                "allowed_collision_matrix", allowed_collision_matrix,
                "link_padding", link_padding,
                "link_scale", link_scale,
                "object_colors", object_colors,
                "world", world,
                "is_diff", is_diff);
    }
}

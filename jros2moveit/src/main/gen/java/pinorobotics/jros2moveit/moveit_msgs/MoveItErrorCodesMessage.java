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
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/** Definition for moveit_msgs/MoveItErrorCodes */
@MessageMetadata(
        name = MoveItErrorCodesMessage.NAME,
        fields = {"val", "message", "source"})
public class MoveItErrorCodesMessage implements Message {

    static final String NAME = "moveit_msgs/MoveItErrorCodes";

    public enum CodeType {
        /** Since Jazzy */
        UNDEFINED(0),

        /** Overall behavior */
        SUCCESS(1),

        FAILURE(99999),

        PLANNING_FAILED(-1),
        INVALID_MOTION_PLAN(-2),
        MOTION_PLAN_INVALIDATED_BY_ENVIRONMENT_CHANGE(-3),
        CONTROL_FAILED(-4),
        UNABLE_TO_AQUIRE_SENSOR_DATA(-5),
        TIMED_OUT(-6),
        PREEMPTED(-7),

        /** Planning and kinematics request errors */
        START_STATE_IN_COLLISION(-10),
        START_STATE_VIOLATES_PATH_CONSTRAINTS(-11),
        START_STATE_INVALID(-26),

        GOAL_IN_COLLISION(-12),
        GOAL_VIOLATES_PATH_CONSTRAINTS(-13),
        GOAL_CONSTRAINTS_VIOLATED(-14),
        GOAL_STATE_INVALID(-27),
        UNRECOGNIZED_GOAL_TYPE(-28),

        INVALID_GROUP_NAME(-15),
        INVALID_GOAL_CONSTRAINTS(-16),
        INVALID_ROBOT_STATE(-17),
        INVALID_LINK_NAME(-18),
        INVALID_OBJECT_NAME(-19),

        /** System errors */
        FRAME_TRANSFORM_FAILURE(-21),
        COLLISION_CHECKING_UNAVAILABLE(-22),
        ROBOT_STATE_STALE(-23),
        SENSOR_INFO_STALE(-24),
        COMMUNICATION_FAILURE(-25),
        CRASH(-29),
        ABORT(-30),

        /** Kinematics errors */
        NO_IK_SOLUTION(-31);

        private int code;

        private CodeType(int code) {
            this.code = code;
        }

        public int getCode() {
            return code;
        }
    }

    private static final Map<Integer, CodeType> CODE_TYPE_MAP =
            Arrays.stream(CodeType.values()).collect(Collectors.toMap(CodeType::getCode, c -> c));

    public Int32Message val = new Int32Message();

    /** A message to provide additional information. */
    public StringMessage message = new StringMessage();

    /** Name of the component that created the status. This is helpful to locate error source. */
    public StringMessage source = new StringMessage();

    public MoveItErrorCodesMessage withVal(int val) {
        this.val.data = val;
        return this;
    }

    public MoveItErrorCodesMessage withMessage(StringMessage message) {
        this.message = message;
        return this;
    }

    public MoveItErrorCodesMessage withSource(StringMessage source) {
        this.source = source;
        return this;
    }

    public boolean isOk() {
        return CODE_TYPE_MAP.get(val.data) == CodeType.SUCCESS;
    }

    public CodeType getCodeType() {
        return CODE_TYPE_MAP.get(val.data);
    }

    @Override
    public int hashCode() {
        return Objects.hash(val, message, source);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof MoveItErrorCodesMessage other)
            return Objects.equals(val, other.val)
                    && Objects.equals(message, other.message)
                    && Objects.equals(source, other.source);
        return false;
    }

    @Override
    public String toString() {
        return XJson.asString(
                "val", val,
                "message", message,
                "source", source);
    }
}

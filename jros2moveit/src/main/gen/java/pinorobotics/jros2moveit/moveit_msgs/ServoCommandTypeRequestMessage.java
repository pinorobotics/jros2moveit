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

import id.jroscommon.RosRelease;
import id.jrosmessages.Message;
import id.jrosmessages.MessageMetadata;
import id.jrosmessages.RosInterfaceType;
import id.jrosmessages.SinceRosRelease;
import id.xfunction.XJson;
import java.util.Objects;

/** Definition for moveit_msgs/ServoCommandTypeServiceRequest */
@MessageMetadata(
        name = ServoCommandTypeRequestMessage.NAME,
        interfaceType = RosInterfaceType.SERVICE)
@SinceRosRelease(release = RosRelease.ROS2_JAZZY)
public class ServoCommandTypeRequestMessage implements Message {

    static final String NAME = "moveit_msgs/ServoCommandTypeServiceRequest";

    public enum CommandType {
        /** Request constants */
        JOINT_JOG,

        TWIST,

        POSE,
    }

    /** Request Field */
    public byte command_type;

    public ServoCommandTypeRequestMessage withCommandType(CommandType command_type) {
        this.command_type = (byte) command_type.ordinal();
        return this;
    }

    @Override
    public int hashCode() {
        return Objects.hash(command_type);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof ServoCommandTypeRequestMessage other)
            return command_type == other.command_type;
        return false;
    }

    @Override
    public String toString() {
        return XJson.asString("command_type", command_type);
    }
}

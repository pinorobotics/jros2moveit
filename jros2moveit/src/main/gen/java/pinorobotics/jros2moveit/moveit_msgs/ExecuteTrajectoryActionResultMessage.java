/*
 * Copyright 2024 jrosmoveit project
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

import id.jrosmessages.MessageMetadata;
import id.jrosmessages.RosInterfaceType;
import id.xfunction.XJson;
import java.util.Objects;
import pinorobotics.jros2actionlib.actionlib_msgs.Action2ResultMessage;
import pinorobotics.jros2actionlib.actionlib_msgs.StatusType;

/** Definition for moveit_msgs/ExecuteTrajectory */
@MessageMetadata(
        name = ExecuteTrajectoryActionResultMessage.NAME,
        fields = {"status", "result"},
        interfaceType = RosInterfaceType.ACTION)
public class ExecuteTrajectoryActionResultMessage
        implements Action2ResultMessage<ExecuteTrajectoryResultMessage> {

    static final String NAME = "moveit_msgs/ExecuteTrajectoryActionResult";

    public byte status;

    public ExecuteTrajectoryResultMessage result = new ExecuteTrajectoryResultMessage();

    public ExecuteTrajectoryActionResultMessage withStatus(byte status) {
        this.status = status;
        return this;
    }

    @Override
    public ExecuteTrajectoryActionResultMessage withResult(ExecuteTrajectoryResultMessage result) {
        this.result = result;
        return this;
    }

    @Override
    public int hashCode() {
        return Objects.hash(status, result);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof ExecuteTrajectoryActionResultMessage other)
            return Objects.equals(status, other.status) && Objects.equals(result, other.result);
        return false;
    }

    @Override
    public String toString() {
        return XJson.asString(
                "status", status,
                "result", result);
    }

    @Override
    public StatusType getStatus() {
        return StatusType.values()[status];
    }

    @Override
    public ExecuteTrajectoryResultMessage getResult() {
        return result;
    }

    @Override
    public Action2ResultMessage<ExecuteTrajectoryResultMessage> withStatus(StatusType status) {
        this.status = (byte) status.ordinal();
        return this;
    }
}

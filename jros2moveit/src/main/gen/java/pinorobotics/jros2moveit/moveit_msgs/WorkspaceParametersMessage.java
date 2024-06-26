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

import id.jros2messages.std_msgs.HeaderMessage;
import id.jrosmessages.Message;
import id.jrosmessages.MessageMetadata;
import id.jrosmessages.geometry_msgs.Vector3Message;
import id.xfunction.XJson;
import java.util.Objects;

/**
 * Definition for moveit_msgs/WorkspaceParameters
 *
 * <p>This message contains a set of parameters useful in setting up the volume (a box) in which the
 * robot is allowed to move. This is useful only when planning for mobile parts of the robot as
 * well.
 */
@MessageMetadata(
        name = WorkspaceParametersMessage.NAME,
        fields = {"header", "min_corner", "max_corner"})
public class WorkspaceParametersMessage implements Message {

    static final String NAME = "moveit_msgs/WorkspaceParameters";

    /** Define the frame of reference for the box corners */
    public HeaderMessage header = new HeaderMessage();

    /** The minumum corner of the box, with respect to the robot starting pose */
    public Vector3Message min_corner = new Vector3Message();

    /** The maximum corner of the box, with respect to the robot starting pose */
    public Vector3Message max_corner = new Vector3Message();

    public WorkspaceParametersMessage withHeader(HeaderMessage header) {
        this.header = header;
        return this;
    }

    public WorkspaceParametersMessage withMinCorner(Vector3Message min_corner) {
        this.min_corner = min_corner;
        return this;
    }

    public WorkspaceParametersMessage withMaxCorner(Vector3Message max_corner) {
        this.max_corner = max_corner;
        return this;
    }

    @Override
    public int hashCode() {
        return Objects.hash(header, min_corner, max_corner);
    }

    @Override
    public boolean equals(Object obj) {
        var other = (WorkspaceParametersMessage) obj;
        return Objects.equals(header, other.header)
                && Objects.equals(min_corner, other.min_corner)
                && Objects.equals(max_corner, other.max_corner);
    }

    @Override
    public String toString() {
        return XJson.asString(
                "header", header,
                "min_corner", min_corner,
                "max_corner", max_corner);
    }
}

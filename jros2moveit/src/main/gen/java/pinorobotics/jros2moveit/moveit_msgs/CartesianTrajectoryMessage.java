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
import id.jrosmessages.std_msgs.StringMessage;
import id.xfunction.XJson;
import java.util.Arrays;
import java.util.Objects;
import pinorobotics.jrosmoveit.moveit_msgs.CartesianTrajectoryPointMessage;

/** Definition for moveit_msgs/CartesianTrajectory */
@MessageMetadata(
        name = CartesianTrajectoryMessage.NAME,
        fields = {"header", "tracked_frame", "points"})
public class CartesianTrajectoryMessage implements Message {

    static final String NAME = "moveit_msgs/CartesianTrajectory";

    /** This message describes the trajectory of a tracked frame in task-space */
    public HeaderMessage header = new HeaderMessage();

    /**
     * The name of the Cartesian frame being tracked with respect to the base frame provided in
     * header.frame_id
     */
    public StringMessage tracked_frame = new StringMessage();

    public CartesianTrajectoryPointMessage[] points = new CartesianTrajectoryPointMessage[0];

    public CartesianTrajectoryMessage withHeader(HeaderMessage header) {
        this.header = header;
        return this;
    }

    public CartesianTrajectoryMessage withTrackedFrame(StringMessage tracked_frame) {
        this.tracked_frame = tracked_frame;
        return this;
    }

    public CartesianTrajectoryMessage withPoints(CartesianTrajectoryPointMessage... points) {
        this.points = points;
        return this;
    }

    @Override
    public int hashCode() {
        return Objects.hash(header, tracked_frame, Arrays.hashCode(points));
    }

    @Override
    public boolean equals(Object obj) {
        var other = (CartesianTrajectoryMessage) obj;
        return Objects.equals(header, other.header)
                && Objects.equals(tracked_frame, other.tracked_frame)
                && Arrays.equals(points, other.points);
    }

    @Override
    public String toString() {
        return XJson.asString(
                "header", header,
                "tracked_frame", tracked_frame,
                "points", points);
    }
}

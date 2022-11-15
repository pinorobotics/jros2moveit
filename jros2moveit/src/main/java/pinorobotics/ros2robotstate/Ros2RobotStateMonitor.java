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
package pinorobotics.ros2robotstate;

import id.jros2messages.sensor_msgs.JointStateMessage;
import id.jrosclient.JRosClient;
import pinorobotics.jros2moveit.entities.JRos2MoveItTransformers;
import pinorobotics.robotstate.RobotStateMonitor;

/**
 * Subscribes to {@link #JOINT_STATES_TOPIC} and keeps last published robot state.
 *
 * @author aeon_flux aeon_flux@eclipso.ch
 */
public class Ros2RobotStateMonitor extends RobotStateMonitor<JointStateMessage> {
    public Ros2RobotStateMonitor(JRosClient client) {
        this(client, new JRos2MoveItTransformers());
    }

    private Ros2RobotStateMonitor(JRosClient client, JRos2MoveItTransformers transformers) {
        super(client, JointStateMessage.class, transformers::toRobotState);
    }
}

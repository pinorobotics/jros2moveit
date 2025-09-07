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
package pinorobotics.jros2moveit;

/**
 * Client which allows to communicate with <a
 * href="https://moveit.picknik.ai/main/doc/examples/realtime_servo/realtime_servo_tutorial.html">MoveIt2
 * Servo</a> (servo_node)
 *
 * @author aeon_flux aeon_flux@eclipso.ch
 */
public interface JRos2MoveItServoClient extends AutoCloseable {

    public enum CommandType {
        JOINT_JOG,
        TWIST,
        POSE,
    }

    /**
     * Send start request to servo_node.
     *
     * <p>Deprecated since Jazzy.
     */
    void startServo();

    /**
     * Send start request to servo_node
     *
     * <p>Available since Jazzy.
     */
    void switchCommandType(CommandType type);

    @Override
    void close();
}

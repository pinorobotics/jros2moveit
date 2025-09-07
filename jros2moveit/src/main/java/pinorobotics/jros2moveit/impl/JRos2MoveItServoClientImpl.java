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
package pinorobotics.jros2moveit.impl;

import id.jros2client.JRos2Client;
import id.jroscommon.RosRelease;
import id.xfunction.Preconditions;
import id.xfunction.logging.XLogger;
import java.util.Optional;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import pinorobotics.jros2moveit.JRos2MoveItServoClient;
import pinorobotics.jros2moveit.moveit_msgs.ServoCommandTypeRequestMessage;
import pinorobotics.jros2moveit.moveit_msgs.ServoCommandTypeResponseMessage;
import pinorobotics.jros2moveit.moveit_msgs.ServoCommandTypeServiceDefinition;
import pinorobotics.jros2services.JRos2ServiceClient;
import pinorobotics.jros2services.JRos2ServicesFactory;
import pinorobotics.jrosservices.std_srvs.TriggerRequestMessage;
import pinorobotics.jrosservices.std_srvs.TriggerServiceDefinition;

/**
 * @author aeon_flux aeon_flux@eclipso.ch
 */
public class JRos2MoveItServoClientImpl implements JRos2MoveItServoClient {
    private static final XLogger LOGGER = XLogger.getLogger(JRos2MoveItServoClientImpl.class);
    private JRos2Client client;
    private RosRelease rosRelease;
    private Optional<
                    JRos2ServiceClient<
                            ServoCommandTypeRequestMessage, ServoCommandTypeResponseMessage>>
            commandTypeServiceClient = Optional.empty();

    public JRos2MoveItServoClientImpl(JRos2Client client, RosRelease rosRelease) {
        this.client = client;
        this.rosRelease = rosRelease;
    }

    @Override
    public void startServo() {
        Preconditions.isLess(
                rosRelease.ordinal(),
                RosRelease.ROS2_JAZZY.ordinal(),
                "startServo is not supported starting from ROS Jazzy");
        try (var service =
                new JRos2ServicesFactory()
                        .createClient(
                                client,
                                new TriggerServiceDefinition(),
                                "/servo_node/start_servo")) {
            var response =
                    service.sendRequestAsync(new TriggerRequestMessage()).get(1, TimeUnit.MINUTES);
            LOGGER.fine("Servo node response: {0}", response);
            Preconditions.isTrue(
                    response.success, "Could not start servo node, reponse " + response);
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            throw new RuntimeException("Could not start servo node", e);
        }
    }

    @Override
    public void switchCommandType(CommandType type) {
        Preconditions.isLessOrEqual(
                RosRelease.ROS2_JAZZY.ordinal(),
                rosRelease.ordinal(),
                "switchCommandType is available starting from ROS Jazzy");
        if (commandTypeServiceClient.isEmpty()) {
            commandTypeServiceClient =
                    Optional.of(
                            new JRos2ServicesFactory()
                                    .createClient(
                                            client,
                                            new ServoCommandTypeServiceDefinition(),
                                            "/servo_node/switch_command_type"));
        }
        try {
            var commandType =
                    switch (type) {
                        case JOINT_JOG -> ServoCommandTypeRequestMessage.CommandType.JOINT_JOG;
                        case POSE -> ServoCommandTypeRequestMessage.CommandType.POSE;
                        case TWIST -> ServoCommandTypeRequestMessage.CommandType.TWIST;
                    };
            var response =
                    commandTypeServiceClient
                            .get()
                            .sendRequestAsync(
                                    new ServoCommandTypeRequestMessage()
                                            .withCommandType(commandType))
                            .get(1, TimeUnit.MINUTES);
            LOGGER.fine("Servo node response: {0}", response);
            Preconditions.isTrue(
                    response.success, "Could not switch command type, reponse " + response);
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            throw new RuntimeException("Could not switch command type", e);
        }
    }

    @Override
    public void close() {
        commandTypeServiceClient.ifPresent(JRos2ServiceClient::close);
    }
}

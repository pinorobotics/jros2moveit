/*
 * Copyright 2025 jrosmoveit project
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
package pinorobotics.jros2moveit.moveit_config;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import id.xfunction.logging.XLogger;
import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import pinorobotics.jros2moveit.moveit_config.models.InitialPositions;
import pinorobotics.jros2moveit.moveit_config.models.MoveItConfig;
import pinorobotics.jros2moveit.moveit_config.models.Ros2Controller;

public class MoveItConfigUtils {
    private static final XLogger LOGGER = XLogger.getLogger(MoveItConfigUtils.class);
    private ObjectReader reader =
            new YAMLMapper()
                    .setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE)
                    // important for Java records support
                    .registerModule(new ParameterNamesModule())
                    .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
                    .reader();

    public MoveItConfig read(Path moveItConfigPackage) {
        LOGGER.fine("Reading MoveIt configuration from {0}", moveItConfigPackage);
        try {
            var initialPositions =
                    reader.readValue(
                            moveItConfigPackage.resolve("config/initial_positions.yaml").toFile(),
                            InitialPositions.class);
            Map<String, Ros2Controller> ros2Controllers =
                    reader.forType(
                                    TypeFactory.defaultInstance()
                                            .constructMapLikeType(
                                                    HashMap.class,
                                                    String.class,
                                                    Ros2Controller.class))
                            .readValue(
                                    moveItConfigPackage
                                            .resolve("config/ros2_controllers.yaml")
                                            .toFile());
            var config = new MoveItConfig(initialPositions, ros2Controllers);
            LOGGER.fine("MoveIt configuration: {0}", config);
            return config;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

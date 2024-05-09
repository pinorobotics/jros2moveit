Tests for **jros2moveit** library.

Integration tests rely on [Panda robot](https://github.com/ros-planning/panda_moveit_config) and TF2 Buffer Server.

Install Panda:

``` bash
apt install ros-humble-moveit-resources-panda-moveit-config
apt -y install ros-humble-controller-manager
apt -y install ros-humble-joint-state-broadcaster
apt -y install ros-humble-moveit-planners-chomp
apt -y install ros-humble-joint-trajectory-controller
```

Use following command to run it:

``` bash
ros2 launch moveit_resources_panda_moveit_config demo.launch.py
```

After that you need start TF2 Buffer Server:

``` bash
ros2 run tf2_ros buffer_server
```

In RViz subscribe to "/visualization_marker_array".

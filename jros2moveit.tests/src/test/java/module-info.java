/**
 * @author aeon_flux aeon_flux@eclipso.ch
 */
open module jros2moveit.tests {
    requires jros2moveit;
    requires jrosmoveit.tests;
    requires id.xfunction;
    requires org.junit.jupiter.api;
    requires jros2client;
    requires jros2rviztools;
    requires jros2tf2;

    exports pinorobotics.jros2moveit.tests;
    exports pinorobotics.ros2robotstate.tests;
}

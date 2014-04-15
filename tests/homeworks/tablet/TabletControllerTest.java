package homeworks.tablet;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TabletControllerTest {
    private TabletController controller;

    @Before
    public void setUp() throws Exception {
        controller = new TabletController();
    }

    @Test
    public void testStartTablet() throws Exception {
        controller.startTablet();

        TabletStatus status = controller.getCurrentStatus();

        assertEquals(false, status.isOverloaded);
        assertEquals(1500, status.cpuSpeed);
        assertEquals(2000, status.freeMemory);
        assertEquals(1, status.applications);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testStartAppsWithNegativeQuantity() throws InvalidOperationException {
        controller.startApps(1, -1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testStartAppsWithNegativeType() throws InvalidOperationException {
        controller.startApps(-1, 1);
    }

    @Test(expected = InvalidOperationException.class)
    public void testStartAppsWithWrongCPUSpeed() throws InvalidOperationException {
        controller.startTablet();

        TabletStatus status = controller.getCurrentStatus();
        status.cpuSpeed = 1;
        controller.startApps(1,1);
    }

    @Test(expected = InvalidOperationException.class)
    public void testStartAppsWithOverloaded() throws InvalidOperationException {
        controller.startTablet();

        TabletStatus status = controller.getCurrentStatus();
        status.isOverloaded = true;
        controller.startApps(1,1);
    }

    @Test()
    public void testStartAppsWithNormalData() throws InvalidOperationException {
        controller.startTablet();
        controller.startApps(1,1);
        TabletStatus status = controller.getCurrentStatus();
        assertEquals(1555, status.cpuSpeed);
        assertEquals(1950, status.freeMemory);
        assertEquals(false, status.isOverloaded);
    }

    @Test()
    public void testStartAppsWithOverloadedLogic() throws InvalidOperationException {
        controller.startTablet();
        TabletStatus status = controller.getCurrentStatus();
        status.applications = 100;
        controller.startApps(1,100);
        assertEquals(6505, status.cpuSpeed);
        assertEquals(0, status.freeMemory);
        assertEquals(true, status.isOverloaded);
    }

}

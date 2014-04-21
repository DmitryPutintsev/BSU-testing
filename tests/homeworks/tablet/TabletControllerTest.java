package homeworks.tablet;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.*;

import static org.junit.Assert.assertEquals;

public class TabletControllerTest {
    private TabletController controller;
    private IConfigurationReader configurationReader;
    private IConfigurationReader mockConfigurationReader;

    @Before
    public void setUp() throws Exception {
        controller = new TabletController();
        configurationReader = new ConfigurationReader();
        mockConfigurationReader = mock(IConfigurationReader.class);
    }

    @Test
    public void testStartTablet() throws Exception {
        controller.setConfigurationReader(configurationReader);
        controller.startTablet();

        TabletStatus status = controller.getCurrentStatus();

        assertEquals(false, status.isOverloaded);
        assertEquals(1500, status.cpuSpeed);
        assertEquals(2000, status.freeMemory);
        assertEquals(1, status.applications);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testStartAppsWithNegativeQuantity() throws InvalidOperationException {
        controller.setConfigurationReader(configurationReader);
        controller.startApps(1, -1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testStartAppsWithNegativeType() throws InvalidOperationException {
        controller.setConfigurationReader(configurationReader);
        controller.startApps(-1, 1);
    }

    @Test(expected = InvalidOperationException.class)
    public void testStartAppsWithWrongCPUSpeed() throws InvalidOperationException {
        controller.setConfigurationReader(configurationReader);
        controller.startTablet();

        TabletStatus status = controller.getCurrentStatus();
        status.cpuSpeed = 1;
        controller.startApps(1,1);
    }

    @Test(expected = InvalidOperationException.class)
    public void testStartAppsWithOverloaded() throws InvalidOperationException {
        controller.setConfigurationReader(configurationReader);
        controller.startTablet();

        TabletStatus status = controller.getCurrentStatus();
        status.isOverloaded = true;
        controller.startApps(1,1);
    }

    @Test()
    public void testStartAppsWithNormalData() throws InvalidOperationException {
        controller.setConfigurationReader(configurationReader);
        controller.startTablet();
        controller.startApps(1,1);
        TabletStatus status = controller.getCurrentStatus();
        assertEquals(1555, status.cpuSpeed);
        assertEquals(1950, status.freeMemory);
        assertEquals(false, status.isOverloaded);
    }

    @Test()
    public void testStartAppsWithOverloadedLogic() throws InvalidOperationException {
        controller.setConfigurationReader(configurationReader);
        controller.startTablet();
        TabletStatus status = controller.getCurrentStatus();
        status.applications = 100;
        controller.startApps(1,100);
        assertEquals(6505, status.cpuSpeed);
        assertEquals(0, status.freeMemory);
        assertEquals(true, status.isOverloaded);
    }

    @Test()
    public void testWithMock() throws InvalidOperationException {
        TabletStatus status = new TabletStatus();

        status.isOverloaded = false;
        status.cpuSpeed = 1000;
        status.freeMemory = 3000;
        status.applications = 0;

        when(mockConfigurationReader.getStatus()).thenReturn(status);
        when(mockConfigurationReader.updateStatus()).thenReturn(true);

        controller.setConfigurationReader(mockConfigurationReader);

        TabletStatus returnStatus = controller.startApps(1, 1);
        Assert.assertEquals(false, returnStatus.isOverloaded);
        Assert.assertEquals(1055, returnStatus.cpuSpeed);
        Assert.assertEquals(2950, returnStatus.freeMemory);
        Assert.assertEquals(1, returnStatus.applications);
    }

}

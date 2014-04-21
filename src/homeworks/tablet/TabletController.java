package homeworks.tablet;

public class TabletController {
    private IConfigurationReader configurationReader;

    public TabletStatus getCurrentStatus() {
        return configurationReader.getStatus();
    }

    public TabletStatus startTablet() {
        TabletStatus status = getCurrentStatus();

        status.isOverloaded = false;
        status.cpuSpeed = 1500;
        status.freeMemory = 2000;
        status.applications = 1;

        return status;
    }

    public TabletStatus startApps(int type, int quantity) throws InvalidOperationException, IllegalArgumentException {
        TabletStatus status = getCurrentStatus();

        if (quantity <= 0) throw new IllegalArgumentException();
        if (type < 0) throw new IllegalArgumentException();

        if (status.cpuSpeed < 10) throw new InvalidOperationException();
        if (status.isOverloaded) throw new InvalidOperationException();

        if (type > 5) type = 1;

        status.cpuSpeed += quantity * 50 + (type == 1 ? 5 : type == 2 ? 3 : 5);
        if (quantity * 50 * type > status.freeMemory) {
            status.freeMemory = 0;
        } else {
            status.freeMemory -= quantity * 50 * type;
        }

        status.applications += quantity;

        status.isOverloaded = status.cpuSpeed > 3000 || status.freeMemory < 50;

        return status;
    }

    public IConfigurationReader getConfigurationReader() {
        return configurationReader;
    }

    public void setConfigurationReader(IConfigurationReader configurationReader) {
        this.configurationReader = configurationReader;
    }

}
package homeworks.tablet;

public class ConfigurationReader implements IConfigurationReader {

    private TabletStatus status = new TabletStatus();

    @Override
    public TabletStatus getStatus() {
        return status;
    }

    @Override
    public boolean updateStatus() {
        return false;
    }
}

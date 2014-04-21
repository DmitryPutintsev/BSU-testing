package homeworks.tablet;

public interface IConfigurationReader {
    TabletStatus getStatus();
    boolean updateStatus();
}
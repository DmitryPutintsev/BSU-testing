package homeworks.tablet;

public class TabletStatus {
    public boolean isOverloaded;
    public int cpuSpeed;
    public int freeMemory;
    public int applications;

    public boolean isOverloaded() {
        return isOverloaded;
    }

    public void setOverloaded(boolean isOverloaded) {
        this.isOverloaded = isOverloaded;
    }

    public int getCpuSpeed() {
        return cpuSpeed;
    }

    public void setCpuSpeed(int cpuSpeed) {
        this.cpuSpeed = cpuSpeed;
    }

    public int getFreeMemory() {
        return freeMemory;
    }

    public void setFreeMemory(int freeMemory) {
        this.freeMemory = freeMemory;
    }

    public int getApplications() {
        return applications;
    }

    public void setApplications(int applications) {
        this.applications = applications;
    }
}

package web.action;

import java.io.IOException;

public interface DeviceAction {
    void findAllDevice() throws IOException;
    void findDeviceByDeviceClassId() throws IOException;
    String findDeviceById() throws IOException;
    void findDeviceByName() throws  IOException;
    void findDeviceByPrice() throws IOException;
    void findDeviceByFuzzy() throws IOException;
    String updateDeviceById() throws IOException;
    public String addDevice() throws IOException;
    public String deleteDevice() throws IOException;
}

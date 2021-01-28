package web.dao;

import web.model.Deviceclass;

import java.util.List;

public interface DeviceClassDao {
    //获得所有设备分类对象的列表
    List<Deviceclass> findAllDeviceClass();
    //获得指定编号的设备分类对象
    Deviceclass findDeviceClass(int deviceClassId);
    void addDeviceClass(Deviceclass deviceclass);
    //删除设备对象
    void deleteDeviceClass(Deviceclass deviceclass);
}

package com.uc.dei.is.Assignment2.business.manager;

import com.uc.dei.is.Assignment2.business.controller.OperationsController;
import com.uc.dei.is.Assignment2.data.models.Item;

import javax.ejb.*;
import java.util.List;

@Singleton
public class DataBaseEmailingService {

    @Lock(LockType.READ)
    @Schedule(second = "*/60", minute = "*", hour = "*", persistent = false)
    public void atSchedule() throws Exception {
        this.emailService();
    }

    @Lock(LockType.READ)
    public void emailService() throws Exception{
        List<Item> items = OperationsController.getLastThreeItems();
        Email.send("gonclabat@gmail.com", items);
    }
}

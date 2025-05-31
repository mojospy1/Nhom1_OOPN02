/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.quanlyphongtap.controller;

import com.mycompany.quanlyphongtap.action.ManagerGymEmployees;
import com.mycompany.quanlyphongtap.entity.Employees;
import com.mycompany.quanlyphongtap.view.LoginView;
import com.mycompany.quanlyphongtap.view.MainView;
import com.mycompany.quanlyphongtap.view.ManagerView;
import com.mycompany.quanlyphongtap.view.GymMemberView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 *
 * @author PC
 */
public class MainController 
{
    private LoginView loginView;
    private ManagerView managerView;
    private GymMemberView residentView;
    private MainView mainView;
    
    public MainController(MainView view)
    {
        this.mainView = view;
        view.addChooseSpecialPersonListener(new ChooseSpecialPersonListener());
        view.addChooseResidentsListener(new ChooseResidentListener());
    }
    public void showMainView() 
    {
        mainView.setVisible(true);
    }
    class ChooseSpecialPersonListener implements ActionListener 
    {
        public void actionPerformed(ActionEvent e) 
        {
            managerView = new ManagerView();
            SpecialPersonController managerController = new SpecialPersonController(managerView);
            managerController.showManagerView();
            mainView.setVisible(false);
        }
    }
    
    class ChooseResidentListener implements ActionListener 
    {
        public void actionPerformed(ActionEvent e) 
        {
            residentView = new GymMemberView();
            GymMemberController residentController = new GymMemberController(residentView);
            residentController.showView();
            mainView.setVisible(false);
        }
    }
}

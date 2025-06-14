/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.quanlygym.controller;

import com.mycompany.quanlygym.action.ManagerGymEmployees;
import com.mycompany.quanlygym.entity.Employees;
import com.mycompany.quanlygym.view.LoginView;
import com.mycompany.quanlygym.view.MainView;
import com.mycompany.quanlygym.view.ManagerView;
import com.mycompany.quanlygym.view.GymMemberView;
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

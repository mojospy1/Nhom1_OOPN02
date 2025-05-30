/*
 * Controller quản lý thành viên phòng gym
 */
package com.mycompany.quanlydoituongdacbiet.controller;

import com.mycompany.quanlydoituongdacbiet.action.ManagerGymMember;
import com.mycompany.quanlydoituongdacbiet.entity.GymMember;
import com.mycompany.quanlydoituongdacbiet.view.*;

import java.awt.event.*;
import java.text.ParseException;
import java.util.*;
import java.util.logging.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class GymMemberController {
    private final GymMemberView gymMemberView;
    private final ManagerGymMember manager;
    private MainView mainView;

    public GymMemberController(GymMemberView view) {
        this.gymMemberView = view;
        this.manager = new ManagerGymMember();
        
        view.addUndoListener(new UndoListener());
        view.addAddMemberListener(new AddMemberListener());
        view.addListMemberSelectionListener(new ListSelectionHandler());
        view.addEditMemberListener(new EditMemberListener());
        view.addClearListener(new ClearFormListener());
        view.addDeleteMemberListener(new DeleteMemberListener());
        view.addSortMemberListener(new SortListener());
        view.addSearchListener(new SearchDialogLauncher());
        view.addSearchDialogListener(new SearchHandler());
        view.addCancelSearchMemberListener(new CancelSearchHandler());
        view.addCancelDialogListener(new CancelDialogHandler());
    }

    public void showView() {
        List<GymMember> list = manager.getListMember();
        gymMemberView.setVisible(true);
        gymMemberView.showListMember(list);    
        gymMemberView.showCountListMembers(list);
    }

    class UndoListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            mainView = new MainView();
            new MainController(mainView).showMainView();
            gymMemberView.setVisible(false);
        }
    }

    class AddMemberListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            GymMember member = gymMemberView.getMemberInfo();
            if (member != null) {
                try {
                    if (!manager.isIDUnique(member)) {
                        throw new IllegalArgumentException("IDCard đã tồn tại!");
                    }
                    manager.add(member);
                    refreshView();
                    gymMemberView.showMessage("Thêm thành công!");
                } catch (IllegalArgumentException ex) {
                    gymMemberView.showMessage(ex.getMessage());
                }
            }
        }   
    }

    class EditMemberListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            GymMember member = gymMemberView.getMemberInfo();
            if (member != null) {
                try {
                    manager.edit(member);
                    refreshView();
                    gymMemberView.showMessage("Cập nhật thành công!");
                } catch (ParseException ex) {
                    Logger.getLogger(GymMemberController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    class DeleteMemberListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            GymMember member = gymMemberView.getMemberInfo();
            if (member != null) {
                manager.deleteGymMemberById(member);
                refreshView();
                gymMemberView.clearMemberInfo();
                gymMemberView.showMessage("Xóa thành công!");
            }
        }
    }

    class ListSelectionHandler implements ListSelectionListener {
        public void valueChanged(ListSelectionEvent e) {
            try {
                gymMemberView.fillMemberFromSelectedRow(manager.getListMember());
            } catch (ParseException ex) {
                Logger.getLogger(GymMemberController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    class ClearFormListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            gymMemberView.clearMemberInfo();
        }
    }

    class SortListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            int option = gymMemberView.getChooseSelectSort();
            switch (option) {
                case 1 -> manager.sortMemberByID();
                case 2 -> manager.sortMemberByName();
                case 3 -> manager.sortMemberByMemberID();
                default -> {
                    gymMemberView.showMessage("Chưa chọn tiêu chí sắp xếp");
                    return;
                }
            }
            gymMemberView.showListMember(manager.getListMember());
        }
    }

    class SearchDialogLauncher implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            gymMemberView.searchMemberInfo();
        }
    }

    class CancelDialogHandler implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            gymMemberView.cancelDialogSearchMemberInfo();
        }
    }

    class CancelSearchHandler implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            gymMemberView.cancelSearchMember();
            gymMemberView.showListMember(manager.getListMember());
        }
    }

    class SearchHandler implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            int type = gymMemberView.getChooseSelectSearch();
            String keyword = gymMemberView.validateSearch();
            List<GymMember> results = new ArrayList<>();
            switch (type) {
                case 1 -> results = manager.searchMemberID(keyword);
                case 2 -> results = manager.searchMemberName(keyword);
                case 3 -> results = manager.searchMemberYear(keyword);
                case 4 -> results = manager.searchMemberAddress(keyword);
            }
            if (!results.isEmpty()) gymMemberView.showListMember(results);
            else gymMemberView.showMessage("Không tìm thấy kết quả!");
        }
    }

    private void refreshView() {
        List<GymMember> list = manager.getListMember();
        gymMemberView.showListMember(list);
        gymMemberView.showCountListMembers(list);
    }
}

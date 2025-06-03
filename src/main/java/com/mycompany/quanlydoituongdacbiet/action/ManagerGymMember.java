    /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.quanlydoituongdacbiet.action;

import com.mycompany.quanlydoituongdacbiet.entity.GymMemberXML;
import com.mycompany.quanlydoituongdacbiet.entity.GymMember;
import com.mycompany.quanlydoituongdacbiet.utils.FileUtils;
import com.mycompany.quanlydoituongdacbiet.view.GymMemberView;
import java.text.Collator;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import javax.swing.JOptionPane;

/**
 *
 * @author PC
 */
public class ManagerGymMember 
{
    private static final String RESIDENT_FILE_NAME = "Residents.xml";
    private List<GymMember> listMember;
    private GymMemberView residentView;
    
    public ManagerGymMember()
    {
        this.listMember = readListResidents();
        if (listMember == null) {
            listMember = new ArrayList<GymMember>();
        }
    }
    
    public List<GymMember> readListResidents() 
    {
        List<GymMember> list = new ArrayList<GymMember>();
        GymMemberXML residentXML = (GymMemberXML) FileUtils.readXMLFile(RESIDENT_FILE_NAME, GymMemberXML.class);
        if (residentXML != null) 
        {
            list = residentXML.getResidents();
        }
        return list;
    }
    
    public void writeListResidents(List<GymMember> residents) 
    {
        GymMemberXML residentXML = new GymMemberXML();
        residentXML.setResidents(residents);
        FileUtils.writeXMLtoFile(RESIDENT_FILE_NAME, residentXML);
    }
    
    public List<GymMember> searchMemberName(String search){
        List<GymMember>temp = new ArrayList<GymMember>();
        for(GymMember person : listMember){
            if(person.getName().toLowerCase().contains(search.toLowerCase())){
                temp.add(person);
            }
        }
        return temp;
    }
    
    /* Hiển thị listSpecialPersons theo nơi ở */
    public List<GymMember> searchMemberAddress(String search){
        List<GymMember>temp = new ArrayList<GymMember>();
        for(GymMember person : listMember){
            if(person.getAddress().toLowerCase().contains(search.toLowerCase())){
                temp.add(person);
            }
        }
        return temp;
    }
    
public List<GymMember> searchMemberID(String id){
     List<GymMember> temp = new ArrayList<GymMember>();
     for(GymMember member : listMember){
         if(member.getMemberID().equals(id)){
             temp.add(member );
         }
}
     return temp;
}

     /* Hiển thị listSpecialPersons theo năm sinh */
    public List<GymMember> searchMemberYear(String year) {
        List<GymMember> temp = new ArrayList<>();
        SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy");

        for (GymMember person : listMember) {
            // Chuyển đổi ngày sinh thành chuỗi năm
            String personYearStr = yearFormat.format(person.getBirthday());

            // So sánh chuỗi năm với năm tìm kiếm
            if (personYearStr.equals(year)) {
                temp.add(person);
            }
        }

        return temp;
    }
    
    public void add(GymMember resident) 
    {
        int max = 0;
        for (int i=0;i<listMember.size();i++)
        {
            if(listMember.get(i).getId()>max) max=listMember.get(i).getId();
        }
        resident.setId(max+1);
        listMember.add(resident);
        writeListResidents(listMember);
    }
    // .
    public boolean validateAdd(GymMember member) {
        try {
            // Kiểm tra số CMT không trùng
            if (!isIDUnique(member)) {
                throw new IllegalArgumentException("Số IDCard đã tồn tại");
            }

            // Kiểm tra vai trò "Chủ hộ" không trùng số hộ khẩu
            if ("Hội Viên".equals(member.getRole()) && !isHouseholdUnique(member)) {
                throw new IllegalArgumentException("Số ID đã tồn tại cho vai trò 'Hội Viên'");
            }

            return true; // Điều kiện kiểm tra thành công
        } catch (IllegalArgumentException ex) {
            // Bắt ngoại lệ và xử lý thông báo
            showMessage("Lỗi: " + ex.getMessage());
            return false; // Điều kiện kiểm tra không thành công
        }
    }
    
    // Hàm kiểm tra số ID không trùng
    public boolean isIDUnique(GymMember member) {
        String ID =member.getIDCard();
        for (GymMember existingResident : listMember) {
            if (existingResident.getIDCard().equals(ID)) {
                return false; // Trùng số CMT
            }
        }
        return true; // Số CMT không trùng
    }

   // Hàm kiểm tra số hộ khẩu không trùng cho vai trò "Chủ hộ"
    public boolean isHouseholdUnique(GymMember member) {
        String MemberID=member.getMemberID();
        String role = member.getRole();
        for (GymMember existingResident : listMember) {
            if ("Hội Viên".equals(role) && existingResident.getMemberID().equals(MemberID) && existingResident.getRole().equals(role)) {
                return false; // Trùng số hộ khẩu cho vai trò "Chủ hộ"
            }
        }
        return true; // Số hộ khẩu không trùng cho vai trò "Chủ hộ"
    }

    
    public void showMessage(String message) {
        JOptionPane.showMessageDialog(residentView, message);
    }
    
    public void edit(GymMember member) throws ParseException 
    {
        SimpleDateFormat fDate=new SimpleDateFormat("dd/MM/yyyy");
        int size = listMember.size();
        for (int i = 0; i < size; i++) 
        {
            if (listMember.get(i).getId() == member.getId()) 
            {
                listMember.get(i).setMemberID(member.getMemberID());
                listMember.get(i).setName(member.getName());
                listMember.get(i).setRole(member.getRole());
                listMember.get(i).setBirthday(member.getBirthday());
                listMember.get(i).setAddress(member.getAddress());
                listMember.get(i).setSex(member.getSex());
                listMember.get(i).setIDCardType(member.getIDCardType());
                listMember.get(i).setIDCard(member.getIDCard());
                listMember.get(i).setBirthPlace(member.getBirthPlace());
                listMember.get(i).setPhoneNumber(member.getPhoneNumber());

                writeListResidents(listMember);
                break;
            }
        }
    }
    
    public boolean deleteGymMemberById(GymMember member) {
        boolean isFound = false;
        int size = listMember.size();
        for (int i = 0; i < size; i++) {
            if (listMember.get(i).getId() == member.getId()) {
                listMember.remove(i);
                isFound = true;
                break;
            }
        }
        if (isFound) {
            // Cập nhật lại ID của các đối tượng sau
            for (int i = 0; i < listMember.size(); i++) {
                if (listMember.get(i).getId() > member.getId()) {
                    listMember.get(i).setId(listMember.get(i).getId() - 1);
                }
            }
            writeListResidents(listMember);
            return true;
        }
        return false;
    }
    
    public void sortMemberByName() 
    {
        Collections.sort(listMember, new Comparator<GymMember>() {
            public int compare(GymMember p1, GymMember p2) {
                Collator collator = Collator.getInstance(new Locale("vi", "VN"));
                // So sánh tên
                int result = collator.compare(p1.getLastName(), p2.getLastName());
                if (result == 0) {
                    // Nếu tên bằng nhau, so sánh họ lót
                    result = collator.compare(p1.getFirstName(), p2.getFirstName());
                }
                return result;
            }
        });
        //Collections.sort(listSpecialPersons, (p1, p2) -> collator.compare(p1.getLastName(), p2.getLastName()));
    }
    
    public void sortMemberByMemberID() {
        Collections.sort(listMember, new Comparator<GymMember>() {
            public int compare(GymMember person1, GymMember person2) {
                return person1.getMemberID().compareTo(person2.getMemberID());
            }
        });
    }

    
    public void sortMemberByID() 
    {
        Collections.sort(listMember, new Comparator<GymMember>() 
        {
            public int compare(GymMember person1, GymMember person2) 
            {
                if (person1.getId() > person2.getId()) 
                {
                    return 1;
                }
                return -1;
            }
        });
    }
    
    public List<GymMember> getListMember() 
    {
        return this.listMember;
    }
}

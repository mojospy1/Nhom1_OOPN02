/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.quanlygym.action;

import com.mycompany.quanlygym.entity.Employees;
import com.mycompany.quanlygym.entity.EmployeesXML;
import com.mycompany.quanlygym.utils.FileUtils;
import com.mycompany.quanlygym.view.ManagerView;
import java.text.Collator;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Locale;

/**
 *
 * @author PC
 */
public class ManagerGymEmployees 
{
    private static final String SPECIALPERSON_FILE_NAME = "GymEmployees.xml";
    private List<Employees> listSpecialPersons;
    public ManagerGymEmployees() {
        this.listSpecialPersons = readListSpecialPersons();
        if (listSpecialPersons == null) {
            listSpecialPersons = new ArrayList<Employees>();
        }
    }

    /**
     * Lưu các đối tượng SpecialPerson vào file GymEmployees.xml
     * 
     * @param specialPersons
     */
    public void writeListSpecialPersons(List<Employees> specialPersons) 
    {
        EmployeesXML specialPersonXML = new EmployeesXML();
        specialPersonXML.setSpecialPerson(specialPersons);
        FileUtils.writeXMLtoFile(SPECIALPERSON_FILE_NAME, specialPersonXML);
    }

    /**
     * Đọc các đối tượng SpecialPerson từ file GymEmployees.xml
     * 
     * @return list SpecialPerson
     */
    public List<Employees> readListSpecialPersons() 
    {
        List<Employees> list = new ArrayList<Employees>();
        EmployeesXML specialPersonXML = (EmployeesXML) FileUtils.readXMLFile(SPECIALPERSON_FILE_NAME, EmployeesXML.class);
        if (specialPersonXML != null) 
        {
            list = specialPersonXML.getSpecialPerson();
        }
        return list;
    }
    
    /* Hiển thị listSpecialPersons theo tên */
    public List<Employees> searchSpecialPersonName(String search){
        List<Employees>temp = new ArrayList<Employees>();
        for(Employees person : listSpecialPersons){
            if(person.getName().toLowerCase().contains(search.toLowerCase())){
                temp.add(person);
            }
        }
        return temp;
    }
    
    /* Hiển thị listSpecialPersons theo nơi ở */
    public List<Employees> searchSpecialPersonAddress(String search){
        List<Employees>temp = new ArrayList<Employees>();
        for(Employees person : listSpecialPersons){
            if(person.getAddress().toLowerCase().contains(search.toLowerCase())){
                temp.add(person);
            }
        }
        return temp;
    }
     /* Hiển thị listSpecialPersons theo năm sinh */
    public List<Employees> searchSpecialPersonYear(String year) {
        List<Employees> temp = new ArrayList<>();
        SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy");

        for (Employees person : listSpecialPersons) {
            // Chuyển đổi ngày sinh thành chuỗi năm
            String personYearStr = yearFormat.format(person.getBirthday());

            // So sánh chuỗi năm với năm tìm kiếm
            if (personYearStr.equals(year)) {
                temp.add(person);
            }
        }

        return temp;
    }
    
    /**
     * thêm SpecialPerson vào listSpecialPersons và lưu listSpecialPersons vào file
     * 
     * @param SpecialPerson
     */
    public void add(Employees specialPerson) 
    {
        int max = 0;
        for (int i=0;i<listSpecialPersons.size();i++)
        {
            if(listSpecialPersons.get(i).getId()>max) max=listSpecialPersons.get(i).getId();
        }
        specialPerson.setId(max+1);
        listSpecialPersons.add(specialPerson);
        writeListSpecialPersons(listSpecialPersons);
    }

    /**
     * cập nhật SpecialPerson vào listSpecialPersons và lưu listSpecialPersons vào file
     * 
     * @param SpecialPerson
     */
    public void edit(Employees specialPerson) throws ParseException 
    {
        SimpleDateFormat fDate=new SimpleDateFormat("dd/MM/yyyy");
        int size = listSpecialPersons.size();
        for (int i = 0; i < size; i++) 
        {
            if (listSpecialPersons.get(i).getId() == specialPerson.getId()) 
            {
                listSpecialPersons.get(i).setName(specialPerson.getName());
                listSpecialPersons.get(i).setBirthday(specialPerson.getBirthday());
                listSpecialPersons.get(i).setAddress(specialPerson.getAddress());
                listSpecialPersons.get(i).setOpeningDate(specialPerson.getOpeningDate());
                listSpecialPersons.get(i).setType(specialPerson.getType());
                listSpecialPersons.get(i).setImage(specialPerson.getImage());
                writeListSpecialPersons(listSpecialPersons);
                break;
            }
        }
    }

    /**
     * xóa SpecialPerson từ listSpecialPersons và lưu listSpecialPersons vào file
     * 
     * @param SpecialPerson
     */
    
    public void image(Employees specialPerson) 
    {
        
    }
      
    public boolean delete(Employees specialPerson) {
         boolean isFound = false;
        int size = listSpecialPersons.size();
        for (int i = 0; i < size; i++) 
        {
            if (listSpecialPersons.get(i).getId() == specialPerson.getId()) 
            {
                specialPerson = listSpecialPersons.get(i);
                isFound = true;
                break;
            }
        }
        if (isFound) 
        {
            listSpecialPersons.remove(specialPerson);
            writeListSpecialPersons(listSpecialPersons);
            return true;
        }
        return false;
    }

    
    
    /**
     * sắp xếp danh sách SpecialPerson theo name theo tứ tự tăng dần
     */
    
    
    public void sortSpecialPersonByName() 
    {
        Collections.sort(listSpecialPersons, new Comparator<Employees>() {
            public int compare(Employees p1, Employees p2) {
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
    
    public void sortSpecialPersonByID() 
    {
        Collections.sort(listSpecialPersons, new Comparator<Employees>() 
        {
            public int compare(Employees SpecialPerson1, Employees SpecialPerson2) 
            {
                if (SpecialPerson1.getId() > SpecialPerson2.getId()) 
                {
                    return 1;
                }
                return -1;
            }
        });
    }
    
    public void sortSpecialPersonByOpeningDate() 
    {
        Collections.sort(listSpecialPersons, new Comparator<Employees>() 
        {
            public int compare(Employees SpecialPerson1, Employees SpecialPerson2) 
            {
                return SpecialPerson1.getOpeningDate().compareTo(SpecialPerson2.getOpeningDate());
            }
        });
    }
    
    /**
     * sắp xếp danh sách SpecialPerson theo năm sinh theo tứ tự tăng dần
     */
    public void sortSpecialPersonByBirthDay() {
        Collections.sort(listSpecialPersons, new Comparator<Employees>() {
            public int compare(Employees person1, Employees person2) {
                return person1.getBirthday().compareTo(person2.getBirthday());
            }
        });
    }

    public List<Employees> getListSpecialPersons() 
    {
        return listSpecialPersons;
    }

    public void setListSpecialPersons(List<Employees> listSpecialPersons) 
    {
        this.listSpecialPersons = listSpecialPersons;
    }
}

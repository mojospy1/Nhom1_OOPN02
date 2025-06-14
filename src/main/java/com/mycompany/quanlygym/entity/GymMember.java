/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.quanlygym.entity;

import java.text.SimpleDateFormat;
import java.util.Date;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author PC
 */

@XmlRootElement(name = "GymMember")
@XmlAccessorType(XmlAccessType.FIELD)
public class GymMember extends Person
{
   private String MemberID;
    private String sex;
    private String role;
    private String birthPlace;
    private Date birthDay;
    private String IDCardType;
    private String IDCard;
    private String phoneNumber;
    private String goiTap;
    private Date NgayDangKy;
    private Date NgayHetHan;
    private SimpleDateFormat fDate = new SimpleDateFormat("dd/MM/yyyy");
    public GymMember()
    {
        this.IDCard="";
        this.IDCardType="<none>";
        this.phoneNumber="";
    }
    
   public GymMember(String MemberID, Date birthday, String address, String sex, String role, String birthPlace, String IDCardType, String IDCard , String phoneNumber , String goiTap , Date NgayDangKy , Date NgayHetHan )
    {
        super();
        SimpleDateFormat fDate=new SimpleDateFormat("dd/MM/yyyy");
       
        this.MemberID = MemberID;
        this.birthDay = birthday;
        this.address = address;
        this.IDCard = IDCard;
        this.sex = sex;
        this.role = role;
        this.birthPlace = birthPlace;
        this.IDCardType =  IDCardType;
        this.goiTap = goiTap ; 
        this.NgayDangKy = NgayDangKy ; 
        this.NgayHetHan = NgayHetHan ;
        
    }

    public String getMemberID() {
        return MemberID;
    }

    public void setMemberID(String MemberID) {
        this.MemberID = MemberID;
    }
    
  
    public String getSex()
    {
        return this.sex;
    }
    
    public void setSex(String _sex)
    {
        this.sex=_sex;
    }
    
    public String getRole()
    {
        return this.role;
    }
    
    public void setRole(String _role)
    {
        this.role=_role;
    }
    
    public String getBirthPlace()
    {
        return this.birthPlace;
    }
    
    public void setBirthPlace(String _birthPlace)
    {
        this.birthPlace=_birthPlace;
    }
    
    public String getIDCardType()
    {
        return this.IDCardType;
    }
    
    public void setIDCardType(String IDCardType)
    {
        this.IDCardType = IDCardType;
    }
    
    public String getIDCard()
    {
        return this.IDCard;
    }
    
    public void setIDCard(String IDCard)
    {
        this.IDCard=IDCard;
    }
    
    public String getPhoneNumber()
    {
        return this.phoneNumber;
    }
    
    public void setPhoneNumber(String phoneNumber)
    {
        this.phoneNumber = phoneNumber;
    }
    public String getgoitap()
    {
        return this.goiTap;
    }
    
    public void setgoiTap (String goiTap)
    {
        this.goiTap  = goiTap ;
    }
    public Date getNgayDangKy()
    {
        return this.NgayDangKy;
    }
    
    public void setNgayDangKy (Date  NgayDangKy)
    {
        this.NgayDangKy  = NgayDangKy ;
    }
    public Date getNgayHetHan ()
    {
        return this.NgayHetHan ;
    }
    
    public void setNgayHetHan (Date  NgayHetHan )
    {
        this.NgayHetHan   = NgayHetHan  ;
    }
}

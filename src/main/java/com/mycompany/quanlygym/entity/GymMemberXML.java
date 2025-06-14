/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.quanlygym.entity;

import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author PC
 */
@XmlRootElement(name = "Residents")
@XmlAccessorType(XmlAccessType.FIELD)
public class GymMemberXML {
    private List<GymMember> residents;

    public List<GymMember> getResidents() {
        return residents;
    }

    public void setResidents(List<GymMember> residents) {
        this.residents = residents;
    } 
}

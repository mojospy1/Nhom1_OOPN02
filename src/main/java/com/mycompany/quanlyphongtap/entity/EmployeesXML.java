package com.mycompany.quanlyphongtap.entity;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "SpecialPersons")
@XmlAccessorType(XmlAccessType.FIELD)
public class EmployeesXML {
    
    private List<Employees> specialPerson;

    public List<Employees> getSpecialPerson() {
        return specialPerson;
    }

    public void setSpecialPerson(List<Employees> specialPerson) {
        this.specialPerson = specialPerson;
    }
}

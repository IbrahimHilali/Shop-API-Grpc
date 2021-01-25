package de.home.collections;

import de.home.entities.Department;

import java.util.ArrayList;

public class Departments {
    private ArrayList<Department> departmentList;

    public Departments() {
    }

    public ArrayList<Department> getDepartmentList() {
        return departmentList;
    }

    public void setDepartmentList(ArrayList<Department> departmentList) {
        this.departmentList = departmentList;
    }

}

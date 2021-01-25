package de.home.collections;

import java.util.ArrayList;

import de.home.entities.Employee;

public class Employees 
{
    private ArrayList<Employee> employeeList;
 
    public Employees() {
	}

	public ArrayList<Employee> getEmployeeList() {
        return employeeList;
    }
 
    public void setEmployeeList(ArrayList<Employee> employeeList) {
        this.employeeList = employeeList;
    }
}
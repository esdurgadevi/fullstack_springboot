package com.hostel.hostel_management_system.model;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
@Entity
public class StudentSpecialMenu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private Long studentId;
    private Long menuId;  
    private boolean selectedSpecial1;
    private boolean selectedSpecial2;
    private double fee;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getStudentId() {
		return studentId;
	}
	public void setStudentId(Long studentId) {
		this.studentId = studentId;
	}
	public Long getMenuId() {
		return menuId;
	}
	public void setMenuId(Long menuId) {
		this.menuId = menuId;
	}
	
	public boolean isSelectedSpecial1() {
		return selectedSpecial1;
	}
	public void setSelectedSpecial1(boolean selectedSpecial1) {
		this.selectedSpecial1 = selectedSpecial1;
	}
	public boolean isSelectedSpecial2() {
		return selectedSpecial2;
	}
	public void setSelectedSpecial2(boolean selectedSpecial2) {
		this.selectedSpecial2 = selectedSpecial2;
	}
	public double getFee() {
		return fee;
	}
	public void setFee(double fee) {
		this.fee = fee;
	}
}


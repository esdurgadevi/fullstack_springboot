package com.hostel.hostel_management_system.controller;
import com.hostel.hostel_management_system.service.FeeService;
import com.hostel.hostel_management_system.model.FeeDetails;
import com.hostel.hostel_management_system.model.StudentDetails;
import com.hostel.hostel_management_system.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/api/fees")
@CrossOrigin(origins = "http://localhost:5173")
public class FeeController {
    @Autowired
    private FeeService feeService;
    @Autowired
    private StudentDetailsRepository studentRepository;

    @GetMapping("/{studentId}")
    public FeeDetails getFeeDetails(@PathVariable Long studentId) {
        return feeService.calculateFee(studentId); 
    }
    @PostMapping("/assign-all")
    public String assignFeesToAllStudents() {
        List<StudentDetails> allStudents = studentRepository.findAll();
        for (StudentDetails student : allStudents) {
            feeService.calculateFee(student.getId()); 
        }
        return "Fees assigned and updated for all students.";
    }
}

package com.hostel.hostel_management_system.controller;

import com.hostel.hostel_management_system.model.Menu;
import com.hostel.hostel_management_system.model.StudentSpecialMenu;
import com.hostel.hostel_management_system.repository.MenuRepository;
import com.hostel.hostel_management_system.service.StudentSpecialMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/special-menu")
@CrossOrigin(origins = "http://localhost:5173")
public class StudentSpecialMenuController {

    @Autowired
    private StudentSpecialMenuService service;

    @GetMapping("/student/{studentId}")
    public ResponseEntity<?> getSpecialMenuForStudent(@PathVariable Long studentId) {
        List<StudentSpecialMenu> selected = service.getByStudentId(studentId);
        if (selected.isEmpty()) {
            return ResponseEntity.ok("No special menu selected by this student.");
        }
        return ResponseEntity.ok(selected);
    }

    @GetMapping("/all")
    public ResponseEntity<List<StudentSpecialMenu>> getAllSpecialMenuSelected() {
        return ResponseEntity.ok(service.getAllWithSpecialMenuSelected());
    }

    @Autowired
    private MenuRepository menuRepository;

    @PostMapping("/student/select")
    public ResponseEntity<String> selectSpecialMenu(@RequestBody StudentSpecialMenu specialMenu) {
        Menu menu = menuRepository.findById(specialMenu.getMenuId()).orElse(null);

        if (menu == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Menu not found for ID: " + specialMenu.getMenuId());
        }

        double totalFee = 0;

        if (specialMenu.isSelectedSpecial1()) {
            totalFee += menu.getSpecial1Fee();
        }

        if (specialMenu.isSelectedSpecial2()) {
            totalFee += menu.getSpecial2Fee();
        }

        specialMenu.setFee(totalFee);
        StudentSpecialMenu saved = service.saveSelection(specialMenu);

        return ResponseEntity.ok("Special menu selected successfully with fee ₹" + totalFee +
                " for student ID: " + saved.getStudentId());
    }


}

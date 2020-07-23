package com.softserve.edu.controller;

import com.softserve.edu.entity.Entity;
import com.softserve.edu.service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.softserve.edu.service.MarathonService;

import javax.xml.crypto.Data;
import java.util.List;

@Controller
public class MarathonController {

    @Qualifier("marathonServiceImpl")
    private MarathonService marathonService;

    @Qualifier("dataServiceImpl")
    private DataService dataService;

    @Value("${welcome.message}")
    private String message;

    @Autowired
    public MarathonController(MarathonService marathonService, DataService dataService) {
        this.dataService = dataService;
        this.marathonService = marathonService;
    }

    @RequestMapping(value = { "/", "/index" }, method = RequestMethod.GET)
    public String index(Model model) {
        model.addAttribute("message", message);
        return "index";
    }

    @GetMapping("/create/student/{studentName}")
    public ResponseEntity<List<Entity>> createStudent(@PathVariable String studentName) {
        System.out.println("CREATE STUDENT CONTROLLER");
        System.out.println("studentName = " + studentName);
        dataService.addStudent(studentName);
        return ResponseEntity.ok(dataService.getStudents());
    }

}

package com.codeCardS.app.Controller;

import org.springframework.web.bind.annotation.RestController;

import com.codeCardS.app.Service.ProgressCalculationService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import com.codeCardS.app.Service.ProgressCalculationService.Response;

@RestController
public class ProgressCalculationControlle {

    @Autowired
    private ProgressCalculationService progressCalculationService;
    
    @CrossOrigin(origins = "*")
    @GetMapping("/progress/{userId}")
    public List<Response> getMethodName(@PathVariable String userId,@RequestParam String courseId) {
        return progressCalculationService.calculateProgress(userId, courseId);
    }  
    
    @CrossOrigin(origins = "*")
    @GetMapping("/totalprogress/{userId}")
    public List<Response> getMethodName(@PathVariable String userId) {
        return progressCalculationService.calculateAllCoursesProgress(userId);
    }
    
}

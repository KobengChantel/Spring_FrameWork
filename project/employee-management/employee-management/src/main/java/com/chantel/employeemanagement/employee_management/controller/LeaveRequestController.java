package com.chantel.employeemanagement.employee_management.controller;

import com.chantel.employeemanagement.employee_management.entity.LeaveRequest;
import com.chantel.employeemanagement.employee_management.service.LeaveRequestService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/leaverequests")
public class LeaveRequestController {

    private final LeaveRequestService leaveRequestService;

    public LeaveRequestController(LeaveRequestService leaveRequestService) {
        this.leaveRequestService = leaveRequestService;
    }

    // Create a new leave request
    @PostMapping("/{employeeId}")
    public ResponseEntity<LeaveRequest> createLeaveRequest(@PathVariable Long employeeId,
                                                           @RequestBody LeaveRequest leaveRequest) {
        LeaveRequest createdRequest = leaveRequestService.createLeaveRequest(employeeId, leaveRequest);
        return new ResponseEntity<>(createdRequest, HttpStatus.CREATED);
    }

    // Get all leave requests for an employee
    @GetMapping("/{employeeId}")
    public ResponseEntity<List<LeaveRequest>> getLeaveRequestsByEmployee(@PathVariable Long employeeId) {
        List<LeaveRequest> leaveRequests = leaveRequestService.getLeaveRequestsByEmployee(employeeId);
        return new ResponseEntity<>(leaveRequests, HttpStatus.OK);
    }

    // Update the status of a leave request
    @PutMapping("/{requestId}/status")
    public ResponseEntity<LeaveRequest> updateLeaveRequestStatus(@PathVariable Long requestId,
                                                                 @RequestParam LeaveRequest.LeaveStatus status) {
        LeaveRequest updatedRequest = leaveRequestService.updateLeaveRequestStatus(requestId, status);
        return new ResponseEntity<>(updatedRequest, HttpStatus.OK);
    }
}

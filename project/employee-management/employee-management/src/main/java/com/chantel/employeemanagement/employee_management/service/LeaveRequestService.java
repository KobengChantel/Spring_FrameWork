package com.chantel.employeemanagement.employee_management.service;


import com.chantel.employeemanagement.employee_management.entity.LeaveRequest;

import java.util.List;

public interface LeaveRequestService {
    LeaveRequest createLeaveRequest(Long employeeId, LeaveRequest leaveRequest);
    List<LeaveRequest> getLeaveRequestsByEmployee(Long employeeId);
    LeaveRequest updateLeaveRequestStatus(Long requestId, LeaveRequest.LeaveStatus status);
}

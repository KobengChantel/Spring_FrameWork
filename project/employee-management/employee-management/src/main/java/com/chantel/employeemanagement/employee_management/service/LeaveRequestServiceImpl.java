package com.chantel.employeemanagement.employee_management.service;

import com.chantel.employeemanagement.employee_management.entity.Employee;
import com.chantel.employeemanagement.employee_management.entity.LeaveRequest;
import com.chantel.employeemanagement.employee_management.repository.EmployeeRepository;
import com.chantel.employeemanagement.employee_management.repository.LeaveRequestRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LeaveRequestServiceImpl implements LeaveRequestService {

    private final LeaveRequestRepository leaveRequestRepository;
    private final EmployeeRepository employeeRepository;

    public LeaveRequestServiceImpl(LeaveRequestRepository leaveRequestRepository, EmployeeRepository employeeRepository) {
        this.leaveRequestRepository = leaveRequestRepository;
        this.employeeRepository = employeeRepository;
    }

    @Override
    public LeaveRequest createLeaveRequest(Long employeeId, LeaveRequest leaveRequest) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new RuntimeException("Employee not found with id: " + employeeId));
        leaveRequest.setEmployee(employee);
        return leaveRequestRepository.save(leaveRequest);
    }

    @Override
    public List<LeaveRequest> getLeaveRequestsByEmployee(Long employeeId) {
        return leaveRequestRepository.findByEmployeeId(employeeId);
    }

    @Override
    public LeaveRequest updateLeaveRequestStatus(Long requestId, LeaveRequest.LeaveStatus status) {
        LeaveRequest leaveRequest = leaveRequestRepository.findById(requestId)
                .orElseThrow(() -> new RuntimeException("Leave request not found with id: " + requestId));
        leaveRequest.setStatus(status);
        return leaveRequestRepository.save(leaveRequest);
    }
}

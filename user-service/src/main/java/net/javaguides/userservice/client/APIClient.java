package net.javaguides.userservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import net.javaguides.userservice.dto.DepartmentDto;

@FeignClient(value = "department-service", url = "http://localhost:8001")
public interface APIClient {

	@GetMapping(value = "/api/departments/{id}")
    DepartmentDto getDepartmentById(@PathVariable("id") Long departmentId);
}
//http://localhost:8001/api/departments/1
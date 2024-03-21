package net.javaguides.userservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import net.javaguides.userservice.client.APIClient;
import net.javaguides.userservice.dto.DepartmentDto;
import net.javaguides.userservice.dto.ResponseDto;
import net.javaguides.userservice.dto.UserDto;
import net.javaguides.userservice.entity.User;
import net.javaguides.userservice.repository.UserRepository;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	/*
	 * @Autowired private WebClient webClient;
	 */
	@Autowired
	private APIClient apiClient;
	
	
    
    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }
    
    public ResponseDto getUser(Long userId) {
    	ResponseDto responseDto = new ResponseDto();
    	try {
        
        User user = userRepository.findById(userId).get();
        UserDto userDto = mapToUser(user);

        DepartmentDto departmentDto = apiClient.getDepartmentById(user.getDepartmentId());
        responseDto.setUser(userDto);
        responseDto.setDepartment(departmentDto);
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
        return responseDto;
    }

	/*
	 * @Override public ResponseDto getUser(Long userId) {
	 * 
	 * ResponseDto responseDto = new ResponseDto(); User user =
	 * userRepository.findById(userId).get(); UserDto userDto = mapToUser(user);
	 * 
	 * DepartmentDto departmentDto = webClient
	 * .get().uri("http://localhost:8081/api/departments/" +
	 * user.getDepartmentId()).retrieve() .bodyToMono(DepartmentDto.class).block();
	 * 
	 * 
	 * 
	 * responseDto.setUser(userDto); responseDto.setDepartment(departmentDto);
	 * 
	 * return responseDto; }
	 */

    private UserDto mapToUser(User user){
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setEmail(user.getEmail());
        return userDto;
    }
}

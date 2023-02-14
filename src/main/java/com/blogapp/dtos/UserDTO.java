package com.blogapp.dtos;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserDTO {

	private Integer id;
	
	@NotNull
	@NotEmpty
	@Size(min =4, message = "Name must conatain at least 3 charcaters")
	private String name;
	
	@Email(message = "Email Address is not valid")
	private String email;
	
	@NotNull
	@NotEmpty
	private String password;
	
	@NotEmpty
	private String about;

}

package com.kd.repository;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import com.kd.entiry.User;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class UserRepositoryTest {

	@Autowired
	private UserRepository repo;
	@Autowired
	private TestEntityManager entityManager;
	
	@Test
	public void testCreateUser() {
		User user = new User();
		user.setEmail("kd@gmail.com");
		user.setFirstName("Kr");
		user.setLastName("kd");
		user.setPassword("kd123");
		User userTest = repo.save(user);
		
		User userexist = entityManager.find(User.class, userTest.getId());
		assertThat(userexist.getEmail()).isEqualTo(user.getEmail());
	}
	
	@Test
	public void testFindByEmail() {
		String email = "kd@gmail.com";
		User user = repo.findByEmail(email);
		assertThat(user).isNotNull();
	}
}

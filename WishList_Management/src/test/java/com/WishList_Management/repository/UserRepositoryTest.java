package com.WishList_Management.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.WishList_Management.models.User;
import com.WishList_Management.models.WishListItem;



@DataJpaTest
@ExtendWith(MockitoExtension.class)
public class UserRepositoryTest {

    @Mock
    private UserRepository userRepository;
    private User user;
    WishListItem item1;
    WishListItem item2;
    @BeforeEach
    public void setUp() {
        String username = "raj";
        user = new User();
        user.setId(1L); // Set the ID
        user.setUsername(username);

        // Create some sample wish list items
        item1 = new WishListItem();
        item1.setItemname("item1");
        item1.setPrice(2444);
        item2 = new WishListItem();
        item2.setItemname("item2");
        item2.setPrice(3444);

       
      
     
       
    }
    @Test
    public void testFindByUsername() {
        // Prepare test data
        String username = "raj";
      

        // Stub repository method
        when(userRepository.findByUsername(username)).thenReturn(Optional.of(user));

        // Call repository method
        Optional<User> retrievedUserOptional = userRepository.findByUsername(username);

        // Verify the result
        assertEquals(user, retrievedUserOptional.orElse(null));
    }

    @Test
    public void testFindAllUsers() {
        // Prepare test data
        List<User> users = Arrays.asList(user);
        // Stub repository method
        when(userRepository.findAll()).thenReturn(users);

        // Call repository method
        List<User> retrievedUsers = userRepository.findAll();

        // Verify the result
        assertEquals(users.size(), retrievedUsers.size());
        assertEquals(users, retrievedUsers);
    }

   

}

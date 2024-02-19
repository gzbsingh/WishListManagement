package com.WishList_Management.repository;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
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
public class WishListRepositoryTest {

    @Mock
    private WishListItemRepository wishListItemRepository;
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
    public void testFindByUserId() {
        // Prepare test data
        long userId = 1L;
      
       
        List<WishListItem> itemList = new ArrayList<>();
        itemList.add(item1);
        itemList.add(item2);

        // Stub repository method
        when(wishListItemRepository.findByUserId(userId)).thenReturn(itemList);

        // Call repository method
        List<WishListItem> retrievedItems = wishListItemRepository.findByUserId(userId);

        // Verify the result
        assertEquals(itemList.size(), retrievedItems.size());
        assertEquals(itemList.get(0).getItemname(), retrievedItems.get(0).getItemname());
        assertEquals(itemList.get(1).getItemname(), retrievedItems.get(1).getItemname());
    }

    @Test
    public void testFindByUser() {
        // Prepare test data
        long userId = 1L;
       
        List<WishListItem> itemList = new ArrayList<>();
        itemList.add(item1);
        itemList.add(item2);

        // Stub repository method
        when(wishListItemRepository.findByUser(user)).thenReturn(itemList);

        // Call repository method
        List<WishListItem> retrievedItems = wishListItemRepository.findByUser(user);

        // Verify the result
        assertEquals(itemList.size(), retrievedItems.size());
        assertEquals(itemList.get(0).getItemname(), retrievedItems.get(0).getItemname());
        assertEquals(itemList.get(1).getItemname(), retrievedItems.get(1).getItemname());
    }



}

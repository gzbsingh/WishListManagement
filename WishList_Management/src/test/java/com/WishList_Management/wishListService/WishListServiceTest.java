package com.WishList_Management.wishListService;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
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

import com.WishList_Management.exception.WishListItemNotFoundException;
import com.WishList_Management.models.User;
import com.WishList_Management.models.WishListItem;
import com.WishList_Management.repository.UserRepository;
import com.WishList_Management.repository.WishListItemRepository;
import com.WishList_Management.service.WishListItemService;



@ExtendWith(MockitoExtension.class)
public class WishListServiceTest {
    @Mock
    private WishListItemRepository wishListItemRepository;
    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private WishListItemService wishListItemService;
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

        // Set the items for the user
        user.setItems(Arrays.asList(item1, item2));

        // Stub behavior of userRepository
        lenient().when(userRepository.findByUsername(username)).thenReturn(Optional.of(user));
    }

    @Test
    public void testGetUserWishList() {
        // Stub behavior of wishListItemRepository
        when(wishListItemRepository.findByUser(user)).thenReturn(user.getItems());

        // Call the service method
        List<WishListItem> retrievedItems = wishListItemService.getUserWishList(user.getUsername());

        // Perform assertions
        assertNotNull(retrievedItems);
        assertEquals(user.getItems().size(), retrievedItems.size());
        for (int i = 0; i < user.getItems().size(); i++) {
            assertEquals(user.getItems().get(i).getItemname(), retrievedItems.get(i).getItemname());
            assertEquals(user.getItems().get(i).getPrice(), retrievedItems.get(i).getPrice());
        }

        // Verify interactions
        verify(userRepository, times(1)).findByUsername(user.getUsername());
        verify(wishListItemRepository, times(1)).findByUser(user);
    }
    
    @Test
    public void testCreateWishListItem() {
       
        when(wishListItemRepository.save(any(WishListItem.class))).thenReturn(item1);
        String username = "raj";
       User user = new User();
        user.setUsername(username);
      
 
        WishListItem savedItem = wishListItemService.CreateWishListItem(user.getUsername(), item1);
        
        assertNotNull(savedItem);
        assertEquals(item1.getItemname(), savedItem.getItemname());
        assertEquals(item1.getPrice(), savedItem.getPrice());
        
        verify(userRepository, times(1)).findByUsername(user.getUsername());
        verify(wishListItemRepository, times(1)).save(any(WishListItem.class));
    }
    
    @Test
    public void testDeleteWishListItem() {
        // Setup
        long itemIdToDelete = 123; // Assuming this ID exists in the database
      
        item1.setId(itemIdToDelete);

        // Stub behavior of wishListItemRepository.findById() to return the item to delete
        when(wishListItemRepository.findById(itemIdToDelete)).thenReturn(Optional.of(item1));

        // Verify that the wish list item is successfully deleted
        assertDoesNotThrow(() -> {
            wishListItemService.deleteWishListItem(itemIdToDelete);
        });

        // Verify that wishListItemRepository.findById() is called once with the specified ID
        verify(wishListItemRepository, times(1)).findById(itemIdToDelete);

        // Verify that wishListItemRepository.delete() is called once with the item to delete
        verify(wishListItemRepository, times(1)).delete(item1);

        // Test scenario where the wish list item is not found
        long nonExistentItemId = 999; // Assuming this ID does not exist in the database
        when(wishListItemRepository.findById(nonExistentItemId)).thenReturn(Optional.empty());

        // Verify that attempting to delete a non-existent wish list item throws a WishListItemNotFoundException
        assertThrows(WishListItemNotFoundException.class, () -> {
            wishListItemService.deleteWishListItem(nonExistentItemId);
        });

        // Verify that wishListItemRepository.findById() is called once with the specified ID
        verify(wishListItemRepository, times(1)).findById(nonExistentItemId);
    }


}

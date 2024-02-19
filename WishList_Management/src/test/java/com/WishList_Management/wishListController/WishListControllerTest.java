package com.WishList_Management.wishListController;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;




import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.WishList_Management.controller.WishListController;
import com.WishList_Management.models.WishListItem;
import com.WishList_Management.service.WishListItemService;

@ExtendWith(MockitoExtension.class)
public class WishListControllerTest {

    @Mock
    private WishListItemService itemService;

    @InjectMocks
    private WishListController controller;

    @Test
    public void testAddWishListItem() {
        // Prepare test data
        String username = "testUser";
        WishListItem item = new WishListItem();
        item.setItemname("Test Item");
        item.setPrice(100);

        // Stub service method
        when(itemService.CreateWishListItem(username, item)).thenReturn(item);

        // Perform the controller action
        ResponseEntity<WishListItem> response = controller.addWishListItem(username, item);

        // Verify the response
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(item, response.getBody());

        // Verify interactions with the service
        verify(itemService, times(1)).CreateWishListItem(username, item);
    }

    @Test
    public void testDeleteWishListItem() {
        // Prepare test data
        long itemId = 123;

        // Perform the controller action
        ResponseEntity<String> response = controller.deteteWishListItem(itemId);

        // Verify the response
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("delete succesFully", response.getBody());

        // Verify interactions with the service
        verify(itemService, times(1)).deleteWishListItem(itemId);
    }

    @Test
    public void testGetUserWishList() {
        // Prepare test data
        String username = "testUser";
        WishListItem item1 = new WishListItem();
        item1.setItemname("Item 1");
        item1.setPrice(100);
        WishListItem item2 = new WishListItem();
        item2.setItemname("Item 2");
        item2.setPrice(200);
        List<WishListItem> itemList = Arrays.asList(item1, item2);

        // Stub service method
        when(itemService.getUserWishList(username)).thenReturn(itemList);

        // Perform the controller action
        ResponseEntity<List<WishListItem>> response = controller.getUserWishList(username);

        // Verify the response
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(itemList, response.getBody());

        // Verify interactions with the service
        verify(itemService, times(1)).getUserWishList(username);
    }
}


package org.yearup.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.yearup.data.CategoryDao;
import org.yearup.data.ProductDao;
import org.yearup.models.Category;
import org.yearup.models.Product;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CategoriesControllerTest {
    private CategoryDao categoryDao;
    private ProductDao productDao;
    private CategoriesController restController;


    @BeforeEach
    public void setup() {
        categoryDao = mock(CategoryDao.class);
        productDao = mock(ProductDao.class);
        restController = new CategoriesController(categoryDao, productDao);
    }

    @Test
    public void testGetAllCategories() {
        // Match the seeded SQL values
        Category electronics = new Category() {{
            setCategoryId(1);
            setName("Electronics");
            setDescription("Explore the latest gadgets and electronic devices.");
        }};
        Category fashion = new Category() {{
            setCategoryId(2);
            setName("Fashion");
            setDescription("Discover trendy clothing and accessories for men and women.");
        }};

        when(categoryDao.getAllCategories()).thenReturn(Arrays.asList(electronics, fashion));

        List<Category> result = restController.getAll();

        assertEquals(2, result.size());
        assertEquals("Electronics", result.get(0).getName());
        assertEquals("Discover trendy clothing and accessories for men and women.", result.get(1).getDescription());
    }


    @Test
    void getById() {
        Category cat = new Category() {{
            setCategoryId(1);
            setName("Books");
            setDescription("Books category");
        }};
        when(categoryDao.getById(1)).thenReturn(cat);

        Category result = restController.getById(1);

        assertNotNull(result);
        assertEquals("Books", result.getName());
        assertEquals("Books category", result.getDescription());
    }

    @Test
    void getProductsById() {
        Product product = new Product() {{
            setProductId(1);
            setName("Smartphone");
        }};
        when(productDao.listByCategoryId(1)).thenReturn(List.of(product));

        List<Product> result = restController.getProductsById(1);
        assertEquals(1, result.size());
    }

    @Test
    void addCategory() {
        Category cat = new Category() {{
            setName("Fashion");
            setDescription("Discover trendy clothing and accessories for men and women.");
        }};
        when(categoryDao.create(cat)).thenReturn(cat);

        Category result = restController.addCategory(cat);

        assertEquals("Fashion", result.getName());
    }

    @Test
    void updateCategory() {
        Category cat = new Category() {{
            setName("Updated");
            setDescription("Updated Description");
        }};
    }

    @Test
    void deleteCategory() {
        doNothing().when(categoryDao).delete(1);

        assertDoesNotThrow(() -> restController.deleteCategory(1));
    }
}
package org.yearup.data.mysql;

import org.springframework.stereotype.Component;
import org.yearup.data.CategoryDao;
import org.yearup.data.ShoppingCartDao;
import org.yearup.models.Category;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class MySqlCategoryDao extends MySqlDaoBase implements CategoryDao
{
    public MySqlCategoryDao(DataSource dataSource)
    {
        super(dataSource);
    }

    @Override
    public List<Category> getAllCategories()
    {
        List<Category> categoryList = new ArrayList<>();
        String query = "SELECT * FROM categories";

        try (
                Connection connection = getConnection();
                PreparedStatement ps = connection.prepareStatement(query);
                ResultSet resultSet = ps.executeQuery()
        ) {
            while (resultSet.next()) {
                categoryList.add(mapRow(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categoryList;
    }

    @Override
    public Category getById(int categoryId)
    {
        // get category by id
        Category category = null;
        String query = "SELECT * FROM categories WHERE category_id = ?;";
        try (
                Connection connection = getConnection();
                PreparedStatement ps = connection.prepareStatement(query);
        ) {
            ps.setInt(1, categoryId);

            try (ResultSet resultSet = ps.executeQuery()) {
                if (resultSet.next()) {
                    category = mapRow(resultSet);
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return category;
    }

    @Override
    public Category create(Category category)
    {
        // create a new category
        String query = "INSERT INTO categories (name, description) VALUES (?,?);\n";
        try (
                Connection connection = getConnection();
                PreparedStatement ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

        ) {
            ps.setString(1, category.getName());
            ps.setString(2, category.getDescription());
            ps.executeUpdate();
            try (ResultSet resultSet = ps.getGeneratedKeys()) {
                resultSet.next();
                category.setCategoryId(resultSet.getInt(1));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return category;
    }

    @Override
    public void update(int categoryId, Category category) {
        // update category
        String query = "UPDATE categories SET name = ? \n" +
                "WHERE category_id = ?;";
        try (
                Connection connection = getConnection();
                PreparedStatement ps = connection.prepareStatement(query);
        ) {
            ps.setString(1, category.getName());
            ps.setInt(2, categoryId);
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int categoryId)
    {
        // delete category
        String query = "DELETE FROM categories \n" +
                "WHERE category_id = ?;";
        try (
                Connection connection = getConnection();
                PreparedStatement ps = connection.prepareStatement(query);
        ) {
            ps.setInt(1, categoryId);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    private Category mapRow(ResultSet row) throws SQLException
    {
        int categoryId = row.getInt("category_id");
        String name = row.getString("name");
        String description = row.getString("description");

        Category category = new Category()
        {{
            setCategoryId(categoryId);
            setName(name);
            setDescription(description);
        }};

        return category;
    }

}

package dao;

import model.Category;
import model.Product;

import java.sql.SQLException;
import java.util.List;

public interface IProductDao {
    public void insertProduct(Product product);
    public Product selectProduct(int id);
    public List<Product> selectAllProduct();
    public boolean deleteProduct(int id) throws SQLException;
    public boolean updateProduct(Product product) throws SQLException;
    Category getCategoryById(int categoryId);
    List<Category> selectAllCategory();
    List<Product> searchByName(String name);

}

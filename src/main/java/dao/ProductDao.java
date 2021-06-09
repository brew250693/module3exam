package dao;

import model.Category;
import model.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDao implements IProductDao {
    private String jdbcUrl = "jdbc:mysql://localhost:3306/product_manager";
    private String jdbcUsername = "root";
    private String jdbcPassword = "250693";
    private static final String INSERT_PRODUCT_SQL = "insert into product (name, price, amount, color) value (?,?,?,?)";

    private static final String SELECT_PRODUCT_BY_ID = "select * from product where id =?";
    private static final String SELECT_ALL_PRODUCT = "select p.id, p.name, p.price, p.amount, p.color ,c.name from product p join category c on c.id = p.category_id";
    private static final String DELETE_PRODUCT_SQL = "delete from product where id = ?;";
    private static final String UPDATE_PRODUCT_SQL = "update product set name=?, price=?, amount=?, color=?, category_id=? where id=?";

    public ProductDao() {
    }

    protected Connection getConnection(){
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcUrl,jdbcUsername,jdbcPassword);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return connection;
    }

    @Override
    public void insertProduct(Product product) {
        System.out.println(INSERT_PRODUCT_SQL);
        Connection connection = getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("insert into product (name, price, amount, color, category_id) value (?,?,?,?,?)");{
                preparedStatement.setString(1,product.getName());
                preparedStatement.setInt(2,product.getPrice());
                preparedStatement.setInt(3,product.getAmount());
                preparedStatement.setString(4,product.getColor());
                preparedStatement.setInt(5, product.getCategory().getId());
                System.out.println(preparedStatement);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public Product selectProduct(int id) {
        Product product = null;
        Connection connection = getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PRODUCT_BY_ID);{
                preparedStatement.setInt(1,id);
                System.out.println(preparedStatement);
                ResultSet rs = preparedStatement.executeQuery();
                while (rs.next()){
                    String name = rs.getString("name");
                    int price = rs.getInt("price");
                    int amount = rs.getInt("amount");
                    String color = rs.getString("color");
                    product = new Product(name,price,amount,color);
                    int category_id = rs.getInt("category_id");
                    Category category = new Category(category_id);
                    product.setCategory(category);
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return product;
    }

    @Override
    public List<Product> selectAllProduct() {
        List<Product> products = new ArrayList<>();
        Connection connection = getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_PRODUCT);{
                System.out.println(preparedStatement);
                ResultSet rs = preparedStatement.executeQuery();
                while (rs.next()){
                    int id = rs.getInt("id");
                    String name = rs.getString("name");
                    int price = rs.getInt("price");
                    int amount = rs.getInt("amount");
                    String color = rs.getString("color");
                    Product p = new Product(id,name,price,amount,color);
                    String category_name = rs.getString("c.name");
                    Category category = new Category(category_name);
                    p.setCategory(category);
                    products.add(p);
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return products;
    }

    @Override
    public boolean deleteProduct(int id) throws SQLException {
        boolean rowDeleted;
        Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(DELETE_PRODUCT_SQL);{
            preparedStatement.setInt(1,id);
            rowDeleted = preparedStatement.executeUpdate()>0;
        }
        return rowDeleted;
    }

    @Override
    public boolean updateProduct(Product product) throws SQLException {
        boolean rowUpdate;
        Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_PRODUCT_SQL);{
            preparedStatement.setString(1,product.getName());
            preparedStatement.setInt(2,product.getPrice());
            preparedStatement.setInt(3,product.getAmount());
            preparedStatement.setString(4,product.getColor());
            preparedStatement.setInt(5,product.getCategory().getId());

            preparedStatement.setInt(6,product.getId());
            rowUpdate = preparedStatement.executeUpdate()>0;
        }
        return rowUpdate;
    }

    @Override
    public Category getCategoryById(int categoryId) {
        Category category = null;
        Connection connection = getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from category where id = ?");
            {
                preparedStatement.setInt(1, categoryId);
                System.out.println(preparedStatement);
                ResultSet rs = preparedStatement.executeQuery();
                while (rs.next()) {
                    String name = rs.getString("name");
                    String des = rs.getString("description");
                    category = new Category(categoryId, name,des);
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return category;
    }
    @Override
    public List<Category> selectAllCategory() {
        List<Category> categoryList = new ArrayList<>();
        Connection connection = getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement("select * from category");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String description = resultSet.getString("description");
                Category category = new Category(id, name,description);
                System.out.println(category);
                categoryList.add(category);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return categoryList;
    }
    @Override
    public List<Product>  searchByName(String name) {
        List<Product> productList = new ArrayList<>();
        Connection connection = getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from product where name like ?");
            {
                preparedStatement.setString(1, "%" + name + "%");
                System.out.println(preparedStatement);
                ResultSet rs = preparedStatement.executeQuery();
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String name1 = rs.getString("name");
                    int price = rs.getInt("price");
                    int amount = rs.getInt("amount");
                    String color = rs.getString("color");
                    int category_id = rs.getInt("category_id");
                    Category category = getCategoryById(category_id);
                    Product p = new Product(id, name1, price, amount, color, category);
                    productList.add(p);
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return productList;
    }
}

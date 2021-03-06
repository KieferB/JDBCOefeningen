
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author kiefer.beernaert
 */
public class Main {

    private static final String URL = "jdbc:mysql://localhost/tuincentrum";
    private static final String USER = "cursist";
    private static final String PASSWORD = "cursist";
    private static final String SQLStatement = "insert into soorten(naam) values (?)";
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        try( Scanner scanner = new Scanner(System.in)){
            System.out.println("Naam: ");
            String naam = scanner.nextLine();
            try (Connection connection = DriverManager.getConnection(URL,USER,PASSWORD);
                    PreparedStatement statement = connection.prepareStatement(SQLStatement, Statement.RETURN_GENERATED_KEYS)){
                statement.setString(1,naam);
                statement.executeUpdate();
                try( ResultSet resultSet = statement.getGeneratedKeys()){
                    resultSet.next();
                    System.out.println(resultSet.getLong(1));
                }
            }catch (SQLException ex){
                System.out.println(ex);
            }
        }
    }
    
}

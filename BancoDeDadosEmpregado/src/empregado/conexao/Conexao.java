package empregado.conexao;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Conexao {
	private final String driver = "oracle.jdbc.driver.OracleDriver";
	private final String url = "jdbc:oracle:thin:@oracle.fiap.com.br:1521:ORCL";
	private String username;
	private String password;
		
	public Connection conectar() {
		Properties properties = new Properties();
				
		try(FileInputStream file = new FileInputStream("config.properties")){
			properties.load(file);
			username = properties.getProperty("username");
			password = properties.getProperty("password");
			Class.forName(driver);
			return DriverManager.getConnection(url, username, password);
		}
		catch(IOException e) {
			System.out.println("Erro ao ler o arquivo de configuração\n" + e);
		} catch (ClassNotFoundException e) {
			System.out.println("Erro ao carregar o driver de conexão\n" + e);
		} catch (SQLException e) {
			System.out.println("Erro ao abrir conexão com o banco de dados\n" + e);
		}
		return null;
	}
}

import java.sql.*;

public class VendaDAO {
    private Connection connection;

    public VendaDAO() {
        try {
            // Configurar a conexão com o banco de dados
            String url = "jdbc:mysql://localhost:3306/meu_banco_de_dados";
            String username = "usuario";
            String password = "senha";
            connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            System.out.println("Falha ao conectar ao banco de dados: " + e.getMessage());
        }
    }

    public void adicionarVenda(Venda venda) {
        // SQL para inserir uma venda na tabela 'vendas'
        String sql = "INSERT INTO vendas (id, produto, preco) VALUES (?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            // Define os parâmetros do PreparedStatement com os valores da venda
            statement.setInt(1, venda.getId());
            statement.setString(2, venda.getProduto());
            statement.setDouble(3, venda.getPreco());
            // Executa o comando SQL para adicionar a venda
            statement.executeUpdate();
            System.out.println("Venda adicionada com sucesso!");
        } catch (SQLException e) {
            System.out.println("Erro ao adicionar venda: " + e.getMessage());
        }
    }

    public void atualizarVenda(Venda venda) {
        // SQL para atualizar uma venda na tabela 'vendas'
        String sql = "UPDATE vendas SET produto = ?, preco = ? WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            // Define os parâmetros do PreparedStatement com os novos valores da venda
            statement.setString(1, venda.getProduto());
            statement.setDouble(2, venda.getPreco());
            statement.setInt(3, venda.getId());
            // Executa o comando SQL para atualizar a venda
            statement.executeUpdate();
            System.out.println("Venda atualizada com sucesso!");
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar venda: " + e.getMessage());
        }
    }

    public void removerVenda(int id) {
        // SQL para remover uma venda da tabela 'vendas'
        String sql = "DELETE FROM vendas WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            // Define o parâmetro do PreparedStatement com o ID da venda a ser removida
            statement.setInt(1, id);
            // Executa o comando SQL para remover a venda
            statement.executeUpdate();
            System.out.println("Venda removida com sucesso!");
        } catch (SQLException e) {
            System.out.println("Erro ao remover venda: " + e.getMessage());
        }
    }

    public Venda obterVendaPorId(int id) {
        // SQL para obter uma venda da tabela 'vendas' pelo ID
        String sql = "SELECT * FROM vendas WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            // Define o parâmetro do PreparedStatement com o ID da venda
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                // Extrai os dados da venda do ResultSet e cria um objeto Venda correspondente
                int vendaId = resultSet.getInt("id");
                String produto = resultSet.getString("produto");
                double preco = resultSet.getDouble("preco");
                return new Venda(vendaId, produto, preco);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao obter venda: " + e.getMessage());
        }

        return null;
    }

    public void listarVendas() {
        // SQL para listar todas as vendas da tabela 'vendas'
        String sql = "SELECT * FROM vendas";

        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                // Extrai os dados de cada venda do ResultSet e exibe na saída
                int id = resultSet.getInt("id");
                String produto = resultSet.getString("produto");
                double preco = resultSet.getDouble("preco");
                System.out.println("ID: " + id + ", Produto: " + produto + ", Preço: " + preco);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar vendas: " + e.getMessage());
        }
    }

    public void fecharConexao() {
        // Fecha a conexão com o banco de dados
        try {
            connection.close();
        } catch (SQLException e) {
            System.out.println("Erro ao fechar conexão com o banco de dados: " + e.getMessage());
        }
    }
}
import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static VendaDAO vendaDAO = new VendaDAO();

    public static void main(String[] args) {
        boolean executando = true;

        while (executando) {
            exibirMenu();
            int opcao = lerOpcao();

            switch (opcao) {
                case 1:
                    adicionarVenda();
                    break;
                case 2:
                    atualizarVenda();
                    break;
                case 3:
                    removerVenda();
                    break;
                case 4:
                    obterVendaPorId();
                    break;
                case 5:
                    listarVendas();
                    break;
                case 6:
                    executando = false;
                    vendaDAO.fecharConexao();
                    break;
                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }
        }
    }

    private static void exibirMenu() {
        // Exibe o menu de opções para o usuário
        System.out.println("===== MENU =====");
        System.out.println("1. Adicionar Venda");
        System.out.println("2. Atualizar Venda");
        System.out.println("3. Remover Venda");
        System.out.println("4. Obter Venda por ID");
        System.out.println("5. Listar Vendas");
        System.out.println("6. Sair");
    }

    private static int lerOpcao() {
        // Lê a opção escolhida pelo usuário
        System.out.print("Digite sua opção: ");
        return scanner.nextInt();
    }

    private static void adicionarVenda() {
        // Adiciona uma nova venda
        System.out.print("Digite o ID da venda: ");
        int id = scanner.nextInt();
        System.out.print("Digite o nome do produto: ");
        scanner.nextLine();
        String produto = scanner.nextLine();
        System.out.print("Digite o preço do produto: ");
        double preco = scanner.nextDouble();

        Venda venda = new Venda(id, produto, preco);
        vendaDAO.adicionarVenda(venda);
    }

    private static void atualizarVenda() {
        // Atualiza uma venda existente
        System.out.print("Digite o ID da venda a ser atualizada: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        Venda venda = vendaDAO.obterVendaPorId(id);

        if (venda != null) {
            System.out.print("Digite o novo nome do produto: ");
            String produto = scanner.nextLine();
            System.out.print("Digite o novo preço do produto: ");
            double preco = scanner.nextDouble();

            venda.setProduto(produto);
            venda.setPreco(preco);
            vendaDAO.atualizarVenda(venda);
        } else {
            System.out.println("Venda não encontrada!");
        }
    }

    private static void removerVenda() {
        // Remove uma venda
        System.out.print("Digite o ID da venda a ser removida: ");
        int id = scanner.nextInt();
        vendaDAO.removerVenda(id);
    }

    private static void obterVendaPorId() {
        // Obtém os detalhes de uma venda pelo seu ID
        System.out.print("Digite o ID da venda: ");
        int id = scanner.nextInt();
        Venda venda = vendaDAO.obterVendaPorId(id);

        if (venda != null) {
            System.out.println(
                    "ID: " + venda.getId() + ", Produto: " + venda.getProduto() + ", Preço: " + venda.getPreco());
        } else {
            System.out.println("Venda não encontrada!");
        }
    }

    private static void listarVendas() {
        // Lista todas as vendas
        vendaDAO.listarVendas();
    }
}
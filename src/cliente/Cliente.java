package cliente;
import comum.Conta;
import comum.ContaNaoEncontradaException;
import comum.ContaService;
import comum.SaldoInsuficienteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Cliente {

    public static void main(String[] args) {
        try {
            String host = args.length > 0 ? args[0] : "localhost";
            Registry registry = LocateRegistry.getRegistry(host, 1099);
            ContaService servico = (ContaService) registry.lookup("ContaService");

            System.out.println("== Contas iniciais ==");
            servico.listarContas().forEach(System.out::println);

            System.out.println("\n== Depositando R$ 200,00 na conta 102 ==");
            servico.depositar(102, 200.0);
            System.out.printf("Novo saldo: R$ %.2f%n", servico.consultarSaldo(102));

            System.out.println("\n== Transferindo R$ 300,00 da conta 101 para a conta 103 ==");
            servico.transferir(101, 103, 300.0);
            System.out.printf("Saldo conta 101: R$ %.2f%n", servico.consultarSaldo(101));
            System.out.printf("Saldo conta 103: R$ %.2f%n", servico.consultarSaldo(103));

            System.out.println("\n== Tentando sacar R$ 10000,00 da conta 103==");
            try {
                servico.sacar(103, 10000.0);
            } catch (SaldoInsuficienteException e) {
                System.out.println("Erro: " + e.getMessage());
            }

            System.out.println("\n== Consultando uma conta que nao existe==");
            try {
                servico.consultarSaldo(999);
            } catch (ContaNaoEncontradaException e) {
                System.out.println("Erro: " + e.getMessage());
            }

            System.out.println("\n== Contas finais ==");
            servico.listarContas().forEach(System.out::println);

        } catch (Exception e) {
            System.err.println("Erro no cliente:");
            e.printStackTrace();
        }
    }
}

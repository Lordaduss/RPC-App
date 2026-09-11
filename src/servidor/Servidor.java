package servidor;
import comum.Conta;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
public class Servidor {

    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.createRegistry(1099);
            ContaServiceImpl servico = new ContaServiceImpl();
            registry.rebind("ContaService", servico);

            servico.abrirConta(new Conta(101, "Marcelo Silva", 1000.0));
            servico.abrirConta(new Conta(102, "Ana Souza", 500.0));
            servico.abrirConta(new Conta(103, "Pedro Lima", 250.0));

            System.out.println("Servidor RMI online. Aguardando chamadas do cliente...");
        } catch (Exception e) {
            System.err.println("Falha ao iniciar o servidor:");
            e.printStackTrace();
        }
    }
}

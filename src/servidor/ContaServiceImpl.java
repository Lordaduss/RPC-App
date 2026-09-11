package servidor;
import comum.Conta;
import comum.ContaNaoEncontradaException;
import comum.ContaService;
import comum.SaldoInsuficienteException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ContaServiceImpl extends UnicastRemoteObject implements ContaService {

    private static final long serialVersionUID = 1L;
    private final Map<Integer, Conta> contas = new HashMap<>();

    protected ContaServiceImpl() throws RemoteException {
        super();
    }

    @Override
    public synchronized void abrirConta(Conta conta) throws RemoteException {
        contas.put(conta.getNumero(), conta);
        System.out.println("[SERVIDOR] Conta aberta: " + conta);
    }

    @Override
    public synchronized double consultarSaldo(int numero)
            throws RemoteException, ContaNaoEncontradaException {
        Conta conta = buscar(numero);
        System.out.println("[SERVIDOR] Consulta de saldo da conta " + numero);
        return conta.getSaldo();
    }

    @Override
    public synchronized void depositar(int numero, double valor)
            throws RemoteException, ContaNaoEncontradaException {
        Conta conta = buscar(numero);
        conta.depositar(valor);
        System.out.printf("[SERVIDOR] Deposito de R$ %.2f na conta %d%n", valor, numero);
    }

    @Override
    public synchronized void sacar(int numero, double valor)
            throws RemoteException, ContaNaoEncontradaException, SaldoInsuficienteException {
        Conta conta = buscar(numero);
        if (conta.getSaldo() < valor) {
            throw new SaldoInsuficienteException(numero, conta.getSaldo(), valor);
        }
        conta.sacar(valor);
        System.out.printf("[SERVIDOR] Saque de R$ %.2f na conta %d%n", valor, numero);
    }

    @Override
    public synchronized void transferir(int numeroOrigem, int numeroDestino, double valor)
            throws RemoteException, ContaNaoEncontradaException, SaldoInsuficienteException {
        Conta origem = buscar(numeroOrigem);
        Conta destino = buscar(numeroDestino);
        if (origem.getSaldo() < valor) {
            throw new SaldoInsuficienteException(numeroOrigem, origem.getSaldo(), valor);
        }
        origem.sacar(valor);
        destino.depositar(valor);
        System.out.printf("[SERVIDOR] Transferencia de R$ %.2f: conta %d -> conta %d%n",
                valor, numeroOrigem, numeroDestino);
    }

    @Override
    public synchronized List<Conta> listarContas() throws RemoteException {
        System.out.println("[SERVIDOR] Listagem solicitada (" + contas.size() + " conta(s))");
        return new ArrayList<>(contas.values());
    }

    private Conta buscar(int numero) throws ContaNaoEncontradaException {
        Conta conta = contas.get(numero);
        if (conta == null) {
            throw new ContaNaoEncontradaException(numero);
        }
        return conta;
    }
}

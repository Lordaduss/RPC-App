package comum;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface ContaService extends Remote {

    void abrirConta(Conta conta) throws RemoteException;

    double consultarSaldo(int numero) throws RemoteException, ContaNaoEncontradaException;

    void depositar(int numero, double valor) throws RemoteException, ContaNaoEncontradaException;

    void sacar(int numero, double valor)
            throws RemoteException, ContaNaoEncontradaException, SaldoInsuficienteException;

    void transferir(int numeroOrigem, int numeroDestino, double valor)
            throws RemoteException, ContaNaoEncontradaException, SaldoInsuficienteException;

    List<Conta> listarContas() throws RemoteException;
}

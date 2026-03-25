package berkeleyServidor;

import java.rmi.Remote;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class MainSever {

	public static void main(String[] args) {
		try {
            // Passa o ID via argumento: java MainServer 1
            String id = args.length > 0 ? args[0] : "1";
            String nome = "Relogio_" + id;

            RelogioServerImplements registro = new RelogioServerImplements();

            // Tenta pegar o registry existente, ou cria um novo
            Registry registry;
            try {
                registry = LocateRegistry.getRegistry(2100);
                registry.list(); // testa se está vivo
            } catch (Exception e) {
                registry = LocateRegistry.createRegistry(2100);
            }

            registry.rebind(nome, registro);
            System.out.println("Servidor " + nome + " registrado com tempo: " + registro.getTime());

        } catch (Exception ex) {
            System.out.println("Erro: " + ex.getMessage());
        }

	}

}

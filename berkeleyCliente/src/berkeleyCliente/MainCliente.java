package berkeleyCliente;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;

import common.RelogioServerInterface;

public class MainCliente {

	public static void main(String[] args) throws RemoteException {
		
		
		ArrayList<RelogioServerInterface> servidores = new ArrayList<>();
        try {
            Registry registry = LocateRegistry.getRegistry("localhost", 2100);

           
            String[] nomes = registry.list();
            for (String nome : nomes) {
                if (nome.startsWith("Relogio_")) {
                    RelogioServerInterface s = (RelogioServerInterface) registry.lookup(nome);
                    servidores.add(s);
                    System.out.println("Encontrado: " + nome);
                }
            }

            if (servidores.isEmpty()) {
                System.out.println("Nenhum servidor encontrado!");
                return;
            }

           
            int soma = 0;
            int[] tempos = new int[servidores.size()];
            for (int i = 0; i < servidores.size(); i++) {
                tempos[i] = servidores.get(i).getTime();
                System.out.println("Tempo do servidor " + i + ": " + tempos[i]);
                soma += tempos[i];
            }

            int media = soma / servidores.size();
            System.out.println("Média: " + media);

           
            for (int i = 0; i < servidores.size(); i++) {
                int diferenca = media - tempos[i];
                System.out.println("Ajuste do servidor " + i + ": " + diferenca);
                servidores.get(i).ajustarTime(diferenca);
            }

            System.out.println("Sincronização concluída!");

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
        

	}

}

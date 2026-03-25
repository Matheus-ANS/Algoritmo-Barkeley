package berkeleyServidor;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Random;

import common.RelogioServerInterface;



public class RelogioServerImplements extends UnicastRemoteObject implements RelogioServerInterface{
	
	private int tempoLocal;
	
	protected RelogioServerImplements() throws RemoteException {
		super();
		Random rand = new Random();
	    this.tempoLocal = rand.nextInt(1200);
		
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public int getTime() throws RemoteException {
	
		return tempoLocal;
	}

	public void ajustarTime(int diferenca) throws RemoteException {
		tempoLocal += diferenca;
		
	}
	 
}

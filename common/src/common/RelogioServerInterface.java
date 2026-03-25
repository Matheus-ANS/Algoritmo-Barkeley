package common;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RelogioServerInterface extends Remote{

	int getTime() throws RemoteException;
	public void ajustarTime(int diferenca) throws RemoteException; 

}

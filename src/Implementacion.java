import java.rmi.server.UnicastRemoteObject;
import java.sql.Date;

import javax.swing.JOptionPane;

import java.io.IOException;
import java.rmi.*;

public class Implementacion extends UnicastRemoteObject implements Banco{

	public Implementacion(String name) throws RemoteException {
		super();
		try	{
			System.out.println("Rebind Object "+ name);
			Naming.rebind(name, this);
		}catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Algo a salido mal (1)","Banco",  JOptionPane.INFORMATION_MESSAGE );
			e.printStackTrace();
		}
	}
	
	Servidor objServidor = new Servidor();
	
	public int crearCuenta(int numCuenta, String propietario, int dinero) throws RemoteException {
		
		int estado = 0;
		
		try {

			estado = objServidor.crearCuenta(numCuenta, propietario, dinero);
			return estado;
			
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Algo a salido mal (1)","Banco",  JOptionPane.INFORMATION_MESSAGE );
			e.printStackTrace();
		}
		return 1;
	}

	
	public int borrarCuenta(int numBorrar) throws RemoteException {
		
		int estado = 0;
		
		estado = objServidor.borrarCuenta(numBorrar);
		
		return estado;
	}

	
	public int modificarCuenta(int numCuenta, String nuevoPropietario) throws RemoteException {
		
		int estado = 0;
		
		estado = objServidor.modificarCuenta(numCuenta, nuevoPropietario);
		
		return estado;
	}

	
	public int adicionarDinero(int numCuenta, int dinero) throws RemoteException {
		
		int estado = 0;
		
		estado = objServidor.adicionarDinero(numCuenta, dinero);
		return estado;
	}

	
	public int retirarDinero(int numCuenta, int dinero) throws RemoteException {
		
		int estado = 0;
		
		estado = objServidor.retirarDinero(numCuenta, dinero);
		
		return estado;
	}

	
	public int[] obtenerSaldo(int numCuenta) throws RemoteException {
		
		int []arreglo = objServidor.obtenerSaldo(numCuenta);

		return arreglo;
	}

	public int loggear(String nombreSucursal, String contraseña) throws RemoteException {
		
		int estado = 0;
		
		estado = objServidor.loggearse(nombreSucursal, contraseña);
		
		return estado;
	}

	public int crearSucursal(String nombreSucursal, String contraseña) throws RemoteException {
		
		int estado = 0;
		
		estado = objServidor.crearSucursal(nombreSucursal, contraseña);
		
		return estado;
	}
	
}

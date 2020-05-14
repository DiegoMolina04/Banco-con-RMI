
public interface Banco extends java.rmi.Remote{
	
    public int crearCuenta(int numCuenta, String propietario, int dinero) 
			throws java.rmi.RemoteException;
	
	public int borrarCuenta(int numCuenta) 
			throws java.rmi.RemoteException;
	
	public int modificarCuenta(int numCuenta, String nuevoPropietario)
			throws java.rmi.RemoteException;
	
	public int adicionarDinero(int numCuenta, int dinero)
			throws java.rmi.RemoteException;
	
	public int retirarDinero(int numCuenta, int dinero)
			throws java.rmi.RemoteException;
	
	public int[] obtenerSaldo(int numCuenta)
			throws java.rmi.RemoteException;
	
	public int loggear(String nombreSucursal, String contraseña)
			throws java.rmi.RemoteException;
	
	public int crearSucursal(String nombreSucursal, String contraseña)
			throws java.rmi.RemoteException;
}

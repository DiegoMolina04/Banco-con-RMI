import java.net.MalformedURLException;
import java.rmi.*;
import java.rmi.server.*;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.JOptionPane;

import java.rmi.registry.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Servidor {
		
	public Servidor() {
		
	}
	
	public int crearCuenta(int numCuenta, String propietario, int dinero) throws IOException {
	
		try {
	
		Banco resultado = (Banco)Naming.lookup("rmi://" +"localhost"+
					"/"+ "Resultado");
		
		FileWriter fichero = new FileWriter("F:/Nueva carpeta (3)/"+numCuenta+".txt");
		BufferedWriter bw = new BufferedWriter(fichero);
		Calendar fecha = new GregorianCalendar();
		int año = fecha.get(Calendar.YEAR);
	    int mes = fecha.get(Calendar.MONTH);
	    int dia = fecha.get(Calendar.DAY_OF_MONTH);
	    
		fichero.write(numCuenta+","+propietario+"\r\n"+dinero+","+dia+"/"+mes+"/"+año);
		
		fichero.close();
		
		return 5;
		
		} catch (MalformedURLException e) {
			e.printStackTrace();
			return 1;
			
		} catch (RemoteException e) {
			e.printStackTrace();
			return 1;
			
		} catch (NotBoundException e) {
			e.printStackTrace();
			return 1;
			
		}
		
	}
	
	public int borrarCuenta(int numBorrar) {
		
	File archivo = new File("F:/Nueva carpeta (3)/"+numBorrar+".txt");
		
		if(archivo.exists()) {
	
			archivo.delete();
						
		}else {
			
			return 1;
			
		}
		
		return 5;
	}
	
	public int modificarCuenta (int numModificar, String nuevoPropietario) {
		
		File archivo = new File("F:/Nueva carpeta (3)/"+numModificar+".txt");
		
		if(archivo.exists()) {
		
			FileReader Fr;
			
			try {
				
			Fr = new FileReader(archivo.toString());
			
	        BufferedReader br = new BufferedReader(Fr);
	        String linea;
	        String delimiter = ","; 

	        String matriz[][] = new String[2][2]; 

	        int numlinea=0;

	        while (((linea = br.readLine()) != null)) {
	       
	            String a[]=linea.split(delimiter);

	            for (int l = 0; l < a.length; l++) {
	                matriz[numlinea][l] = a[l];
	            }
	            numlinea++;
	        }
	        
	        String cuenta = matriz[0][0];
	        String dinero = matriz[1][0];
	        String fecha = matriz[1][1];
	       
	        FileWriter fichero = new FileWriter(archivo);
	        
	        fichero.write("");
	        
	        fichero.write(cuenta+","+nuevoPropietario+"\r\n"+dinero+","+fecha);
	        fichero.close();
	        	
			} catch (FileNotFoundException e) {
				
				e.printStackTrace();
				return 1;
				
			} catch (IOException e) {
				
				e.printStackTrace();
				return 1;	
			}
			
		}else {
			return 1;
		}
		
		return 5;
	}
	
	public int adicionarDinero (int numCuenta, int dinero) {
		
		File archivo = new File("F:/Nueva carpeta (3)/"+numCuenta+".txt");
		
		if(archivo.exists()) {
			
	        try {
	        		
			FileReader Fr = new FileReader(archivo.toString());
	        BufferedReader br = new BufferedReader(Fr);
	        String linea;
	        String delimiter = ","; 

	        String matriz[][] = new String[2][2]; 

	        int numlinea=0;

	        while (((linea = br.readLine()) != null)) {
	       
	            String a[]=linea.split(delimiter);

	            for (int l = 0; l < a.length; l++) {
	                matriz[numlinea][l] = a[l];
	            }
	            numlinea++;
	        }
	        
			String cuenta = matriz[0][0];
			String nombre = matriz[0][1];
		    String dinero1 = matriz[1][0];
		    String fecha = matriz[1][1];
		        
		    int dinero2 = Integer.parseInt(dinero1);
		    int nuevoSaldo = dinero2+dinero;
				
		    FileWriter fichero;
			
			fichero = new FileWriter(archivo);
		
			fichero.write("");
				        
			fichero.write(cuenta+","+nombre+"\r\n"+nuevoSaldo+","+fecha);
			fichero.close();
				    
			} catch (IOException e) {
				
				e.printStackTrace();
				return 1;
					
			}
				
		}else {
			
			return 1;
		}
		
		return 5;
}
	
	public int retirarDinero(int numCuenta, int dinero) {
		
		try {
		File archivo = new File("F:/Nueva carpeta (3)/"+numCuenta+".txt");
		FileReader Fr;
		Fr = new FileReader(archivo.toString());
		
        BufferedReader br = new BufferedReader(Fr);
        String linea;
        String delimiter = ","; 

        String matriz[][] = new String[2][2]; 

        int numlinea=0;

        while (((linea = br.readLine()) != null)) {
       
            String a[]=linea.split(delimiter);

            for (int l = 0; l < a.length; l++) {
                matriz[numlinea][l] = a[l];
            }
            numlinea++;
        }
		
		String cuenta = matriz[0][0];
		String nombre = matriz[0][1];
        String dinero1 = matriz[1][0];
        String fecha = matriz[1][1];
        
        int dinero2 = Integer.parseInt(dinero1);
        
        if(dinero > dinero2) {
        		
        	return 1;
        	
        }else{
        	
        	int nuevoSaldo = dinero2-dinero;
	        FileWriter fichero = new FileWriter(archivo);
	        
	        fichero.write("");
	        
	        fichero.write(cuenta+","+nombre+"\r\n"+nuevoSaldo+","+fecha);
	        fichero.close();
	        
        }
		
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
			return 1;
			
		} catch (IOException e) {
			
			e.printStackTrace();
			return 1;
			
		}
		
		return 5;
		
	}
	
	public int[] obtenerSaldo(int numBorrar) {
		
		File archivo = new File("F:/Nueva carpeta (3)/"+numBorrar+".txt");
		
		int saldo = 0;
		if(archivo.exists()) {
			
			try {
				
			FileReader Fr = new FileReader(archivo.toString());
			BufferedReader br = new BufferedReader(Fr);
			String linea;
			String delimiter = ","; 
			String matriz[][] = new String[2][2]; 

			int numlinea=0;

			while (((linea = br.readLine()) != null)) {
				
				String a[]=linea.split(delimiter);

			    for (int l = 0; l < a.length; l++) {
			    	
			        matriz[numlinea][l] = a[l];
			        
			    		}
			            numlinea++;
			        }
			
			int aux = Integer.parseInt(matriz[1][0]);
			saldo = aux;

			} catch (FileNotFoundException e) {
				
				e.printStackTrace();
				int[] retorno= {1};
				return retorno;
				
			} catch (IOException e) {
				
				e.printStackTrace();
				int[] retorno= {1};
				return retorno;
			}
			
			}else {
				int[] retorno= {1};
				return retorno;
		}
		
		int[] retorno= {5,saldo};
				
		return retorno;
	}


	public int loggearse(String nombreSucursal, String contraseña) {
		
		File archivo = new File("F:/Nueva carpeta (4)/"+nombreSucursal+".txt");
		
		try {
		
		if(archivo.exists()) {
			
			String informacion; 
			
			FileReader fr = new FileReader("F:/Nueva carpeta (4)/"+nombreSucursal+".txt");
			BufferedReader bf = new BufferedReader(fr);
			informacion = bf.readLine();
			
			if(informacion.equals(contraseña)) {
				
				return 5;
				
			}else {
				
				return 1;
						
			}
		
		}else {
			
			return 1;
		}
		
		} catch (IOException e) {
			
			e.printStackTrace();
			return 1;
		}
		
	}
	
	public int crearSucursal (String nomSucursal, String nuevaContraseña) {
		
		try {
		
		FileWriter fichero = new FileWriter("F:/Nueva carpeta (4)/"+nomSucursal+".txt");
		
		fichero.write(nuevaContraseña);
					
		fichero.close();
		
		} catch (IOException e) {
			
			e.printStackTrace();
			return 1;
		}
		
		return 5;
	}

	public static void main(String[] args) {
		
		try {
			Implementacion crear = new
					Implementacion("Crear");
			
			Implementacion borrar = new
					Implementacion("Borrar");
			
			Implementacion modificar = new
					Implementacion("Modificar");
			
			Implementacion adicionar = new
					Implementacion("Adicionar");
                        
            Implementacion retirar = new
					Implementacion("Retirar");
                        
            Implementacion saldo = new
					Implementacion("Saldo");
            
            Implementacion resultado = new
            		Implementacion("Resultado");
            
            Implementacion loggearse = new
            		Implementacion("Loggearse");
            
            Implementacion nuevaSucursal = new
            		Implementacion("nuevaSucursal");
                        
		
		}catch(Exception e) {
			e.printStackTrace();
		}
		

	}

}

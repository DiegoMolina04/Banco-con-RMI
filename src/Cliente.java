import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.registry.*;
import java.rmi.server.*;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Scanner;

import javax.swing.Icon;
import javax.swing.JOptionPane;

public class Cliente {
	
	public static void main(String[] args) throws IOException, NotBoundException {
		
		JOptionPane.showMessageDialog(null, "Bienvenido","Banco",  JOptionPane.INFORMATION_MESSAGE );
		
		while(true) {
			
		String aux;
		
		Object [] opciones ={"1. Loggearse","2. Crear cuenta"};
		String seleccion = (String) JOptionPane.showInputDialog(null,"Selecciona una opcion", "Elegir",JOptionPane.QUESTION_MESSAGE,null,opciones, opciones[0]);
	
		switch(seleccion) {
		
		case "1. Loggearse":
			
			Banco loggearse = (Banco)Naming.lookup("rmi://" + args[0]+
					"/"+ "Crear");
			
			String sucursal = JOptionPane.showInputDialog("Ingrese el nombre de la sucursal");
			
			String contraseña = JOptionPane.showInputDialog("Digite la contraseña");
		
			int resultado = loggearse.loggear(sucursal, contraseña);
		
			if(resultado == 5) {
			
					JOptionPane.showMessageDialog(null, "Operacion exitosa (5)","Banco",  JOptionPane.INFORMATION_MESSAGE );
						
					int numCuenta;
					String propietario;
					int dinero;
					
					String aux1;
					String aux2;
					int aux3;
					
					while (true) {
					Object [] opciones2 ={"1. Crear Cuenta","2. Borrar Cuenta","3. Modificar Cuenta","4. Adicionar Dinero","5. Retirar Dinero","6. Obtener Saldo","7. Salir"};
					
					String seleccion2 = (String) JOptionPane.showInputDialog(null,"Seleccione la operación a realizar", "Elegir",JOptionPane.QUESTION_MESSAGE,null,opciones2, opciones2[0]);				
					
					try {
						
					
					switch(seleccion2) {
					
					case "1. Crear Cuenta":
						
						Banco crear = (Banco)Naming.lookup("rmi://" + args[0]+
								"/"+ "Crear");
								
						aux1= JOptionPane.showInputDialog("Ingrese el numero de la cuenta");
						
						numCuenta = Integer.parseInt(aux1);
						
						propietario= JOptionPane.showInputDialog("Ingrese el nombre del propietario");
						
						aux2 =JOptionPane.showInputDialog("Ingrese la cantidad de dinero");
						dinero = Integer.parseInt(aux2);
						
						aux3 = crear.crearCuenta(numCuenta, propietario, dinero);
						
						if (aux3 == 5) {
							
							JOptionPane.showMessageDialog(null, "Operacion exitosa (5)","Banco",  JOptionPane.INFORMATION_MESSAGE );
							
						}else {
							
							JOptionPane.showMessageDialog(null, "Algo a salio mal (1)","Banco", 0,null );
							
						}
					
						break;
						
					case "2. Borrar Cuenta":
						
						Banco borrar = (Banco)Naming.lookup("rmi://" + args[0]+
								"/"+ "Borrar");
						
						aux1 =JOptionPane.showInputDialog("Ingrese el numero de la cuenta");
						
						numCuenta = Integer.parseInt(aux1);
						
						aux3 = borrar.borrarCuenta(numCuenta);
						
						if (aux3 == 5) {
							
							JOptionPane.showMessageDialog(null, "Operacion exitosa (5)","Banco",  JOptionPane.INFORMATION_MESSAGE );
							
						}else {
							
							JOptionPane.showMessageDialog(null, "Algo a salio mal (1)","Banco", 0,null );
							
						}
						
						break;
						
					case "3. Modificar Cuenta":
						
						Banco modificar = (Banco)Naming.lookup("rmi://" + args[0]+
								"/"+ "Modificar");
						
						aux1 =JOptionPane.showInputDialog("Ingrese el numero de la cuenta");
						numCuenta = Integer.parseInt(aux1);
						
						propietario = JOptionPane.showInputDialog("Ingrese el nuevo nombre");
							
						aux3 = modificar.modificarCuenta(numCuenta, propietario);
						
						if (aux3 == 5) {
							
							JOptionPane.showMessageDialog(null, "Operacion exitosa (5)","Banco",  JOptionPane.INFORMATION_MESSAGE );
							
						}else {
							
							JOptionPane.showMessageDialog(null, "Algo a salio mal (1)","Banco", 0,null );
							
						}
						
						//JOptionPane.showMessageDialog(null, "Operacion exitosa (5)","Banco",  JOptionPane.INFORMATION_MESSAGE );
						
						break;
						
					case "4. Adicionar Dinero":
						
						Banco adicionar = (Banco)Naming.lookup("rmi://" + args[0]+
								"/"+ "Adicionar");
						
						aux1 =JOptionPane.showInputDialog("Ingrese el numero de la cuenta");
						numCuenta = Integer.parseInt(aux1);
							
						aux2 =JOptionPane.showInputDialog("Digite la cantidad de dinero");
						dinero = Integer.parseInt(aux2);
						
						
						aux3 = adicionar.adicionarDinero(numCuenta, dinero);
						
						if (aux3 == 5) {
							
							JOptionPane.showMessageDialog(null, "Operacion exitosa (5)","Banco",  JOptionPane.INFORMATION_MESSAGE );
							
						}else {
							
							JOptionPane.showMessageDialog(null, "Algo a salio mal (1)","Banco", 0,null );
							
						}
						
						break;
						
					case "5. Retirar Dinero":
						
						Banco retirar = (Banco)Naming.lookup("rmi://" + args[0]+
								"/"+ "Retirar");
	
						aux1 =JOptionPane.showInputDialog("Ingrese el numero de la cuenta");
						numCuenta = Integer.parseInt(aux1);
			
						aux2 =JOptionPane.showInputDialog("Digite la cantidad a retirar");
						dinero = Integer.parseInt(aux2);
						
						aux3 = retirar.retirarDinero(numCuenta, dinero);
						
						if (aux3 == 5) {
							
							JOptionPane.showMessageDialog(null, "Operacion exitosa (5)","Banco",  JOptionPane.INFORMATION_MESSAGE );
							
						}else {
							
							JOptionPane.showMessageDialog(null, "Algo a salio mal (1)","Banco", 0,null );
							
						}
						
						break;
						
					case "6. Obtener Saldo":
						
						Banco saldo = (Banco)Naming.lookup("rmi://" + args[0]+
								"/"+ "Saldo");
					
						aux1 =JOptionPane.showInputDialog("Ingrese el numero de la cuenta");
						numCuenta = Integer.parseInt(aux1);
						
						
						int[]aux4 = saldo.obtenerSaldo(numCuenta);
						
						
						if (aux4[0] == 5) {
							
							JOptionPane.showMessageDialog(null, "El saldo es de: "+aux4[1],"Banco",  JOptionPane.INFORMATION_MESSAGE );
							JOptionPane.showMessageDialog(null, "Operacion exitosa (5)","Banco",  JOptionPane.INFORMATION_MESSAGE );
							
						}else {
							
							JOptionPane.showMessageDialog(null, "Algo a salio mal (1)","Banco", 0,null );
							
						}
						
						break;
						
					case "7. Salir":
						
						System.exit(0);
						break;
						
						}
				
					} catch (Exception e) {
						
						JOptionPane.showMessageDialog(null, "Algo a salio mal (1)","Banco", 0,null );
					}
					
				}

			}else {
				JOptionPane.showMessageDialog(null, "Algo a salio mal (1)","Banco", 0,null );
			}
			
		
		case "2. Crear cuenta":
			
			
			try {
				
			Banco nuevaSucursal = (Banco)Naming.lookup("rmi://" + args[0]+
					"/"+ "Saldo");

			String nomSucursal = JOptionPane.showInputDialog("Ingrese el nombre de la sucursal");

			String nuevaContraseña = JOptionPane.showInputDialog("Digite la contraseña");
			
			int respuesta = nuevaSucursal.crearSucursal(nomSucursal, nuevaContraseña);
			
			System.out.println("Esta es la respuesta "+respuesta);
			

			if (respuesta == 5) {
				
				JOptionPane.showMessageDialog(null, "Operacion exitosa (5)","Banco",  JOptionPane.INFORMATION_MESSAGE );
				
			}else {
				
				JOptionPane.showMessageDialog(null, "Algo a salio mal (1)","Banco", 0,null );
				
			}
			
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Algo a salio mal (1)","Banco", 0,null );
			}
			
			
		}
	}
		}
		
	}


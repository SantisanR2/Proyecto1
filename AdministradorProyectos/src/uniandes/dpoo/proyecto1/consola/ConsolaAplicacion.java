package uniandes.dpoo.proyecto1.consola;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import uniandes.dpoo.proyecto1.modelo.Participante;
import uniandes.dpoo.proyecto1.modelo.Proyecto;
import uniandes.dpoo.proyecto1.procesamiento.LoaderAplicacion;

/**
 * @author Usuario
 *
 */
public class ConsolaAplicacion 
{
	/**
	 * Un mapa con todos los participantes registrados en la aplicacion.
	 */
	private Map<String, Participante> mapaUsuarios;
	
	/**
	 * La lista con todos los proyectos de la aplicacion.
	 */
	private ArrayList<Proyecto> proyectos;
	
	/**
	 * El usuario que va a utilizar la aplicacion.
	 */
	private Participante usuario;
	
	/**
	 * Se carga la informacion de la aplicacion y se inicia con el registro del usuario.
	 */
	public void iniciarAplicacion()
	{
		System.out.println(centrar("BIENVENID@ AL ADMINISTRADOR DE PROYECTOS", 50,"***"));
		cargar();
		ingreso();
		comenzar();
		guardar();
	}
	
	/**
	 * Carga toda la informacion anterior de la aplicacion.
	 */
	public void cargar()
	{
		mapaUsuarios = new HashMap<String, Participante>();
		LoaderAplicacion.cargar();
	}
	
	/**
	 * Guarda toda la informacion de los proyectos y de los usuarios.
	 */
	public void guardar()
	{
		LoaderAplicacion.guardar();
	}
	
	/**
	 * Comienza la interaccion con la aplicacion una vez ingresado el usuario.
	 */
	public void comenzar()
	{
		//TODO: Realizar la implementacion por consola para preguntar al usuario que realizar.
		
		//Posibles peticiones del usuario:
		
		/**
		 * Crear un nuevo proyecto.
		 * Agregar participantes a un proyecto.
		 * Seleccionar un proyecto viejo.
		 * Agregar una actividad a un proyecto.
		 * Sacar un reporte de una actividad.
		 * Sacar un reporte de un proyecto.
		 * Sacar un reporte de un participante.
		 */
	}
	
	/**
	 * Permite y verifica el ingreso de un usuario a la aplicacion.
	 */
	public void ingreso()
	{
		Scanner sc = new Scanner(System.in);
		System.out.print("Ingrese su nombre: \n");  
		String nombre = sc.nextLine();
		System.out.println("Ingrese su correo: ");
		String correo = sc.nextLine(); 
		if(correo.contains("@"))
		{
			if(mapaUsuarios.containsKey(correo))
			{
				if(mapaUsuarios.get(correo).getNombre().equalsIgnoreCase(nombre))
				{
					usuario = mapaUsuarios.get(correo);
				}
				else 
				{
					System.out.println("Ese correo ya se encuentra registrado a otro nombre, intente con otro correo o cambie el nombre. \n");
					ingreso();
				}
			}
			else
			{
				crearParticipante(nombre, correo);
			}
		}
		else 
		{
			System.out.println("Por favor ingrese un correo valido \n");
			ingreso();
		}
	}
	
	/**
	 * Centra una cadena de caracteres en un espacio determinado con una cadena especifica iniciando y terminando.
	 * @param s La cadena que desea centrarse.
	 * @param ancho El espacio disponible para colocar el mensaje.
	 * @param a Los caracteres que desean ponerse antes y despues del mensaje centrado.
	 * @return La cadena centrada en el espacio determinado con los caracteres el inicio y al final.
	 */
	private String centrar(String s, int ancho, String a)
	{
        int lonText=s.length()-2*a.length();
        int tamañoCampo=(ancho/2)+(lonText/2);
        String n = s;
        String m= String.format("%" + tamañoCampo + "s", s);
        s = String.format("%" + tamañoCampo + "s", s).replace(" ","*");
        s = String.format("%-" + ancho  + "s", s).replace(" ","*");
        m= String.format("%-" + ancho + "s", m);
        if(ancho < a.length() + n.length())
        {
        	return a+m+a;
        }
        else
        {
        	return a+" ".repeat(a.length())+m+a;
        }
	}
	
	/**
	 * Centra una cadena de caracteres en un espacio determinado.
	 * @param s La cadena que desea centrarse.
	 * @param ancho El espacio disponible para colocar el mensaje.
	 * @return La cadena centrada en el espacio determinado.
	 */
	private String centrar(String s, int ancho)
	{
        int lonText=s.length();
        int tamañoCampo=(ancho/2)+(lonText/2);
        String m= String.format("%" + tamañoCampo + "s", s);
        s = String.format("%" + tamañoCampo + "s", s).replace(" ","*");
        s = String.format("%-" + ancho  + "s", s).replace(" ","*");
        m= String.format("%-" + ancho + "s", m);
        return m;
	}
	
	/**
	 * Crea un nuevo participante y lo agrega al mapa de usuarios.
	 * @param pNombre Nombre del participante.
	 * @param pCorreo Correo del participante.
	 * @return El participante que se creo.
	 */
	public Participante crearParticipante(String pNombre, String pCorreo)
	{
		usuario = new Participante(pNombre, pCorreo);
		mapaUsuarios.put(pCorreo, usuario);
		return usuario;
	}
	
	/**
	 * Crea un proyecto nuevo.
	 * @param pNombre El nombre del proyecto.
	 * @param pFechaInicio La fecha de inicio del proyecto.
	 * @param pFechaFinal La fecha de finalizacion o tentativa de finalizacion del proyecto.
	 * @param pParticipante El participante que creo el proyecto.
	 * @param pDescripcion La descripcion del proyecto.
	 * @param pTiposDisponibles La lista con los tipos de actividades disponibles del proyecto.
	 * @return
	 */
	public Proyecto crearProyecto(String pNombre, LocalDateTime pFechaInicio, LocalDateTime pFechaFinal, Participante pParticipante,
			String pDescripcion, ArrayList<String> pTiposDisponibles)
	{
		Proyecto proyecto = new Proyecto(pNombre, pFechaInicio, pFechaFinal,pParticipante, pDescripcion, pTiposDisponibles);
		proyectos.add(proyecto);
		return proyecto;
	}
	
	/**
	 * Agrega al mapa de usuarios un nuevo participante.
	 * @param participante El participante a ser agregado.
	 */
	public void agregarParticipante(Participante participante)
	{
		mapaUsuarios.put(participante.getCorreo(), participante);
	}
	
	/**
	 * Agrega a la lista de proyectos un nuevo proyecto.
	 * @param proyecto El proyecto a ser agregado.
	 */
	public void agregarProyecto(Proyecto proyecto)
	{
		proyectos.add(proyecto);
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) 
	{
		ConsolaAplicacion consola = new ConsolaAplicacion();
		consola.iniciarAplicacion();
	}
}

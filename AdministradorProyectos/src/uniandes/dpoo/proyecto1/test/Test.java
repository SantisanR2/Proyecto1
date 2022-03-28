package uniandes.dpoo.proyecto1.test;

import java.time.LocalDateTime;
import java.util.ArrayList;

import uniandes.dpoo.proyecto1.modelo.Participante;
import uniandes.dpoo.proyecto1.modelo.Proyecto;

public class Test {

	public static void main(String[] args) 
	{
		Participante participante1 = new Participante("Santiago", "sa.ramirezr@uniandes.edu.co");
		ArrayList<String> lista = new ArrayList<String>();
		lista.add("tipo 1");
		lista.add("tipo2");
		Proyecto proyecto = new Proyecto("Proyecto Prueba", LocalDateTime.of(2022, 03, 28, 0, 0, 0), LocalDateTime.of(2022, 03, 30, 0, 0, 0), participante1, "Una corta descripcion", lista);
	}

}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Scanner;
import java.util.ArrayList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author jojusuar
 */
public class UtilitariaConfig implements Serializable {

    public static void ingresarTermino(ArrayList<Termino> terminos, int x, int y, VBox vbTerminos) {
        Termino t = new Termino(x, y); //se crea término con los datos ingresados
        terminos.add(t);
        vbTerminos.getChildren().add(new Label(t.toString()));
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("src/main/resources/memory/terminos.ser"));) {
            out.writeObject(terminos);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static void editarTermino(ArrayList<Termino> terminos, Termino sel, String x) {
        System.out.println("<<EDITANDO TÉRMINO>>");
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("src/main/resources/memory/terminos.ser"));) {
            out.writeObject(terminos);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("src/main/resources/memory/paralelos.ser"));) {
            ArrayList<Paralelo> paralelos = (ArrayList<Paralelo>) in.readObject();
            for (Paralelo p : paralelos) {
                if (p.getTermino().toString().equals(x)) {
                    p.setTermino(sel);
                }
            }
            try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("src/main/resources/memory/paralelos.ser"));) {
                out.writeObject(paralelos);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        } catch (IOException ex) {
            System.out.println("Nada que editar");
        } catch (ClassNotFoundException e) {
            System.out.println("No se encontró la clase");
        }
    }

    public static void seleccionarTermino(ArrayList<Termino> terminos, Scanner input) {
        System.out.println("<<SELECCIONANDO TÉRMINO>>");
        System.out.println("SELECCIONE EL NÚMERO DEL TÉRMINO PARA EL JUEGO");
        int opcion = input.nextInt() - 1;
        Termino tJuego = terminos.get(opcion); // se obtiene el término seleccionado
        System.out.println("Termino seleccionado:" + "PAO " + tJuego.getNumTermino() + " " + tJuego.getAnio());

    }

    public static void ingresarMateria(ArrayList<Materia> materias, String nombreM, String codigoM, int nivelesM) {
        //Se solicitan los datos para la creacion de la nueva materia

        //Se crea un arrayList de preguntaas vacias que se llenaran en la parte de administrar preguntas.
        ArrayList<Pregunta> preguntas = new ArrayList<>();
        //Se crea la nueva materia
        Materia nuevaMateria = new Materia(codigoM, nombreM, nivelesM, preguntas);
        // se añade la materia a la lista de materias
        materias.add(nuevaMateria);
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("src/main/resources/memory/materias.ser"));) {
            out.writeObject(materias);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static void editarMateria(ArrayList<Materia> materias, Scanner input) {
        System.out.println("<<EDITANDO MATERIA>>");
        //Se pide nombre o codigo de materia
        System.out.print("Ingrese el codigo o el nombre de la materia a editar: ");
        String entrada = input.nextLine();
        for (Materia m : materias) {
            String nMateria = m.getNombre();
            String cMateria = m.getCodigo();
            //Se compara con el nombre y codigo de cada materia con OR
            if ((entrada.equals(nMateria)) || (entrada.equals(cMateria))) {
                //Se pide nuevo nombre y nueva cantidad de niveles
                System.out.print("Ingrese nuevo nombre (ingrese '*' si no desea modificar): ");
                String nuevoNombre = input.nextLine();
                System.out.print("Ingrese nueva cantidad de niveles (ingrese '0' si no desea modificar): ");
                int nuevoNivel = input.nextInt();
                input.nextLine();
                //Se modifican los nombres y niveles
                if (!(nuevoNombre.equals("*"))) {
                    m.setNombre(nuevoNombre);
                }
                if (nuevoNivel != 0) {
                    m.setNiveles(nuevoNivel);
                }
            }
        }
        System.out.println("");
    }

    public static void agregarParalelo(VBox vbParalelos, Materia m, Termino t, ArrayList<Paralelo> paralelos, ArrayList<Estudiante> participantes, int num) {

        Paralelo paraleloGenerado = new Paralelo(participantes, m, t, num);
        //Se añade nuestro paralelo a la lista de paralelos.
        paralelos.add(paraleloGenerado);
        vbParalelos.getChildren().add(new Label(paraleloGenerado.toString()));
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("src/main/resources/memory/paralelos.ser"));) {
            out.writeObject(paralelos);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        System.out.println("<<PARALELO CREADO>>");
    }

    public static void eliminarParalelo(ArrayList<Materia> materias, ArrayList<Paralelo> paralelos, ArrayList<Estudiante> participantes, Scanner input) {
        System.out.println("Seleccione el paralelo que desea eliminar:");
        int n = 0;
        for (Paralelo p : paralelos) {
            System.out.println(n + ". " + p);
            n++;
        }
        System.out.print("Ingrese el numero del paralelo o '*' si desea cancelar: ");
        String seleccion = input.nextLine();
        if (seleccion.equals("*")) {
            System.out.println("<<CANCELANDO>>");
        } else {
            int seleccionN = Integer.parseInt(seleccion);
            paralelos.remove(seleccionN);
            System.out.println("<<ELIMINANDO PARALELO>>");
        }

    }

    public static void visualizarPreguntas(ArrayList<Materia> materias, Scanner input) {
        System.out.println("Seleccione la materia ingresando su código:");
        for (Materia m : materias) { //se itera en la lista de materias para mostrarlas
            System.out.println(m);
        }
        String code = input.nextLine(); // se pide el ingreso de una de las materias mostradas
        for (Materia m : materias) { // si la ingresada coincide con una en la lista, se muestran sus preguntas
            if (code.equals(m.getCodigo())) {
                System.out.println(m.getPreguntas());
            }
        }
    }

    public static void agregarPreguntas(ArrayList<Materia> materias, Materia q) {
        for (Materia m : materias) {
            if (q.getCodigo().equals(m.getCodigo())) { //se verifica que la materia exista
                Stage query = new Stage();
                VBox fields = new VBox();
                TextField enunciado = new TextField("Ingrese el enunciado");
                TextField t = new TextField("Ingrese el literal correcto");
                TextField s = new TextField("Ingrese literal falso 1");
                TextField x = new TextField("Ingrese literal falso 2");
                TextField y = new TextField("Ingrese literal falso 3");
                TextField z = new TextField("Ingrese el nivel de la pregunta (de 1 a " + m.getNiveles() + ")");
                Button save = new Button("Agregar");
                fields.getChildren().addAll(enunciado, t, s, x, y, z, save);
                Stage popup = new Stage();
                Scene layout = new Scene(fields, 250, 200);
                popup.setScene(layout);
                popup.show();
                save.setOnAction(ev -> {
                    int lmao = Integer.parseInt(z.getText());
                    m.setPregunta(new Pregunta(enunciado.getText(), lmao, t.getText(), s.getText(), x.getText(), y.getText()));
                    try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("src/main/resources/memory/materias.ser"));) {
                        out.writeObject(materias);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                    popup.close();
                });
            }
        }

    }

    public static void eliminarPregunta(ArrayList<Materia> materias, Scanner input) {
        System.out.println("Seleccione la materia ingresando su código:");
        String code3 = input.nextLine();
        Materia dummy1 = new Materia(); //instancio objetos materia y pregunta vacíos
        Pregunta dummy2 = new Pregunta();
        for (Materia m : materias) {
            if (code3.equals(m.getCodigo())) {
                dummy1 = m; //guardo la coincidencia en el dummy
                System.out.println("Ingrese el enunciado de la pregunta a eliminar:");
                String del = input.nextLine();
                for (Pregunta p : m.getPreguntas()) {
                    if (del.equals(p.getEnunciado())) {
                        dummy2 = p; // guardo la coincidencia en el dummy
                    }
                }
            }
        }//de no haber hecho el respaldo en dummys, al intentar modificar un arreglo dentro de una iteración se hubiera generado una excepción al ejecutar
        dummy1.removePregunta(dummy2); //ahora puedo eliminar la pregunta del banco de la materia
    }
}

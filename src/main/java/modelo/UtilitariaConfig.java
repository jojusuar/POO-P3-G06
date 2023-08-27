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
import java.util.ArrayList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *Métodos estáticos auxiliares para la funcionalidad de las configuraciones del Juego.
 */
public class UtilitariaConfig implements Serializable {

    /**
     *Método que crea un Término.
     * @param terminos Lista de todo Término registrado.
     * @param x Año del Término a registrar.
     * @param y Período académico del Término a registrar.
     * @param vbTerminos VBox que muestra etiquetas asociadas a los términos registrados.
     */
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

    /**
     *Método que edita un término registrado.
     * @param terminos Lista de todo Término registrado.
     * @param sel Término a editar en la lista.
     * @param x String que representa al Término.
     */
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

    /**
     *Método que crea una Materia.
     * @param materias Lista de toda Materia registrada.
     * @param nombreM Nombre de la Materia.
     * @param codigoM Código de la Materia.
     * @param nivelesM Nivel máximo de las preguntas en la Materia.
     */
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

    /**
     *Método que edita una Materia registrada.
     * @param materias  Lista de toda Materia registrada.
     * @param sel Materia a ser editada.
     * @param x String que representa a la Materia a editar.
     */
    public static void editarMateria(ArrayList<Materia> materias, Materia sel, String x) {
        System.out.println("<<EDITANDO MATERIA>>");
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("src/main/resources/memory/materias.ser"));) {
            out.writeObject(materias);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("src/main/resources/memory/paralelos.ser"));) {
            ArrayList<Paralelo> paralelos = (ArrayList<Paralelo>) in.readObject();
            for (Paralelo p : paralelos) {
                if (p.getMateria().getCodigo().equals(x)) {
                    p.setMateria(sel);
                    try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("src/main/resources/memory/paralelos.ser"));) {
                        out.writeObject(paralelos);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        } catch (IOException ex) {
            System.out.println("Nada que editar");
        } catch (ClassNotFoundException e) {
            System.out.println("No se encontró la clase");
        }

    }

    /**
     *Método que crea un Paralelo.
     * @param vbParalelos VBox que muestra etiquetas asociadas a los Paralelos registrados.
     * @param m Materia asociada al Paralelo.
     * @param t Término del Paralelo.
     * @param paralelos Lista de todo Paralelo registrado.
     * @param participantes Lista de Estudiantes inscritos en el Paralelo.
     * @param num Número del Paralelo.
     */
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

    /**
     *Método que elimina un Paralelo registrado.
     * @param paralelos Lista de todo Paralelo registrado.
     */
    public static void eliminarParalelo(ArrayList<Paralelo> paralelos) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("src/main/resources/memory/paralelos.ser"));) {
            out.writeObject(paralelos);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     *Método que crea una Pregunta y la agrega a la Materia asignada.
     * @param materias Lista de toda Materia registrada.
     * @param q Materia registrada a la cual se agrega la pregunta.
     */
    public static void agregarPreguntas(ArrayList<Materia> materias, Materia q) {
        Stage query = new Stage();
        VBox fields = new VBox();
        TextField enunciado = new TextField("Ingrese el enunciado");
        TextField t = new TextField("Ingrese el literal correcto");
        TextField s = new TextField("Ingrese literal falso 1");
        TextField x = new TextField("Ingrese literal falso 2");
        TextField y = new TextField("Ingrese literal falso 3");
        TextField z = new TextField("Ingrese el nivel de la pregunta (de 1 a " + q.getNiveles() + ")");
        Button save = new Button("Agregar");
        fields.getChildren().addAll(enunciado, t, s, x, y, z, save);
        Stage popup = new Stage();
        Scene layout = new Scene(fields, 250, 200);
        popup.setScene(layout);
        popup.show();
        save.setOnAction(ev -> {
            int lmao = Integer.parseInt(z.getText());
            q.setPregunta(new Pregunta(enunciado.getText(), lmao, t.getText(), s.getText(), x.getText(), y.getText()));
            try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("src/main/resources/memory/materias.ser"));) {
                out.writeObject(materias);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("src/main/resources/memory/paralelos.ser"));) {
                ArrayList<Paralelo> paralelos = (ArrayList<Paralelo>) in.readObject();
                for (Paralelo p : paralelos) {
                    if (p.getMateria().toString().equals(q.toString())) {
                        p.setMateria(q);
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
            popup.close();
        });

    }
}

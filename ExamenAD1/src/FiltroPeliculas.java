import java.io.*;
import java.util.*;

public class FiltroPeliculas {
    private List<String[]> peliculas;

    //Lee las películas del archivo PeliculasCSV
    public FiltroPeliculas(String archivoCSV) {
        peliculas = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(archivoCSV))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                peliculas.add(linea.split(","));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Filtrar las Películas
    public List<String[]> filtrarPorAño(int año) {
        List<String[]> peliculasFiltradas = new ArrayList<>();
        for (String[] pelicula : peliculas) {
            if (Integer.parseInt(pelicula[2]) > año) {
                peliculasFiltradas.add(pelicula);
            }
        }
        return peliculasFiltradas;
    }

    // Crear un nuevo CSV con el año especificado
    public void generarCSVFiltrado(int año) {
        List<String[]> peliculasFiltradas = filtrarPorAño(año);
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("PeliculasPosterioresA" + año + ".csv"))) {
            for (String[] pelicula : peliculasFiltradas) {
                bw.write(String.join(",", pelicula));
                bw.newLine();
            }
            System.out.println("PeliculasPosterioresA" + año + ".csv Creado.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
        //Solicitar el año
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese el año para filtrar las películas: ");
        int año = scanner.nextInt();
        // Crear el archivo filtrado
        FiltroPeliculas filtro = new FiltroPeliculas("peliculas.csv");
        filtro.generarCSVFiltrado(año);
    }
}

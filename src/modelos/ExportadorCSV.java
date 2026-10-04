package modelos;

import modelos.Factura;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

public class ExportadorCSV {
    private static final Locale ES = Locale.forLanguageTag("es-ES");
    private static final DateTimeFormatter FORMATO_FECHA = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public static void exportar(List<Factura> facturas, String ruta) throws IOException {
        try (PrintWriter pw = new PrintWriter(
                new OutputStreamWriter(new FileOutputStream(ruta), StandardCharsets.UTF_8))) {

            pw.print('\uFEFF');   // EXCEL LEE , Y €
            pw.println("Fecha;Cliente;Producto;Cantidad;Total (€)");

            for (Factura f : facturas) {
                pw.println(f.getFecha().format(FORMATO_FECHA) + ";"
                        + limpiar(f.getUsuario().getNombre()) + ";"
                        + limpiar(f.getProducto().getNombre()) + ";"
                        + f.getCantidad() + ";"
                        + String.format(ES, "%.2f", f.getTotal()));
            }
        }
    }

    private static String limpiar(String texto) {
        if (texto.contains(";") || texto.contains("\"")) {
            return "\"" + texto.replace("\"", "\"\"") + "\"";
        }
        return texto;
    }
}
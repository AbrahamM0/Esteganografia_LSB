import com.formdev.flatlaf.FlatDarculaLaf;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class Forma extends JFrame {

    private JPanel panelPrincipal;
    private JRadioButton HIDERadioButton;
    private JRadioButton SHOWRadioButton;
    private JButton PROCESARButton;
    private JButton seleccionarArchivoTxtButton;
    private JButton seleccionarArchivoBmpButton;

    private File archivoTXT;
    private File archivoBMP;

    public Forma() {

        inicializarForma();
        configurarRadioButtons();

        seleccionarArchivoTxtButton.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
                archivoTXT = fileChooser.getSelectedFile();
                JOptionPane.showMessageDialog(this,
                        "Archivo TXT seleccionado:\n" + archivoTXT.getName());
            }
        });


        seleccionarArchivoBmpButton.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
                archivoBMP = fileChooser.getSelectedFile();
                JOptionPane.showMessageDialog(this,
                        "Archivo BMP seleccionado:\n" + archivoBMP.getName());
            }
        });

        PROCESARButton.addActionListener(e -> procesar());
    }

    private void inicializarForma() {
        setContentPane(panelPrincipal);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);
    }

    private void configurarRadioButtons() {
        ButtonGroup grupo = new ButtonGroup();
        grupo.add(HIDERadioButton);
        grupo.add(SHOWRadioButton);
        HIDERadioButton.setSelected(true);
    }

    private void procesar() {

        if (HIDERadioButton.isSelected()) {
            cifrar(archivoBMP, archivoTXT);
            JOptionPane.showMessageDialog(this,
                    "Cifrado completado correctamente.");
        } else if (SHOWRadioButton.isSelected()) {
            descifrar(archivoBMP);
            JOptionPane.showMessageDialog(this,
                    "Descifrado completado correctamente.");
        }
    }


    private void cifrar(File bmp, File txt) {

        try {

            BufferedImage img = ImageIO.read(bmp);
            String contenido = Files.readString(txt.toPath());

            char[] contrasena = new char[contenido.length() + 2];

            contrasena[0] = 219;
            for (int i = 0; i < contenido.length(); i++) {
                contrasena[i + 1] = contenido.charAt(i);
            }
            contrasena[contenido.length() + 1] = 219;

            System.out.println("CONTENIDO:");
            for (char c : contrasena) {
                System.out.println(c);
            }

            int w = img.getWidth();
            int h = img.getHeight();
            int c = 0, b = 0;

            for (int y = 0; y < h && c < contrasena.length; y++) {
                for (int x = 0; x < w && c < contrasena.length; x++) {

                    int rgb = img.getRGB(x, y);
                    int R = (rgb >> 16) & 255;
                    int G = (rgb >> 8) & 255;
                    int B = rgb & 255;

                    int[] col = {R, G, B};

                    for (int i = 0; i < 3 && c < contrasena.length; i++) {

                        int bit = (contrasena[c] & 128) >> 7;
                        col[i] = (col[i] & 254) | bit;
                        contrasena[c] <<= 1;
                        b++;

                        if (b == 8) {
                            b = 0;
                            c++;
                        }
                    }

                    int nuevo = (col[0] << 16) | (col[1] << 8) | col[2];
                    img.setRGB(x, y, nuevo);
                }
            }

            ImageIO.write(img, "bmp", new File("imagen_oculta.bmp"));

        } catch (IOException e) {
            System.out.println("Error al procesar archivos: " + e.getMessage());
        }
    }


    private void descifrar(File bmp) {

        try {
            BufferedImage img = ImageIO.read(bmp);

            int w = img.getWidth();
            int h = img.getHeight();

            StringBuilder mensaje = new StringBuilder();
            int bitCount = 0;
            int caracter = 0;
            boolean leyendo = false;

            for (int y = 0; y < h; y++) {
                for (int x = 0; x < w; x++) {

                    int rgb = img.getRGB(x, y);
                    int R = (rgb >> 16) & 255;
                    int G = (rgb >> 8) & 255;
                    int B = rgb & 255;

                    int[] col = {R, G, B};

                    for (int i = 0; i < 3; i++) {

                        int bit = col[i] & 1; // LSB
                        caracter = (caracter << 1) | bit;
                        bitCount++;

                        if (bitCount == 8) {

                            char c = (char) caracter;

                            if (c == 219) {
                                if (!leyendo) {
                                    leyendo = true; // inicio del mensaje
                                } else {
                                    // fin del mensaje
                                    Files.writeString(
                                            new File("contraseña_descifrada.txt").toPath(),
                                            mensaje.toString()
                                    );
                                    return;
                                }
                            }
                            else if (leyendo) {
                                mensaje.append(c);
                            }

                            bitCount = 0;
                            caracter = 0;
                        }
                    }
                }
            }


        } catch (IOException e) {
            System.out.println("Error al abrir imagen: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        FlatDarculaLaf.setup();
        Forma forma = new Forma();
        forma.setVisible(true);
    }

}
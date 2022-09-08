package simuladormaquinafinita;

import java.awt.Color;

public class SimuladorMaquinaFinita {
    public static void main(String[] args) {
        Programa programa = new Programa();
        programa.getContentPane().setBackground(Color.LIGHT_GRAY);
        programa.setLocationRelativeTo(null);
        programa.setVisible(true);
    }
}

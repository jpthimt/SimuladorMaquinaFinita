/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simuladormaquinafinita;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import javax.swing.JOptionPane;

/**
 *
 * @author Master
 */
public class Programa extends javax.swing.JFrame {

    public int n;
    public int l;
    public int u;
    public double num;

    public Programa() {
        initComponents();
    }

    public void binario(double num) {
        int partInt = (int) num;
        double partFrac = Math.abs(num - (double) ((int) num));
        String binFrac = "";
        String aux = Integer.toBinaryString(Math.abs(partInt));
        String binInt = "0" + aux;

        while (partFrac != 0) {
            if (partFrac * 2.0 >= 1.0) {
                binFrac = binFrac + "1";
                partFrac = partFrac * 2.0 - 1.0;
            } else {
                binFrac = binFrac + "0";
                partFrac *= 2.0;
            }
        }

        String bin = binInt + binFrac;
        int i;
        char c;
        int pos;
        String var10000;
        if (num < 0.0) {
            for (i = 0; i < bin.length(); ++i) {
                c = bin.charAt(i);
                if (c == '0') {
                    var10000 = bin.substring(0, i);
                    bin = var10000 + "1" + bin.substring(i + 1);
                } else {
                    var10000 = bin.substring(0, i);
                    bin = var10000 + "0" + bin.substring(i + 1);
                }
            }

            pos = -1;
            i = bin.length() - 1;

            while (pos != 0) {
                c = bin.charAt(i);
                if (c == '0') {
                    var10000 = bin.substring(0, i);
                    bin = var10000 + "1" + bin.substring(i + 1);
                    pos = 0;
                } else {
                    var10000 = bin.substring(0, i);
                    bin = var10000 + "0" + bin.substring(i + 1);
                    --i;
                }
            }
        } else if (num == 0) {
            binInt = "0";
            binFrac = "0";
            bin = "00";
        }

        pos = binInt.length();
        binInt = bin.substring(0, pos);
        binFrac = bin.substring(pos);
        String auxIntFrac = "";
        String auxInt;
        if (binInt.length() > 1) {
            auxIntFrac = binInt.substring(1);
            auxInt = binInt.substring(0, 1);
        } else {
            auxInt = binInt.substring(0, 1);
        }

        if (auxInt.equals("1")) {
            auxIntFrac = "1" + auxIntFrac;
            auxInt = "0";
        }

        String auxFrac = auxIntFrac + binFrac;
        int posAux = auxIntFrac.length();
        String binAuxFrac = auxFrac;
        if (auxFrac.length() > 1) {
            while (auxFrac.charAt(0) == '0') {
                auxFrac = auxFrac.substring(1);
                --posAux;
            }
        }

        String erro = "";
        if (auxFrac.length() >= this.n) {
            String auxFrac2 = auxFrac.substring(this.n);
            auxFrac = auxFrac.substring(0, this.n);
            int cont = 0;

            for (i = 1; i < auxFrac2.length(); ++i) {
                if (auxFrac2.charAt(i) == '1') {
                    ++cont;
                }
            }

            if (auxFrac2.charAt(0) == '1' && cont > 0) {
                cont = 0;
                int aux3 = this.n - 1;
                if (auxFrac.length() < aux3) {
                    aux3 = auxFrac.length();
                }

                label169:
                while (true) {
                    while (true) {
                        if (cont != 0) {
                            break label169;
                        }

                        if (auxFrac.charAt(aux3) == '1' && auxFrac.length() <= aux3) {
                            var10000 = auxFrac.substring(0, aux3);
                            auxFrac = var10000 + "0" + auxFrac.substring(aux3 + 1);
                            --aux3;
                        } else {
                            var10000 = auxFrac.substring(0, aux3);
                            auxFrac = var10000 + "1" + auxFrac.substring(aux3 + 1);
                            ++cont;
                        }
                    }
                }
            }

            erro = erro + "Arredondamento; ";
        }

        if (posAux < this.l) {
            erro = erro + "Underflow; ";
        } else if (posAux > this.u) {
            erro = erro + "Overflow; ";
        }

        double decimal = 0.0;
        if (num < 0.0) {
            String bin2 = auxFrac;

            for (i = 0; i < bin2.length(); ++i) {
                c = bin2.charAt(i);
                if (c == '0') {
                    var10000 = bin2.substring(0, i);
                    bin2 = var10000 + "1" + bin2.substring(i + 1);
                } else {
                    var10000 = bin2.substring(0, i);
                    bin2 = var10000 + "0" + bin2.substring(i + 1);
                }
            }

            int aux2 = -1;
            i = bin2.length() - 1;

            while (aux2 != 0) {
                c = bin2.charAt(i);
                if (c == '0') {
                    var10000 = bin2.substring(0, i);
                    bin2 = var10000 + "1" + bin2.substring(i + 1);
                    aux2 = 0;
                } else {
                    var10000 = bin2.substring(0, i);
                    bin2 = var10000 + "0" + bin2.substring(i + 1);
                    --i;
                }
            }

            int posAux2 = posAux;
            if (bin2.length() < posAux) {
                posAux2 = bin2.length();
            }

            binInt = bin2.substring(0, posAux2);
            binFrac = bin2.substring(posAux2);

            for (i = binInt.length() - 1; i >= 0; --i) {
                if (binInt.charAt(i) == '1') {
                    decimal += Math.pow(2.0, (double) (binInt.length() - i - 1));
                }
            }

            for (i = 0; i <= binFrac.length() - 1; ++i) {
                if (binFrac.charAt(i) == '1') {
                    decimal += Math.pow(2.0, (double) (-i - 1));
                }
            }

            decimal *= -1.0;
        } else {
            int posAux2 = posAux;
            if (posAux2 > this.n) {
                posAux2 = this.n;
            }
            String auxFrac2 = auxFrac;
            if (posAux2 < 0) {
                for (i = 0; i < Math.abs(posAux2); i++) {
                    auxFrac2 = "0" + auxFrac2;
                }
                posAux2 = 0;
            }
            if (posAux < 0) {
                binInt = auxFrac2.substring(0, posAux2);
                binFrac = auxFrac2.substring(posAux2);
            } else {
                binInt = auxFrac.substring(0, posAux2);
                binFrac = auxFrac.substring(posAux2);
            }

            for (i = binInt.length() - 1; i >= 0; --i) {
                if (binInt.charAt(i) == '1') {
                    decimal += Math.pow(2.0, (double) (binInt.length() - i - 1));
                }
            }

            for (i = 0; i < binFrac.length(); i++) {
                if (binFrac.charAt(i) == '1') {
                    decimal += Math.pow(2.0, (double) (-i - 1));
                }
            }
        }
        
        JOptionPane.showMessageDialog(null, "-> n: " + this.n + "  |  l: " + this.l + "  |  u: " + this.u 
                + " <-\nNumero digitado: " + num 
                + "\nBinario: " + bin.substring(0, pos) + "." + bin.substring(pos) 
                + "\nNormatizado: " + auxInt + "." + auxFrac + " x 2^" + posAux 
                + "\nErro: " + erro 
                + "\nDecimal: " + decimal);
        // "\nNormatizado 1: " + auxInt + "." + binAuxFrac + " x 2^" + posAux + 
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        configBT = new javax.swing.JButton();
        numeroBT = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        configBT.setBackground(new java.awt.Color(153, 153, 153));
        configBT.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        configBT.setText("CONFIGURAR");
        configBT.setToolTipText("");
        configBT.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        configBT.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        configBT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                configBTActionPerformed(evt);
            }
        });

        numeroBT.setBackground(new java.awt.Color(153, 153, 153));
        numeroBT.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        numeroBT.setText("INSERIR NUMERO");
        numeroBT.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        numeroBT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                numeroBTActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(configBT, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(numeroBT, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 155, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addComponent(configBT, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41)
                .addComponent(numeroBT, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(57, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void configBTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_configBTActionPerformed
        int aux;
        while (true) {
            try {
                do {
                    this.n = Integer.parseInt(JOptionPane.showInputDialog("Insira a precisão da mantissa (n)"));
                    this.l = Integer.parseInt(JOptionPane.showInputDialog("Insira o menor valor do expoente (l)"));
                    this.u = Integer.parseInt(JOptionPane.showInputDialog("Insira o maior valor do expoente (u)"));
                    if (l > u) {
                        JOptionPane.showMessageDialog(null, "Menor expoente maior que o maior expoente.");
                        JOptionPane.showMessageDialog(null, "Insira novamente.");
                        aux = 1;
                    }else{
                        aux=0;
                    }
                } while (aux > 0);
                break;
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "ERRO! Digite um número!");
            }
        }
    }//GEN-LAST:event_configBTActionPerformed

    private void numeroBTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_numeroBTActionPerformed
        while (true) {
            try {

                this.num = Double.parseDouble(JOptionPane.showInputDialog("Insira o valor a ser convertido"));
                this.binario(this.num);
                break;
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "ERRO! Digite um nÃºmero!");
                continue;
            }
        }
    }//GEN-LAST:event_numeroBTActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Programa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Programa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Programa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Programa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Programa().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton configBT;
    private javax.swing.JButton numeroBT;
    // End of variables declaration//GEN-END:variables
}

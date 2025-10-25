
    package com.mycompany.ejercicio11;
    import java.awt.event.*;
    import javax.swing.*;
    import java.awt.*;



public class Ejercicio11 extends JFrame implements ActionListener {

    // Atributos
    private JTextField entradaValores;
    private JButton[] botones;
    private String[] operaciones = {"+", "-", "*", "/"};
    private double primerNumero = 0;
    private String operador = "";
    JPanel panel = new JPanel();

    // Constructor
    Ejercicio11() {
        // Configurar el frame
        setTitle("Calculadora");
        setSize(300, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Campo de texto
        entradaValores = new JTextField();
        entradaValores.setEditable(false);
        entradaValores.setHorizontalAlignment(JTextField.RIGHT);
        add(entradaValores, BorderLayout.NORTH);

        setVisible(true);
        panel.setLayout(new GridLayout(5,4,5,5));
          
//crear los botones de numero y operaciones 
   String[] textButton = {
    "C", "7", "8", "9", "/",
    "4", "5", "6", "*",
    "1", "2", "3", "-",
    "0", ".", "=", "+"
};
    botones = new JButton[textButton.length];
    for (int i = 0; i < textButton.length; i++) {
    botones[i] = new JButton(textButton[i]);
    botones[i].addActionListener(this);
    panel.add(botones[i]);
    if (textButton[i].matches("[0-9\\.]")) {
                botones[i].setBackground(new Color(200, 200, 200)); // gris claro para números
                botones[i].setForeground(Color.BLACK);
            } else if (textButton[i].equals("=")) {
                botones[i].setBackground(new Color(0, 120, 255)); // azul para "="
                botones[i].setForeground(Color.WHITE);
            } else {
                botones[i].setBackground(new Color(255, 140, 0)); // naranja para operaciones
                botones[i].setForeground(Color.WHITE);
            }
}
    
    add(panel, BorderLayout.CENTER);
    setVisible(true);
    
    
        
    }

    // Implementación del método abstracto
@Override
public void actionPerformed(ActionEvent e) {
    String comando = e.getActionCommand();

    // Si es número
    if (comando.charAt(0) >= '0' && comando.charAt(0) <= '9') {
        entradaValores.setText(entradaValores.getText() + comando);
    } 
    // Si es "C", limpia
    else if (comando.equals("C")) {
        entradaValores.setText("");
        operador = "";
    } 
    // Si es una operación (+ - * /)
    else if (comando.equals("+") || comando.equals("-") || 
             comando.equals("*") || comando.equals("/")) {
        primerNumero = Double.parseDouble(entradaValores.getText());
        operador = comando;
        entradaValores.setText("");
    } 
    // Si es "=" realiza la operación
    else if (comando.equals("=")) {
        double segundoNumero = Double.parseDouble(entradaValores.getText());
        double resultado = 0;

        switch (operador) {
            case "+": resultado = primerNumero + segundoNumero; break;
            case "-": resultado = primerNumero - segundoNumero; break;
            case "*": resultado = primerNumero * segundoNumero; break;
            case "/": 
                if (segundoNumero != 0) resultado = primerNumero / segundoNumero;
                else {
                    JOptionPane.showMessageDialog(this, "Error: Division entre 0");
                    return;
                }
                break;
        }

        entradaValores.setText(String.valueOf(resultado));
        operador = "";
    }
}


    public static void main(String[] args) {
        new Ejercicio11(); // Mostrar ventana
    }
}

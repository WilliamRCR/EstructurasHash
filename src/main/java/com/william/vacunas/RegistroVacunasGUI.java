/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.william.vacunas;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author William
 */
public class RegistroVacunasGUI extends JFrame implements ActionListener {
    private HashMap<String, RegistroVacunas> registroVacunas;
    private JTextField cuiField;
    private JTextArea outputArea;

    public RegistroVacunasGUI() {
        super("Registro de Vacunas");
        registroVacunas = new HashMap<>();
        loadFromFile("registros.txt");

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new FlowLayout());

        JLabel cuiLabel = new JLabel("CUI:");
        cuiField = new JTextField(10);
        JButton buscarButton = new JButton("Buscar");
        buscarButton.addActionListener(this);

        JButton agregarButton = new JButton("Agregar Registro");
        agregarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                agregarRegistro();
            }
        });

        inputPanel.add(cuiLabel);
        inputPanel.add(cuiField);
        inputPanel.add(buscarButton);
        inputPanel.add(agregarButton);

        outputArea = new JTextArea(20, 30);
        outputArea.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(outputArea);

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(inputPanel, BorderLayout.NORTH);
        getContentPane().add(scrollPane, BorderLayout.CENTER);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 400);
        setVisible(true);
    }

    private void loadFromFile(String fileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                String cui = parts[0].trim();
                String nombreVacuna = parts[1].trim();
                String fechaAplicacion = parts[2].trim();
                registroVacunas.put(cui, new RegistroVacunas(nombreVacuna, fechaAplicacion));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void saveToFile(String fileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (String cui : registroVacunas.keySet()) {
                RegistroVacunas registro = registroVacunas.get(cui);
                writer.write(cui + ", " + registro.getNombreVacuna() + ", " + registro.getFechaAplicacion() + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void agregarRegistro() {
        String cui = cuiField.getText().trim();
        String nombreVacuna = JOptionPane.showInputDialog("Ingrese el nombre de la vacuna:");
        String fechaAplicacion = JOptionPane.showInputDialog("Ingrese la fecha de aplicación (YYYY-MM-DD):");

        RegistroVacunas registro = new RegistroVacunas(nombreVacuna, fechaAplicacion);
        registroVacunas.put(cui, registro);
        saveToFile("registros.txt");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String cui = cuiField.getText().trim();
        if (registroVacunas.containsKey(cui)) {
            RegistroVacunas registro = registroVacunas.get(cui);
            outputArea.setText(registro.toString());
        } else {
            outputArea.setText("No existe la persona con CUI: " + cui);
        }
    }
    
}

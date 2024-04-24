package GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SimulationFrame extends JFrame implements ActionListener {

    public JLabel numberOfQueues;
    public JLabel numberOfClients;
    public JLabel minArrivalTime;
    public JLabel maxArrivalTime;
    public JLabel minServiceTime;
    public JLabel maxServiceTime;

    public JLabel simulationTime;
    public JLabel sleepTime;
    static public JTextArea simulation;
    public JScrollPane bar;
    public JButton start;
    static public int starting = 0;

    public JTextField numberOfQueuesField, numberOfClientsField, minArrivalTimeField, maxArrivalTimeField, minServiceTimeField, maxServiceTimeField, simulationTimeField, sleepTimeField;
    static public int queuesNr, clientNr, minArr, maxArr, minSer, maxSer, sim, slep;
    static public JComboBox<String> checkBox;
    public SimulationFrame(){

        String[] p = {"shortest queue", "time "};
        checkBox = new JComboBox<>(p);
        checkBox.setBounds(400,400,150,50);
        add(checkBox);

        start = new JButton("Start");
        start.setBounds(600,400,150,70);
        start.addActionListener(this);
        add(start);

        simulation = new JTextArea("");
        bar = new JScrollPane(simulation);
        simulation.setBounds(450,0,400,400);
        bar.setBounds(460,0,400,400);
        bar.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        simulation.setWrapStyleWord(true);
        simulation.setLineWrap(true);
        simulation.setVisible(true);
        simulation.setEditable(false);
        bar.setVisible(true);
        add(bar);

        numberOfQueues = new JLabel("Number Of Queues : ");
        numberOfQueues.setBounds(50,50,200,50);
        add(numberOfQueues);

        numberOfQueuesField = new JTextField();
        numberOfQueuesField.setBounds(200,50,100,50);
        add(numberOfQueuesField);

        numberOfClients = new JLabel("Number Of Clients : ");
        numberOfClients.setBounds(50,100,200,50);
        add(numberOfClients);

        numberOfClientsField = new JTextField();
        numberOfClientsField.setBounds(200,100,100,50);
        add(numberOfClientsField);

        minArrivalTime = new JLabel("Min arrival time : ");
        minArrivalTime.setBounds(50,150,200,50);
        add(minArrivalTime);

        minArrivalTimeField = new JTextField();
        minArrivalTimeField.setBounds(200,150,100,50);
        add(minArrivalTimeField);

        maxArrivalTime = new JLabel("Max arrival time : ");
        maxArrivalTime.setBounds(50,200,200,50);
        add(maxArrivalTime);

        maxArrivalTimeField = new JTextField();
        maxArrivalTimeField.setBounds(200,200,100,50);
        add(maxArrivalTimeField);

        minServiceTime = new JLabel("Min service time : ");
        minServiceTime.setBounds(50,250,200,50);
        add(minServiceTime);

        minServiceTimeField = new JTextField();
        minServiceTimeField.setBounds(200,250,100,50);
        add(minServiceTimeField);

        maxServiceTime = new JLabel("Max service time : ");
        maxServiceTime.setBounds(50,300,200,50);
        add(maxServiceTime);

        maxServiceTimeField = new JTextField();
        maxServiceTimeField.setBounds(200,300,100,50);
        add(maxServiceTimeField);

        simulationTime = new JLabel("Simulation time : ");
        simulationTime.setBounds(50,350,200,50);
        add(simulationTime);

        simulationTimeField = new JTextField();
        simulationTimeField.setBounds(200,350,100,50);
        add(simulationTimeField);

        sleepTime = new JLabel("Sleep time : ");
        sleepTime.setBounds(50,400,200,50);
        add(sleepTime);

        sleepTimeField = new JTextField();
        sleepTimeField.setBounds(200,400,100,50);
        add(sleepTimeField);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000,500);
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == start){
            queuesNr = Integer.parseInt(numberOfQueuesField.getText());
            clientNr = Integer.parseInt(numberOfClientsField.getText());
            minSer = Integer.parseInt(minServiceTimeField.getText());
            maxSer = Integer.parseInt(maxServiceTimeField.getText());
            minArr = Integer.parseInt(minArrivalTimeField.getText());
            maxArr = Integer.parseInt(maxArrivalTimeField.getText());
            slep = Integer.parseInt(sleepTimeField.getText());
            sim = Integer.parseInt(simulationTimeField.getText());
            starting = 1;
        }
    }
}

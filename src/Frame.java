import javax.swing.*;
import javax.swing.text.NumberFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Frame extends JFrame implements ActionListener {

    private JPanel createPanel, editPanel;
    private JButton btnAddNode, btnSubmitCreate, btnSearchShortestWay, btnAddBranch, btnInfo;
    private JTextField nodeCreateInput, branchNode1Input, branchNode2Input, pathNode1Input, pathNode2Input;
    private JFormattedTextField amountNodesInput, branchWeightInput;

    private Graph graph;

    public Frame(Graph graph) throws HeadlessException {
        super();

        this.graph = graph;

        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        createPanel = new JPanel();
        createPanel.setBackground(Color.WHITE);
        createPanel.setBounds(0, 0, 800, 600);
        createPanel.setLayout(null);

        NumberFormatter formatter = new NumberFormatter();
        formatter.setAllowsInvalid(false);
        formatter.setMaximum(1000);

        JLabel lblAmountText = new JLabel("Amount of citys:");
        lblAmountText.setSize(new Dimension(600, 40));
        lblAmountText.setLocation(new Point(100, 200));
        createPanel.add(lblAmountText);

        amountNodesInput = new JFormattedTextField(formatter);
        amountNodesInput.setSize(new Dimension(600, 40));
        amountNodesInput.setLocation(new Point(100, 240));
        amountNodesInput.setBackground(Color.WHITE);
        createPanel.add(amountNodesInput);

        btnSubmitCreate = new JButton();
        btnSubmitCreate.setText("Submit");
        btnSubmitCreate.addActionListener(this);
        btnSubmitCreate.setSize(new Dimension(600, 40));
        btnSubmitCreate.setLocation(new Point(100, 320));
        createPanel.add(btnSubmitCreate);

        add(createPanel);

        editPanel = new JPanel();
        editPanel.setBackground(Color.WHITE);
        editPanel.setBounds(0, 0, 800, 600);
        editPanel.setLayout(null);

        int offsetY = -200;

        JLabel lblAddNode = new JLabel("Add city:");
        lblAddNode.setSize(new Dimension(400, 40));
        lblAddNode.setLocation(new Point(100, 200 + offsetY));
        editPanel.add(lblAddNode);

        nodeCreateInput = new JTextField();
        nodeCreateInput.setSize(new Dimension(400, 40));
        nodeCreateInput.setLocation(new Point(100, 240 + offsetY));
        nodeCreateInput.setBackground(Color.WHITE);
        editPanel.add(nodeCreateInput);

        btnAddNode = new JButton();
        btnAddNode.setText("Add Node");
        btnAddNode.addActionListener(this);
        btnAddNode.setSize(new Dimension(150, 40));
        btnAddNode.setLocation(new Point(550, 240 + offsetY));
        editPanel.add(btnAddNode);


        JLabel lblAddBranchNode1 = new JLabel("City1:");
        lblAddBranchNode1.setSize(new Dimension(100, 40));
        lblAddBranchNode1.setLocation(new Point(100, 300 + offsetY));
        editPanel.add(lblAddBranchNode1);

        branchNode1Input = new JTextField();
        branchNode1Input.setSize(new Dimension(100, 40));
        branchNode1Input.setLocation(new Point(100, 340 + offsetY));
        branchNode1Input.setBackground(Color.WHITE);
        editPanel.add(branchNode1Input);

        JLabel lblAddBranchNode2 = new JLabel("City2:");
        lblAddBranchNode2.setSize(new Dimension(100, 40));
        lblAddBranchNode2.setLocation(new Point(250, 300 + offsetY));
        editPanel.add(lblAddBranchNode2);

        branchNode2Input = new JTextField();
        branchNode2Input.setSize(new Dimension(100, 40));
        branchNode2Input.setLocation(new Point(250, 340 + offsetY));
        branchNode2Input.setBackground(Color.WHITE);
        editPanel.add(branchNode2Input);

        JLabel lblAddBranchWeight = new JLabel("Weight:");
        lblAddBranchWeight.setSize(new Dimension(600, 40));
        lblAddBranchWeight.setLocation(new Point(400, 300 + offsetY));
        editPanel.add(lblAddBranchWeight);

        formatter.setMaximum(1000000);
        branchWeightInput = new JFormattedTextField(formatter);
        branchWeightInput.setSize(new Dimension(100, 40));
        branchWeightInput.setLocation(new Point(400, 340 + offsetY));
        branchWeightInput.setBackground(Color.WHITE);
        editPanel.add(branchWeightInput);

        btnAddBranch = new JButton();
        btnAddBranch.setText("Add Path");
        btnAddBranch.addActionListener(this);
        btnAddBranch.setSize(new Dimension(150, 40));
        btnAddBranch.setLocation(new Point(550, 340 + offsetY));
        editPanel.add(btnAddBranch);


        JLabel lblPathNode1 = new JLabel("City1:");
        lblPathNode1.setSize(new Dimension(175, 40));
        lblPathNode1.setLocation(new Point(100, 400 + offsetY));
        editPanel.add(lblPathNode1);

        pathNode1Input = new JTextField();
        pathNode1Input.setSize(new Dimension(175, 40));
        pathNode1Input.setLocation(new Point(100, 440 + offsetY));
        pathNode1Input.setBackground(Color.WHITE);
        editPanel.add(pathNode1Input);

        JLabel lblPathNode2 = new JLabel("City2:");
        lblPathNode2.setSize(new Dimension(175, 40));
        lblPathNode2.setLocation(new Point(325, 400 + offsetY));
        editPanel.add(lblPathNode2);

        pathNode2Input = new JTextField();
        pathNode2Input.setSize(new Dimension(175, 40));
        pathNode2Input.setLocation(new Point(325, 440 + offsetY));
        pathNode2Input.setBackground(Color.WHITE);
        editPanel.add(pathNode2Input);

        btnSearchShortestWay = new JButton();
        btnSearchShortestWay.setText("Shortest Path");
        btnSearchShortestWay.addActionListener(this);
        btnSearchShortestWay.setSize(new Dimension(150, 40));
        btnSearchShortestWay.setLocation(new Point(550, 440 + offsetY));
        editPanel.add(btnSearchShortestWay);

        btnInfo = new JButton();
        btnInfo.setText("Info");
        btnInfo.addActionListener(this);
        btnInfo.setSize(new Dimension(600, 40));
        btnInfo.setLocation(new Point(100, 640 + offsetY));
        editPanel.add(btnInfo);


        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == btnSubmitCreate) {
            int nodeAmount = Integer.valueOf(amountNodesInput.getText());
            if (nodeAmount > 0) {
                graph.init(nodeAmount);
                this.remove(createPanel);
                this.add(editPanel);
                this.repaint();
                System.out.println("Switched to edit panel");
            } else {
                System.out.println("Bitte gib eine Zahl an, die größer als 0 ist.");
            }
        } else if (e.getSource() == btnAddNode) {
            String nodeName = nodeCreateInput.getText();
            if (!nodeName.isBlank()) {
                graph.addNode(nodeName);
                nodeCreateInput.setText(null);
                System.out.println("Added Node " + nodeName);
            } else {
                System.out.println("Bitte gib einen Wert an.");
            }
        } else if (e.getSource() == btnAddBranch) {
            String node1Name = branchNode1Input.getText();
            String node2Name = branchNode2Input.getText();
            String branchWeightText = branchWeightInput.getText();
            if (!node1Name.isBlank() && !node2Name.isBlank() && !branchWeightText.isBlank()) {
                int weight = Integer.valueOf(branchWeightText);
                graph.addPath(node1Name, node2Name, weight);
                branchNode1Input.setText(null);
                branchNode2Input.setText(null);
                System.out.println("Added Path : " + node1Name + " - " + node2Name + " : " + weight);
            } else {
                System.out.println("Bitte gib einen Wert an.");
            }
        } else if (e.getSource() == btnSearchShortestWay) {
            String node1Name = pathNode1Input.getText();
            String node2Name = pathNode2Input.getText();
            int weight = Integer.valueOf(branchWeightInput.getText());
            if (!node1Name.isBlank() && !node2Name.isBlank()) {
                int length = graph.searchShortestWay(node1Name, node2Name);
                JOptionPane.showMessageDialog(this, "Shortest way: " + length + "m");
                pathNode1Input.setText(null);
                pathNode2Input.setText(null);
                System.out.println("Searching Path : " + node1Name + " - " + node2Name);
            } else {
                System.out.println("Bitte gib einen Wert an.");
            }
        } else if (e.getSource() == btnInfo) {
            new InfoFrame(graph.toString());
        }
    }
}

import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

public class vendasVIEW extends javax.swing.JFrame {

    private javax.swing.JTable listaVendas;
    private javax.swing.JButton btnVoltar;
    private javax.swing.JLabel jLabel1;

    public vendasVIEW() {
        initComponents();
        listarProdutosVendidos();
    }

    private void initComponents() {
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Produtos Vendidos");

        jLabel1 = new javax.swing.JLabel("Produtos Vendidos");
        jLabel1.setFont(new java.awt.Font("Lucida Fax", 0, 18));

        listaVendas = new javax.swing.JTable();
        listaVendas.setModel(new DefaultTableModel(
            new Object[][]{},
            new String[]{"ID", "Nome", "Valor", "Status"}
        ));
        javax.swing.JScrollPane scroll = new javax.swing.JScrollPane(listaVendas);

        btnVoltar = new javax.swing.JButton("Voltar");
        btnVoltar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dispose();
            }
        });

        javax.swing.JPanel panel = new javax.swing.JPanel();
        panel.setLayout(new java.awt.BorderLayout(10, 10));
        panel.setBorder(javax.swing.BorderFactory.createEmptyBorder(15, 15, 15, 15));
        panel.add(jLabel1, java.awt.BorderLayout.NORTH);
        panel.add(scroll, java.awt.BorderLayout.CENTER);
        panel.add(btnVoltar, java.awt.BorderLayout.SOUTH);

        getContentPane().add(panel);
        setSize(450, 350);
        setLocationRelativeTo(null);
    }

    private void listarProdutosVendidos(){
        try {
            ProdutosDAO produtosdao = new ProdutosDAO();

            DefaultTableModel model = (DefaultTableModel) listaVendas.getModel();
            model.setNumRows(0);

            ArrayList<ProdutosDTO> listagem = produtosdao.listarProdutosVendidos();

            for (int i = 0; i < listagem.size(); i++){
                model.addRow(new Object[]{
                    listagem.get(i).getId(),
                    listagem.get(i).getNome(),
                    listagem.get(i).getValor(),
                    listagem.get(i).getStatus()
                });
            }
        } catch (Exception e) {
            javax.swing.JOptionPane.showMessageDialog(null, "Erro ao carregar vendas: " + e.getMessage());
        }
    }
}
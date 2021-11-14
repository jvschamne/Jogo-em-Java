package jogotecprog;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Controle extends JPanel {

    private Menu m1;
    protected String nome1, nome2;
    private int fase;
    private int vidaJ1;
    private int vidaJ2;

    public Controle() {
        criaMenu();/*cria um novo menu*/
    }

    public void comecaJogo() {
        JFrame janela = new JFrame();//criando um novo frame

        /*Criando os jlabels para mostrar o nome e vida dos jogadores*/
        JLabel label = new JLabel(nome1 + ": ");
        JLabel label2 = new JLabel(vidaJ1 + "");
        JLabel label3 = new JLabel("         " + nome2 + ": ");
        JLabel label4 = new JLabel(vidaJ2 + "");
        JPanel panel = new JPanel();

        panel.add(label);
        panel.add(label2);
        panel.add(label3);
        panel.add(label4);

        janela.getContentPane().add(BorderLayout.NORTH, panel);

        Fase f1 = new Fase(label2, label4, nome1, nome2, fase, vidaJ1, vidaJ2);
        janela.add(f1);

        janela.setSize(2048, 728);
        janela.setResizable(false);
        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        janela.setVisible(true);
        janela.setLocationRelativeTo(null);
    }

    public void setVidaJogadores(int v1, int v2) {
        vidaJ1 = v1;
        vidaJ2 = v2;
    }

    public void criaMenu() {
        m1 = new Menu();//novo objeto menu
        m1.setVisible(true);//deixa visivel
        m1.enviaPalavras(this);//enviando esse objeto controle para o objeto menu
    }

    public void setNomeDuo(String nome1, String nome2, int fase, int vidaJ1, int vidaJ2) {
        /*colocando as informacoes necessarias para inicializar a fase*/
        this.nome1 = nome1;
        this.nome2 = nome2;
        this.fase = fase;
        this.vidaJ1 = vidaJ1;
        this.vidaJ2 = vidaJ2;
    }

}

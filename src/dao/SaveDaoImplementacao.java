package dao;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SaveDaoImplementacao implements SaveDao {

    private Save save;

    @Override
    public void salvar(String arquivo) {
        /*salvando os dados no arquivo*/
        try {
            FileWriter fw = new FileWriter(arquivo);
            PrintWriter pw = new PrintWriter(fw);
            pw.println(save.getNomeJogador1());
            pw.println(save.getNomeJogador2());
            pw.println(save.getVida1());
            pw.println(save.getVida2());
            pw.println(save.getFase());
            pw.close();
            fw.close();

        } catch (IOException ex) {
            Logger.getLogger(SaveDaoImplementacao.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void preencheDados(String arquivo, Save save, String nomeJ1, String nomeJ2, int fase, int vidaJ1, int vidaJ2) {
        /*preenchendo os dados no objeto save*/
        this.save = save;
        save.setNomeJogador1(nomeJ1);
        save.setNomeJogador2(nomeJ2);
        save.setVida1(vidaJ1 + "");
        save.setVida2(vidaJ2 + "");
        save.setFase(fase + "");
        salvar(arquivo);
    }

    @Override
    public String[] carregarDados(String arquivo) {
        String linhas[] = new String[5];
        Scanner in = null;

        int contador = 0;
        try {
            in = new Scanner(new FileReader(arquivo));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(SaveDaoImplementacao.class.getName()).log(Level.SEVERE, null, ex);
        }

        while (in.hasNextLine()) {
            String linha = in.nextLine();
            linhas[contador] = linha;
            contador++;
        }

        return linhas;
    }

    @Override
    public void preencheDadosRecorde(String arquivo, Save save, int vidaJ, String nomeJ) {
        this.save = save;
        save.setNomeJogador1(nomeJ);
        save.setVida1(vidaJ + "");
        salvarRecorde(arquivo);
    }

    @Override
    public String[] carregarRecordes(String arquivo) {
        String linhas[] = new String[100];
        Scanner in = null;

        int contador = 0;
        try {
            in = new Scanner(new FileReader(arquivo));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(SaveDaoImplementacao.class.getName()).log(Level.SEVERE, null, ex);
        }

        String organizado = "";

        while (contador < 100) {
            if (in.hasNextLine()) {
                String vida = in.nextLine();
                String nome = in.nextLine();
                organizado = nome + ": " + vida + " pts de vida";
                linhas[contador] = organizado;
            }
            contador++;
        }

        return linhas;
    }

    public void salvarRecorde(String arquivo) {
        try {
            FileWriter fw = new FileWriter(arquivo, true);
            PrintWriter pw = new PrintWriter(fw);
            pw.println(save.getVida1());
            pw.println(save.getNomeJogador1());
            pw.close();
            fw.close();

        } catch (IOException ex) {
            Logger.getLogger(SaveDaoImplementacao.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}

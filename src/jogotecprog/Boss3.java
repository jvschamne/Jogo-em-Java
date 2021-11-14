package jogotecprog;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;

public class Boss3 extends Personagem {

    private boolean isVisivel;
    private int flagSlow = 0;
    private int segueJogador;
    private List<PoderBoss3> poder;
    private PoderBoss3 pod;

    public Boss3() {
        vida = 600;
        this.x = 1000;
        this.y = 300;
        altura = 50;
        largura = 50;
        isVisivel = true;
        poder = new ArrayList<PoderBoss3>();
        pod = new PoderBoss3(x, y);
        dano = pod.getDano();
        dx = 1;
        dy = 1;
        this.load();
        direcao = 1;//para baixo
    }

    public void load() {
        ImageIcon referencia = new ImageIcon("imagens\\ThanosParado.png");
        referencia.setImage(referencia.getImage().getScaledInstance(300, 300, 100));
        imagem = referencia.getImage();

        this.largura = imagem.getWidth(null);
        this.altura = imagem.getHeight(null);

    }

    public boolean taVisivel() {
        return isVisivel;
    }

    public void mover(Jogador jogador1, Jogador2 jogador2) {

        //sera decicido aleatoriamente se o thanos fará um ataque direto aos jogadores
        int ataque;
        
        /*a movimentacao do Boss3 é diferente dos demais
        ele fica indo para cima e para baixo atirando seu poder e de modo aletorio, 
        de vez algumas vezes ele parte na direcao dos jogadores para um ataque 
        direto*/
        
        if (direcao != 3 && direcao != 4) {

            //ATACA COM PODER A DISTANCIA
            ataque = 1 + (int) (Math.random() * 200);
            if (ataque == 3) {
                usaPoder();
            }

            if (direcao == 1) {
                y -= 1;

                if (getY() <= 100) {
                    direcao = 2;
                    ataque = 1 + (int) (Math.random() * 4);//numero de 1 a 4
                    if (ataque == 3) {
                        direcao = 3;//ele sairam correndo em direcao dos jogadores
                    }
                }
            } else {
                y += 1;

                if (getY() >= 400) {
                    direcao = 1;
                    ataque = 1 + (int) (Math.random() * 4);//numero de 1 a 4
                    if (ataque == 3) {
                        direcao = 3;//ele sairam correndo em direcao dos jogadores
                    }
                }
            }
        } else {
            if (direcao == 3) {
                x -= 4;
                if (getX() <= 0) {
                    direcao = 4;
                }
            } else {
                x += 4;
                if (getX() >= 1000) {
                    direcao = 1;
                }
            }

        }

    }

    public void usaPoder() {
        this.poder.add(new PoderBoss3(x - largura, y + (altura / 2) - 50));
    }

    public void setSegueJogador(int segjog) {
        segueJogador = segjog;
    }

    public int getSegueJogador() {
        return segueJogador;
    }

    void setVisivel(boolean b) {
        isVisivel = b;
        y = 8000;
    }

    public List<PoderBoss3> getPoder() {
        return poder;
    }

}

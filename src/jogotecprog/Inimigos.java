package jogotecprog;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;

public abstract class Inimigos extends Personagem {

    protected boolean isVisivel;
    private int segueJogador;

    public boolean getVisivel() {
        return isVisivel;
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x, y + 50, largura - 100, altura - 100);
        //return new Rectangle(x, y, largura, altura);
    }

    public void mover(Jogador jogador1, Jogador2 jogador2) {
        /*variaveis para guardar a distancia entre o inimigo e o jogador 1 e 2*/
        int distanciaJog1;
        distanciaJog1 = (int) Math.hypot(Math.abs(this.x - jogador1.getX()), Math.abs(this.y - jogador1.getY()));

        int distanciaJog2;
        distanciaJog2 = (int) Math.hypot(Math.abs(this.x - jogador2.getX()), Math.abs(this.y - jogador2.getY()));

        if (distanciaJog1 < distanciaJog2) {
            if (this.getX() > jogador1.getX() + 60) {
                x -= dx;
            } else if (this.getX() < jogador1.getX() + 60) {
                x += dx;
            }
            if (this.getY() > jogador1.getY() + 60) {
                y -= dy;
            } else if (this.getY() < jogador1.getY() - 60) {
                y += dy;
            }
            segueJogador = 2;
            
        } else {
            if (this.getX() > jogador2.getX()) {
                x -= dx;
            } else if (this.getX() < jogador2.getX()) {
                x += dx;
            }
            if (this.getY() > jogador2.getY() - 60) {
                y -= dy;
            } else if (this.getY() < jogador2.getY() - 60) {
                y += dy;
            }
            segueJogador = 1;
        }

    }

    public void setSegueJogador(int segjog) {
        segueJogador = segjog;
    }

    public int getSegueJogador() {
        return segueJogador;
    }

    @Override
    void setVisivel(boolean b) {
        isVisivel = b;
    }
}

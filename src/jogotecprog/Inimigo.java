package jogotecprog;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;

public class Inimigo extends Inimigos {

    public Inimigo(int x, int y) {
        vida = 25;
        dano = 10;
        this.x = x;
        this.y = y;
        altura = 200;
        largura = 200;
        isVisivel = true;
        dx = 1;
        dy = 1;
        this.load();
    }

    @Override
    public void load() {
        ImageIcon referencia = new ImageIcon("imagens\\Troll_01_1_RUN_000.png");
        referencia.setImage(referencia.getImage().getScaledInstance(largura, altura, 100));
        imagem = referencia.getImage();

        this.largura = imagem.getWidth(null);
        this.altura = imagem.getHeight(null);

    }

}

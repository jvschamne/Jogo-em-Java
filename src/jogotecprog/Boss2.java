package jogotecprog;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;

public class Boss2 extends Inimigos {

    public Boss2() {
        vida = 400;
        dano = 3;
        this.x = 1000;
        this.y = 300;
        altura = 50;
        largura = 50;
        isVisivel = true;
        dx = 1;
        dy = 1;
        this.load();
    }

    public void load() {

        ImageIcon referencia = new ImageIcon("imagens\\chefe2.png");
        referencia.setImage(referencia.getImage().getScaledInstance(300, 300, 100));
        imagem = referencia.getImage();

        this.largura = imagem.getWidth(null);
        this.altura = imagem.getHeight(null);

    }

    @Override
    void setVisivel(boolean b) {
        isVisivel = b;
        y = 8000;
    }

}

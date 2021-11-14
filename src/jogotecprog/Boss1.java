package jogotecprog;

import javax.swing.ImageIcon;

public class Boss1 extends Inimigos {

    public Boss1() {
        vida = 250;
        dano = 5;
        this.x = 1000;
        this.y = 300;
        altura = 50;
        largura = 50;
        isVisivel = true;
        dx = 1;
        dy = 1;
        this.load();
    }

    @Override
    public void load() {

        ImageIcon referencia = new ImageIcon("imagens\\chefe1.png");
        referencia.setImage(referencia.getImage().getScaledInstance(200, 200, 100));
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

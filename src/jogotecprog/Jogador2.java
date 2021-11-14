package jogotecprog;

import java.awt.Image;
import java.util.List;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.ImageIcon;

public class Jogador2 extends Personagem {

    private List<Tiros> tiros;
    private Tiros tiro;
    private int vidaFase;
    private String nomeJ2;

    public Jogador2() {
        x = 200;
        y = 300;
        altura = 100;
        largura = 100;
        dano = 0;
        this.direcao = 1;//direita
        this.direcaoAnterior = 1;//direita
        this.movendo = false;
        this.faseMovimento = 0;
        tiros = new ArrayList<Tiros>();
        tiro = new Tiros(x, y);
        dano = tiro.getDano();

        isVisivel = true;
        contaAtaque = 0;
        atacando = false;
    }

    public void load() {
        ImageIcon referencia = null;
        Image img = null;
        
        /*codigo para a movimentacao de sprites*/
        //movendo para direita
        if (this.direcao == 1 || this.direcaoAnterior == 1) {
            if (atacando) {
                this.movendo = false;

                if (contaAtaque <= 80) {
                    referencia = new ImageIcon("imagens\\tirodireita.png");
                    img = referencia.getImage();
                    contaAtaque++;
                } else {
                    atacando = false;
                    contaAtaque = 0;
                }

            } else if (this.movendo == true) {
                if (faseMovimento <= 20) {
                    referencia = new ImageIcon("imagens\\2direita0.png");
                    img = referencia.getImage();
                    faseMovimento++;
                } else if (faseMovimento <= 40) {
                    referencia = new ImageIcon("imagens\\2direita1.png");
                    img = referencia.getImage();
                    faseMovimento++;
                } else if (faseMovimento <= 60) {

                    referencia = new ImageIcon("imagens\\2direita2.png");
                    img = referencia.getImage();
                    faseMovimento++;
                } else if (faseMovimento <= 80) {

                    referencia = new ImageIcon("imagens\\2direita3.png");
                    img = referencia.getImage();
                    faseMovimento++;
                } else if (faseMovimento <= 100) {

                    referencia = new ImageIcon("imagens\\2direita4.png");
                    img = referencia.getImage();
                    faseMovimento++;
                } else {
                    referencia = new ImageIcon("imagens\\2direita0.png");
                    img = referencia.getImage();
                    faseMovimento = 0;
                }
            } else {//se estiver parado
                referencia = new ImageIcon("imagens\\2direita0.png");
                img = referencia.getImage();
                faseMovimento = 0;
            }
        } //movendo para a esquerda
        else if (this.direcao == 2 || this.direcaoAnterior == 2) {
            if (atacando) {
                this.movendo = false;

                if (contaAtaque <= 80) {
                    referencia = new ImageIcon("imagens\\tiroesquerda.png");
                    img = referencia.getImage();
                    contaAtaque++;
                } else {
                    atacando = false;
                    contaAtaque = 0;
                }

            } else if (this.movendo == true) {
                if (faseMovimento <= 20) {
                    referencia = new ImageIcon("imagens\\2esquerda0.png");
                    img = referencia.getImage();
                    faseMovimento++;
                } else if (faseMovimento <= 40) {
                    referencia = new ImageIcon("imagens\\2esquerda1.png");
                    img = referencia.getImage();
                    faseMovimento++;
                } else if (faseMovimento <= 60) {

                    referencia = new ImageIcon("imagens\\2esquerda2.png");
                    img = referencia.getImage();
                    faseMovimento++;
                } else if (faseMovimento <= 80) {

                    referencia = new ImageIcon("imagens\\2esquerda3.png");
                    img = referencia.getImage();
                    faseMovimento++;
                } else if (faseMovimento <= 100) {

                    referencia = new ImageIcon("imagens\\2esquerda4.png");
                    img = referencia.getImage();
                    faseMovimento++;
                } else {
                    referencia = new ImageIcon("imagens\\2esquerda0.png");
                    img = referencia.getImage();
                    faseMovimento = 0;
                }
            } else {//se estiver parado
                referencia = new ImageIcon("imagens\\2esquerda0.png");
                img = referencia.getImage();
                faseMovimento = 0;
            }
        }
        
        /*a direcao pode ser 1(dir),2(esq),3(baix) ou 4(cima)
        caso o jogador va para cima ou para baixo, o sprite mostrado sera da
        direcao anterior que ele estava indo (direita ou esquerda), pois nao
        tem sprites de cima ou baixo*/
        if (this.direcao == 1 || this.direcao == 2) {
            this.direcaoAnterior = this.direcao;
        }

        //ImageIcon img = new ImageIcon("imagens\\cenario1.png");
        if (atacando) {
            referencia.setImage(referencia.getImage().getScaledInstance(130, 130, 100));
        } else {
            referencia.setImage(referencia.getImage().getScaledInstance(100, 100, 100));
        }

        imagem = referencia.getImage();

        this.largura = imagem.getWidth(null);
        this.altura = imagem.getHeight(null);

    }

    public void mover() {//faz com que o player se mova
        /*o codigo inclui limitacoes de acordo com o cenario
        por isso ele verifica se y<=520, etc*/
        if (y <= 520 && direcao == 3) {
            y += dy;
        }
        if (y >= 280 && direcao == 4) {
            y -= dy;
        }
        if (x <= 1300 && direcao == 1) {
            x += dx;
        }
        if (x >= 0 && direcao == 2) {
            x -= dx;
        }

    }

    public void tiroSimples() {
        this.tiros.add(new Tiros(x + largura, y + (altura / 2) - 50));
        //adicionando um novo tiro à lista de tiros
    }

    public void apertaTecla(KeyEvent tecla) {
        int codigo = tecla.getKeyCode();

        if (codigo == KeyEvent.VK_X) {
            /*codigo para nao permitir que o jogador atire
            infinitos tiros enquanto aperta a tecla
            nesse caso, ele só podera atirar novamente ao soltar
            a tecla*/
            if (atacando == true) {
                tiroSimples();
                atacando = false;
            }
        }

        if (codigo == KeyEvent.VK_W) {
            dy = 4;
            direcao = 4;
        }
        if (codigo == KeyEvent.VK_S) {
            dy = 4;
            direcao = 3;
        }
        if (codigo == KeyEvent.VK_A) {
            dx = 4;
            direcao = 2;
        }
        if (codigo == KeyEvent.VK_D) {
            dx = 4;
            direcao = 1;
        }
        this.movendo = true;
    }

    public void soltaTecla(KeyEvent tecla) {
        int codigo = tecla.getKeyCode();

        if (codigo == KeyEvent.VK_X) {
            atacando = true;
            /*quando a tecla é solta, o jogador pode atirar novamente*/
        }
        if (codigo == KeyEvent.VK_W) {
            dy = 0;
        }
        if (codigo == KeyEvent.VK_S) {
            dy = 0;
        }
        if (codigo == KeyEvent.VK_A) {
            dx = 0;
        }
        if (codigo == KeyEvent.VK_D) {
            dx = 0;
        }
        this.movendo = false;

    }

    public List<Tiros> getTiros() {
        return tiros;
    }

    public int getLargura() {
        return largura;
    }

    public int getAltura() {
        return altura;
    }

    @Override
    void setVisivel(boolean b) {
        isVisivel = b;
        y = 8000;
    }

    void setY(int novoY) {
        y = novoY;
    }

    public void setX(int novoX) {
        x = novoX;
    }

    void setNome(String nomeJ2) {
        this.nomeJ2 = nomeJ2;
    }

    String getNome() {
        return nomeJ2;
    }

    int getVidaFase() {
        return vidaFase;
    }

    void setVidaFase(int vida) {
        vidaFase = vida;
    }

    public boolean getVisivel() {
        return isVisivel;
    }
}

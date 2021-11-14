package jogotecprog;

import java.awt.Image;
import java.util.List;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.ImageIcon;

public class Jogador extends Personagem {

    private List<Soco> soco;
    private Soco socos;
    private boolean socando = false;
    private int contaSoco = 0;
    private String nomeJ2;
    private int vidaFase;

    public Jogador() {
        x = 100;
        y = 300;
        altura = 100;
        largura = 100;
        this.direcao = 1;//direita
        this.direcaoAnterior = 1;//direita
        this.movendo = false;
        this.faseMovimento = 0;
        isVisivel = true;
        socos = new Soco(x, y);
        dano = socos.getDano();
        soco = new ArrayList<Soco>();
    }

    @Override
    public void load() {
        ImageIcon referencia = null;
        Image img = null;

        /*codigo para a movimentacao de sprites*/
        
        //movendo para direita
        if (this.direcao == 1 || this.direcaoAnterior == 1) {
            if (socando) {
                this.movendo = false;

                if (contaSoco <= 20) {
                    referencia = new ImageIcon("imagens\\socodireita1.png");
                    img = referencia.getImage();
                    contaSoco++;
                } else if (contaSoco <= 40) {
                    referencia = new ImageIcon("imagens\\socodireita2.png");
                    img = referencia.getImage();
                    contaSoco++;
                } else {
                    socando = false;
                    contaSoco = 0;
                }

            } else if (this.movendo == true) {
                if (faseMovimento <= 20) {
                    referencia = new ImageIcon("imagens\\direita0.png");
                    img = referencia.getImage();
                    faseMovimento++;
                } else if (faseMovimento <= 40) {
                    referencia = new ImageIcon("imagens\\direita1.png");
                    img = referencia.getImage();
                    faseMovimento++;
                } else if (faseMovimento <= 60) {

                    referencia = new ImageIcon("imagens\\direita2.png");
                    img = referencia.getImage();
                    faseMovimento++;
                } else if (faseMovimento <= 80) {

                    referencia = new ImageIcon("imagens\\direita3.png");
                    img = referencia.getImage();
                    faseMovimento++;
                } else if (faseMovimento <= 100) {

                    referencia = new ImageIcon("imagens\\direita4.png");
                    img = referencia.getImage();
                    faseMovimento++;
                } else {
                    referencia = new ImageIcon("imagens\\direita0.png");
                    img = referencia.getImage();
                    faseMovimento = 0;
                }
            } else {//se estiver parado
                referencia = new ImageIcon("imagens\\direita0.png");
                img = referencia.getImage();
                faseMovimento = 0;
            }
        } //movendo para a esquerda
        else if (this.direcao == 2 || this.direcaoAnterior == 2) {
            if (socando) {
                this.movendo = false;

                if (contaSoco <= 20) {
                    referencia = new ImageIcon("imagens\\socoesquerda1.png");
                    img = referencia.getImage();
                    contaSoco++;
                } else if (contaSoco <= 40) {
                    referencia = new ImageIcon("imagens\\socoesquerda2.png");
                    img = referencia.getImage();
                    contaSoco++;
                } else {
                    socando = false;
                    contaSoco = 0;
                }

            } else if (this.movendo == true) {
                if (faseMovimento <= 20) {
                    referencia = new ImageIcon("imagens\\esquerda0.png");
                    img = referencia.getImage();
                    faseMovimento++;
                } else if (faseMovimento <= 40) {
                    referencia = new ImageIcon("imagens\\esquerda1.png");
                    img = referencia.getImage();
                    faseMovimento++;
                } else if (faseMovimento <= 60) {

                    referencia = new ImageIcon("imagens\\esquerda2.png");
                    img = referencia.getImage();
                    faseMovimento++;
                } else if (faseMovimento <= 80) {

                    referencia = new ImageIcon("imagens\\esquerda3.png");
                    img = referencia.getImage();
                    faseMovimento++;
                } else if (faseMovimento <= 100) {

                    referencia = new ImageIcon("imagens\\esquerda4.png");
                    img = referencia.getImage();
                    faseMovimento++;
                } else {
                    referencia = new ImageIcon("imagens\\esquerda0.png");
                    img = referencia.getImage();
                    faseMovimento = 0;
                }
            } else {//se estiver parado
                referencia = new ImageIcon("imagens\\esquerda0.png");
                img = referencia.getImage();
                faseMovimento = 0;
            }
        }
        
        /*a direcao pode ser 1(dir),2(esq),3(baix) ou 4(cima)
        caso o jogador va para cima ou para baixo, o sprite mostrado sera da
        direcao anterior que ele estava indo (direita ou esquerda), pois nao
        tem sprites de cima ou baixo*/
        if (direcao == 1 || direcao == 2) {
            direcaoAnterior = direcao;
        }

        referencia.setImage(referencia.getImage().getScaledInstance(largura, altura, 100));
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

    public void socar() {
        this.soco.add(new Soco(x + largura, y + (altura / 2) - 50));
        //adicionando um novo soco à lista de socos
    }
    
    public void apertaTecla(KeyEvent tecla) {
        int codigo = tecla.getKeyCode();

        if (codigo == KeyEvent.VK_L) {
            /*codigo para nao permitir que o jogador atire
            infinitos tiros enquanto aperta a tecla
            nesse caso, ele só podera atirar novamente ao soltar
            a tecla*/
            if (socando == true) {
                socar();
                socando = false;
            }
        }

        if (codigo == KeyEvent.VK_UP) {
            dy = 8;
            direcao = 4;
        }
        if (codigo == KeyEvent.VK_DOWN) {
            dy = 8;
            direcao = 3;
        }
        if (codigo == KeyEvent.VK_LEFT) {
            dx = 8;
            direcao = 2;
        }
        if (codigo == KeyEvent.VK_RIGHT) {
            dx = 8;
            direcao = 1;
        }
        this.movendo = true;
    }

    public void soltaTecla(KeyEvent tecla) {
        int codigo = tecla.getKeyCode();

        if (codigo == KeyEvent.VK_L) {
            socando = true;
            /*quando a tecla é solta, o jogador pode dar soco novamente*/
        }

        if (codigo == KeyEvent.VK_UP) {
            dy = 0;
        }
        if (codigo == KeyEvent.VK_DOWN) {
            dy = 0;
        }
        if (codigo == KeyEvent.VK_LEFT) {
            dx = 0;
        }
        if (codigo == KeyEvent.VK_RIGHT) {
            dx = 0;
        }
        this.movendo = false;

    }

    public void setX(int novoX) {
        x = novoX;
    }

    public int getLargura() {
        return largura;
    }

    public int getAltura() {
        return altura;
    }

    List<Soco> getSoco() {
        return soco;
    }

    @Override
    void setVisivel(boolean b) {
        isVisivel = b;
        y = 8000;
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

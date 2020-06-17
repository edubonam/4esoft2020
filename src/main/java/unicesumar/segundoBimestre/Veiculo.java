package unicesumar.segundoBimestre;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Veiculo {
    @Id
    private String id;

    private String modelo;

    private int qtdeDeRodas;

    private double potenciaVeiculo;

    public Veiculo(String id, String modelo, int qtdeDeRodas, double potenciaVeiculo){
       this.id = id;
       this.modelo = modelo;
       this.qtdeDeRodas = qtdeDeRodas;
       this.potenciaVeiculo = potenciaVeiculo;
    }

    public String getId(){
        return id;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public int getQtdeDeRodas() {
        return qtdeDeRodas;
    }

    public void setQtdeDeRodas(int qtdeDeRodas) {
        this.qtdeDeRodas = qtdeDeRodas;
    }

    public double getPotenciaVeiculo() {
        return potenciaVeiculo;
    }

    public void setPotenciaVeiculo(double potenciaVeiculo) {
        this.potenciaVeiculo = potenciaVeiculo;
    }
}

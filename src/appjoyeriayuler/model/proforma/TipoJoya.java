package appjoyeriayuler.model.proforma;

public class TipoJoya {
    private int idTipoJoya;
    private String joya;

    public TipoJoya() {}

    public TipoJoya(int idTipoJoya, String joya) {
        this.idTipoJoya = idTipoJoya;
        this.joya = joya;
    }

    public int getIdTipoJoya() {
        return idTipoJoya;
    }

    public void setIdTipoJoya(int idTipoJoya) {
        this.idTipoJoya = idTipoJoya;
    }

    public String getJoya() {
        return joya;
    }

    public void setJoya(String joya) {
        this.joya = joya;
    }

    @Override
    public String toString() {
        return joya;
    }
}

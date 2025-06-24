package appjoyeriayuler.model.proforma;

public class TipoMaterial {
    private int idMaterial;
    private String material;

    public TipoMaterial() {}

    public TipoMaterial(int idMaterial, String material) {
        this.idMaterial = idMaterial;
        this.material = material;
    }

    public int getIdMaterial() {
        return idMaterial;
    }

    public void setIdMaterial(int idMaterial) {
        this.idMaterial = idMaterial;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    @Override
    public String toString() {
        return material;
    }
}

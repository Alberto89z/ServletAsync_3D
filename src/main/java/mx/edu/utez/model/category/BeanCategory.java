package mx.edu.utez.model.category;

public class BeanCategory {
    private int idCategory;
    private String nameCategory;
    private int status;

    public BeanCategory() {
    }

    public BeanCategory(int idCategory, String nameCategory, int status) {
        this.idCategory = idCategory;
        this.nameCategory = nameCategory;
        this.status = status;
    }

    public int getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(int idCategory) {
        this.idCategory = idCategory;
    }

    public String getNameCategory() {
        return nameCategory;
    }

    public void setNameCategory(String nameCategory) {
        this.nameCategory = nameCategory;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}

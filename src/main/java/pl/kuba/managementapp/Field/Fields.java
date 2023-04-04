package pl.kuba.managementapp.Field;

public enum Fields {

    STRAWBERRY_FIELD("Strawberries");
    private final String fieldname;

    private Fields(String fieldname) {
        this.fieldname = fieldname;
    }

    public String getFieldname() {
        return fieldname;
    }
}

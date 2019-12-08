import com.sun.istack.internal.Nullable;

public enum AvailableItems {

    Teste123(1),
    Teste2(2);
    private Integer id;

    AvailableItems(Integer value) {
        this.id = value;
    }

    public Integer getId() {
        return id;
    }

    @Nullable
    public static AvailableItems fromId(Integer id) {
        for (AvailableItems at : AvailableItems.values()) {
            if (at.getId().equals(id)) {
                return at;
            }
        }
        return null;
    }
}

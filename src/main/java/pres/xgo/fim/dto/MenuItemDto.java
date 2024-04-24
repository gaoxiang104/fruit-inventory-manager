package pres.xgo.fim.dto;

import lombok.Data;

import java.util.Objects;

/**
 * 菜单按钮 dto
 */
@Data
public class MenuItemDto {
    private String name;

    private String fxmlUrlStr; // fxmlUrl

    public MenuItemDto() {
    }

    public MenuItemDto(String name, String fxmlUrlStr) {
        this.name = name;
        this.fxmlUrlStr = fxmlUrlStr;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MenuItemDto that = (MenuItemDto) o;
        return Objects.equals(name, that.name) && Objects.equals(fxmlUrlStr, that.fxmlUrlStr);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, fxmlUrlStr);
    }

    @Override
    public String toString() {
        return name;
    }
}

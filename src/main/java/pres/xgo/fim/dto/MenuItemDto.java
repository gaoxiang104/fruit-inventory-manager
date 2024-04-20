package pres.xgo.fim.dto;

import lombok.Data;

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
    public String toString() {
        return name;
    }
}

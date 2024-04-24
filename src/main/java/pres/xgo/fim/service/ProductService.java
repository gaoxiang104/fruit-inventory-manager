package pres.xgo.fim.service;

public interface ProductService {

    /**
     * 导入 果品
     * @param filePath
     * @return
     */
    Integer importProduct(String filePath);

    void allList();
}

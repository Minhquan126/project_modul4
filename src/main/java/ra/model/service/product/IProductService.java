package ra.model.service.product;

import ra.model.entity.Product;
import ra.model.service.IGeneric;

import java.util.List;

public interface IProductService extends IGeneric<Product> {
    List<Product> searchByCatalogId(int id);
    boolean setHeart(int id);
    List<Product> showList();
}

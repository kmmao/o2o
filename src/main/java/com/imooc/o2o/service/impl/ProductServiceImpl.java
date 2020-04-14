package com.imooc.o2o.service.impl;

import com.imooc.o2o.dao.ProductDao;
import com.imooc.o2o.dao.ProductImgDao;
import com.imooc.o2o.dto.ImageHolder;
import com.imooc.o2o.dto.ProductExecution;
import com.imooc.o2o.entity.Product;
import com.imooc.o2o.entity.ProductImg;
import com.imooc.o2o.enums.ProductStateEnum;
import com.imooc.o2o.exceptions.ProductOperationException;
import com.imooc.o2o.service.ProductService;
import com.imooc.o2o.util.ImageUtil;
import com.imooc.o2o.util.PageCalculator;
import com.imooc.o2o.util.PathUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.InputStream;
import java.security.spec.ECField;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {


    @Autowired
    private ProductDao productDao;
    @Autowired
    private ProductImgDao productImgDao;

    @Override
    @Transactional
    /**
     * 1、处理缩略图，获取缩略图相对路径并赋值product
     * 2、往tb_product写入商品信息，获取product
     * 3、结合productId批量处理商品详情图
     * 4、将商品详情图列表批量写入tb_product_img中
     */
    public ProductExecution addProduct(Product product, ImageHolder thumbnail, List<ImageHolder> productImgList)
            throws ProductOperationException {
//        控制判读
        if (product !=null && product.getShop()!=null && product.getShop().getShopId()!=null) {
            //写入事件属性
            product.setCreateTime(new Date());
            product.setLastEditTime(new Date());
            //默认的上架状态
            product.setEnableStatus(1);

            //处理缩略图
            if (thumbnail != null) {
                addThumbnail(product,thumbnail);
            }
//                添加商品
            try {
                int effectedNum = productDao.insertProduct(product);
                if (effectedNum <= 0) {
                    throw new ProductOperationException("商品添加失败！");
                }
            } catch (Exception e) {
                throw new ProductOperationException("商品添加失败！"+e.toString());
            }
            //处理详情图
            if (productImgList != null && productImgList.size() > 0) {
                addProductImgList(product, productImgList);
            }
            return new ProductExecution(ProductStateEnum.SUCCESS);
        } else {
            return new ProductExecution(ProductStateEnum.EMPTY);
        }

    }

    // 通过商品id查询唯一的商品信息
    @Override
    public Product getProductById(Long productId) {
        return productDao.queryProductById(productId);
    }

    //修改商品信息以及图片处理
    @Override
    @Transactional
    //1、若缩略图参数有值，则处理缩略图，若原先存在缩略图则先删除在添加新图，
    //之后获取缩略图相对路径并赋值给product
    //2、若商品详情图列表参数有值，对商品详情图列表进行同样的操作
    //3、将tb_product_img 下面的该商品原有的商品详情图记录全部删除
    //4、更新tb_product_img 以及tb_product的信息
    public ProductExecution modifyProduct(Product product, ImageHolder thumbnail, List<ImageHolder> productImgList) throws ProductOperationException {

        //空值判断
        if (product != null && product.getShop() != null && product.getShop().getShopId() != null) {

            //设置更新时间
            product.setLastEditTime(new Date());

            //若商品缩略图不为空，且原有缩略图也不为空则删除原有的缩略图
            if (thumbnail != null) {
                Product tempProduct = productDao.queryProductById(product.getProductId());
                if (tempProduct.getImgAddr() != null) {
                    ImageUtil.deleteOrFilePath(tempProduct.getImgAddr());
                }
                addThumbnail(product, thumbnail);
            }
            //如果新传入的详情图不为空则删除，之后添加新的详情图
            if (productImgList != null && productImgList.size() > 0) {
                deleteProductImgList(product.getProductId());
                addProductImgList(product, productImgList);
            }

            int i = productDao.updateProduct(product);
            try {
                if (i <= 0) {
                    throw new ProductOperationException("商品更新失败");
                }
                return new ProductExecution(ProductStateEnum.SUCCESS, product);
            } catch (Exception e) {
                throw new ProductOperationException("商品更新失败" + e.toString());
            }
        } else {
            return new ProductExecution(ProductStateEnum.EMPTY);
        }

    }

    @Override
    public ProductExecution getProductList(Product productCondition, int pageIndex, int pageSize) {
        int rowIndex = PageCalculator.calculateRowIndex(pageIndex, pageSize);
        List<Product> productList = productDao.queryProductList(productCondition, rowIndex, pageSize);
        int count = productDao.queryProductCount(productCondition);
        ProductExecution pe = new ProductExecution();
        if (productList != null) {
            pe.setProductList(productList);
            pe.setCount(count);
        } else {
            pe.setState(ProductStateEnum.INNER_ERROR.getState());
        }
        return pe;
    }

    //删除详情图
    private void deleteProductImgList(Long productId) {
        //先获取数据库的详情图信息
        List<ProductImg> productImgList = productImgDao.queryProductImgList(productId);
        //删除原先的图片
        for (ProductImg productImg : productImgList) {
            ImageUtil.deleteOrFilePath(productImg.getImgAddr());
        }
        //删除数据库的图片
        productImgDao.deleteProductImgByProductId(productId);
    }

    //详情图
    private void addProductImgList(Product product, List<ImageHolder> productImgList) {
        //获取图片存储路径，直接存放到相应的店铺文件下；
        String shopImagePath = PathUtil.getShopImagePath(product.getShop().getShopId());
        ArrayList<ProductImg> productImgsList = new ArrayList<>();
        //遍历图片一次去处理，并添加进productImg实体类这里
        for (ImageHolder productImgHolder : productImgList) {
            String s = ImageUtil.generateNormalImg(productImgHolder, shopImagePath);
            ProductImg productImg = new ProductImg();
            productImg.setImgAddr(s);
            productImg.setProductId(product.getProductId());
            productImg.setCreateTime(new Date());
            productImgsList.add(productImg);
        }
        //如果确实有图片需要添加大的，就执行批量添加操作
        if (productImgsList.size() > 0) {
            try {
                int effectedNum = productImgDao.batchInsertProductImg(productImgsList);
                if (effectedNum <= 0) {
                    throw new ProductOperationException("创建详情图片失败！");
                }
            } catch (Exception e) {
                throw new ProductOperationException("创建详情图片失败！" + e.toString());

            }
        }
    }

    //缩略图
    private void addThumbnail(Product product, ImageHolder thumbnail) {
        String shopImagePath = PathUtil.getShopImagePath(product.getShop().getShopId());
        String s = ImageUtil.generateThumbnail(thumbnail, shopImagePath);
        product.setImgAddr(s);
    }
}

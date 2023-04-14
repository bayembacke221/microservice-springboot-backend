package com.bayembacke.product.resource;

import com.bayembacke.product.entity.Products;
import com.bayembacke.product.exception.ProductException;
import com.bayembacke.product.repository.ProductRepository;
import com.bayembacke.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

@RestController
@RequestMapping("produit")
@CrossOrigin("*")
public class ProductController {
    @Autowired
    ProductService productService;
    @Autowired
    ProductRepository productRepository;
    public static String UPLOAD_DIRECTORY = System.getProperty("user.dir") + "/PictureProduct/";
    @GetMapping("/all")
    public ResponseEntity<List<Products>> getAllProducts()
    {
        return new ResponseEntity<>(productService.getAllProducts(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Products> getProductById(@PathVariable("id") Long id){
        return new ResponseEntity<>(productService.getProductById(id),HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity addProduct(@RequestBody Products product) throws ProductException
    {
        productService.addProduct(product);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PutMapping
    public ResponseEntity updateProduct(@RequestBody Products product) throws ProductException
    {
        productService.updateProduct(product);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity deleteProduct( @PathVariable("id") Long id) throws ProductException
    {
        productService.deleteProduct(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(path = "/photoProd/{id}",produces = MediaType.IMAGE_JPEG_VALUE)
    public byte[] getPhoto(@PathVariable("id") Long id) throws Exception{
        Products product=productRepository.findByIdProduct(id);
        return   Files.readAllBytes(Paths.get(UPLOAD_DIRECTORY+product.getPhoto()));

    }
    @PostMapping(path = "/upload/{productID}")
    public void uploadPhoto(MultipartFile file, @PathVariable("productID") Long productID) throws Exception{
        Products product=productRepository.findByIdProduct(productID);
        product.setPhoto(productID+".jpg");
        Files.write(Paths.get(UPLOAD_DIRECTORY+product.getPhoto()),file.getBytes());

        productRepository.save(product);
    }
}

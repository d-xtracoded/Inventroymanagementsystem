package com.prime.InventroryMgtSystem.services.impl;

import com.prime.InventroryMgtSystem.dtos.ProductDTO;
import com.prime.InventroryMgtSystem.dtos.Response;
import com.prime.InventroryMgtSystem.exceptions.NotFoundExecption;
import com.prime.InventroryMgtSystem.models.Category;
import com.prime.InventroryMgtSystem.models.Product;
import com.prime.InventroryMgtSystem.reposit.CategoryRepository;
import com.prime.InventroryMgtSystem.reposit.ProductRepository;
import com.prime.InventroryMgtSystem.services.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductServiceImpl implements ProductService {

    private  final ProductRepository productRepository;
    private final ModelMapper modelMapper;
    private final CategoryRepository categoryRepository;



    private static final String IMAGE_DIRECTORY = System.getProperty("user.dir") + "/product-images/";

    //AFTER YOUR FRONTEND IS SETUP CHANGE THE IMAGE DIRECTORY TO YHE FRONTEND YOU ARE USING
   private static final String IMAGE_DIRECTORY_2 = "/Users/dennismac/phegonDev/ims-react/public/products/";

    @Override
    public Response addProduct(ProductDTO productDTO, MultipartFile imageFile) {
        Category category = categoryRepository.findById(productDTO.getCategoryid())
                .orElseThrow(() -> new NotFoundExecption("Category Not Found"));

        //map our dto to product entity
        Product productToSave = Product.builder()
                .name(productDTO.getName())
                .sku(productDTO.getSku())
                .price(productDTO.getPrice())
                .stockQuantity(productDTO.getStockQuantity())
                .description(productDTO.getDescription())
                .category(category)
                .build();

        if (imageFile != null && !imageFile.isEmpty()) {
            log.info("Image file exist");
//            String imagePath = saveImage(imageFile); //use this when you haven't setup your frontend
            String imagePath = saveImage2(imageFile); //use this when you ave set up your frontend locally but haven't deployed to produiction

            System.out.println("IMAGE URL IS: " + imagePath);
            productToSave.setImageurl(imagePath);
        }

        //save the product entity
        productRepository.save(productToSave);

        return Response.builder()
                .status(200)
                .message("Product successfully saved")
                .build();
    }

    @Override
    public Response updateProduct(ProductDTO productDTO,MultipartFile imageFile) {
        return null;
    }

    @Override
    public Response getProduct() {
        return null;
    }

    @Override
    public Response getProductbyid(Long id) {
        return null;
    }

    @Override
    public Response deleteProduct(Long id) {
        return null;
    }

    @Override
    public Response searchProduct(String Input) {
        return null;
    }


    //this save to the root of your project
    private String saveImage(MultipartFile imageFile) {
        //validate image and check if it is greater than 1GIB
        if (!imageFile.getContentType().startsWith("image/") || imageFile.getSize() > 1024 * 1024 * 1024) {
            throw new IllegalArgumentException("Only image files under 1GIG is allowed");
        }

        //create the directory if it doesn't exist
        File directory = new File(IMAGE_DIRECTORY);

        if (!directory.exists()) {
            directory.mkdir();
            log.info("Directory was created");
        }
        //generate unique file name for the image
        String uniqueFileName = UUID.randomUUID() + "_" + imageFile.getOriginalFilename();

        //Get the absolute path of the image
        String imagePath = IMAGE_DIRECTORY + uniqueFileName;

        try {
            File destinationFile = new File(imagePath);
            imageFile.transferTo(destinationFile); //we are writing the image to this folder
        } catch (Exception e) {
            throw new IllegalArgumentException("Error saving Image: " + e.getMessage());
        }
        return imagePath;

    }

    //This saved image to the public folder in your frontend
    //Use this if your have setup your frontend
    private String saveImage2(MultipartFile imageFile) {
        //validate image and check if it is greater than 1GIB
        if (!imageFile.getContentType().startsWith("image/") || imageFile.getSize() > 1024 * 1024 * 1024) {
            throw new IllegalArgumentException("Only image files under 1GIG is allowed");
        }

        //create the directory if it doesn't exist
        File directory = new File(IMAGE_DIRECTORY_2);

        if (!directory.exists()) {
            directory.mkdir();
            log.info("Directory was created");
        }
        //generate unique file name for the image
        String uniqueFileName = UUID.randomUUID() + "_" + imageFile.getOriginalFilename();

        //Get the absolute path of the image
        String imagePath = IMAGE_DIRECTORY_2 + uniqueFileName;

        try {
            File destinationFile = new File(imagePath);
            imageFile.transferTo(destinationFile); //we are writing the image to this folder
        } catch (Exception e) {
            throw new IllegalArgumentException("Error saving Image: " + e.getMessage());
        }
        return "products/"+uniqueFileName;


    }

}

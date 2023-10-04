package com.ooothla.productservice.services;

import com.ooothla.productservice.exceptions.NotFoundException;
import com.ooothla.productservice.thirdpartyclients.FakeStoreProductDto;
import com.ooothla.productservice.dtos.GenericProductDto;
import com.ooothla.productservice.thirdpartyclients.FakeStoryProductServiceClient;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FakeStoreProductService implements ProductService{

    private final FakeStoryProductServiceClient fakeStoryProductServiceClient;
    private final ModelMapper modelMapper;

    public FakeStoreProductService(FakeStoryProductServiceClient fakeStoryProductServiceClient, ModelMapper modelMapper){
        this.fakeStoryProductServiceClient = fakeStoryProductServiceClient;
        this.modelMapper = modelMapper;
    }

    @Override
    public GenericProductDto getProductById(Long id) throws NotFoundException {

        FakeStoreProductDto response = fakeStoryProductServiceClient.getProductById(id);

        if(response == null){
            throw new NotFoundException("Product not found");
        }

        return modelMapper.map(response, GenericProductDto.class);
    }

    @Override
    public List<GenericProductDto> getAllProducts() {

        List<GenericProductDto> products = new ArrayList<>();

        List<FakeStoreProductDto> response = fakeStoryProductServiceClient.getAllProducts();

        for (FakeStoreProductDto fakeStoreProductDto : response) {
             products.add(modelMapper.map(fakeStoreProductDto, GenericProductDto.class));
        }

        return products;
    }

    @Override
    public GenericProductDto createProduct(GenericProductDto genericProductDto ) {
        FakeStoreProductDto response = fakeStoryProductServiceClient.createProduct(genericProductDto);
        return modelMapper.map(response, GenericProductDto.class);
    }

    @Override
    public GenericProductDto deleteProduct(Long id) {
        FakeStoreProductDto response = fakeStoryProductServiceClient.deleteProduct(id);
        return modelMapper.map(response, GenericProductDto.class);
    }

    @Override
    public GenericProductDto updateProductById(Long id, GenericProductDto genericProductDto) {
        FakeStoreProductDto response = fakeStoryProductServiceClient.updateProductById(id, genericProductDto);
        return modelMapper.map(response, GenericProductDto.class);
    }


}

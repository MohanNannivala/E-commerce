package com.ooothla.productservice.services;

import com.ooothla.productservice.dtos.FakeStoreProductDto;
import com.ooothla.productservice.dtos.GenericProductDto;
import org.modelmapper.ModelMapper;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpMessageConverterExtractor;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class FakeStoreProductService implements ProductService{

    private final RestTemplateBuilder restTemplateBuilder;
    private final ModelMapper modelMapper;

    public FakeStoreProductService(RestTemplateBuilder restTemplateBuilder, ModelMapper modelMapper){
        this.restTemplateBuilder = restTemplateBuilder;
        this.modelMapper = modelMapper;
    }

    @Override
    public GenericProductDto getProductById(Long id) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto> response = restTemplate.getForEntity("https://fakestoreapi.com/products/{id}", FakeStoreProductDto.class, id);
        FakeStoreProductDto fakeStoreProductDto = response.getBody();
        return modelMapper.map(fakeStoreProductDto, GenericProductDto.class);
    }

    @Override
    public List<GenericProductDto> getAllProducts() {

        List<GenericProductDto> products = new ArrayList<>();

        RestTemplate restTemplate = restTemplateBuilder.build();
        FakeStoreProductDto[] response = restTemplate.getForEntity("https://fakestoreapi.com/products", FakeStoreProductDto[].class).getBody();

        for (FakeStoreProductDto fakeStoreProductDto : response) {
             products.add(modelMapper.map(fakeStoreProductDto, GenericProductDto.class));
        }

        return products;
    }

    @Override
    public GenericProductDto createProduct(GenericProductDto genericProductDto ) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto> response = restTemplate.postForEntity("https://fakestoreapi.com/products", genericProductDto, FakeStoreProductDto.class);
        FakeStoreProductDto fakeStoreProductDto = response.getBody();
        return modelMapper.map(fakeStoreProductDto, GenericProductDto.class);
    }

    @Override
    public GenericProductDto deleteProduct(Long id) {
        RestTemplate restTemplate = restTemplateBuilder.build();

        RequestCallback requestCallback = restTemplate.acceptHeaderRequestCallback(FakeStoreProductDto.class);
        ResponseExtractor<ResponseEntity<FakeStoreProductDto>> responseExtractor =
                restTemplate.responseEntityExtractor(FakeStoreProductDto.class);
        ResponseEntity<FakeStoreProductDto> response= restTemplate.execute("https://fakestoreapi.com/products/{id}", HttpMethod.DELETE, requestCallback, responseExtractor, id);

        FakeStoreProductDto fakeStoreProductDto = response.getBody();

        return modelMapper.map(fakeStoreProductDto, GenericProductDto.class);
    }

    @Override
    public GenericProductDto updateProductById(Long id, GenericProductDto genericProductDto) {

        RestTemplate restTemplate = restTemplateBuilder.build();
        RequestCallback requestCallback = restTemplate.httpEntityCallback(genericProductDto, FakeStoreProductDto.class);
        ResponseExtractor<ResponseEntity<FakeStoreProductDto>> responseExtractor = restTemplate.responseEntityExtractor(FakeStoreProductDto.class);
        ResponseEntity<FakeStoreProductDto> response = restTemplate.execute("https://fakestoreapi.com/products/{id}", HttpMethod.PUT, requestCallback, responseExtractor, id);

        FakeStoreProductDto fakeStoreProductDto = response.getBody();

        return modelMapper.map(fakeStoreProductDto, GenericProductDto.class);
    }


}

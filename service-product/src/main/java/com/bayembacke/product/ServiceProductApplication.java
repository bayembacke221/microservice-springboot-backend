package com.bayembacke.product;

import com.bayembacke.product.entity.Products;
import com.bayembacke.product.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;

@SpringBootApplication
public class ServiceProductApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(ServiceProductApplication.class, args);
    }
    @Autowired
    ProductRepository productRepository;
    @Override
    public void run(String... args) throws Exception {
        productRepository.save(new Products(
                "cafe",
                "Cafe touba",
                9,
                "https://sedicom.sn/wp-content/uploads/2020/06/cafetouba-bakhdad500.jpg",
                2));
        productRepository.save(new Products(
                "Sucre",
                "Vanille,Cristal",
                9,
                "https://www.science-et-vie.com/wp-content/uploads/scienceetvie/2022/09/shutterstock_1564269901-1-scaled.jpg",
                2));
        productRepository.save(new Products(
                "Blé",
                "Ukrainien",
                9,
                "https://www.markal.fr/application/files/medias_markal/produits/3329482070019-Ble-monde-precuit-500g-AV.png",
                2));
        productRepository.save(new Products(
                "Huile",
                "SOSA,Olive",
                9,
                "https://www.auchan.sn/339-home_default/lesieur-huile-de-soja-3l.jpg",
                2));
        productRepository.save(new Products(
                "Riz",
                "Parfumé",
                9,
                "https://fr.openfoodfacts.org/images/products/303/835/453/4008/front_fr.50.full.jpg",
                2));
        productRepository.save(new Products(
                "Lait",
                "Halib 20g",
                9,
                "https://im.qccdn.fr/node/guide-d-achat-lait-9393/inline-104328.jpg",
                2));
    }

    @Bean
    public CorsFilter corsFilter() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowCredentials(true);
        corsConfiguration.setAllowedOrigins(Arrays.asList("http://localhost:4200"));
        corsConfiguration.setAllowedHeaders(Arrays.asList("Origin", "Access-Control-Allow-Origin", "Content-Type",
                "Accept", "Authorization", "Origin, Accept", "X-Requested-With",
                "Access-Control-Request-Method", "Access-Control-Request-Headers"));
        corsConfiguration.setExposedHeaders(Arrays.asList("Origin", "Content-Type", "Accept", "Authorization",
                "Access-Control-Allow-Origin", "Access-Control-Allow-Origin", "Access-Control-Allow-Credentials"));
        corsConfiguration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
        urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);
        return new CorsFilter(urlBasedCorsConfigurationSource);
    }
}

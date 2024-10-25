package com.dinemap.dinemap.restaurants.services;

import com.dinemap.dinemap.restaurants.entities.RestaurantEntity;
import com.dinemap.dinemap.users.services.IUsersService;
import com.dinemap.dinemap.users.token.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RestaurantsServiceImpl implements IRestaurantsService {
    @Autowired
    private com.dinemap.dinemap.restaurants.repositories.IRestaurantsRepository restaurantsRepository;
    @Autowired
    private IUsersService usersService;

    @Override
    public List<RestaurantEntity> findByDistrict(String district) throws Exception{
        try{
            String province = usersService.getUserProvince(district);
            return restaurantsRepository.findByDistrict(province);
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }
}

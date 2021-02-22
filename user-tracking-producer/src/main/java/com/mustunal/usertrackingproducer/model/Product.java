package com.mustunal.usertrackingproducer.model;

import com.mustunal.usertrackingproducer.enums.Color;
import com.mustunal.usertrackingproducer.enums.DesignType;
import com.mustunal.usertrackingproducer.enums.ProductType;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Product {

    private Color color;

    private ProductType productType;

    private DesignType designType;


}

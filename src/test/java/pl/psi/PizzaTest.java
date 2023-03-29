// ******************************************************************
//
// Copyright 2023 PSI Software AG. All rights reserved.
// PSI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms
//
// ******************************************************************

package pl.psi;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

/**
 * TODO: Describe this class (The first line - until the first dot - will interpret as the brief description).
 */
class PizzaTest {

    @Test
    void shouldReturnPriceEqualTo22(){
        Pizza pizza = new Pizza(15);
        pizza = new HamDecorator(pizza);
        pizza = new MushromDecorator(pizza);

        Pizza pizza2 = new MushromDecorator(new HamDecorator(new Pizza(15)));

        assertThat(pizza.getPrice()).isEqualTo(22);
        assertThat(pizza2.getPrice()).isEqualTo(22);
    }
}